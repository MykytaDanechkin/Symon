package com.mykyda.symon.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@Getter
@Setter
public class RegistrationDefaultDto {

    String username;

    String password;

    String email;
}