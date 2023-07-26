package com.example.eurekaclient.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Request.PaymentSNAPRequest;
import com.example.eurekaclient.Pojo.Response.ParentResponse;
import com.example.eurekaclient.Validator.ValidatorTest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class Controller {
    
    private Environment environment;
    private final ValidatorTest validator;

    String serviceCode;


    @GetMapping("/test")
    public String status(){
        return "Masuk di port " + environment.getProperty("local.server.port");
    }

    @PostMapping("/v1.0/transfer-va/inquiry")
    public ResponseEntity<?> inquiry(@RequestBody InquirySNAPRequest request ){
        this.serviceCode = "24";
        ParentResponse result = validator.isValid(request, serviceCode);

        return new ResponseEntity<>(result.getInquirySNAPResponse(), result.getHttpStatus());
    }

    @PostMapping("/v1.0/transfer-va/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentSNAPRequest request ){
        this.serviceCode = "25";
        ParentResponse result = validator.isValid(request, serviceCode);

        return new ResponseEntity<>(result.getInquirySNAPResponse(), result.getHttpStatus());
    }
}
