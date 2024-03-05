package com.jrusch.flinktest;

import java.util.List;

public class ActivityRecords {
    String type;
    String clusterId; // note that clusterId will contain an empty string on RDS Oracle and RDS SQL Server
    String instanceId;
    List<ActivityEvent> databaseActivityEventList;

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<ActivityEvent> getDatabaseActivityEventList() {
        return databaseActivityEventList;
    }

    public void setDatabaseActivityEventList(List<ActivityEvent> databaseActivityEventList) {
        this.databaseActivityEventList = databaseActivityEventList;
    }

    // toString
    @Override
    public String toString() {
        return "ActivityRecords{" +
                "type='" + type + '\'' +
                ", clusterId='" + clusterId + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", databaseActivityEventList=" + databaseActivityEventList +
                '}';
    }
}
