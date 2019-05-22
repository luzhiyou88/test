package com.education.classroom.utils.exception;

public class ECMSystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String error_code;

    public ECMSystemException(String error_description) {

        super(error_description);

        error_code = "COMMON_ERR_0000";
    }

    public ECMSystemException(Throwable cause) {
        super(cause);
    }

    public ECMSystemException(String err_code, String error_description) {

        super(error_description);

        error_code = err_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
