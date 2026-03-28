package com.pd.patchnumberrequestapp.model;

public class PatchConfig {
    private Long id;
    
    // Open Book
    private Long openBookRPP1;
    private Long openBookRPP2;
    private Long openBookRPP3;
    private String openBookPatchFormat;
    private String openBookLastDeployedPatch;

    // Closed Book
    private Long closedBookRPP1;
    private Long closedBookRPP2;
    private Long closedBookRPP3;
    private String closedBookPatchFormat;
    private String closedBookLastDeployedPatch;
    
    // Max Migration
    private Long maxMigRPP1;
    private Long maxMigRPP2;
    private Long maxMigRPP3;
    private String maxMigPatchFormat;
    private String maxMigLastDeployedPatch;

    public PatchConfig() {
        this.openBookRPP1 = 0L;
        this.openBookRPP2 = 0L;
        this.openBookRPP3 = 0L;
        this.openBookPatchFormat = "OBJ-%04d";
        this.openBookLastDeployedPatch = "";
        
        this.closedBookRPP1 = 0L;
        this.closedBookRPP2 = 0L;
        this.closedBookRPP3 = 0L;
        this.closedBookPatchFormat = "CBJ-%04d";
        this.closedBookLastDeployedPatch = "";
        
        this.maxMigRPP1 = 0L;
        this.maxMigRPP2 = 0L;
        this.maxMigRPP3 = 0L;
        this.maxMigPatchFormat = "MIG-%04d";
        this.maxMigLastDeployedPatch = "";
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

    public String getOpenBookPatchFormat() {
        return openBookPatchFormat;
    }

    public void setOpenBookPatchFormat(String openBookPatchFormat) {
        this.openBookPatchFormat = openBookPatchFormat;
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

    public String getClosedBookPatchFormat() {
        return closedBookPatchFormat;
    }

    public void setClosedBookPatchFormat(String closedBookPatchFormat) {
        this.closedBookPatchFormat = closedBookPatchFormat;
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

    public String getMaxMigPatchFormat() {
        return maxMigPatchFormat;
    }

    public void setMaxMigPatchFormat(String maxMigPatchFormat) {
        this.maxMigPatchFormat = maxMigPatchFormat;
    }

    public String getMaxMigLastDeployedPatch() {
        return maxMigLastDeployedPatch;
    }

    public void setMaxMigLastDeployedPatch(String maxMigLastDeployedPatch) {
        this.maxMigLastDeployedPatch = maxMigLastDeployedPatch;
    }
}
