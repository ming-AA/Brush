package com.fiveletters.brush.web;

import com.fiveletters.brush.service.integrationAuction.IntegrationAuctionService;
import com.fiveletters.brush.web.dto.IntegrationAuctionDto;
import com.fiveletters.brush.web.dto.IntegrationLotDetailDto;
import com.fiveletters.brush.web.dto.IntegrationLotDto;
import com.fiveletters.brush.web.dto.IntegrationLotImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IntegrationAuctionApiController {

    private final IntegrationAuctionService integrationAuctionService;

    @PutMapping("/setIntegrationAuctionTitle")
    public Long setAuctionTitle(@RequestBody IntegrationAuctionDto integrationAuctionDto) throws Exception {

        return integrationAuctionService.setAuctionTitle(integrationAuctionDto);
    }

    @PutMapping("/setIntegrationLotUseYN")
    public void setLotUseYN(@RequestBody IntegrationLotDto integrationLotDto) throws Exception {

        integrationAuctionService.setLotUseYN(integrationLotDto.getLotId(), integrationLotDto.getLotUseYN());
    }

    @PutMapping("/setIntegrationLotDetail")
    public void setLotDetail(@RequestBody IntegrationLotDetailDto integrationLotDetailDto) throws Exception {

        integrationAuctionService.setLotDetail(integrationLotDetailDto);
    }

    @PutMapping("/setIntegrationLotRepImg")
    public void setLotRepImg(@RequestBody IntegrationLotImageDto integrationLotImageDto) throws Exception {

        integrationAuctionService.setLotRepImg(integrationLotImageDto);

    }

}
