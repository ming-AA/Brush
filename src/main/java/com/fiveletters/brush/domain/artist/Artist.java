package com.fiveletters.brush.domain.artist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BRUSH_ARTIST")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private long id;

    @Column(name = "ARTIST_NAME")
    private String artistName;

    @Column(name = "ENG_ARTIST_NAME")
    private String engArtistName;

    @Column(name = "LOWER_ENG_ARTIST_NAME")
    private String lowerEngArtistName;

    @Column(name = "BORN")
    private String born;

    @Column(name = "DEATH")
    private String death;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Column(name = "USE_YN")
    private String useYN;

    @Column(name = "INSERT_ID")
    private String insertId;

    @Column(name = "INSERT_DT")
    private String insertDateTime;


    @Builder
    public Artist(String artistName, String engArtistName, String lowerEngArtistName, String born, String death, int weight, String insertDateTime){
        this.artistName = artistName;
        this.engArtistName = engArtistName;
        this.lowerEngArtistName = lowerEngArtistName;
        this.born = born;
        this.death = death;
        this.weight = weight;
        this.useYN = "Y";
        this.insertId = "BRUSH";
        this.insertDateTime = insertDateTime;
    }

    public void update(String artistName, String engArtistName, String born, String death, int weight) {
        this.artistName = artistName;
        this.engArtistName = engArtistName;
        this.born = born;
        this.death = death;
        this.weight = weight;
    }

    public void updateUseYN(String useYN) {
        this.useYN = useYN;
    }
}
