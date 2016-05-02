package com.chaturv.fileio.split.upload;

import java.io.Serializable;

public class UploadStatus implements Serializable {

    private Status status;
    private String uploadId;
    private String message;

    public UploadStatus() {
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

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UploadStatus{" +
                "status=" + status +
                ", uploadId='" + uploadId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Status {
        SUCCESS,
        ERROR
    }
}
