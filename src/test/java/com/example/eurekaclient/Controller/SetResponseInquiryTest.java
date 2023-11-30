package com.example.eurekaclient.Controller;

import com.example.eurekaclient.Pojo.Response.BaseResponse;
import com.example.eurekaclient.Pojo.Response.InquirySNAPResponse;
import com.example.eurekaclient.Pojo.Response.ParentResponse;
import org.springframework.http.HttpStatus;

public class SetResponseInquiryTest {

    static ParentResponse buildParentResponsePositive() {
        ParentResponse snapResponse = new ParentResponse();
        var response = InquirySNAPResponse.builder()
                .responseCode("")
                .responseMessage("")
                .build();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.OK.value())
                .append("24")
                .append("00");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "SUCCESS",
                response);

        snapResponse.setHttpStatus(HttpStatus.OK);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }

    static ParentResponse buildParentResponseNegativeMandatoryBankCd() {
        ParentResponse snapResponse = new ParentResponse();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.BAD_REQUEST.value())
                .append("24")
                .append("02");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "Invalid Mandatory Field additionalInfo.bankCd");

        snapResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }

    static ParentResponse buildParentResponseNegativeMandatoryPartnerServiceId() {
        ParentResponse snapResponse = new ParentResponse();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.BAD_REQUEST.value())
                .append("24")
                .append("02");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "Invalid Mandatory Field partnerServiceId");

        snapResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }

    static ParentResponse buildParentResponseNegativeFormatPartnerServiceId() {
        ParentResponse snapResponse = new ParentResponse();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.BAD_REQUEST.value())
                .append("24")
                .append("01");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "Invalid Field Format partnerServiceId");

        snapResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }
}
