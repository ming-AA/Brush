package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.integrationAuction.IntegrationAuction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IntegrationAuctionDto {

    private Long id;
    private String auctionTypeCode;
    private String auctionType;
    private Long auctionSeq;
    private String auctionTitle;
    private String auctionUrl;
    private String saleDateFrom;
    private String saleDateTo;
    private String saleLocationCode;
    private String saleLocation;
    private String onlineYN;
    private String insertDateTime;
    private String searchValue="";

    public IntegrationAuctionDto(IntegrationAuction entity){
        this.id = entity.getId();
        this.auctionTypeCode = entity.getAuctionTypeCode();
        this.auctionType = entity.getAuctionType();
        this.auctionSeq = entity.getAuctionSeq();
        this.auctionTitle = entity.getAuctionTitle();
        this.auctionUrl = entity.getAuctionUrl();
        this.saleDateFrom = entity.getSaleDateFrom();
        this.saleDateTo = entity.getSaleDateTo();
        this.saleLocationCode = entity.getSaleLocationCode();
        this.saleLocation = entity.getSaleLocation();
        this.onlineYN = entity.getOnlineYN();
        this.insertDateTime = entity.getInsertDateTime();
        this.searchValue = entity.getAuctionTitle();

    }
}
