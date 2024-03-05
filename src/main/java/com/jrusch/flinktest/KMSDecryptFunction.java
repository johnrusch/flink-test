package com.jrusch.flinktest;

import com.amazonaws.encryptionsdk.CommitmentPolicy;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoInputStream;
import com.amazonaws.encryptionsdk.jce.JceMasterKey;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class KMSDecryptFunction extends RichMapFunction<String, ActivityRecords> {

    private transient AWSKMS kmsClient;
    private transient ObjectMapper jsonParser;
    private String keyId = "7d8f384b-e9ae-44e9-bf5f-be321db0b7b9";
    private Map<String,String> encryptionContext = new HashMap<String, String>();
    private static final AwsCrypto CRYPTO = AwsCrypto.builder().withCommitmentPolicy(CommitmentPolicy.RequireEncryptAllowDecrypt).build();

    @Override
    public void open(Configuration parameters) {
        // Initialize the KMS client
        kmsClient = AWSKMSClientBuilder.standard().build();
        jsonParser = new ObjectMapper();
        encryptionContext.put("aws:rds:dbc-id", "cluster-46O4DNHZ73PKKX3X6WTVI32A5Q");
    }

    @Override
    public ActivityRecords map(String value) {
//        System.out.println("Decrypting data..." + value);
        // Parse the JSON string
        try {
            JsonNode rootNode = jsonParser.readTree(value);

            // get the databaseActivityEvents node
            JsonNode databaseActivityEventsNode = rootNode.get("databaseActivityEvents");
            if (databaseActivityEventsNode == null || databaseActivityEventsNode.isNull()) {
                // Handle missing or null "databaseActivityEvents"
                System.err.println("Missing 'databaseActivityEvents' in JSON");
                return null;
            }
//            System.out.println("Database activity events node: " + databaseActivityEventsNode.toString());
            String databaseActivityEvents = databaseActivityEventsNode.asText();
            final byte[] decoded = Base64.getDecoder().decode(databaseActivityEvents);

            // get key to text
            JsonNode key = rootNode.get("key");
            String keyString = key.asText();
            final byte[] decodedDataKey = Base64.getDecoder().decode(keyString);

            // Create a decrypt request
            ByteBuffer cipherTextBlobKey = ByteBuffer.wrap(decodedDataKey);

            // Decrypt the key
            final DecryptRequest reqKey = new DecryptRequest().withCiphertextBlob(cipherTextBlobKey).withEncryptionContext(encryptionContext);
            final DecryptResult decryptResult = kmsClient.decrypt(reqKey);
            final byte[] decrypted = decrypt(decoded, getByteArray(decryptResult.getPlaintext()));

            // Decompress the decrypted data
            byte[] decompressedData = decompress(decrypted);
            // json activity records
            final ActivityRecords activityRecords = jsonParser.readValue(new String(decompressedData, StandardCharsets.UTF_8), ActivityRecords.class);

            // iterate through the list of database activity events
//            for (ActivityEvent activityEvent : activityRecords.getDatabaseActivityEventList()) {
//                System.out.println("Activity Event: " + activityEvent);
//            }
//            System.out.println("Decompressed data: " + new String(decompressedData, StandardCharsets.UTF_8));

            return activityRecords;
        } catch (AmazonServiceException e) {
            System.out.println("AWS service error: " + e.getErrorMessage());
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Error decoding Base64: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Error decrypting data: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] decompress(final byte[] src) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(src);
        GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
        return IOUtils.toByteArray(gzipInputStream);
    }

    private static byte[] decrypt(final byte[] decoded, final byte[] decodedDataKey) throws IOException {
        // Create a JCE master key provider using the random key and an AES-GCM encryption algorithm
        final JceMasterKey masterKey = JceMasterKey.getInstance(new SecretKeySpec(decodedDataKey, "AES"),
                "BC", "DataKey", "AES/GCM/NoPadding");
        try (final CryptoInputStream<JceMasterKey> decryptingStream = CRYPTO.createDecryptingStream(masterKey, new ByteArrayInputStream(decoded));
             final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            IOUtils.copy(decryptingStream, out);
            return out.toByteArray();
        }
    }

    private static byte[] getByteArray(final ByteBuffer b) {
        byte[] byteArray = new byte[b.remaining()];
        b.get(byteArray);
        return byteArray;
    }
}
