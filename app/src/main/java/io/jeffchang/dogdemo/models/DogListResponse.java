package io.jeffchang.dogdemo.models;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Map;

/**
 * POJO for response from the dog breeds API endpoint.
 */

public class DogListResponse {

    @Json(name = "status")
    private String status;

    @Json(name = "message")
    private Map<String, List<String>> message;

    public DogListResponse(String status, Map<String, List<String>> message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public void setMessage(Map<String, List<String>> message) {
        this.message = message;
    }
}
