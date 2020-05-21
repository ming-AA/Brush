package com.fiveletters.brush.domain.exchangeRate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExchangeRateRepository extends PagingAndSortingRepository<ExchangeRate, String> {

    int countByYearMonthContainingIgnoreCase(String searchValue);

    List<ExchangeRate> findAllByYearMonthContainingIgnoreCase(String searchValue, Pageable pageable);
}
