package com.fiveletters.brush.domain.lot;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LotChristiesRepository extends PagingAndSortingRepository<LotChristies, Long> {

    List<LotChristies> findAllByAuctionIdAndLotTitleContaining(Long auctionid, String searchValue, Pageable pageable);

    List<LotChristies> findAllByAuctionIdAndLotArtistContaining(Long auctionid, String searchValue, Pageable pageable);

    int countByAuctionIdAndLotTitleContaining(Long auctionId, String searchValue);

    int countByAuctionIdAndLotArtistContaining(Long auctionId, String searchValue);
}
