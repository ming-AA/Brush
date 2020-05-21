package com.fiveletters.brush.domain.exchangeRate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BRUSH_LOT_PRICE_EXCHANGE_RATE")
public class ExchangeRate {

    @Id
    @Column(name = "YEARMONTH")
    private String yearMonth;

    @Column(name = "GBP_RATE")
    private double gbpRate;

    @Column(name = "EUR_RATE")
    private double eurRate;

    @Column(name = "HKD_RATE")
    private double hkdRate;

    @Column(name = "CNY_RATE")
    private double cnyRate;

    @Column(name = "CHF_RATE")
    private double chfRate;

    @Column(name = "QAR_RATE")
    private double qarRate;

    @Column(name = "AED_RATE")
    private double aedRate;

    @Column(name = "INR_RATE")
    private double inrRate;

    @Column(name = "INSERT_DT")
    private String insertDateTime;


    @Builder
    public ExchangeRate(String yearMonth, double gbpRate, double eurRate, double hkdRate, double cnyRate, double chfRate, double qarRate, double aedRate, double inrRate, String insertDateTime) {
        this.yearMonth = yearMonth;
        this.gbpRate = gbpRate;
        this.eurRate = eurRate;
        this.hkdRate = hkdRate;
        this.cnyRate = cnyRate;
        this.chfRate = chfRate;
        this.qarRate = qarRate;
        this.aedRate = aedRate;
        this.inrRate = inrRate;
        this.insertDateTime = insertDateTime;
    }

    public void update(String gbpRate, String eurRate, String hkdRate, String cnyRate, String chfRate, String qarRate, String aedRate, String inrRate) {

        this.gbpRate = Double.parseDouble(gbpRate);
        this.eurRate = Double.parseDouble(eurRate);
        this.hkdRate = Double.parseDouble(hkdRate);
        this.cnyRate = Double.parseDouble(cnyRate);
        this.chfRate = Double.parseDouble(chfRate);
        this.qarRate = Double.parseDouble(qarRate);
        this.aedRate = Double.parseDouble(aedRate);
        this.inrRate = Double.parseDouble(inrRate);
    }


}
