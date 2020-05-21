package com.fiveletters.brush.service.artist;

import com.fiveletters.brush.domain.artist.Artist;
import com.fiveletters.brush.domain.artist.ArtistRepository;
import com.fiveletters.brush.web.dto.ArtistDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArtistServiceImpl implements ArtistService {

    @NonNull
    private final ArtistRepository artistRepository;

    @Transactional
    public int getArtistTotalRecordCount(String searchValue) throws Exception {

        int totalRecordCount = artistRepository.countByArtistNameContainingIgnoreCase(searchValue);

        return totalRecordCount;
    }

    @Transactional
    public List<ArtistDto> getArtistList(PageDto pageDto, String searchValue) throws Exception {

        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<ArtistDto> artistList = artistRepository
                .findAllByArtistNameContainingIgnoreCase(searchValue, paging)
                .stream()
                .map(ArtistDto::new)
                .collect(Collectors.toList());

        return artistList;
    }

    @Transactional(readOnly = true)
    public ArtistDto getArtistDetail(Long artistId) throws Exception {
        Artist entity = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 없습니다. id="+artistId));

        return new ArtistDto(entity);
    }

    @Transactional
    public void saveArtist(ArtistDto artistDto) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        artistDto.setInsertDateTime(simpleDateFormat.format(new Date()));
        artistRepository.save(artistDto.toEntity());
    }

    @Transactional
    public void updateArtist(Long artistId, ArtistDto artistDto) throws Exception {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 없습니다. id="+artistId));

        artist.update(artistDto.getArtistName()
                , artistDto.getEngArtistName()
                , artistDto.getBorn()
                , artistDto.getDeath()
                , artistDto.getWeight());
    }

    @Transactional
    public void updateArtistUseYN(ArtistDto artistDto) throws Exception {
        Artist artist = artistRepository.findById(artistDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 없습니다. id="+artistDto.getId()));

        artist.updateUseYN(artistDto.getUseYN());
    }

    @Transactional
    public void deleteArtist(Long artistId) throws Exception {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 없습니다. id="+artistId));

        artistRepository.delete(artist);

    }


}
