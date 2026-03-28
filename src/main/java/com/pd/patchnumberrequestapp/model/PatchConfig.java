package com.pd.patchnumberrequestapp.model;

public class PatchConfig {
    private Long id;
    
    // Open Book Series
    private Long openBookRPP1;
    private Long openBookRPP2;
    private Long openBookRPP3;
    private String openBookLastDeployedPatch;

    // Closed Book Series
    private Long closedBookRPP1;
    private Long closedBookRPP2;
    private Long closedBookRPP3;
    private String closedBookLastDeployedPatch;

    public PatchConfig() {
        this.openBookRPP1 = 0L;
        this.openBookRPP2 = 0L;
        this.openBookRPP3 = 0L;
        this.closedBookRPP1 = 0L;
        this.closedBookRPP2 = 0L;
        this.closedBookRPP3 = 0L;
        this.openBookLastDeployedPatch = "";
        this.closedBookLastDeployedPatch = "";
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
               ", openBookRPP1=" + openBookRPP1 +
               ", openBookRPP2=" + openBookRPP2 +
               ", openBookRPP3=" + openBookRPP3 +
               ", closedBookRPP1=" + closedBookRPP1 +
               ", closedBookRPP2=" + closedBookRPP2 +
               ", closedBookRPP3=" + closedBookRPP3 +
               ", openBookLastDeployedPatch='" + openBookLastDeployedPatch + '\'' +
               ", closedBookLastDeployedPatch='" + closedBookLastDeployedPatch + '\'' +
               '}';
    }
}
