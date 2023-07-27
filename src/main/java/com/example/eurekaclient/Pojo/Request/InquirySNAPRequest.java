package com.example.eurekaclient.Pojo.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquirySNAPRequest {
    
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String inquiryRequestId;
    private AdditionalInfo additionalInfo;
}