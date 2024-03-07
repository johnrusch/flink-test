package com.jrusch.flinktest;

public class Review {
    private Integer docProfileId;
    private Integer treatmentId;
    private String reviewSlug;
    private Integer rating;
    private Double rawRating;
    private String body;
    private String sentiment;
    private String email;
    private String displayName;
    private Boolean hasBody;
    private String status;
    private String reviewSource;
    private String reviewSourceId;
    private String reviewDate;
    private String destination;
    private String maturity;
    private String publishDate;
    private String distributionDate;
    private String lastModified;
    private String created;
    private String body2;
    private String sentiment2;
    private String body3;
    private String sentiment3;
    private String body4;
    private String sentiment4;
    private Boolean commentEdited;
    private String response;
    private String responseDate;
    private String responseAuthor;
    private String archiveReason;
    private Boolean autoTagged;
    private Integer age;
    private String dob; // Date of Birth could be a Date object, using String for simplicity
    private String gender;
    private String race;
    private String language;
    private String patientId;
    private Profile profile;

    // Default constructor
    public Review() {
    }

    public Integer getDocProfileId() {
        return docProfileId;
    }

    public void setDocProfileId(Integer docProfileId) {
        this.docProfileId = docProfileId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getReviewSlug() {
        return reviewSlug;
    }

    public void setReviewSlug(String reviewSlug) {
        this.reviewSlug = reviewSlug;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getRawRating() {
        return rawRating;
    }

    public void setRawRating(Double rawRating) {
        this.rawRating = rawRating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getHasBody() {
        return hasBody;
    }

    public void setHasBody(Boolean hasBody) {
        this.hasBody = hasBody;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public String getReviewSourceId() {
        return reviewSourceId;
    }

    public void setReviewSourceId(String reviewSourceId) {
        this.reviewSourceId = reviewSourceId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getBody2() {
        return body2;
    }

    public void setBody2(String body2) {
        this.body2 = body2;
    }

    public String getSentiment2() {
        return sentiment2;
    }

    public void setSentiment2(String sentiment2) {
        this.sentiment2 = sentiment2;
    }

    public String getBody3() {
        return body3;
    }

    public void setBody3(String body3) {
        this.body3 = body3;
    }

    public String getSentiment3() {
        return sentiment3;
    }

    public void setSentiment3(String sentiment3) {
        this.sentiment3 = sentiment3;
    }

    public String getBody4() {
        return body4;
    }

    public void setBody4(String body4) {
        this.body4 = body4;
    }

    public String getSentiment4() {
        return sentiment4;
    }

    public void setSentiment4(String sentiment4) {
        this.sentiment4 = sentiment4;
    }

    public Boolean getCommentEdited() {
        return commentEdited;
    }

    public void setCommentEdited(Boolean commentEdited) {
        this.commentEdited = commentEdited;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseAuthor() {
        return responseAuthor;
    }

    public void setResponseAuthor(String responseAuthor) {
        this.responseAuthor = responseAuthor;
    }

    public String getArchiveReason() {
        return archiveReason;
    }

    public void setArchiveReason(String archiveReason) {
        this.archiveReason = archiveReason;
    }

    public Boolean getAutoTagged() {
        return autoTagged;
    }

    public void setAutoTagged(Boolean autoTagged) {
        this.autoTagged = autoTagged;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Review{" +
                "docProfileId=" + docProfileId +
                ", treatmentId=" + treatmentId +
                ", reviewSlug='" + reviewSlug + '\'' +
                ", rating=" + rating +
                ", rawRating=" + rawRating +
                ", body='" + body + '\'' +
                ", sentiment='" + sentiment + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", hasBody=" + hasBody +
                ", status='" + status + '\'' +
                ", reviewSource='" + reviewSource + '\'' +
                ", reviewSourceId='" + reviewSourceId + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", destination='" + destination + '\'' +
                ", maturity='" + maturity + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", distributionDate='" + distributionDate + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", created='" + created + '\'' +
                ", body2='" + body2 + '\'' +
                ", sentiment2='" + sentiment2 + '\'' +
                ", body3='" + body3 + '\'' +
                ", sentiment3='" + sentiment3 + '\'' +
                ", body4='" + body4 + '\'' +
                ", sentiment4='" + sentiment4 + '\'' +
                ", commentEdited=" + commentEdited +
                ", response='" + response + '\'' +
                ", responseDate='" + responseDate + '\'' +
                ", responseAuthor='" + responseAuthor + '\'' +
                ", archiveReason='" + archiveReason + '\'' +
                ", autoTagged=" + autoTagged +
                ", age=" + age +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", language='" + language + '\'' +
                ", patientId='" + patientId + '\'' +
                ", profile=" + profile +
                '}';
    }
}
