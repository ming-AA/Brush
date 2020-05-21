package com.fiveletters.brush.web;

import com.fiveletters.brush.service.exchangeRate.ExchangeService;
import com.fiveletters.brush.web.dto.ExchangeRateDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ExchangeRateController {

    private final ExchangeService exchangeService;

    @RequestMapping(value = "/exchangeRate", method = {RequestMethod.GET, RequestMethod.POST} )
    public String exchangeRateList(Model model, PageDto pageDto, ExchangeRateDto exchangeRateDto) throws Exception {

        int totalRecordCount = exchangeService.getExchangeRateTotalRecordCount(exchangeRateDto.getSearchValue());
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<ExchangeRateDto> exchangeRateList = exchangeService.getExchangeRateList(pageDto, exchangeRateDto.getSearchValue());
            model.addAttribute("exchangeRateList", exchangeRateList);
        }

        model.addAttribute("searchValue", exchangeRateDto.getSearchValue());
        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);

        return "exchangeRate";
    }

    @RequestMapping(value = "/exchangeRateDetail/{yearMonth}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String exchangeRateDetail(Model model, @PathVariable String yearMonth) throws Exception {

        ExchangeRateDto exchangeRateDetail = exchangeService.getExchangeRateDetail(yearMonth);
        model.addAttribute("exchangeRateDetail", exchangeRateDetail);

        return "exchangeRateDetail";
    }

    @RequestMapping(value = "/exchangeRateCreate", method = {RequestMethod.GET} )
    public String exchangeRateCreate() throws Exception {

        return "exchangeRateCreate";
    }

    @ResponseBody
    @PostMapping("/saveExchangeRate")
    public void saveExchangeRate(@RequestBody ExchangeRateDto exchangeRateDto) throws Exception {

        exchangeService.saveExchangeRate(exchangeRateDto);
    }

    @ResponseBody
    @PutMapping("/updateExchangeRate/{yearMonth}")
    public void updateExchangeRate(@PathVariable String yearMonth, @RequestBody ExchangeRateDto exchangeRateDto) throws Exception {

        exchangeService.updateExchangeRate(yearMonth, exchangeRateDto);
    }

    @ResponseBody
    @DeleteMapping("/deleteExchangeRate/{yearMonth}")
    public void deleteExchangeRate(@PathVariable String yearMonth) throws Exception {

        exchangeService.deleteExchangeRate(yearMonth);
    }

}
