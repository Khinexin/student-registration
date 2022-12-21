package com.demo.studentregistration.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponseDTO {
	
	@ApiModelProperty(position = 0)
	private String id;
	@ApiModelProperty(position = 1)
	private String name;
	@ApiModelProperty(position = 2)
	private String fathername;
	@ApiModelProperty(position = 3)
	private String dob;
	@ApiModelProperty(position = 4)
	private String contactNo;
	@ApiModelProperty(position = 5)
	private String email;
	@ApiModelProperty(position = 6)
	private String address;
}
