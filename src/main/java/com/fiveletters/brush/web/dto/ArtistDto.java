package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.artist.Artist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ArtistDto {

    private long id;
    private String artistName;
    private String engArtistName;
    private String lowerEngArtistName;
    private String born;
    private String death;
    private Integer weight;
    private String imageURL;
    private String useYN;
    private String insertId;
    private String insertDateTime;
    private String searchValue="";

    public ArtistDto(Artist entity){
        this.id = entity.getId();
        this.artistName = entity.getArtistName();
        this.engArtistName = entity.getEngArtistName();
        this.lowerEngArtistName = entity.getLowerEngArtistName();
        this.born = entity.getBorn();
        this.death = entity.getDeath();
        this.weight = entity.getWeight();
        this.imageURL = entity.getImageURL();
        this.useYN = entity.getUseYN();
        this.insertId = entity.getInsertId();
        this.insertDateTime = entity.getInsertDateTime();
    }

    public Artist toEntity() {
        return Artist.builder()
                .artistName(artistName)
                .engArtistName(engArtistName)
                .lowerEngArtistName(lowerEngArtistName)
                .born(born)
                .death(death)
                .weight(weight)
                .insertDateTime(insertDateTime)
                .build();
    }
}
