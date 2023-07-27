package com.example.eurekaclient.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import com.example.eurekaclient.Pojo.Response.ParentResponse;

public interface CoreService {
    ParentResponse inquiry(InquirySNAPRequest request, String codeService);
    ParentResponse payment(PaymentSNAPRequest request, String codeService);
    // ParentResponse reversal(ReversalSNAPRequest request, HttpServletRequest httpServletRequest, BindingResult bindingResult, String codeService);
}
