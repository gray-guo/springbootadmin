package com.sbm.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description
 * @Author guoxiaoyong
 * @Date 2023/6/18
 */
@Data
@ToString
public class LoginDTO implements Serializable {

    @JsonProperty("user_name")
    private String userName;

    private String password;

}
