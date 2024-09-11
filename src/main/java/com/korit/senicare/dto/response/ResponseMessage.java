package com.korit.senicare.dto.response;

// ResponseDto의 message상수
public interface ResponseMessage {
    
    String SUCCESS = "Success.";

    String VALIDATION_FAIL = "Vlidation failed.";

    String DUPLICATED_USER_ID = "Duplicated user id.";

    String DUPLICATED_TEL_NUMBER="Duplicated user telnumper.";

    String TEL_AUTH_FAIL="Tel number auth failed.";
    
    String MESSAGE_SEND_FAIL ="Auth number send failed.";

    String DATABASE_ERROR = "Database error.";

}
