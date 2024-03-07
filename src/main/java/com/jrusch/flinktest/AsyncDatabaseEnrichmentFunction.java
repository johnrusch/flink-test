package com.jrusch.flinktest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncDatabaseEnrichmentFunction extends RichAsyncFunction<Review, Review> {

    private transient HikariDataSource dataSource;
    private transient Map<Integer, Profile> profileCache;

    @Override
    public void open(Configuration parameters) throws Exception {
        System.out.println("Opening connection to the database");
        super.open(parameters);
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Initialize your data source or connection pool here
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://transparency-dev-for-innovation-cluster.cluster-ro-cnuznriibgfw.us-east-1.rds.amazonaws.com:3306/docscores");
        config.setUsername("docscoresadmin");
        config.setPassword("D!soMUCH#p0wer");
        // Configure other HikariCP settings as needed
        dataSource = new HikariDataSource(config);
        profileCache = new ConcurrentHashMap<>();
    }

    @Override
    public void asyncInvoke(Review review, ResultFuture<Review> resultFuture) {
        // Check if the profile is already in the cache
        Profile cachedProfile = profileCache.get(review.getDocProfileId());
        if (cachedProfile != null) {
            System.out.println("Setting review's profile from the cache");
            review.setProfile(cachedProfile);
            resultFuture.complete(Collections.singleton(review));
        } else {
            // Perform the async database query
            CompletableFuture.supplyAsync(() -> {
                try (Connection connection = dataSource.getConnection()) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM docprofile WHERE docprofileid = ?");
                    System.out.println("Executing query to find docprofile #" + review.getDocProfileId() + " in the database");
                    stmt.setInt(1, review.getDocProfileId());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        System.out.println("Profile found in the database");
                        Profile profile = new Profile();
                        // Populate the profile object from ResultSet
                        System.out.println("Populating profile object from ResultSet");
                        profile.setDocprofileId(rs.getInt("docprofileid"));
                        System.out.println("Profile ID: " + profile.getDocprofileId());
                        profile.setLive(rs.getBoolean("live"));
                        profile.setName(rs.getString("name"));
                        System.out.println("Profile name: " + profile.getName());
                        // Populate the organization object from ResultSet
                        Organization organization = new Organization();
                        organization.setOrganizationId((rs.getInt("organizationid")));
                        System.out.println("Organization ID: " + organization.getOrganizationId());
                        profile.setOrganization(organization);
                        System.out.println("Organization: " + profile.getOrganization());
                        profile.setProfileSlug(rs.getString("docprofileslug"));
                        System.out.println("Profile slug: " + profile.getProfileSlug());
                        profile.setProfileStatus(rs.getString("status"));
                        System.out.println("Profile status: " + profile.getProfileStatus());
                        System.out.println("Profile: " + profile);
                        return profile;
                    }
                } catch (SQLException e) {
                    System.out.println("Unable to either find profile or populate profile object from ResultSet");
                    // Handle exceptions
                }
                return null;
            }).thenAccept(profile -> {
                if (profile != null) {
                    System.out.println("Setting review's profile and caching profile object");
                    review.setProfile(profile);
                    profileCache.put(review.getDocProfileId(), profile);
                } else {
                    System.out.println("No profile found in the database");
                    // Handle the case where no profile is found, if necessary
                }
                resultFuture.complete(Collections.singleton(review));
            });
        }
    }

    @Override
    public void close() throws Exception {
        if (dataSource != null) {
            System.out.println("Closing connection to the database");
            dataSource.close();
        }
        super.close();
    }
}
