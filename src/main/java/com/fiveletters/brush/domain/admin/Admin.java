package com.fiveletters.brush.domain.admin;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "BRUSH_ADMIN")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO")
    private Long seq;

    @Column(name = "ID")
    private String adminId;

    @Column(name = "PASSWORD")
    private String adminPassword;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHORITY")
    private String authority;

    @Builder
    public Admin(String adminId, String adminPassword, String name, String authority) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.name = name;
        this.authority = authority;
    }
}
