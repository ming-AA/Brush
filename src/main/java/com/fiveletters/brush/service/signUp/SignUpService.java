package com.fiveletters.brush.service.signUp;

import com.fiveletters.brush.web.dto.PageDto;
import com.fiveletters.brush.web.dto.SignUpDto;

import java.util.List;

public interface SignUpService {

    public int getUserTotalRecordCount(String searchValue) throws Exception;

    public List<SignUpDto> getUserList(PageDto pageDto, String searchValue) throws Exception;

    public SignUpDto getUserDetail(Long userId) throws Exception;

    public void updateUser(Long userId, SignUpDto signUpDto) throws Exception;

    public void deleteUser(Long userId) throws Exception;
}
