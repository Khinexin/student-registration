package com.demo.studentregistration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentregistration.dto.StudentDataDTO;
import com.demo.studentregistration.dto.StudentResponseDTO;
import com.demo.studentregistration.dto.UserResponseDTO;
import com.demo.studentregistration.model.Student;
import com.demo.studentregistration.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@Api(tags = "students", description = "Endpoints for Creating, Retrieving, Updating and Deleting of Student.")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
public class StudentController {

	private final StudentService studentService;
	private final ModelMapper modelMapper;

	@PostMapping("/create")
	@ApiOperation(value = "${StudentController.create}")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied") })
	public String create(@ApiParam("Add Student") @RequestBody StudentDataDTO student) {
		studentService.create(modelMapper.map(student, Student.class));
		return "Successfully added new student | name = " + student.getName();
	}

	@PostMapping("/update/{id}")
	@ApiOperation(value = "${StudentController.update}")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied") })
	public String update(@ApiParam("Edit Student") @RequestBody StudentDataDTO student,
			@ApiParam("Edit Student By Id") @PathVariable Integer id) {
		Student tempStudent = modelMapper.map(student, Student.class);
		tempStudent.setId(id);
		studentService.update(tempStudent);
		return "Successfully updated student | id = " + id;
	}

	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "${StudentController.delete}", authorizations = { @Authorization(value = "apiKey") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Student doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public String delete(@ApiParam("id") @PathVariable Integer id) {
		studentService.delete(id);
		return "Successfully deleted student | id = " + id;
	}

	@GetMapping(value = "{id}")
	@ApiOperation(value = "${StudentController.findById}", response = StudentResponseDTO.class, authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public StudentResponseDTO findById(@ApiParam("id") @PathVariable Integer id) {
		return modelMapper.map(studentService.findById(id), StudentResponseDTO.class);
	}

	@GetMapping
	@ApiOperation(value = "${StudentController.findAll}", response = UserResponseDTO.class, authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public List<Student> findAll(HttpServletRequest req) {
		return studentService.findAll();
	}

}
