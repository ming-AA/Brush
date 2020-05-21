package com.fiveletters.brush.domain.integrationLot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PALETTE_LOTS")
public class IntegrationLot {

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

    @Column(name = "LOT_ARTIST_SEQ")
    private String lotArtistId;

    @Column(name = "LOT_ARTIST_NAME")
    private String lotArtistName;

    @Column(name = "LOT_CURRENCY")
    private String lotCurrency;

    @Column(name = "LOT_ESTIMATED_LOW")
    private Long lotEstimatedLow;

    @Column(name = "LOT_ESTIMATED_HIGH")
    private Long lotEstimatedHigh;

    @Column(name = "LOT_RELEASED")
    private Long lotReleased;

    @Column(name = "LOT_HAMMER")
    private Long lotHammer;

    @Column(name = "LOT_SIZE_WIDTH")
    private Float lotSizeWidth;

    @Column(name = "LOT_SIZE_HEIGHT")
    private Float lotSizeHeight;

    @Column(name = "LOT_SIZE_BREADTH")
    private Float lotSizeBreadth;

    @Column(name = "LOT_LINK_URL")
    private String lotLinkUrl;

    @Column(name = "USE_YN")
    private String lotUseYN;

    @Column(name = "INSERT_DT")
    private String insertDateTime;

    public void updateUseYN(String lotUseYN) {
        this.lotUseYN = lotUseYN;
    }


}
