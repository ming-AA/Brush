package com.fiveletters.brush.web;

import com.fiveletters.brush.service.admin.AdminService;
import com.fiveletters.brush.web.dto.AdminDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST} )
    public String admin(Model model, PageDto pageDto, AdminDto adminDto) throws Exception {

        String searchValue = adminDto.getSearchValue();
        int totalRecordCount = adminService.getAdminTotalRecordCount(searchValue);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<AdminDto> adminList = adminService.getAdminList(pageDto, searchValue);
            model.addAttribute("adminList", adminList);
        }

        model.addAttribute("searchValue", searchValue);
        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);

        return "admin";
    }

    @GetMapping("/adminCreate")
    public String adminCreate() {

        return "adminCreate";
    }

    @ResponseBody
    @PostMapping("/saveAdmin")
    public void saveAdmin(AdminDto adminDto) throws Exception {
        adminDto.setAdminPassword(passwordEncoder.encode(adminDto.getAdminPassword()));
        adminService.saveAdmin(adminDto);
    }

    @ResponseBody
    @DeleteMapping("/deleteAdmin/{seq}")
    public void deleteAdmin(@PathVariable Long seq) throws Exception {

        adminService.deleteAdmin(seq);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() {
        // 향후 로그인 기능과 함께 로그인 화면으로 이동 처리한다.
        return "redirect:/adminLogin";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {

        return "accessDenied";
    }

    @GetMapping("/adminLogin")
    public String adminLogin() {

        return "adminLogin";
    }

}
