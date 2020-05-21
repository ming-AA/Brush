package com.fiveletters.brush.web;

import com.fiveletters.brush.service.auction.AuctionService;
import com.fiveletters.brush.web.dto.AuctionDto;
import com.fiveletters.brush.web.dto.LotDetailDto;
import com.fiveletters.brush.web.dto.LotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuctionApiController {

    private final AuctionService auctionService;


    @PutMapping("/setAuctionTitle")
    public void setAuctionTitle(@RequestBody AuctionDto auctionDto) throws Exception {
        auctionService.setAuctionTitle(auctionDto);
    }

    @PutMapping("/crawlStatus")
    public void setCrawlStatus(@RequestBody AuctionDto auctionDto) throws Exception {
        auctionService.setCrawlStatus(auctionDto);
    }

    @PutMapping("/transStatus")
    public void setTransStatus(@RequestBody AuctionDto auctionDto) throws Exception {
        auctionService.setTransStatus(auctionDto);
    }

    @PutMapping("/setLotIntegStatus")
    public void setLotIntegStatus(@RequestBody LotDto lotDto) throws Exception {
        auctionService.setLotIntegStatus(lotDto);
    }

    @PutMapping("/setLotDetail")
    public void setLotDetail(@RequestBody LotDetailDto lotDetailDto) throws Exception {
        auctionService.setLotDetail(lotDetailDto);
    }


}
