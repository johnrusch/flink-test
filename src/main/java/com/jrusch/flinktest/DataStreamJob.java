/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jrusch.flinktest;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.kinesis.FlinkKinesisConsumer;
import org.apache.flink.streaming.connectors.kinesis.config.ConsumerConfigConstants;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Skeleton for a Flink DataStream Job.
 *
 * <p>For a tutorial how to write a Flink application, check the
 * tutorials and examples on the <a href="https://flink.apache.org">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		try {
			// Set up the execution environment
			final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

			// Parse the application parameters
			ParameterTool parameter = ParameterTool.fromArgs(args);

			// Make parameters available in the web interface
			env.getConfig().setGlobalJobParameters(parameter);

			// Create Kinesis source
			DataStream<String> kinesisSource = createKinesisSource(env, parameter);

			// Decrypt the data stream
			DataStream<ActivityRecords> decryptedDataStream = kinesisSource.map(new KMSDecryptFunction());

			// Filter out ActivityEvents in the ActivityRecords where objectType is not "TABLE"
			// and objectName is not "reviews"
			DataStream<ActivityEvent> filteredDataStream = decryptedDataStream.flatMap(new FilterAndFlattenActivityEvents());

			DataStream<Review> reviewDataStream = filteredDataStream.map(new ActivityEventToReview());

			// Enrich the Review data stream with data from the database
			DataStream<Review> enrichedReviewDataStream = AsyncDataStream.unorderedWait(
					reviewDataStream,
					new AsyncDatabaseEnrichmentFunction(),
					1000,
					TimeUnit.MILLISECONDS,
					5
			);

			// Print the enriched review data stream
			enrichedReviewDataStream.print();

			// Execute the job
			env.execute("Flink Kinesis Data Stream Job");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method creates Kinesis source based on Application properties
	 *
	 * @param env
	 * @param parameter
	 * @return
	 */
	private static DataStream<String> createKinesisSource(StreamExecutionEnvironment env, ParameterTool paramTool) {
//		log.info("Creating Kinesis source from Application Properties");
		Properties inputProperties = new Properties();
		inputProperties.setProperty(ConsumerConfigConstants.AWS_REGION, paramTool.get("region"));
		inputProperties.setProperty(ConsumerConfigConstants.STREAM_INITIAL_POSITION,
				paramTool.get("stream_init_position"));
//		if (paramTool.get("stream_init_position").equalsIgnoreCase(StreamPosition.AT_TIMESTAMP.name())) {
//			inputProperties.setProperty(ConsumerConfigConstants.STREAM_INITIAL_TIMESTAMP,
//					paramTool.get("stream_initial_timestamp"));
//		}

		return env.addSource(new FlinkKinesisConsumer<>(paramTool.get("input_stream_name"), new SimpleStringSchema(),
				inputProperties));
	};
}
