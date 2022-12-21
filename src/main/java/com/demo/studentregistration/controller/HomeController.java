package com.demo.studentregistration.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentregistration.annotation.Logger;
import com.demo.studentregistration.dto.EmailDTO;
import com.demo.studentregistration.dto.StudentDataDTO;
import com.demo.studentregistration.dto.UserResponseDTO;
import com.demo.studentregistration.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "home", description = "Test Server")
@RequiredArgsConstructor
public class HomeController {

	private final MailService mailService;

	@Logger("No AuthTest")
	@GetMapping("/public/test")
	public String testNoAnnotation(HttpServletRequest request) throws InterruptedException {
		return "Test success ... ";
	}

	@GetMapping("/test")
	@ApiOperation(value = "${HomeController.test}", authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public String testAnnotation(HttpServletRequest request) throws InterruptedException {
		return "Test success ... ";
	}

	@Logger("Sent Mail.")
	@PostMapping("/mail")
	@ApiOperation(value = "${HomeController.sendEmail}", authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public String sendEmail(@ApiParam("Sent Mail") @RequestBody EmailDTO emailDto) throws MessagingException, IOException {

//		EmailDTO emailDto = EmailDTO.builder().to("myjavaoceantest@gmail.com").from("mytestmail.aabbcc@gmail.com")
//				.subject("Hello").content("How are you ?").attachName("student.xlsx").build();
//		mailService.sendEmail(emailDto);
		
		mailService.sendEmailWithAttachment(emailDto);

		return "Successfully send mail";

	}
}
