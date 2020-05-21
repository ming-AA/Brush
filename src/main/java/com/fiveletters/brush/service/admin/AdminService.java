package com.fiveletters.brush.service.admin;

import com.fiveletters.brush.web.dto.AdminDto;
import com.fiveletters.brush.web.dto.PageDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {

    public int getAdminTotalRecordCount(String searchValue) throws Exception;

    public List<AdminDto> getAdminList(PageDto pageDto, String searchValue) throws Exception;

    public void saveAdmin(AdminDto adminDto) throws Exception;

    public void deleteAdmin(Long seq) throws Exception;
}
