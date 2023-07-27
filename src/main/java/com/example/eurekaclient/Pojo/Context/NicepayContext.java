package com.example.eurekaclient.Pojo.Context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import com.example.eurekaclient.Pojo.Response.BaseResponse;
import com.example.eurekaclient.Pojo.Response.InquirySNAPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NicepayContext {
    private String codeService;
    private BindingResult bind;
    private InquirySNAPRequest inquirySNAPRequest;
    private PaymentSNAPRequest paymentSNAPRequest;
    // private ReversalSNAPRequest reversalSNAPRequest;
    // private NicepayRequest inquiryRequest;
    // private NicepayResponse inquiryResponse;
    private InquirySNAPResponse inquirySNAPResponse;
    private BaseResponse<?> baseResponse;

    private Object request;

    public NicepayContext(
            InquirySNAPRequest inquirySNAPRequest,
            String codeService
    ) {
        this.inquirySNAPRequest = inquirySNAPRequest;
        this.codeService = codeService;
    }
    public NicepayContext(
            PaymentSNAPRequest paymentSNAPRequest,
            String codeService
    ) {
        this.paymentSNAPRequest = paymentSNAPRequest;
        this.codeService = codeService;
    }
    // public NicepayContext(
    //         ReversalSNAPRequest reversalSNAPRequest,
    //         HttpServletRequest httpServletRequest,
    //         BindingResult bind,
    //         String codeService,
    //         int nicePayProcess
    // ) {
    //     this.reversalSNAPRequest = reversalSNAPRequest;
    //     this.httpServletRequest = httpServletRequest;
    //     this.codeService = codeService;
    //     this.bind = bind;
    //     this.nicePayProcess = nicePayProcess;
    // }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }  
}
