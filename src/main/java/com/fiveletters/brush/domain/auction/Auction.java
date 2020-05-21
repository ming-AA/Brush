package com.fiveletters.brush.domain.auction;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Auction {

    @Id
    @Column(name = "SEQ")
    private Long id;

    @Column(name = "AUCTION_NAME")
    private String auctionName;

    @Column(name = "LANDING_URL")
    private String landingUrl;

    @Column(name = "SALE_YEAR")
    private int saleYear;

    @Column(name = "SALE_PERIOD")
    private String salePeriod;

    @Column(name = "SALE_LOCATION")
    private String saleLocation;

    @Column(name = "ONLINE_YN")
    private String online;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CRAWL_YN")
    private String crawlStatus;

    @Column(name = "TRANS_YN")
    private String transStatus;

    @Column(name = "INTEG_YN")
    private String integStatus;

    @Column(name = "INSERT_DT")
    private String crawlDateTime;

    public void update(String crawlStatus){
        this.crawlStatus = crawlStatus;
    }
}
