package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.auction.Auction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuctionDto{

    private Long id;
    private String auctionName;
    private String landingUrl;
    private int saleYear;
    private String salePeriod;
    private String saleLocation;
    private String online;
    private String title;
    private String crawlStatus;
    private String transStatus;
    private String integStatus;
    private String crawlDateTime;
    private String searchValue="";

    public AuctionDto(Auction entity){
        this.id = entity.getId();
        this.auctionName = entity.getAuctionName();
        this.landingUrl = entity.getLandingUrl();
        this.saleYear = entity.getSaleYear();
        this.salePeriod = entity.getSalePeriod();
        this.saleLocation = entity.getSaleLocation();
        this.online = entity.getOnline();
        this.title = entity.getTitle();
        this.crawlStatus = entity.getCrawlStatus();
        this.transStatus = entity.getTransStatus();
        this.integStatus = entity.getIntegStatus();
        this.crawlDateTime = entity.getCrawlDateTime();
    }

}
