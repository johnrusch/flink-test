package com.jrusch.flinktest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ActivityEvent {
    @JsonProperty("class") String _class;
    String clientApplication;
    String command;
    String commandText;
    String databaseName;
    String dbProtocol;
    String dbUserName;
    String endTime;
    String errorMessage;
    String exitCode;
    String logTime;
    String netProtocol;
    String objectName;
    String objectType;
    List<String> paramList;
    String pid;
    String remoteHost;
    String remotePort;
    String rowCount;
    String serverHost;
    String serverType;
    String serverVersion;
    String serviceName;
    String sessionId;
    String startTime;
    String statementId;
    String substatementId;
    String transactionId;
    String type;

    public String getClientApplication() {
        return clientApplication;
    }

    public void setClientApplication(String clientApplication) {
        this.clientApplication = clientApplication;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDbProtocol() {
        return dbProtocol;
    }

    public void setDbProtocol(String dbProtocol) {
        this.dbProtocol = dbProtocol;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExitCode() {
        return exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getNetProtocol() {
        return netProtocol;
    }

    public void setNetProtocol(String netProtocol) {
        this.netProtocol = netProtocol;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getSubstatementId() {
        return substatementId;
    }

    public void setSubstatementId(String substatementId) {
        this.substatementId = substatementId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString method
    @Override
    public String toString() {
        return "ActivityEvent{" +
                "_class='" + _class
                + '\'' + ", clientApplication='" + clientApplication
                + '\'' + ", command='" + command
                + '\'' + ", commandText='" + commandText
                + '\'' + ", databaseName='" + databaseName
                + '\'' + ", dbProtocol='" + dbProtocol
                + '\'' + ", dbUserName='" + dbUserName
                + '\'' + ", endTime='" + endTime
                + '\'' + ", errorMessage='" + errorMessage
                + '\'' + ", exitCode='" + exitCode
                + '\'' + ", logTime='" + logTime
                + '\'' + ", netProtocol='" + netProtocol
                + '\'' + ", objectName='" + objectName
                + '\'' + ", objectType='" + objectType
                + '\'' + ", paramList=" + paramList
                + ", pid='" + pid
                + '\'' + ", remoteHost='" + remoteHost
                + '\'' + ", remotePort='" + remotePort
                + '\'' + ", rowCount='" + rowCount
                + '\'' + ", serverHost='" + serverHost
                + '\'' + ", serverType='" + serverType
                + '\'' + ", serverVersion='" + serverVersion
                + '\'' + ", serviceName='" + serviceName
                + '\'' + ", sessionId='" + sessionId
                + '\'' + ", startTime='" + startTime
                + '\'' + ", statementId='" + statementId
                + '\'' + ", substatementId='" + substatementId
                + '\'' + ", transactionId='" + transactionId
                + '\'' + ", type='" + type
                + '\'' + '}';
    }
}
