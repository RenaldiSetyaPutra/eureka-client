package com.example.eurekaclient.Pojo.Response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentResponse {
    private HttpStatus httpStatus;
    private InquirySNAPResponse inquirySNAPResponse;
}
