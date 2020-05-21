package com.fiveletters.brush.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IntegrationLotDetailDto {

    private String lotUUID;
    private String lotNumber;
    private String lotType;
    private String lotTitle;
    private String lotArtist;
    private String lotArtistBirth;
    private String lotArtistDeath;
    private String lotEstimatedHighPrice;
    private String lotEstimatedLowPrice;
    private String lotPriceRealised;
    private String lotHammerPrice;
    private String lotCurrency;
    private String lotProvenance;
    private String lotLiterature;
    private String lotExhibited;
    private String lotDescription;
    private String lotLink;
    private String lotEssay;
    private List lotSizeList;
    private List lotImageList;
    private List lotImageS3List;
    private String saleDate;
    private String saleLocation;
    private String saleTitle;
}
