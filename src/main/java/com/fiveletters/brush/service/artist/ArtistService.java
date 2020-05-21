package com.fiveletters.brush.service.artist;

import com.fiveletters.brush.web.dto.ArtistDto;
import com.fiveletters.brush.web.dto.PageDto;

import java.util.List;

public interface ArtistService {

    public int getArtistTotalRecordCount(String search) throws Exception;

    public List<ArtistDto> getArtistList(PageDto pageDto, String searchValue) throws Exception;

    public ArtistDto getArtistDetail(Long artistId) throws Exception;

    public void saveArtist(ArtistDto artistDto) throws Exception;

    public void updateArtist(Long artistId, ArtistDto artistDto) throws Exception;

    public void updateArtistUseYN(ArtistDto artistDto) throws Exception;

    public void deleteArtist(Long artistId) throws Exception;

}

