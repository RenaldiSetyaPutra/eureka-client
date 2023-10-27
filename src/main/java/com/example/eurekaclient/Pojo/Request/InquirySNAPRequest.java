package com.example.eurekaclient.Pojo.Request;

import java.util.Map;

import com.example.eurekaclient.Pojo.Interface.InquirySNAPInterface;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InquirySNAPRequest {

    @NotBlank(groups = InquirySNAPInterface.class, message = "Invalid Mandatory Field")
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String inquiryRequestId;
    private Map<String, Object> additionalInfo;
}