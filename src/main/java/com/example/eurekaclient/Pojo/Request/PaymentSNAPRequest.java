package com.example.eurekaclient.Pojo.Request;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSNAPRequest {
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String trxId;
    private String paymentRequestId;
    private Amount totalAmount;
    private Map<String, Object> additionalInfo;
}
