package com.works.mvcproject.rest.models.cartproc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartProcRest {

    private Boolean status;
    private String message;
    private List<ResultRest> resultRest = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultRest> getResult() {
        return resultRest;
    }

    public void setResult(List<ResultRest> resultRest) {
        this.resultRest = resultRest;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}