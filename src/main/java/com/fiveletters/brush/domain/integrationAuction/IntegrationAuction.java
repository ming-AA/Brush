package com.fiveletters.brush.domain.integrationAuction;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name="PALETTE_AUCTION_RESULTS")
public class IntegrationAuction {

    @Id
    @Column(name = "SEQ")
    private Long id;

    @Column(name = "AUCTION_TYPE_CODE")
    private String auctionTypeCode;

    @Column(name = "AUCTION_TYPE")
    private String auctionType;

    @Column(name = "AUCTION_SEQ")
    private Long auctionSeq;

    @Column(name = "AUCTION_TITLE")
    private String auctionTitle;

    @Column(name = "AUCTION_URL")
    private String auctionUrl;

    @Column(name = "SALE_YMD_FROM")
    private String saleDateFrom;

    @Column(name = "SALE_YMD_TO")
    private String saleDateTo;

    @Column(name = "SALE_LOCATION_CODE")
    private String saleLocationCode;

    @Column(name = "SALE_LOCATION")
    private String saleLocation;

    @Column(name = "ONLINE_YN")
    private String onlineYN;

    @Column(name = "INSERT_DT")
    private String insertDateTime;

    public void update(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }
}
