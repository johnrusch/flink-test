package com.jrusch.flinktest;

public class Organization {
    private Integer organizationId;

    public Organization() {
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                '}';
    }
}
