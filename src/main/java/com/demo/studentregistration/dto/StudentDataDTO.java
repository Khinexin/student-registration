package com.demo.studentregistration.dto;

import java.util.List;

import com.demo.studentregistration.model.UserRole;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDataDTO {
	
	@ApiModelProperty(position = 0)
	private String name;
	@ApiModelProperty(position = 1)
	private String fathername;
	@ApiModelProperty(position = 2)
	private String dob;
	@ApiModelProperty(position = 3)
	private String contactNo;
	@ApiModelProperty(position = 4)
	private String email;
	@ApiModelProperty(position = 5)
	private String address;
	
}
