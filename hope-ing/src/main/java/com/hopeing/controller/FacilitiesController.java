package com.hopeing.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
	private static final int ITEMS_PER_PAGE = 15;
	private static final String OPEN_API_URL = "url 주소 입력";
	private static final String SERVICE_KEY = "인증키 입력";

	@GetMapping(value = "/facilities_search", produces = "application/json; charset=utf-8")
	public ModelAndView facilitiesSearch(
			@RequestParam("searchKeyword") String searchKeyword,
			@RequestParam(defaultValue = "1") int page)
					throws IOException, ParseException {
		List<FacilitiesVO> searchResults = searchFacilitiesByKeyword(searchKeyword, page);
		
		ModelAndView modelAndView = new ModelAndView("/hope-ing/facilities/facilities_search");
		modelAndView.addObject("searchKeyword", searchKeyword);
		modelAndView.addObject("searchResults", searchResults);
		modelAndView.addObject("currentPage", page);
		
		int totalResults = searchResults.size();
		int totalPages = (int) Math.ceil((double) totalResults / ITEMS_PER_PAGE);
		modelAndView.addObject("totalPages", totalPages);
		
		return modelAndView;
	}
	
	private List<FacilitiesVO> searchFacilitiesByKeyword(String searchKeyword, int page) throws IOException, ParseException {
		List<FacilitiesVO> searchResults = new ArrayList<>();
		
		String encodedSearchKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
		int currentPage = 1;
		boolean hasNextPage = true;
		
		while (hasNextPage) {
			String apiUrl = OPEN_API_URL
							+ "q=" + encodedSearchKeyword
							+ "&page=" + currentPage
							+ "&perPage=" + ITEMS_PER_PAGE
							+ "&serviceKey=" + SERVICE_KEY
							+ "&_type=json";
			
			List<FacilitiesVO> facilitiesData = getFacilitiesListFromOpenAPI(apiUrl);
			
			for (FacilitiesVO facilities : facilitiesData) {
				if (facilities.get시설명().contains(searchKeyword)) {
					searchResults.add(facilities);
				}
			}
			hasNextPage = !facilitiesData.isEmpty();
			currentPage++;
		}
		int startIndex = (page - 1) * ITEMS_PER_PAGE;
		int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, searchResults.size());
		
		return searchResults.subList(startIndex, endIndex);
	}


	@GetMapping(value="/detail", produces="application/json; charset=utf-8")
	public ModelAndView facilitiesDetail(@RequestParam("시설명") String facilitiesName) throws IOException, ParseException {
		FacilitiesVO facilitiesData = getFacilitiesDataFromOpenAPI(facilitiesName);
		
		ModelAndView modelAndView = new ModelAndView("/hope-ing/facilities/detail");
		modelAndView.addObject("facilitiesData", facilitiesData);
		
		return modelAndView;
	}
	
	private FacilitiesVO getFacilitiesDataFromOpenAPI(String facilitiesName) throws IOException, ParseException {
		int currentPage = 1;
		
		while (true) {
			String encodedFacilitiesName = URLEncoder.encode(facilitiesName, "UTF-8");
			String apiUrl = OPEN_API_URL
							+ "q=" + encodedFacilitiesName
							+ "&page=" + currentPage
							+ "&perPage=" + ITEMS_PER_PAGE
							+ "&serviceKey=" + SERVICE_KEY
							+ "&_type=json";
			
			List<FacilitiesVO> facilitiesData = getFacilitiesListFromOpenAPI(apiUrl);
			
			if (facilitiesData.isEmpty()) {
				throw new IllegalArgumentException("시설명에 해당하는 데이터를 찾을 수 없습니다.");
			}
			
			FacilitiesVO targetFacilities = findTargetFacilities(facilitiesData, facilitiesName);
			
			if (targetFacilities != null) {
				return targetFacilities;
			}
			currentPage++;
		}
	}
	
	private FacilitiesVO findTargetFacilities(List<FacilitiesVO> facilitiesData, String facilitiesName) {
		for (FacilitiesVO facilities : facilitiesData) {
			if (facilities.get시설명().equals(facilitiesName)) {
				return facilities;
			}
		}
		return null;
	}
	
	@GetMapping(value="/facilities_list", produces="application/json; charset=utf-8")
	public ModelAndView facilictiesGET(@RequestParam(defaultValue = "1") int page) throws IOException, ParseException {
		String apiUrl = OPEN_API_URL
						+ "page=" + page
						+ "&perPage=" + ITEMS_PER_PAGE
						+ "&serviceKey=" + SERVICE_KEY
						+ "&_type=json";
		
		List<FacilitiesVO> facilitiesList = getFacilitiesListFromOpenAPI(apiUrl);
	
		ModelAndView modelAndView = new ModelAndView("/hope-ing/facilities/facilities_list");
		modelAndView.addObject("facilitiesList", facilitiesList);
		modelAndView.addObject("currentPage", page);
		
		return modelAndView;
	}
	
	private List<FacilitiesVO> getFacilitiesListFromOpenAPI(String apiUrl) throws IOException, ParseException {
		URL url = new URL(apiUrl);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		
		StringBuilder result = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream(), "UTF-8"))) {
		
			String returnLine;
			
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine + "\n");
			}
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
			facilitiesVO.set법정읍면동명칭((String) facilitiesJson.get("법정읍면동명칭"));
			facilitiesVO.set리_명칭((String) facilitiesJson.get("리 명칭"));
			facilitiesVO.set번지((String) facilitiesJson.get("번지"));
			facilitiesVO.set도로명_이름((String) facilitiesJson.get("도로명 이름"));
			facilitiesVO.set건물_번호((String) facilitiesJson.get("건물 번호"));
			facilitiesVO.set위도((String) facilitiesJson.get("위도"));
			facilitiesVO.set경도((String) facilitiesJson.get("경도"));
			facilitiesVO.set우편번호((Long) facilitiesJson.get("우편번호"));
			facilitiesVO.set도로명주소((String) facilitiesJson.get("도로명주소"));
			facilitiesVO.set지번주소((String) facilitiesJson.get("지번주소"));
			facilitiesVO.set전화번호((String) facilitiesJson.get("전화번호"));
			facilitiesVO.set홈페이지((String) facilitiesJson.get("홈페이지"));
			facilitiesVO.set휴무일((String) facilitiesJson.get("휴무일"));
			facilitiesVO.set운영시간((String) facilitiesJson.get("운영시간"));
			facilitiesVO.set주차_가능여부((String) facilitiesJson.get("주차 가능여부"));
			facilitiesVO.set입장_이용료_가격_정보((String) facilitiesJson.get("입장(이용료)가격 정보"));
			facilitiesVO.set반려동물_동반_가능정보((String) facilitiesJson.get("반려동물 동반 가능정보"));
			facilitiesVO.set반려동물_전용_정보((String) facilitiesJson.get("반려동물 전용 정보"));
			facilitiesVO.set입장_가능_동물_크기((String) facilitiesJson.get("입장 가능 동물 크기"));
			facilitiesVO.set반려동물_제한사항((String) facilitiesJson.get("반려동물 제한사항"));
			facilitiesVO.set장소_실내_여부((String) facilitiesJson.get("장소(실내)여부"));
			facilitiesVO.set장소_실외_여부((String) facilitiesJson.get("장소(실외)여부"));
			facilitiesVO.set기본_정보_장소설명((String) facilitiesJson.get("기본 정보 장소설명"));
			facilitiesVO.set애견_동반_추가_요금((String) facilitiesJson.get("애견 동반 추가 요금"));
			
			facilitiesList.add(facilitiesVO);
		}
		return facilitiesList;
	}
}