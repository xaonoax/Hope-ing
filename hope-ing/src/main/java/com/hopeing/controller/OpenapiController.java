package com.hopeing.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hope-ing/api")
@Slf4j
public class OpenapiController {
	
	@GetMapping(value="/data", produces="application/json; charset=utf-8")
	public String openApiGET() throws IOException{
		StringBuilder result = new StringBuilder();

			String apiUrl = "url 주소 입력" +
					"page=1" +
					"&perPage=20" +
                    "인증키 입력" +
                    "&_type=json";
			
			URL url = new URL(apiUrl);
			
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();

        return result.toString();
	}
}