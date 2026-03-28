package com.pd.patchnumberrequestapp.model;

public class PatchConfig {
    private Long id;
    
    // Open Book
    private String openBookPrefix;
    private String openBookFixString;
    private String openBookMajorVersion;
    private String openBookMinorVersion;
    private Long openBookRPP1;
    private Long openBookRPP2;
    private Long openBookRPP3;
    private String openBookLastDeployedPatch;

    // Closed Book
    private String closedBookPrefix;
    private String closedBookFixString;
    private String closedBookMajorVersion;
    private String closedBookMinorVersion;
    private Long closedBookRPP1;
    private Long closedBookRPP2;
    private Long closedBookRPP3;
    private String closedBookLastDeployedPatch;
    
    // Migration
    private String maxMigPrefix;
    private String maxMigFixString;
    private String maxMigMajorVersion;
    private String maxMigMinorVersion;
    private Long maxMigRPP1;
    private Long maxMigRPP2;
    private Long maxMigRPP3;
    private String maxMigLastDeployedPatch;

    public PatchConfig() {
        this.openBookPrefix = "";
        this.openBookFixString = "";
        this.openBookMajorVersion = "";
        this.openBookMinorVersion = "";
        this.openBookRPP1 = 0L;
        this.openBookRPP2 = 0L;
        this.openBookRPP3 = 0L;
        this.openBookLastDeployedPatch = "";
        
        this.closedBookPrefix = "";
        this.closedBookFixString = "";
        this.closedBookMajorVersion = "";
        this.closedBookMinorVersion = "";
        this.closedBookRPP1 = 0L;
        this.closedBookRPP2 = 0L;
        this.closedBookRPP3 = 0L;
        this.closedBookLastDeployedPatch = "";
        
        this.maxMigPrefix = "";
        this.maxMigFixString = "";
        this.maxMigMajorVersion = "";
        this.maxMigMinorVersion = "";
        this.maxMigRPP1 = 0L;
        this.maxMigRPP2 = 0L;
        this.maxMigRPP3 = 0L;
        this.maxMigLastDeployedPatch = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenBookPrefix() {
        return openBookPrefix;
    }

    public void setOpenBookPrefix(String openBookPrefix) {
        this.openBookPrefix = openBookPrefix;
    }

    public String getOpenBookFixString() {
        return openBookFixString;
    }

    public void setOpenBookFixString(String openBookFixString) {
        this.openBookFixString = openBookFixString;
    }

    public String getOpenBookMajorVersion() {
        return openBookMajorVersion;
    }

    public void setOpenBookMajorVersion(String openBookMajorVersion) {
        this.openBookMajorVersion = openBookMajorVersion;
    }

    public String getOpenBookMinorVersion() {
        return openBookMinorVersion;
    }

    public void setOpenBookMinorVersion(String openBookMinorVersion) {
        this.openBookMinorVersion = openBookMinorVersion;
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

    public String getOpenBookLastDeployedPatch() {
        return openBookLastDeployedPatch;
    }

    public void setOpenBookLastDeployedPatch(String openBookLastDeployedPatch) {
        this.openBookLastDeployedPatch = openBookLastDeployedPatch;
    }

    public String getClosedBookPrefix() {
        return closedBookPrefix;
    }

    public void setClosedBookPrefix(String closedBookPrefix) {
        this.closedBookPrefix = closedBookPrefix;
    }

    public String getClosedBookFixString() {
        return closedBookFixString;
    }

    public void setClosedBookFixString(String closedBookFixString) {
        this.closedBookFixString = closedBookFixString;
    }

    public String getClosedBookMajorVersion() {
        return closedBookMajorVersion;
    }

    public void setClosedBookMajorVersion(String closedBookMajorVersion) {
        this.closedBookMajorVersion = closedBookMajorVersion;
    }

    public String getClosedBookMinorVersion() {
        return closedBookMinorVersion;
    }

    public void setClosedBookMinorVersion(String closedBookMinorVersion) {
        this.closedBookMinorVersion = closedBookMinorVersion;
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

    public String getClosedBookLastDeployedPatch() {
        return closedBookLastDeployedPatch;
    }

    public void setClosedBookLastDeployedPatch(String closedBookLastDeployedPatch) {
        this.closedBookLastDeployedPatch = closedBookLastDeployedPatch;
    }

    public String getMaxMigPrefix() {
        return maxMigPrefix;
    }

    public void setMaxMigPrefix(String maxMigPrefix) {
        this.maxMigPrefix = maxMigPrefix;
    }

    public String getMaxMigFixString() {
        return maxMigFixString;
    }

    public void setMaxMigFixString(String maxMigFixString) {
        this.maxMigFixString = maxMigFixString;
    }

    public String getMaxMigMajorVersion() {
        return maxMigMajorVersion;
    }

    public void setMaxMigMajorVersion(String maxMigMajorVersion) {
        this.maxMigMajorVersion = maxMigMajorVersion;
    }

    public String getMaxMigMinorVersion() {
        return maxMigMinorVersion;
    }

    public void setMaxMigMinorVersion(String maxMigMinorVersion) {
        this.maxMigMinorVersion = maxMigMinorVersion;
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

    public String getMaxMigLastDeployedPatch() {
        return maxMigLastDeployedPatch;
    }

    public void setMaxMigLastDeployedPatch(String maxMigLastDeployedPatch) {
        this.maxMigLastDeployedPatch = maxMigLastDeployedPatch;
    }
}
