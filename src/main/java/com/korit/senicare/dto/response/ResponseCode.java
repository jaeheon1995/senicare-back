package com.korit.senicare.dto.response;


// ResponseDto의 code 상수
public interface ResponseCode {
    
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TEL_NUMBER = "DTE";

    String TEL_AUTH_FAIL = "TAF";
    String SIGN_IN_FAIL ="SF";
    
    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
    String DATABASE_EREOR = "DBE";
}