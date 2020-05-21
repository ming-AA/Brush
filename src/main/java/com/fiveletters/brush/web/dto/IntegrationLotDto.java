package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.integrationLot.IntegrationLot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IntegrationLotDto {

    private Long lotId;
    private Long auctionId;
    private String auctionTitle;
    private String lotUuid;
    private String lotNumber;
    private String lotTitle;
    private String lotArtistId;
    private String lotArtistName;
    private String lotCurrency;
    private Long lotEstimatedLow;
    private Long lotEstimatedHigh;
    private Long lotReleased;
    private Long lotHammer;
    private Float lotSizeWidth;
    private Float lotSizeHeight;
    private Float lotSizeBreadth;
    private String lotLinkUrl;
    private String lotUseYN;
    private String insertDateTime;
    private String searchKey="Lot Title";
    private String searchValue="";

    public IntegrationLotDto(IntegrationLot entity){
        this.lotId = entity.getLotId();
        this.auctionId = entity.getAuctionId();
        this.lotUuid = entity.getLotUuid();
        this.lotNumber = entity.getLotNumber();
        this.lotTitle = entity.getLotTitle();
        this.lotArtistId = entity.getLotArtistId();
        this.lotArtistName = entity.getLotArtistName();
        this.lotCurrency = entity.getLotCurrency();
        this.lotEstimatedLow = entity.getLotEstimatedLow();
        this.lotEstimatedHigh = entity.getLotEstimatedHigh();
        this.lotReleased = entity.getLotReleased();
        this.lotHammer = entity.getLotHammer();
        this.lotSizeWidth = entity.getLotSizeWidth();
        this.lotSizeHeight = entity.getLotSizeHeight();
        this.lotSizeBreadth = entity.getLotSizeBreadth();
        this.lotUseYN = entity.getLotUseYN();
        this.lotLinkUrl = entity.getLotLinkUrl();
        this.insertDateTime = entity.getInsertDateTime();
    }

    public IntegrationLotDto(Long auctionId, String auctionTitle, String searchKey, String searchValue) {
        this.auctionId = auctionId;
        this.auctionTitle = auctionTitle;
        this.searchKey = searchKey;
        this.searchValue = searchValue;
    }
}
