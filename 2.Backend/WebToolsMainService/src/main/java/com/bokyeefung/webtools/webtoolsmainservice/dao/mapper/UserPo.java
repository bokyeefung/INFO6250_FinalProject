package com.bokyeefung.webtools.webtoolsmainservice.dao.mapper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserPo {
    private String uuid;
    private String groupId;
    private String name;
    private String passwd;
    private String changePasswdTime;
    private String email;
    private String phone;
    private String address;
    private String entity;
    private String role;
}
