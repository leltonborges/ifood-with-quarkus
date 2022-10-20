package org.project.ifood.marketplace.handler.data;

public class Standard {
    private String message;
    private String path;
    private Long timestamp;
    private String status;

    public Standard(String message, String path, Long timestamp, String status) {
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
