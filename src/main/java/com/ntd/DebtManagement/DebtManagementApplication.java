package com.ntd.DebtManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DebtManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebtManagementApplication.class, args);
		openHomePage();
	}

	private static void openHomePage() {
		try {
			String url = "http://localhost:8090/";
			Runtime rt = Runtime.getRuntime();
			rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
