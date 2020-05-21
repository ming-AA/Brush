package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.admin.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {

    private Long seq;
    private String adminId;
    private String adminPassword;
    private String name;
    private String authority;
    private String searchValue="";

    public AdminDto(Admin entity) {
        this.seq = entity.getSeq();
        this.adminId = entity.getAdminId();
        this.adminPassword = entity.getAdminPassword();
        this.name = entity.getName();
        this.authority = entity.getAuthority();
    }

    public Admin toEntity(){

        return Admin.builder()
                .adminId(adminId)
                .adminPassword(adminPassword)
                .name(name)
                .authority(authority)
                .build();

    }
}
