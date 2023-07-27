package com.example.eurekaclient.Pojo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String responseCode;
    private String responseMessage;
    private Object virtualAccountData;

    public BaseResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
