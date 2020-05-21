package com.fiveletters.brush.service.integrationAuction;

import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.web.dto.*;

import java.util.List;

public interface IntegrationAuctionService {

    public List<IntegrationAuctionDto> getAuctionResultsList(PageDto pageDto, String search) throws Exception;

    public int getTotalRecordCount(String search) throws Exception;

    public Long setAuctionTitle(IntegrationAuctionDto integrationAuctionDto) throws Exception;

    public List<IntegrationLotDto> getAuctionRotList(IntegrationLotDto integrationLot, PageDto pageDto) throws Exception;

    public int getLotTotalRecordCount(IntegrationLotDto integrationLot) throws Exception;

    public void setLotUseYN(Long lotId, String useYN) throws Exception;

    public LotDetail getLotDetail(String lotUUID) throws Exception;

    public void setLotDetail(IntegrationLotDetailDto integrationLotDetailDto) throws Exception;

    public int getLotImageTotalRecordCount(String lotUUID) throws Exception;

    public List<IntegrationLotImageDto> getAuctionLotImage(String lotUUID) throws Exception;

    public void setLotRepImg(IntegrationLotImageDto integrationLotImageDto) throws Exception;

}
