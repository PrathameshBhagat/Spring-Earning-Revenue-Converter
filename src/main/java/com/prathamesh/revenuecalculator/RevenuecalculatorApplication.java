package com.prathamesh.revenuecalculator;
 
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RevenuecalculatorApplication {

	public static void main(String[] args) {
		if (Desktop.isDesktopSupported()) {
      
			//making a desktop object
			Desktop desktop = Desktop.getDesktop();
			try {
			   URI uri = new URI("http://localhost:8080/Home.html");
			   desktop.browse(uri);
			} catch (IOException excp) {
			   excp.printStackTrace();
			} catch (URISyntaxException excp) {
			   excp.printStackTrace();
			}
		 }
		SpringApplication.run(RevenuecalculatorApplication.class, args);
		
	}

}
