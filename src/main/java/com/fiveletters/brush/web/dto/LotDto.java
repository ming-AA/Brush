package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.lot.LotChristies;
import com.fiveletters.brush.domain.lot.LotSothebys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LotDto {

    private Long lotId;
    private Long auctionId;
    private String auctionName;
    private String auctionTitle;
    private String lotUuid;
    private String lotNumber;
    private String lotTitle;
    private String lotArtist;
    private int lotEstimatedLow;
    private int lotEstimatedHigh;
    private int lotReleased;
    private String lotCurrency;
    private String lotSizeWidth;
    private String lotSizeHeight;
    private String lotSizeBreadth;
    private String lotLink;
    private String integStatus;
    private String crawlStatus;
    private String crawlDateTime;
    private String searchKey="Lot Title";
    private String searchValue="";


    public LotDto(LotChristies entity){
        this.lotId = entity.getLotId();
        this.auctionId = entity.getAuctionId();
        this.lotUuid = entity.getLotUuid();
        this.lotNumber = entity.getLotNumber();
        this.lotTitle = entity.getLotTitle();
        this.lotArtist = entity.getLotArtist();
        this.lotEstimatedLow = entity.getLotEstimatedLow();
        this.lotEstimatedHigh = entity.getLotEstimatedHigh();
        this.lotReleased = entity.getLotEstimatedHigh();
        this.lotCurrency = entity.getLotCurrency();
        this.lotSizeWidth = entity.getLotSizeWidth();
        this.lotSizeHeight = entity.getLotSizeHeight();
        this.lotSizeBreadth = entity.getLotSizeBreadth();
        this.lotLink = entity.getLotLink();
        this.integStatus = entity.getIntegStatus();
        this.crawlStatus = entity.getCrawlStatus();
        this.crawlDateTime = entity.getCrawlDateTime();
    }

    public LotDto(LotSothebys entity){
        this.lotId = entity.getLotId();
        this.auctionId = entity.getAuctionId();
        this.lotUuid = entity.getLotUuid();
        this.lotNumber = entity.getLotNumber();
        this.lotTitle = entity.getLotTitle();
        this.lotArtist = entity.getLotArtist();
        this.lotEstimatedLow = entity.getLotEstimatedLow();
        this.lotEstimatedHigh = entity.getLotEstimatedHigh();
        this.lotReleased = entity.getLotEstimatedHigh();
        this.lotCurrency = entity.getLotCurrency();
        this.lotSizeWidth = entity.getLotSizeWidth();
        this.lotSizeHeight = entity.getLotSizeHeight();
        this.lotSizeBreadth = entity.getLotSizeBreadth();
        this.lotLink = entity.getLotLink();
        this.integStatus = entity.getIntegStatus();
        this.crawlStatus = entity.getCrawlStatus();
        this.crawlDateTime = entity.getCrawlDateTime();
    }

    public LotDto(Long auctionId, String auctionName, String auctionTitle, String searchKey, String searchValue) {
        this.auctionId = auctionId;
        this.auctionName = auctionName;
        this.auctionTitle = auctionTitle;
        this.searchKey = searchKey;
        this.searchValue = searchValue;
    }
}
