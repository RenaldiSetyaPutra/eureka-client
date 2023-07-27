package com.example.eurekaclient.Service.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.eurekaclient.Exception.ResponseErrorException;
import com.example.eurekaclient.Pojo.Context.NicepayContext;
import com.example.eurekaclient.Pojo.Interface.InquirySNAPInterface;
import com.example.eurekaclient.Pojo.Request.Amount;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import com.example.eurekaclient.Pojo.Response.BaseResponse;
import com.example.eurekaclient.Pojo.Response.InquirySNAPResponse;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NicepayComponent {

    public void checkBindData(BindingResult bind, String service) {
        if (bind.hasErrors()) {
            FieldError error = bind.getFieldErrors().get(0);
            String[] msg = error.getDefaultMessage().split("#");
            throw new ResponseErrorException(
                    HttpStatus.BAD_REQUEST.value() + service + msg[1],
                    msg[0] + " " + error.getField(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public Try<NicepayContext> checkBind(NicepayContext context) {
        return Try.of(() -> context)
                .andThenTry(ctx -> {
                    checkBindData(ctx.getBind(), ctx.getCodeService());
                    if (ctx.getBind().hasErrors()) {
                        throw new ResponseErrorException(
                                HttpStatus.TOO_MANY_REQUESTS.value() + ctx.getCodeService() + "00",
                                HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(),
                                HttpStatus.BAD_REQUEST
                        );
                    }
                });
    }

    public Try<NicepayContext> valid(NicepayContext context){
        return Try.of(() -> context)
            .andThenTry(ctx -> {
                if(ctx.getCodeService().equalsIgnoreCase("24")){
                    ctx.setRequest(ctx.getInquirySNAPRequest());
                }else if(ctx.getCodeService().equalsIgnoreCase("25")){
                    ctx.setRequest(ctx.getPaymentSNAPRequest());
                }

                Configuration<?> config = Validation.byDefaultProvider().configure();
                FileInputStream in = new FileInputStream(new File("C:/Users/Dev Vendor 5/Documents/Eureka/eureka-client/src/main/resources/vault/NicepaySNAPValidation.xml"));
                // FileInputStream in = new FileInputStream(new File("D:/Documents/Data Kantor/Latihan/Eureka/eureka-client/src/main/resources/vault/NicepaySNAPValidation.xml"));
                config.addMapping(in);
                ValidatorFactory factory = config.buildValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(ctx.getRequest());
                constraintViolations = validator.validate(ctx.getRequest(), InquirySNAPInterface.class);

                for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                    if(!constraintViolation.getMessage().isEmpty()){
                        throw new ResponseErrorException(
                            "400any00", 
                            constraintViolation.getMessage() + " " + constraintViolation.getPropertyPath(), 
                            HttpStatus.BAD_REQUEST
                        );
                    }
                }
            });
    }

    public Try<NicepayContext> fieldInquiryAdditionalToMandatory(NicepayContext context, String... fields) {
        return Try.of(() -> context)
                .andThenTry(ctx -> {
                    InquirySNAPRequest snapRequest = ctx.getInquirySNAPRequest();
                    grabFieldMandatory(snapRequest.getAdditionalInfo(), ctx.getCodeService(), fields);
                    for (String field : fields) {
                        if(field.equals("bankCd") && snapRequest.getAdditionalInfo().get("bankCd").toString().length() != 4 ){
                            throw new ResponseErrorException(
                                HttpStatus.BAD_REQUEST.value() + "any" + "01",
                                String.format("Invalid Field Format".concat(" additionalInfo.%s"), field),
                                HttpStatus.BAD_REQUEST
                            );
                        }
                    }
                });
    }

    public Try<NicepayContext> fieldPaymentAdditionalToMandatory(NicepayContext context, String... fields) {

        return Try.of(() -> context)
                .andThenTry(ctx -> {
                    PaymentSNAPRequest snapRequest = ctx.getPaymentSNAPRequest();
                    grabFieldMandatory(snapRequest.getAdditionalInfo(), ctx.getCodeService(), fields);
                    for (String field : fields) {
                        if(field.equals("bankCd") && snapRequest.getAdditionalInfo().get("bankCd").toString().length() != 4 ){
                            throw new ResponseErrorException(
                                HttpStatus.BAD_REQUEST.value() + "any" + "01",
                                String.format("Invalid Field Format".concat(" additionalInfo.%s"), field),
                                HttpStatus.BAD_REQUEST
                            );
                        }
                        if(field.equals("tXIdVA") && snapRequest.getAdditionalInfo().get("tXIdVA").toString().length() > 30 ){
                            throw new ResponseErrorException(
                                HttpStatus.BAD_REQUEST.value() + "any" + "01",
                                String.format("Invalid Field Format".concat(" additionalInfo.%s"), field),
                                HttpStatus.BAD_REQUEST
                            );
                        }
                    }
                });
    }

    private static void grabFieldMandatory(Map<String, Object> additionalInfo, String serviceCode, String[] fields) {
        for (String field : fields) {
            StringBuilder sb = new StringBuilder();
            StringBuilder append = sb.append(HttpStatus.BAD_REQUEST.value())
                    .append(serviceCode)
                    .append("02");

            if (additionalInfo.get(field) == null || additionalInfo.get(field) == "") {
                throw new ResponseErrorException(
                        append.toString(),
                        String.format("Invalid Mandatory Field".concat(" additionalInfo.%s"), field),
                        HttpStatus.BAD_REQUEST);
            }
        }
    }

    public Try<NicepayContext> setResponseInquery(NicepayContext context) {
        return Try.of(() -> context)
                .andThenTry(ctx -> {
                    var reqNicepay = ctx.getInquirySNAPRequest();
                    // var transactionTemplate = ctx.getTransactionTemplate();
                    // var cashInResponse = ctx.getCashInResponse();

                    // var amount = new Amount(
                    //         transactionTemplate.getAmount() != 0 ? String.valueOf(transactionTemplate.getAmount()) : "0.00",
                    //         "IDR");

                    // HashMap<String, String> additionalInfo = new HashMap<>();
                    // additionalInfo.put("goodsNm", transactionTemplate.getName());


                    var response = InquirySNAPResponse.builder()
                            .responseCode("")
                            .responseMessage("")
                            // .partnerServiceId(reqNicepay.getPartnerServiceId())
                            // .customerNo(reqNicepay.getCustomerNo())
                            // .virtualAccountNo(reqNicepay.getVirtualAccountNo())
                            // .virtualAccountName(transactionTemplate.getName())
                            // .inquiryRequestId("")
                            // .totalAmount(amount)
                            // .trxId(transactionTemplate.getTransactionId())
                            // .additionalInfo(additionalInfo)
                            .build();

                    StringBuilder responseCode = new StringBuilder()
                            .append(HttpStatus.OK.value())
                            .append(ctx.getCodeService())
                            .append("00");

                    BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                            responseCode.toString(),
                            "SUCCESS",
                            response);

                    ctx.setBaseResponse(baseResponse);
                });
    }
}
