package com.hopeing.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FacilitiesVO {
	private String 시설명;
	private String 카테고리1;
	private String 카테고리2;
	private String 시도_명칭;
	private String 시군구_명칭;
	private String 법정읍면동명칭;
	private String 리_명칭;
	private String 번지;
	private String 도로명_이름;
	private String 건물_번호;
	private String 위도;
	private String 경도;
	private int 우편번호;
	private String 도로명주소;
	private String 지번주소;
	private String 전화번호;
	private String 홈페이지;
	private String 휴무일;
	private String 운영시간;
	private String 주차_가능여부;
	private String 입장_이용료_가격_정보	;
	private String 반려동물_동반_가능정보;
	private String 반려동물_전용_정보;
	private String 입장_가능_동물_크기;
	private String 반려동물_제한사항;
	private String 장소_실내_여부;
	private String 장소_실외_여부;
	private String 기본_정보_장소설명;
	private String 애견_동반_추가_요금;
	private String 최종작성일;
}
