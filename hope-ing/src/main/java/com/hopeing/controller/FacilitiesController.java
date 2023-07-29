package com.hopeing.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hopeing.beans.vo.FacilitiesVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hope-ing/facilities/*")
@Slf4j
public class FacilitiesController {
	private static final int ITEMS_PER_PAGE = 20;

	@GetMapping(value="/facilities_list", produces="application/json; charset=utf-8")
	public ModelAndView facilictiesGET(@RequestParam(defaultValue = "1") int page) throws IOException, ParseException {
		StringBuilder result = new StringBuilder();
		
		int offset = (page - 1) * ITEMS_PER_PAGE;
		
		String apiUrl = "url 주소 입력" +
			"page=" + page +
			"&perPage=" + ITEMS_PER_PAGE +
			"인증키 입력" +
			"&_type=json";
	
		URL url = new URL(apiUrl);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		
		String returnLine;
		
		while((returnLine = bufferedReader.readLine()) != null) {
			result.append(returnLine + "\n");
		}
		
		urlConnection.disconnect();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
		JSONArray facilitiesArray = (JSONArray) jsonObject.get("data");
		
		List<FacilitiesVO> facilitiesList = new ArrayList<>();
		
		for (Object obj : facilitiesArray) {
			JSONObject facilitiesJson = (JSONObject) obj;
			FacilitiesVO facilitiesVO = new FacilitiesVO();
			
			facilitiesVO.set시설명((String) facilitiesJson.get("시설명"));
			facilitiesVO.set카테고리1((String) facilitiesJson.get("카테고리1"));
			facilitiesVO.set카테고리2((String) facilitiesJson.get("카테고리2"));
			facilitiesVO.set시도_명칭((String) facilitiesJson.get("시도 명칭"));
			facilitiesVO.set시군구_명칭((String) facilitiesJson.get("시군구 명칭"));
			
			facilitiesList.add(facilitiesVO);
		}
		
		ModelAndView modelAndView = new ModelAndView("/hope-ing/facilities/facilities_list");
		modelAndView.addObject("facilitiesList", facilitiesList);
		modelAndView.addObject("currentPage", page);
		
		return modelAndView;
	}
}