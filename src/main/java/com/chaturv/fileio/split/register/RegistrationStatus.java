package com.chaturv.fileio.split.register;

import java.io.Serializable;

public class RegistrationStatus implements Serializable {

    private Status status;
    private String uploadId;
    private String fileName;

    public RegistrationStatus() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "RegistrationStatus{" +
                "status=" + status +
                ", uploadId='" + uploadId + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public enum Status {
        SUCCESS,
        WAIT,
        ERROR
    }
}
