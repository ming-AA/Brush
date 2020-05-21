package com.fiveletters.brush.web;

import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.service.integrationAuction.IntegrationAuctionService;
import com.fiveletters.brush.web.dto.IntegrationAuctionDto;
import com.fiveletters.brush.web.dto.IntegrationLotDto;
import com.fiveletters.brush.web.dto.IntegrationLotImageDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IntegrationAuctionController {

    private final IntegrationAuctionService integrationAuctionService;

    @RequestMapping(value = "/integrationAuctionResults", method = {RequestMethod.GET, RequestMethod.POST} )
    public String getAuctionResult(Model model, PageDto pageDto, IntegrationAuctionDto integrationAuctionDto) throws Exception {

        String searchValue = integrationAuctionDto.getSearchValue();
        int totalRecordCount = integrationAuctionService.getTotalRecordCount(searchValue);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<IntegrationAuctionDto> auctionResultsList = integrationAuctionService.getAuctionResultsList(pageDto, searchValue);
            model.addAttribute("auctionResultsList", auctionResultsList);
        }

        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);
        model.addAttribute("searchValue", searchValue);

        return "integrationAuctionResults";
    }

    @RequestMapping(value = "/integrationAuctionLot/{auctionId}/{auctionTitle}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String getAuctionRotList(Model model
            , @RequestParam(defaultValue="Lot Title") String searchKey
            , @RequestParam(defaultValue="") String searchValue
            , @PathVariable Long auctionId
            , @PathVariable String auctionTitle
            , PageDto pageDto) throws Exception {

        IntegrationLotDto integrationLotDto = new IntegrationLotDto(auctionId, auctionTitle, searchKey, searchValue);

        int totalRecordCount = integrationAuctionService.getLotTotalRecordCount(integrationLotDto);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<IntegrationLotDto> lotList = integrationAuctionService.getAuctionRotList(integrationLotDto, pageDto);
            model.addAttribute("lotList", lotList);
        }

        model.addAttribute("lotValues", integrationLotDto);
        model.addAttribute("totalRecordCount", totalRecordCount);
        model.addAttribute("pagging", pageDto);

        return "integrationAuctionLot";
    }

    @RequestMapping(value = "/integrationAuctionLotDetail/{lotUUID}", method = {RequestMethod.GET} )
    public String getAuctionLotDetail(Model model, @PathVariable String lotUUID) throws Exception {

        LotDetail lotDetail = integrationAuctionService.getLotDetail(lotUUID);
        model.addAttribute("lotDetail", lotDetail);

        return "integrationAuctionLotDetail";
    }

    @RequestMapping(value = "/integrationAuctionLotImgChoose/{lotUUID}", method = {RequestMethod.GET} )
    public String getAuctionLotImage(Model model, @PathVariable String lotUUID) throws Exception {

        int totalRecordCount = integrationAuctionService.getLotImageTotalRecordCount(lotUUID);

        if(totalRecordCount !=0){
            List<IntegrationLotImageDto> lotImageList = integrationAuctionService.getAuctionLotImage(lotUUID);
            model.addAttribute("lotImageList", lotImageList);
        }
        model.addAttribute("lotUUID", lotUUID);

        return "integrationAuctionLotImgChoose";
    }
}
