package com.example.eurekaclient.Helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.eurekaclient.Pojo.Context.NicepayContext;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import io.vavr.control.Try;

@Service
public class Helper {
    /**
     * @param request
     * @return
     */
    public Try<NicepayContext> initInquiry(
            InquirySNAPRequest request,
            String codeService,
            BindingResult bindingResult
        ){
        return Try.ofSupplier(() -> new NicepayContext(request, codeService, bindingResult));
    }

    public Try<NicepayContext> initPayment(
            PaymentSNAPRequest request,
            String codeService
    ){
        return Try.ofSupplier(() -> new NicepayContext(request, codeService));
    }

    // public Try<NicepayContext> initReversal(
    //         ReversalSNAPRequest request,
    //         HttpServletRequest httpServletRequest,
    //         BindingResult bindingResult,
    //         String codeService,
    //         int nicepayProses
    // ){

    //     return Try.ofSupplier(() -> new InquiryContext(request, httpServletRequest, bindingResult, codeService ,nicepayProses));
    // }
}
