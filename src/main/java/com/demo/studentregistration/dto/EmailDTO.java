package com.demo.studentregistration.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {

	@ApiModelProperty(position = 0)
	private String to;
	@ApiModelProperty(position = 1)
	private String from;
	@ApiModelProperty(position = 2)
	private String subject;
	@ApiModelProperty(position = 3)
	private String content;

//	private String attachment;

	@ApiModelProperty(position = 4)
	private String attachName;

}
