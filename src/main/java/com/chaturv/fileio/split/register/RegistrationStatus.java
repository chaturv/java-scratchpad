package com.chaturv.fileio.split.register;

import java.io.Serializable;

public class RegistrationStatus implements Serializable {

    private Status status;
    private String uploadId;

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

    public enum Status {
        SUCCESS,
        WAIT,
        ERROR
    }
}
