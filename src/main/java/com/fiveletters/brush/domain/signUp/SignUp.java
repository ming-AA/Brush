package com.fiveletters.brush.domain.signUp;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PAINTING_SIGNUP")
public class SignUp {

    @Id
    @Column(name = "SEQ")
    private long userId;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "INSERT_DT")
    private String userInsertDateTime;

    public void update(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
