package com.example.eurekaclient.Pojo.Request;

import java.util.Map;

import com.example.eurekaclient.Pojo.Interface.InquirySNAPInterface;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InquirySNAPRequest {

    @NotBlank(
            message = "Invalid Mandatory Field" + "#" +"02",
            groups = InquirySNAPInterface.class
    )
    @Size(
            max  = 20,
            message = "Invalid Field Format" + "#" + "01",
            groups = InquirySNAPInterface.class
    )
    private String partnerServiceId;

    private String customerNo;
    private String virtualAccountNo;
    private String inquiryRequestId;
    private Map<String, Object> additionalInfo;
}