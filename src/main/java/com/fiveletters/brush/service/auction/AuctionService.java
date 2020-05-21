package com.fiveletters.brush.service.auction;

import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.web.dto.*;

import java.util.List;

public interface AuctionService {

    public List<AuctionDto> getAuctionResultsList(PageDto pageDto, String search) throws Exception;

    public int getTotalRecordCount(String search) throws Exception;

    public void setAuctionTitle(AuctionDto auctionDto) throws Exception;

    public void setCrawlStatus(AuctionDto auctionDto) throws Exception;

    public void setTransStatus(AuctionDto auctionDto) throws Exception;

    public List<LotDto> getAuctionRotList(LotDto lotDto, PageDto pageDto) throws Exception;

    public int getLotTotalRecordCount(LotDto lotDto) throws Exception;

    public void setLotIntegStatus(LotDto lotDto) throws Exception;

    public LotDetail getLotDetail(String auctionName, String lotUUID) throws Exception;

    public void setLotDetail(LotDetailDto lotDetailDto) throws Exception;
}
