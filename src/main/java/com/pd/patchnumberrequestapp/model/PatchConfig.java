package com.pd.patchnumberrequestapp.model;

public class PatchConfig {
    private Long id;
    
    // Open Book
    private Long openBookRPP1;
    private Long openBookRPP2;
    private Long openBookRPP3;
    private String openBookSeries;
    private String openBookFixedString;
    private String openBookLastDeployedPatch;

    // Closed Book
    private Long closedBookRPP1;
    private Long closedBookRPP2;
    private Long closedBookRPP3;
    private String closedBookSeries;
    private String closedBookFixedString;
    private String closedBookLastDeployedPatch;
    
    // Migration
    private Long maxMigRPP1;
    private Long maxMigRPP2;
    private Long maxMigRPP3;
    private String maxMigSeries;
    private String maxMigFixedString;
    private String maxMigLastDeployedPatch;

    public PatchConfig() {
        this.openBookRPP1 = 0L;
        this.openBookSeries = "";
        this.openBookFixedString = "";
        this.openBookLastDeployedPatch = "";
        
        this.closedBookRPP1 = 0L;
        this.closedBookSeries = "";
        this.closedBookFixedString = "";
        this.closedBookLastDeployedPatch = "";
        
        this.maxMigRPP1 = 0L;
        this.maxMigSeries = "";
        this.maxMigFixedString = "";
        this.maxMigLastDeployedPatch = "";
        
        this.openBookRPP2 = 0L;
        this.openBookRPP3 = 0L;
        this.closedBookRPP2 = 0L;
        this.closedBookRPP3 = 0L;
        this.maxMigRPP2 = 0L;
        this.maxMigRPP3 = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpenBookRPP1() {
        return openBookRPP1;
    }

    public void setOpenBookRPP1(Long openBookRPP1) {
        this.openBookRPP1 = openBookRPP1;
    }

    public Long getOpenBookRPP2() {
        return openBookRPP2;
    }

    public void setOpenBookRPP2(Long openBookRPP2) {
        this.openBookRPP2 = openBookRPP2;
    }

    public Long getOpenBookRPP3() {
        return openBookRPP3;
    }

    public void setOpenBookRPP3(Long openBookRPP3) {
        this.openBookRPP3 = openBookRPP3;
    }

    public String getOpenBookSeries() {
        return openBookSeries;
    }

    public void setOpenBookSeries(String openBookSeries) {
        this.openBookSeries = openBookSeries;
    }

    public String getOpenBookFixedString() {
        return openBookFixedString;
    }

    public void setOpenBookFixedString(String openBookFixedString) {
        this.openBookFixedString = openBookFixedString;
    }

    public String getOpenBookLastDeployedPatch() {
        return openBookLastDeployedPatch;
    }

    public void setOpenBookLastDeployedPatch(String openBookLastDeployedPatch) {
        this.openBookLastDeployedPatch = openBookLastDeployedPatch;
    }

    public Long getClosedBookRPP1() {
        return closedBookRPP1;
    }

    public void setClosedBookRPP1(Long closedBookRPP1) {
        this.closedBookRPP1 = closedBookRPP1;
    }

    public Long getClosedBookRPP2() {
        return closedBookRPP2;
    }

    public void setClosedBookRPP2(Long closedBookRPP2) {
        this.closedBookRPP2 = closedBookRPP2;
    }

    public Long getClosedBookRPP3() {
        return closedBookRPP3;
    }

    public void setClosedBookRPP3(Long closedBookRPP3) {
        this.closedBookRPP3 = closedBookRPP3;
    }

    public String getClosedBookSeries() {
        return closedBookSeries;
    }

    public void setClosedBookSeries(String closedBookSeries) {
        this.closedBookSeries = closedBookSeries;
    }

    public String getClosedBookFixedString() {
        return closedBookFixedString;
    }

    public void setClosedBookFixedString(String closedBookFixedString) {
        this.closedBookFixedString = closedBookFixedString;
    }

    public String getClosedBookLastDeployedPatch() {
        return closedBookLastDeployedPatch;
    }

    public void setClosedBookLastDeployedPatch(String closedBookLastDeployedPatch) {
        this.closedBookLastDeployedPatch = closedBookLastDeployedPatch;
    }

    public Long getMaxMigRPP1() {
        return maxMigRPP1;
    }

    public void setMaxMigRPP1(Long maxMigRPP1) {
        this.maxMigRPP1 = maxMigRPP1;
    }

    public Long getMaxMigRPP2() {
        return maxMigRPP2;
    }

    public void setMaxMigRPP2(Long maxMigRPP2) {
        this.maxMigRPP2 = maxMigRPP2;
    }

    public Long getMaxMigRPP3() {
        return maxMigRPP3;
    }

    public void setMaxMigRPP3(Long maxMigRPP3) {
        this.maxMigRPP3 = maxMigRPP3;
    }

    public String getMaxMigSeries() {
        return maxMigSeries;
    }

    public void setMaxMigSeries(String maxMigSeries) {
        this.maxMigSeries = maxMigSeries;
    }

    public String getMaxMigFixedString() {
        return maxMigFixedString;
    }

    public void setMaxMigFixedString(String maxMigFixedString) {
        this.maxMigFixedString = maxMigFixedString;
    }

    public String getMaxMigLastDeployedPatch() {
        return maxMigLastDeployedPatch;
    }

    public void setMaxMigLastDeployedPatch(String maxMigLastDeployedPatch) {
        this.maxMigLastDeployedPatch = maxMigLastDeployedPatch;
    }
}
