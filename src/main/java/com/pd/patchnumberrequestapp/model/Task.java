package com.pd.patchnumberrequestapp.model; 

public class Task {
    private Long id;
    private String bookType;
    private String lineType;
    private String taskNumber;
    private String taskShortDescription;
    private String requestedBy;
    private String patchNumber;

    public Task() {
    }

    public Task(String bookType, String lineType, String taskNumber, String taskShortDescription, String requestedBy, String patchNumber) {
        this.bookType = bookType;
        this.lineType = lineType;
        this.taskNumber = taskNumber;
        this.taskShortDescription = taskShortDescription;
        this.requestedBy = requestedBy;
        this.patchNumber = patchNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskShortDescription() {
        return taskShortDescription;
    }

    public void setTaskShortDescription(String taskShortDescription) {
        this.taskShortDescription = taskShortDescription;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getPatchNumber() {
        return patchNumber;
    }

    public void setPatchNumber(String patchNumber) {
        this.patchNumber = patchNumber;
    }

    @Override
    public String toString() {
        return "Task{" +
               "id=" + id +
               ", bookType='" + bookType + '\'' +
               ", lineType='" + lineType + '\'' +
               ", taskNumber='" + taskNumber + '\'' +
               ", taskShortDescription='" + taskShortDescription + '\'' +
               ", requestedBy='" + requestedBy + '\'' +
               ", patchNumber='" + patchNumber + '\'' +
               '}';
    }
}
