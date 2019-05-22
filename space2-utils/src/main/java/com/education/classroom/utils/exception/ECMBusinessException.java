package com.education.classroom.utils.exception;

public class ECMBusinessException extends Exception {
    private static final long serialVersionUID = 1L;

    private String error_code;
    private String error_description;
    public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public ECMBusinessException(String err_code, String error_description) {
        super(error_description);

        error_code = err_code;
        this.error_description=error_description;
    }

    public ECMBusinessException(String error_description) {
        super(error_description);

        error_code = "COMMON_ERR_0000";
    }

    public ECMBusinessException(Throwable cause) {
        super(cause);
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
    
   
}
