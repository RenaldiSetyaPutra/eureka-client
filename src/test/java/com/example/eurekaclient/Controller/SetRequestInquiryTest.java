package com.example.eurekaclient.Controller;

import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;

import java.util.HashMap;
import java.util.Map;

public class SetRequestInquiryTest {

    static InquirySNAPRequest setRequestInquiryPositive(){
        InquirySNAPRequest request = new InquirySNAPRequest();
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("bankCd", "HNBN");
        request.setPartnerServiceId("97720208");
        request.setCustomerNo("781315391070");
        request.setVirtualAccountNo("9772020870717292");
        request.setInquiryRequestId("41807553358950093184");
        request.setAdditionalInfo(additionalInfo);
        return request;
    }

    static InquirySNAPRequest setRequestInquiryMandatoryAdditionalInfo(){
        InquirySNAPRequest request = new InquirySNAPRequest();
        Map<String,Object> additionalInfo = new HashMap<>();
        request.setPartnerServiceId("97720208");
        request.setCustomerNo("781315391070");
        request.setVirtualAccountNo("9772020870717292");
        request.setInquiryRequestId("41807553358950093184");
        request.setAdditionalInfo(additionalInfo);
        return request;
    }

    static InquirySNAPRequest setRequestInquiryMandatoryPartnerServiceId(){
        InquirySNAPRequest request = new InquirySNAPRequest();
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("bankCd", "HNBN");
        request.setPartnerServiceId("");
        request.setCustomerNo("781315391070");
        request.setVirtualAccountNo("9772020870717292");
        request.setInquiryRequestId("41807553358950093184");
        request.setAdditionalInfo(additionalInfo);
        return request;
    }

    static InquirySNAPRequest setRequestInquiryFormatPartnerServiceId(){
        InquirySNAPRequest request = new InquirySNAPRequest();
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("bankCd", "HNBN");
        request.setPartnerServiceId("977202081111111111111");
        request.setCustomerNo("781315391070");
        request.setVirtualAccountNo("9772020870717292");
        request.setInquiryRequestId("41807553358950093184");
        request.setAdditionalInfo(additionalInfo);
        return request;
    }

}
