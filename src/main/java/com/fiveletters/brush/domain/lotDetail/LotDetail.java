package com.fiveletters.brush.domain.lotDetail;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = " ")
public class LotDetail {

    // Partition key
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

    @DynamoDBHashKey(attributeName = "LotUUID")
    public String getLotUUID() {
        return lotUUID;
    }

    @DynamoDBAttribute(attributeName = "LotNumber")
    public String getLotNumber() {
        return lotNumber;
    }

    @DynamoDBAttribute(attributeName = "LotType")
    public String getLotType() {
        return lotType;
    }

    @DynamoDBAttribute(attributeName = "LotTitle")
    public String getLotTitle() {
        return lotTitle;
    }

    @DynamoDBAttribute(attributeName = "LotArtist")
    public String getLotArtist() {
        return lotArtist;
    }

    @DynamoDBAttribute(attributeName = "LotArtistBirth")
    public String getLotArtistBirth() {
        return lotArtistBirth;
    }

    @DynamoDBAttribute(attributeName = "LotArtistDeath")
    public String getLotArtistDeath() {
        return lotArtistDeath;
    }

    @DynamoDBAttribute(attributeName = "LotEstimatedHighPrice")
    public String getLotEstimatedHighPrice() {
        return lotEstimatedHighPrice;
    }

    @DynamoDBAttribute(attributeName = "LotEstimatedLowPrice")
    public String getLotEstimatedLowPrice() { return lotEstimatedLowPrice; }

    @DynamoDBAttribute(attributeName = "LotPriceRealised")
    public String getLotPriceRealised() {
        return lotPriceRealised;
    }

    @DynamoDBAttribute(attributeName = "LotHammerPrice")
    public String getLotHammerPrice() {
        return lotHammerPrice;
    }

    @DynamoDBAttribute(attributeName = "LotCurrency")
    public String getLotCurrency() {
        return lotCurrency;
    }

    @DynamoDBAttribute(attributeName = "LotProvenance")
    public String getLotProvenance() {
        return lotProvenance;
    }

    @DynamoDBAttribute(attributeName = "LotLiterature")
    public String getLotLiterature() {
        return lotLiterature;
    }

    @DynamoDBAttribute(attributeName = "LotExhibited")
    public String getLotExhibited() {
        return lotExhibited;
    }

    @DynamoDBAttribute(attributeName = "LotDescription")
    public String getLotDescription() {
        return lotDescription;
    }

    @DynamoDBAttribute(attributeName = "LotLink")
    public String getLotLink() {
        return lotLink;
    }

    @DynamoDBAttribute(attributeName = "LotEssay")
    public String getLotEssay() {
        return lotEssay;
    }

    public List getLotImageList() {
        return lotImageList;
    }

    public List getLotImageS3List() {
        return lotImageS3List;
    }

    public List getLotSizeList() {
        return lotSizeList;
    }

    @DynamoDBAttribute(attributeName = "SaleDate")
    public String getSaleDate() {
        return saleDate;
    }

    @DynamoDBAttribute(attributeName = "SaleLocation")
    public String getSaleLocation() {
        return saleLocation;
    }

    @DynamoDBAttribute(attributeName = "SaleTitle")
    public String getSaleTitle() {
        return saleTitle;
    }

}
