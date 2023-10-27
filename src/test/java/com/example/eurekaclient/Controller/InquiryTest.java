package com.example.eurekaclient.Controller;

import com.example.eurekaclient.Helper.Helper;
import com.example.eurekaclient.Pojo.Request.InquirySNAPRequest;
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
        InquirySNAPRequest snapRequest = SetRequestInquiryTest.setRequestInquiryPositive();
        ParentResponse snapResponse = SetResponseInquiryTest.buildParentResponsePositive();

        given(coreService.inquiry(any(), anyString(), any())).willReturn(snapResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/client/v1.0/transfer-va/inquiry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(snapRequest)))
                .andExpect(status().isOk())
                .andDo(
                        mvcResult ->
                                Assertions.assertThat(SetRequestInquiryTest.setRequestInquiryPositive() == null).isFalse()
                );
    }

    @Test
    public void inquiryBadRequestInvalidMandatoryAddiitonalInfo() throws Exception {
        InquirySNAPRequest snapRequest = SetRequestInquiryTest.setRequestInquiryMandatoryAdditionalInfo();
        ParentResponse snapResponse = SetResponseInquiryTest.buildParentResponseNegative();

        given(coreService.inquiry(any(), anyString(), any())).willReturn(snapResponse);

        String content = mapper.writeValueAsString(snapRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/client/v1.0/transfer-va/inquiry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(snapRequest)))
                .andExpect(status().isBadRequest())
                .andDo(mvcResult ->
                                Assertions.assertThat(snapRequest.getAdditionalInfo().isEmpty()).isTrue()
                );
    }
}