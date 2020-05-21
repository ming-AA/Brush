package com.fiveletters.brush.domain.integrationLot;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IntegrationLotRepository extends PagingAndSortingRepository<IntegrationLot, Long> {

    int countByAuctionIdAndLotTitleContainingIgnoreCase(Long auctionId, String searchValue);

    int countByAuctionIdAndLotArtistNameContainingIgnoreCase(Long auctionId, String searchValue);

    List<IntegrationLot> findAllByAuctionIdAndLotTitleContainingIgnoreCase(Long auctionId, String searchValue, Pageable pageable);

    List<IntegrationLot> findAllByAuctionIdAndLotArtistNameContainingIgnoreCase(Long auctionId, String searchValue, Pageable pageable);
}
