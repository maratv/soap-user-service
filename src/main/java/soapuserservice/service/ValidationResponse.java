package soapuserservice.service;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "", propOrder = {
        "success",
        "errors"
})
@Component
public class ValidationResponse {

    boolean success;
    List<String> errors;


    public ValidationResponse(List<String> errors) {
        success = false;
        this.errors = errors;
    }

    public ValidationResponse() {
        success = true;
    }

    public void setSuccess(boolean value) {
        this.success = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getErrors() {
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
