package com.korit.senicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.senicare.common.object.ChargedCustomer;
import com.korit.senicare.dto.response.ResponseCode;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.dto.response.ResponseMessage;
import com.korit.senicare.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class GetChargedCutomerResponseDto extends ResponseDto {
    
    private List<ChargedCustomer> customers;

    private GetChargedCutomerResponseDto(List<CustomerEntity> customerEntities) {
        super(ResponseCode.SUCCCESS, ResponseMessage.SUCCESS);
        this.customers = ChargedCustomer.getList(customerEntities);
    }

    public static ResponseEntity<GetChargedCutomerResponseDto> success(List<CustomerEntity> customerEntities) {
        GetChargedCutomerResponseDto responseBody = new GetChargedCutomerResponseDto(customerEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    } 

}
