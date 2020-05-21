package com.fiveletters.brush.domain.feedback;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FeedbackRepository extends PagingAndSortingRepository<Feedback, Long> {

    int countByNameContainingIgnoreCase(String searchValue);

    int countByEmailContainingIgnoreCase(String searchValue);

    List<Feedback> findAllByNameContainingIgnoreCase(String searchValue, Pageable pageable);

    List<Feedback> findAllByEmailContainingIgnoreCase(String searchValue, Pageable pageable);
}
