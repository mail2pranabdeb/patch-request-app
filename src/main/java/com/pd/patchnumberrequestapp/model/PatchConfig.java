package com.pd.patchnumberrequestapp.model;

public class PatchConfig {
    private Long id;
    private String dateFormat; // e.g., "yyyyMMdd"
    private int sequenceLength; // e.g., 3 for 001, 002
    private String openBookLastDeployedPatch; 
    private String closedBookLastDeployedPatch;

    public PatchConfig() {
        this.dateFormat = "yyyyMMdd";
        this.sequenceLength = 3;
        this.openBookLastDeployedPatch = "";
        this.closedBookLastDeployedPatch = "";
    }

    public PatchConfig(String dateFormat, int sequenceLength, String openBookLastDeployedPatch, String closedBookLastDeployedPatch) {
        this.dateFormat = dateFormat;
        this.sequenceLength = sequenceLength;
        this.openBookLastDeployedPatch = openBookLastDeployedPatch;
        this.closedBookLastDeployedPatch = closedBookLastDeployedPatch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public void setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    public String getOpenBookLastDeployedPatch() {
        return openBookLastDeployedPatch;
    }

    public void setOpenBookLastDeployedPatch(String openBookLastDeployedPatch) {
        this.openBookLastDeployedPatch = openBookLastDeployedPatch;
    }

    public String getClosedBookLastDeployedPatch() {
        return closedBookLastDeployedPatch;
    }

    public void setClosedBookLastDeployedPatch(String closedBookLastDeployedPatch) {
        this.closedBookLastDeployedPatch = closedBookLastDeployedPatch;
    }

    @Override
    public String toString() {
        return "PatchConfig{" +
               "id=" + id +
               ", dateFormat='" + dateFormat + '\'' +
               ", sequenceLength=" + sequenceLength +
               ", openBookLastDeployedPatch='" + openBookLastDeployedPatch + '\'' +
               ", closedBookLastDeployedPatch='" + closedBookLastDeployedPatch + '\'' +
               '}';
    }
}
