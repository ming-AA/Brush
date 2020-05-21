package com.fiveletters.brush.service.admin;

import com.fiveletters.brush.domain.admin.Admin;
import com.fiveletters.brush.domain.admin.AdminRepository;
import com.fiveletters.brush.domain.admin.Role;
import com.fiveletters.brush.web.dto.AdminDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @NonNull
    private final AdminRepository adminRepository;


    @Transactional
    public int getAdminTotalRecordCount(String searchValue) throws Exception {

        int totalRecordCount = adminRepository.countByNameContainingIgnoreCase(searchValue);

        return totalRecordCount;
    }

    @Transactional
    public List<AdminDto> getAdminList(PageDto pageDto, String searchValue) throws Exception {

        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<AdminDto> adminList = adminRepository
                .findAllByNameContainingIgnoreCase(searchValue, paging)
                .stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());

        return adminList;
    }

    @Transactional
    public void saveAdmin(AdminDto adminDto) throws Exception {

        adminRepository.save(adminDto.toEntity());
    }

    @Transactional
    public void deleteAdmin(Long seq) throws Exception {
        Admin admin = adminRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. id="+seq));

        adminRepository.delete(admin);

    }

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("adminId="+adminId));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(admin.getAuthority().equals(Role.ADMIN.getValue2())) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        else if(admin.getAuthority().equals(Role.MEMBER.getValue2())) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(admin.getAdminId(), admin.getAdminPassword(), grantedAuthorities);
    }


}
