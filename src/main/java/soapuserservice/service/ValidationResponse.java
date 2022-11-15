package soapuserservice.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationResponse {
    private boolean success;
    private List<String> errors;

    String error;


    public ValidationResponse(List<String> errors) {
        success = false;
        this.errors = errors;
    }

    public ValidationResponse(String error) {
        success = false;
        this.error = error;
    }

    public ValidationResponse() {
        success = true;
    }

/*
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean value) {
        this.success = value;
    }

    public List<String> getErrors() {
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        return this.errors;
    }
 */

}
