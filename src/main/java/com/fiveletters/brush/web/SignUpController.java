package com.fiveletters.brush.web;

import com.fiveletters.brush.service.signUp.SignUpService;
import com.fiveletters.brush.web.dto.PageDto;
import com.fiveletters.brush.web.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST} )
    public String userList(Model model, PageDto pageDto, SignUpDto signUpDto) throws Exception {

        String searchValue = signUpDto.getSearchValue();
        int totalRecordCount = signUpService.getUserTotalRecordCount(searchValue);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<SignUpDto> userList = signUpService.getUserList(pageDto, searchValue);
            model.addAttribute("userList", userList);
        }

        model.addAttribute("searchValue", searchValue);
        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);

        return "signUp";
    }

    @RequestMapping(value = "/userDetail/{userId}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String userDetail(Model model, @PathVariable Long userId) throws Exception {

        SignUpDto userDetail = signUpService.getUserDetail(userId);
        model.addAttribute("userDetail", userDetail);

        return "signUpDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser/{userId}", method = {RequestMethod.GET, RequestMethod.PUT} )
    public void updateArtist(@PathVariable Long userId, @RequestBody SignUpDto signUpDto) throws Exception {

        signUpService.updateUser(userId, signUpDto);
    }

    @ResponseBody
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) throws Exception {

        signUpService.deleteUser(userId);
    }
}
