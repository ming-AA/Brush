package com.fiveletters.brush.domain.integrationAuction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IntegrationAuctionRepository extends PagingAndSortingRepository<IntegrationAuction, Long> {

    List<IntegrationAuction> findAllByAuctionTitleContainingIgnoreCase(String searchValue, Pageable pageable);

    int countByAuctionTitleContainingIgnoreCase(String searchValue);

}
