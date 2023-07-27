// package com.example.eurekaclient.Validator;

// import java.io.File;
// import java.io.FileInputStream;
// import java.util.Set;

// import javax.validation.Configuration;
// import javax.validation.ConstraintViolation;
// import javax.validation.Validation;
// import javax.validation.Validator;
// import javax.validation.ValidatorFactory;

// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Component;

// import com.example.eurekaclient.Pojo.Response.InquirySNAPResponse;
// import com.example.eurekaclient.Pojo.Response.ParentResponse;

// @Component
// public class ValidatorTest {

//    	public static ParentResponse isValid(Object request, String serviceCode) {
// 		try {
// 			ParentResponse response = new ParentResponse();
// 			InquirySNAPResponse inquirySNAPResponse = new InquirySNAPResponse();

//             Configuration<?> config = Validation.byDefaultProvider().configure();
// 			FileInputStream in = new FileInputStream(new File("C:/Users/Dev Vendor 5/Documents/Eureka/eureka-client/src/main/resources/VirtualAccountSNAPValidation.xml"));
// 			// FileInputStream in = new FileInputStream(new File("D:/Documents/Data Kantor/Latihan/Eureka/eureka-client/src/main/resources/VirtualAccountSNAPValidation.xml"));
// 			config.addMapping(in);
//             ValidatorFactory factory = config.buildValidatorFactory();
// 			Validator validator = factory.getValidator();
// 			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
// 	        // constraintViolations = validator.validate(request,InquirySNAPInterface.class);
			
// 			inquirySNAPResponse = InquirySNAPResponse.builder()
// 						.responseCode("200" + serviceCode + "00")
// 						.responseMessage("Success")
// 						.build();
					
// 					response = ParentResponse.builder()
// 						.httpStatus(HttpStatus.OK)
// 						.build();

// 			//printing the results
// 			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
//                 if(!constraintViolation.getMessage().isEmpty()){
//                     inquirySNAPResponse = InquirySNAPResponse.builder()
// 						.responseCode("400" + serviceCode + "00")
// 						.responseMessage(constraintViolation.getMessage() + " " + constraintViolation.getPropertyPath())
// 						.build();
					
// 					response = ParentResponse.builder()
// 						.httpStatus(HttpStatus.BAD_REQUEST)
// 						.build();
					
// 					// throw new ResponseErrorException(
//                     //     "400any00", 
//                     //     constraintViolation.getMessage() + " " + constraintViolation.getPropertyPath(), 
//                     //     HttpStatus.BAD_REQUEST
// 					// );
//                 }
// 			}

// 			response = ParentResponse.builder()
// 				.httpStatus(response.getHttpStatus())
// 				.inquirySNAPResponse(inquirySNAPResponse)
// 				.build();

//             return response;
//         } catch (Exception e){
//             return ParentResponse.builder()
// 				.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
// 				.inquirySNAPResponse(null)
// 				.build();
//         }
// 	}
// }
