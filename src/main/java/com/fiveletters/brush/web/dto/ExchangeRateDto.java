package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.exchangeRate.ExchangeRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateDto {

    private String yearMonth;
    private String gbpRate;
    private String eurRate;
    private String hkdRate;
    private String cnyRate;
    private String chfRate;
    private String qarRate;
    private String aedRate;
    private String inrRate;
    private String insertDateTime;
    private String searchValue="";

    public ExchangeRateDto(ExchangeRate entity){
        this.yearMonth = entity.getYearMonth();
        this.gbpRate = String.valueOf(entity.getGbpRate());
        this.eurRate = String.valueOf(entity.getEurRate());
        this.hkdRate = String.valueOf(entity.getHkdRate());
        this.cnyRate = String.valueOf(entity.getCnyRate());
        this.chfRate = String.valueOf(entity.getChfRate());
        this.qarRate = String.valueOf(entity.getQarRate());
        this.aedRate = String.valueOf(entity.getAedRate());
        this.inrRate = String.valueOf(entity.getInrRate());
        this.insertDateTime = entity.getInsertDateTime();
    }

    public ExchangeRate toEntity() {
        return ExchangeRate.builder()
                .yearMonth(yearMonth)
                .gbpRate(gbpRate.equals("") ? 1 : Double.parseDouble(gbpRate))
                .eurRate(eurRate.equals("") ? 1 : Double.parseDouble(eurRate))
                .hkdRate(hkdRate.equals("") ? 1 : Double.parseDouble(hkdRate))
                .cnyRate(cnyRate.equals("") ? 1 : Double.parseDouble(cnyRate))
                .chfRate(chfRate.equals("") ? 1 : Double.parseDouble(chfRate))
                .qarRate(qarRate.equals("") ? 1 : Double.parseDouble(qarRate))
                .aedRate(aedRate.equals("") ? 1 : Double.parseDouble(aedRate))
                .inrRate(inrRate.equals("") ? 1 : Double.parseDouble(inrRate))
                .insertDateTime(insertDateTime)
                .build();
    }

}
