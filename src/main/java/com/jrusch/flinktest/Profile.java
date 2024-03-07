package com.jrusch.flinktest;

public class Profile {
    private Integer docprofileId;
    private Boolean live;
    private String name;
    private Organization organization;
    private String profileSlug;
    private String profileStatus;

    public Profile() {

    }

    public Integer getDocprofileId() {
        return docprofileId;
    }

    public void setDocprofileId(Integer docprofileId) {
        this.docprofileId = docprofileId;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getProfileSlug() {
        return profileSlug;
    }

    public void setProfileSlug(String profileSlug) {
        this.profileSlug = profileSlug;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "docprofileId=" + docprofileId +
                ", live=" + live +
                ", name='" + name + '\'' +
                ", organization=" + organization +
                ", profileSlug='" + profileSlug + '\'' +
                ", profileStatus='" + profileStatus + '\'' +
                '}';
    }
}
