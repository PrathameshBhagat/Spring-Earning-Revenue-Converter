package com.prathamesh.revenuecalculator;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
enum Currency{
    INR,USD,EUR,GBP,AUD,CAD,SGD,CHF,MYR,JPY,CNY,NZD
};

@RestController
public class RevenueReciever {
    private Map <Currency,Double> currencyMap=new LinkedHashMap<>();
    /*
     * 1 INR = 1 INR
     * 1 USD = 83 INR
     * 1 EUR = 90 INR
     *  1 GBP = 105 INR
     * 1 AUD = 54 INR
     * 1 CAD = 61 INR
     * 1 SGD = 61 INR
     * 1 CHF = 96 INR
     * 1 MYR = 17 INR
     * 1 JPY = 0.6 INR
     * 1 CNY = 11.6 INR
     * 1 NZD = 52 INR
     * These values need to be changed as per the current market rates 
     */
    RevenueReciever(){
        currencyMap.put(Currency.INR, 1.0);
        currencyMap.put(Currency.USD, 83.0);
        currencyMap.put(Currency.EUR, 90.0);
        currencyMap.put(Currency.GBP, 105.0);
        currencyMap.put(Currency.AUD, 54.0);
        currencyMap.put(Currency.CAD, 61.0);
        currencyMap.put(Currency.SGD, 61.0);
        currencyMap.put(Currency.CHF, 96.0);
        currencyMap.put(Currency.MYR, 17.0);
        currencyMap.put(Currency.JPY, 0.6);
        currencyMap.put(Currency.CNY, 11.6);
        currencyMap.put(Currency.NZD, 52.0);
    }
    
    
    @GetMapping("/getRevenue")
    public ResponseEntity<Object> getRevenue(@RequestParam("revenue") double revenue,@RequestParam("from")  Currency obtainedCurrency,@RequestParam("to") Currency desiredCurrency) {
    //@CrossOrigin(origins = "http://localhost:9000,null")
        double revenueInDesiredCurrency = revenue*currencyMap.get(obtainedCurrency)/currencyMap.get(desiredCurrency);
        return RevenueProvider.getAllDurationRevenue(revenueInDesiredCurrency);
    }
    
}
