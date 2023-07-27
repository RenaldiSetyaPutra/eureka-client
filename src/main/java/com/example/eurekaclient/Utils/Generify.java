package com.example.eurekaclient.Utils;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.example.eurekaclient.Exception.ResponseErrorException;
import com.example.eurekaclient.Pojo.Response.BaseResponse;
import com.example.eurekaclient.Pojo.Response.ParentResponse;

import lombok.extern.slf4j.Slf4j;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Slf4j
public class Generify {
    public static ParentResponse successResponse(BaseResponse<?> baseResponse) {
        ParentResponse response = new ParentResponse();

        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(baseResponse.getResponseCode().substring(0, 3)));

        response.setHttpStatus(status);
        response.setBaseResponse(baseResponse);

        return response;
    }

    public static ParentResponse failResponse(Throwable e, String serviceCode) {
        ParentResponse response = new ParentResponse();
        BaseResponse<Void> baseResponse;

        if (e instanceof ResponseErrorException) {
            ResponseErrorException errorException = (ResponseErrorException) e;

            String strReplace = errorException.getCode();
            if( errorException.getCode().contains("any")){
                 strReplace = errorException.getCode().replace("any", serviceCode);
            }

            baseResponse = new BaseResponse<>(
                    strReplace ,
                    errorException.getMessage()
            );

            response.setHttpStatus(errorException.getHttpCode());
            response.setBaseResponse(baseResponse);

            return response;
        }

        baseResponse = new BaseResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value()
                        + serviceCode
                        + "01",
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );

        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setBaseResponse(baseResponse);

        return response;
    }

    public static void printWhenError(Throwable throwable, String serviceCode) {
        Match(throwable).of(
                Case($(instanceOf(ResponseErrorException.class)), (bes) -> {
                    log.error("Service exception, service: {}, code: {}, message: {}", serviceCode, bes.getCode(), bes.getMessage());
                    return bes;
                }),
                Case($(), v -> {
                    log.error(ExceptionUtils.getFullStackTrace(v));
                    return v;
                })
        );
    }
}
