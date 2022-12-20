package com.demo.studentregistration.dto;

import java.util.List;

import com.demo.studentregistration.model.UserRole;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;
  @ApiModelProperty(position = 1)
  private String username;
  @ApiModelProperty(position = 2)
  List<UserRole> userRoles;

}
