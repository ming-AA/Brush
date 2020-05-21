package com.fiveletters.brush.domain.artist;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {

    int countByArtistNameContainingIgnoreCase(String searchValue);

    List<Artist> findAllByArtistNameContainingIgnoreCase(String searchValue, Pageable pageable);


}
