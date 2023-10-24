package com.example.eurekaclient.Controller;

import com.example.eurekaclient.Helper.Helper;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
import com.example.eurekaclient.Pojo.Response.BaseResponse;
import com.example.eurekaclient.Pojo.Response.InquirySNAPResponse;
import com.example.eurekaclient.Pojo.Response.ParentResponse;
import com.example.eurekaclient.Service.Component.NicepayComponent;
import com.example.eurekaclient.Service.CoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class InquiryTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CoreService coreService;

    @Mock
    private Helper helper;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private NicepayComponent nicepayComponent;

    @InjectMocks
    private Controller controller;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void inquirySucces() throws Exception{
        InquirySNAPRequest snapRequest = InquiryRequestTest.setRequestInquiryPositive();
        ParentResponse snapResponse = buildParentResponsePositive();

        given(coreService.inquiry(any(), anyString())).willReturn(snapResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/client/v1.0/transfer-va/inquiry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(snapRequest)))
                .andExpect(status().isOk())
                .andDo(
                        mvcResult ->
                                Assertions.assertThat(InquiryRequestTest.setRequestInquiryPositive() == null).isFalse()
                );
    }

    @Test
    public void inquiryBadRequest() throws Exception {
        InquirySNAPRequest snapRequest = InquiryRequestTest.setRequestInquiryMandatoryAdditionalInfo();
        ParentResponse snapResponse = buildParentResponseNegative();

        given(coreService.inquiry(any(), anyString())).willReturn(snapResponse);

        String content = mapper.writeValueAsString(snapRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/client/v1.0/transfer-va/inquiry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(snapRequest)))
                .andExpect(status().isBadRequest());
//                .andDo(mvcResult ->
//                                Assertions.assertThat(snapRequest.getAdditionalInfo().isEmpty()).isTrue()
//                );
    }

    static ParentResponse buildParentResponsePositive() {
        ParentResponse snapResponse = new ParentResponse();
        var response = InquirySNAPResponse.builder()
                .responseCode("")
                .responseMessage("")
                .build();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.OK.value())
                .append("24")
                .append("00");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "SUCCESS",
                response);

        snapResponse.setHttpStatus(HttpStatus.OK);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }

    static ParentResponse buildParentResponseNegative() {
        ParentResponse snapResponse = new ParentResponse();
        StringBuilder responseCode = new StringBuilder()
                .append(HttpStatus.BAD_REQUEST.value())
                .append("24")
                .append("00");
        BaseResponse<InquirySNAPResponse> baseResponse = new BaseResponse<>(
                responseCode.toString(),
                "Invalid Mandatory");

        snapResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        snapResponse.setBaseResponse(baseResponse);

        return snapResponse;
    }

}