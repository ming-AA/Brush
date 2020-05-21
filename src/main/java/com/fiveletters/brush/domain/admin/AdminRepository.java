package com.fiveletters.brush.domain.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {

    int countByNameContainingIgnoreCase(String searchValue);

    List<Admin> findAllByNameContainingIgnoreCase(String searchValue, Pageable pageable);

    Optional<Admin> findByAdminId(String adminId);
}
