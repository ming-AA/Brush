package com.fiveletters.brush.service.signUp;

import com.fiveletters.brush.domain.signUp.SignUp;
import com.fiveletters.brush.domain.signUp.SignUpRepository;
import com.fiveletters.brush.web.dto.PageDto;
import com.fiveletters.brush.web.dto.SignUpDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    @NonNull
    private final SignUpRepository signUpRepository;

    @Transactional(readOnly = true)
    public int getUserTotalRecordCount(String searchValue) throws Exception {

        int totalRecordCount = signUpRepository.countByUserNameContainingIgnoreCase(searchValue);

        return totalRecordCount;
    }

    @Transactional(readOnly = true)
    public List<SignUpDto> getUserList(PageDto pageDto, String searchValue) throws Exception {

        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<SignUpDto> userList = signUpRepository
                .findAllByUserNameContainingIgnoreCase(searchValue, paging)
                .stream()
                .map(SignUpDto::new)
                .collect(Collectors.toList());

        return userList;
    }

    @Transactional(readOnly = true)
    public SignUpDto getUserDetail(Long userId) throws Exception {

        SignUp entity = signUpRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+userId));

        return new SignUpDto(entity);
    }

    @Transactional
    public void updateUser(Long userId, SignUpDto signUpDto) throws Exception {
        SignUp user = signUpRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+userId));

        user.update(signUpDto.getUserName()
                , signUpDto.getUserEmail());
    }

    @Transactional
    public void deleteUser(Long userId) throws Exception {
        SignUp user = signUpRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+userId));

        signUpRepository.delete(user);
    }

}
