package com.example.eurekaclient.Pojo.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InquirySNAPResponse {
    private String responseCode;
    private String responseMessage;
}
