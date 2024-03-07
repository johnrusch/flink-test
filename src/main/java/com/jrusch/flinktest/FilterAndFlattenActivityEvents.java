package com.jrusch.flinktest;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import java.util.List;

public class FilterAndFlattenActivityEvents implements FlatMapFunction<ActivityRecords, ActivityEvent> {

    @Override
    public void flatMap(ActivityRecords activityRecords, Collector<ActivityEvent> out) {
        if (activityRecords == null) {
            return;
        }

        List<ActivityEvent> databaseActivityEventList = activityRecords.getDatabaseActivityEventList();
        if (databaseActivityEventList == null) {
            return;
        }

        for (ActivityEvent activityEvent : databaseActivityEventList) {
            if (activityEvent == null) {
                continue;
            }

            String objectType = activityEvent.getObjectType();
            String objectName = activityEvent.getObjectName();
            String commandText = activityEvent.getCommandText();
            if (objectType != null && objectName != null && commandText != null
                    && objectType.equals("TABLE") && objectName.equals("review")
                    && (commandText.startsWith("UPDATE") || commandText.startsWith("INSERT") || commandText.startsWith("DELETE"))
                    && !activityEvent.getDbUserName().equals("rdsadmin")
                    && activityEvent.getDatabaseName().equals("docscores")) {

                out.collect(activityEvent); // Emit the ActivityEvent directly
            }
        }
    }
}
