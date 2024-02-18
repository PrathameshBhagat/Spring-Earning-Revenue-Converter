package com.prathamesh.revenuecalculator;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
enum Currencies {
    INR,USD,EUR,GBP,AUD,CAD,SGD,CHF,MYR,JPY,CNY,NZD
};

@RestController
public class RevenueReciever {
    private Map <Currencies ,Double> exchageRatesFromINR=new LinkedHashMap<>();
    /*
     * These values need to be changed as per the current market rates 
     */
    RevenueReciever(){
        exchageRatesFromINR.put(Currencies.INR,1.0);
        exchageRatesFromINR.put(Currencies.USD, 83.0);
        exchageRatesFromINR.put(Currencies.EUR, 90.0);
        exchageRatesFromINR.put(Currencies.GBP, 105.0);
        exchageRatesFromINR.put(Currencies.AUD, 54.0);
        exchageRatesFromINR.put(Currencies.CAD, 61.0);
        exchageRatesFromINR.put(Currencies.SGD, 61.0);
        exchageRatesFromINR.put(Currencies.CHF, 96.0);
        exchageRatesFromINR.put(Currencies.MYR, 17.0);
        exchageRatesFromINR.put(Currencies.JPY, 0.6);
        exchageRatesFromINR.put(Currencies.CNY, 11.6);
        exchageRatesFromINR.put(Currencies.NZD, 52.0);
    }
    
    
    @GetMapping("/getRevenue")
    public ResponseEntity<Object> getRevenue(@RequestParam("revenue") double revenue,@RequestParam("from")  Currencies  obtainedCurrency,@RequestParam("to") Currencies  desiredCurrency) {
    //@CrossOrigin(origins = "http://localhost:9000,null")
        double revenueInDesiredCurrency = revenue*exchageRatesFromINR.get(obtainedCurrency)/exchageRatesFromINR.get(desiredCurrency);
        return RevenueProvider.getAllDurationRevenue(revenueInDesiredCurrency,desiredCurrency);
    }

    @GetMapping("/getRevenueInAllCurrencies")
    public List<Object> getMethodName(@RequestParam("revenue") double revenue,@RequestParam("from")  Currencies  obtainedCurrency) {
        List<Object> listOfRevenueInAllCurrencies = new LinkedList<>(); 
        RestTemplate restTemplate = new RestTemplate();
        for(Currencies  to : Currencies .values()){
            Object myobj =  restTemplate.getForObject("http://localhost:8080/getRevenue?from=" + obtainedCurrency + "&to=" + to + "&revenue=" + revenue, Object.class); 
        listOfRevenueInAllCurrencies.add(myobj);
        
    }

        return listOfRevenueInAllCurrencies;
    } 
}
