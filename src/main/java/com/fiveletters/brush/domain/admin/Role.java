package com.fiveletters.brush.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    MEMBER("ROLE_MEMBER", "member"),
    ADMIN("ROLE_ADMIN", "admin");

    private String value; // 권한 생성 시 사용
    private String value2; // 권한 확인 시 사용
}
