package com.fiveletters.brush.service.exchangeRate;

import com.fiveletters.brush.domain.exchangeRate.ExchangeRate;
import com.fiveletters.brush.domain.exchangeRate.ExchangeRateRepository;
import com.fiveletters.brush.web.dto.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @NonNull
    private final ExchangeRateRepository exchangeRateRepository;

    @Transactional(readOnly = true)
    public int getExchangeRateTotalRecordCount(String searchValue) throws Exception {

        int totalRecordCount = exchangeRateRepository.countByYearMonthContainingIgnoreCase(searchValue);

        return totalRecordCount;
    }

    @Transactional(readOnly = true)
    public List<ExchangeRateDto> getExchangeRateList(PageDto pageDto, String searchValue) throws Exception {

        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<ExchangeRateDto> exchangeRateList = exchangeRateRepository
                .findAllByYearMonthContainingIgnoreCase(searchValue, paging)
                .stream()
                .map(ExchangeRateDto::new)
                .collect(Collectors.toList());

        return exchangeRateList;
    }

    @Transactional(readOnly = true)
    public ExchangeRateDto getExchangeRateDetail(String yearMonth) throws Exception {

        ExchangeRate entity = exchangeRateRepository.findById(yearMonth)
                .orElseThrow(() -> new IllegalArgumentException("해당 환율이 없습니다. id="+yearMonth));

        return new ExchangeRateDto(entity);
    }

    @Transactional
    public void saveExchangeRate(ExchangeRateDto exchangeRateDto) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        exchangeRateDto.setInsertDateTime(simpleDateFormat.format(new Date()));

        exchangeRateRepository.save(exchangeRateDto.toEntity());
    }

    @Transactional
    public void updateExchangeRate(String yearMonth, ExchangeRateDto exchangeRateDto) throws Exception {

        ExchangeRate exchangeRate = exchangeRateRepository.findById(yearMonth)
                .orElseThrow(() -> new IllegalArgumentException("해당 환율이 없습니다. id="+yearMonth));

        exchangeRate.update(exchangeRateDto.getGbpRate()
                , exchangeRateDto.getEurRate()
                , exchangeRateDto.getHkdRate()
                , exchangeRateDto.getCnyRate()
                , exchangeRateDto.getChfRate()
                , exchangeRateDto.getQarRate()
                , exchangeRateDto.getAedRate()
                , exchangeRateDto.getInrRate());
    }

    @Transactional
    public void deleteExchangeRate(String yearMonth) throws Exception {

        ExchangeRate exchangeRate = exchangeRateRepository.findById(yearMonth)
                .orElseThrow(() -> new IllegalArgumentException("해당 환율이 없습니다. id="+yearMonth));

        exchangeRateRepository.delete(exchangeRate);
    }


}
