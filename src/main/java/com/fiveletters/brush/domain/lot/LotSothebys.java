package com.fiveletters.brush.domain.lot;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name="SOTHEBYS_LOTS")
public class LotSothebys {

    @Id
    @Column(name = "SEQ")
    private Long lotId;

    @Column(name = "AUCTION_SEQ")
    private Long auctionId;

    @Column(name = "LOT_UUID")
    private String lotUuid;

    @Column(name = "LOT_NUMBER")
    private String lotNumber;

    @Column(name = "LOT_TITLE")
    private String lotTitle;

    @Column(name = "LOT_ARTIST")
    private String lotArtist;

    @Column(name = "LOT_ESTIMATED_LOW")
    private int lotEstimatedLow;

    @Column(name = "LOT_ESTIMATED_HIGH")
    private int lotEstimatedHigh;

    @Column(name = "LOT_RELEASED")
    private int lotReleased;

    @Column(name = "LOT_CURRENCY")
    private String lotCurrency;

    @Column(name = "LOT_SIZE_WIDTH")
    private String lotSizeWidth;

    @Column(name = "LOT_SIZE_HEIGHT")
    private String lotSizeHeight;

    @Column(name = "LOT_SIZE_BREADTH")
    private String lotSizeBreadth;

    @Column(name = "LOT_LINK")
    private String lotLink;

    @Column(name = "INTEG_YN")
    private String integStatus;

    @Column(name = "CRAWL_YN")
    private String crawlStatus;

    @Column(name = "CRAWL_DT")
    private String crawlDateTime;

    public void updateIntegStatus(String integStatus) {
        this.integStatus = integStatus;
    }
}
