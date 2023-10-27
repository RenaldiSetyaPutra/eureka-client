package com.example.eurekaclient.Service.Impl;

import com.example.eurekaclient.Helper.Helper;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import com.example.eurekaclient.Pojo.Response.ParentResponse;
import com.example.eurekaclient.Service.Component.NicepayComponent;
import com.example.eurekaclient.Service.CoreService;
import com.example.eurekaclient.Utils.Generify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class CoreServiceImpl implements CoreService{
    private String serviceCode;
    private final Helper helper;
    private final NicepayComponent nicepayComponent;

    @Override
    public ParentResponse inquiry(InquirySNAPRequest request, String codeService, BindingResult bind) {
            this.serviceCode = codeService;

            return helper.initInquiry(request, codeService, bind)
            // .flatMap(nicepayComponent::checkHeader)
//            .flatMap(nicepayComponent::valid)
            .flatMap(ctx -> nicepayComponent.fieldInquiryAdditionalToMandatory(ctx, "bankCd"))
            .flatMap(nicepayComponent::setResponseInquery)
            .onFailure(e -> Generify.printWhenError(e, serviceCode))
            .fold(e -> Generify.failResponse(e, serviceCode),
                    v -> Generify.successResponse(v.getBaseResponse()));
    }

    @Override
    public ParentResponse payment(PaymentSNAPRequest request, String codeService) {
            this.serviceCode = codeService;

            return helper.initPayment(request, codeService)
            // .flatMap(nicepayComponent::checkHeader)
//            .flatMap(nicepayComponent::valid)
            .flatMap(ctx -> nicepayComponent.fieldPaymentAdditionalToMandatory(ctx,  "bankCd", "tXIdVA"))
            .flatMap(nicepayComponent::setResponseInquery)
            .onFailure(e -> Generify.printWhenError(e, serviceCode))
            .fold(e -> Generify.failResponse(e, serviceCode),
                    v -> Generify.successResponse(v.getBaseResponse()));
    }
    
}
