package com.fiveletters.brush.web;

import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.service.auction.AuctionService;
import com.fiveletters.brush.web.dto.AuctionDto;
import com.fiveletters.brush.web.dto.LotDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuctionController {

    private final AuctionService auctionService;

    @RequestMapping(value = "/auctionResults", method = {RequestMethod.GET, RequestMethod.POST} )
    public String getAuctionResult(Model model, PageDto pageDto, AuctionDto auctionDto) throws Exception {
        String searchValue = auctionDto.getSearchValue();
        int totalRecordCount = auctionService.getTotalRecordCount(searchValue);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<AuctionDto> auctionResultsList = auctionService.getAuctionResultsList(pageDto, searchValue);
            model.addAttribute("auctionResultsList", auctionResultsList);
        }

        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);
        model.addAttribute("searchValue", searchValue);

        return "auctionResults";
    }

    @RequestMapping(value = "/auctionLot/{auctionName}/{auctionId}/{auctionTitle}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String getAuctionRotList(Model model
            , @RequestParam(defaultValue="Lot Title") String searchKey
            , @RequestParam(defaultValue="") String searchValue
            , @PathVariable Long auctionId
            , @PathVariable String auctionName
            , @PathVariable String auctionTitle
            , PageDto pageDto) throws Exception {

        LotDto lotDto = new LotDto(auctionId, auctionName, auctionTitle, searchKey, searchValue);

        int totalRecordCount = auctionService.getLotTotalRecordCount(lotDto);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<LotDto> lotList = auctionService.getAuctionRotList(lotDto, pageDto);
            model.addAttribute("lotList", lotList);
        }

        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);
        model.addAttribute("lotValues", lotDto);


        return "auctionLot";
    }

    @RequestMapping(value = "/auctionLotDetail/{auctionName}/{lotUUID}", method = {RequestMethod.GET} )
    public String getAuctionLotDetail(Model model, @PathVariable String auctionName, @PathVariable String lotUUID) throws Exception {

        LotDetail lotDetail = auctionService.getLotDetail(auctionName, lotUUID);

        model.addAttribute("lotDetail", lotDetail);
        model.addAttribute("auctionName", auctionName);

        return "auctionLotDetail";
    }
}
