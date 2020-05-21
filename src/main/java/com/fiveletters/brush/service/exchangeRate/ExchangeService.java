package com.fiveletters.brush.service.exchangeRate;

import com.fiveletters.brush.web.dto.ExchangeRateDto;
import com.fiveletters.brush.web.dto.PageDto;

import java.util.List;

public interface ExchangeService {

    public int getExchangeRateTotalRecordCount(String searchValue) throws Exception;

    public List<ExchangeRateDto> getExchangeRateList(PageDto pageDto, String searchValue) throws Exception;

    public ExchangeRateDto getExchangeRateDetail(String yearMonth) throws Exception;

    public void saveExchangeRate(ExchangeRateDto exchangeRateDto) throws Exception;

    public void updateExchangeRate(String yearMonth, ExchangeRateDto exchangeRateDto) throws Exception;

    public void deleteExchangeRate(String yearMonth) throws Exception;
}
