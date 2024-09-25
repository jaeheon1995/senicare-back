package com.korit.senicare.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.senicare.common.object.Customer;
import com.korit.senicare.dto.response.ResponseCode;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.dto.response.ResponseMessage;
import com.korit.senicare.repository.resultSet.GetCustomerResultSet;

import lombok.Getter;

@Getter
public class GetCustomerListResponseDto extends ResponseDto {

    private List<Customer> customers;

    private GetCustomerListResponseDto (List<GetCustomerResultSet> resultSets) {
        super(ResponseCode.SUCCCESS, ResponseMessage.SUCCESS);
        this.customers = Customer.getList(resultSets);
    }

    public static ResponseEntity<GetCustomerListResponseDto> success(List<GetCustomerResultSet> resultSets) {
        GetCustomerListResponseDto responseBody = new GetCustomerListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}