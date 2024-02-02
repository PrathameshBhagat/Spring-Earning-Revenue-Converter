package com.prathamesh.revenuecalculator;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class RevenueProvider {
		
	@GetMapping("/getAllDurationRevenue")
	public static ResponseEntity<Object> getAllDurationRevenue(
							@RequestParam("revenue") Double revenue ) {

		NumberFormat nf  = NumberFormat.getInstance(Locale.US);
		
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Yearly", nf.format(revenue));
		map.put("Monthly", nf.format(revenue / 12));
		map.put("Weekly", nf.format((revenue / 313) * 6));
		map.put("Daily", nf.format(revenue / 313));
		map.put("Hourly", nf.format((revenue / 313) / 8));
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
