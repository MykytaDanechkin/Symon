package com.mykyda.symon.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@Value
@FieldNameConstants
@Getter
@Setter
public class UserEditDTO {

    Long id;

    String username;

    String oldAvatar;

    MultipartFile avatar;
}
