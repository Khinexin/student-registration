package com.demo.studentregistration;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.studentregistration.model.Student;
import com.demo.studentregistration.model.User;
import com.demo.studentregistration.model.UserRole;
import com.demo.studentregistration.service.StudentService;
import com.demo.studentregistration.service.UserService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentRegistrationApplication implements CommandLineRunner {

	private final UserService userService;
	private final StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params) throws Exception {

		if (userService.countAppUser() == 0) {

			// admin
			userService.signup(User.builder().username("admin").password("admin")
					.userRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_ADMIN))).build());

			// client
			userService.signup(User.builder().username("client").password("client")
					.userRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_CLIENT))).build());
		}

		if (studentService.countStudent() == 0) {

			studentService.create(Student.builder().name("Mr A").fathername("A's Father").dob("1/1/1")
					.contactNo("0000000").email("mra@abc.def").address("somewhere").build());

			studentService.create(Student.builder().name("Mr B").fathername("B's Father").dob("1/2/3")
					.contactNo("0011223").email("mrb@abc.def").address("somewhere").build());
		}

	}
}
