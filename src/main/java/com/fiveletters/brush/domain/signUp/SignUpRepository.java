package com.fiveletters.brush.domain.signUp;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SignUpRepository extends PagingAndSortingRepository<SignUp, Long> {

    int countByUserNameContainingIgnoreCase(String searchValue);

    List<SignUp> findAllByUserNameContainingIgnoreCase(String searchValue, Pageable pageable);

}
