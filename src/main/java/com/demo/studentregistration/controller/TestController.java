package com.demo.studentregistration.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentregistration.annotation.Logger;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "test", description = "Test Server")
@RequiredArgsConstructor
public class TestController {
	
	@Logger("Test")
	@GetMapping("/test")
	public String testAnnotation(HttpServletRequest request) throws InterruptedException {
		return "Test success ... ";
	}
}
