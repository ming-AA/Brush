package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.signUp.SignUp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    private long userId;
    private String userEmail;
    private String userName;
    private String userInsertDateTime;
    private String searchValue="";

    public SignUpDto(SignUp entity){
        this.userId = entity.getUserId();
        this.userEmail = entity.getUserEmail();
        this.userName = entity.getUserName();
        this.userInsertDateTime = entity.getUserInsertDateTime();
    }

}
