package com.nextroom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextroom.vo.PartyVo;

@Repository
public class PartyDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//21-09-26 by 대니
	//sido로 카페No, 카페이름가져오기
	public List<PartyVo> getCafeList(String sido) {
		System.out.println("다오구간시도: " + sido);
		
		List<PartyVo> cafeList = sqlSession.selectList("party.cafeListSelect", sido);
		
		System.out.println("다오구간 카페리스트: " + cafeList);
		
		return cafeList;
	}
	
	
	//21-09-27 by 대니
	//cafeNo로 테마NO, 테마이름가져오기
	public List<PartyVo> getThemeList(int cafeNo) {
		System.out.println("다오구간카페: " + cafeNo);
		
		List<PartyVo> themeList = sqlSession.selectList("party.themeListSelect", cafeNo);
		
		System.out.println("다오구간 테마리스트: " + themeList);
		
		return themeList;
	}
	
	
	//21-09-28 by 대니
	//themeNo로 시간표 가져오기
	public List<PartyVo> getThemeTimeList(int themeNo) {
		System.out.println("다오구간테마NO: " + themeNo);
		
		List<PartyVo> themeTimeList = sqlSession.selectList("party.themeTimeListSelect", themeNo);
		
		System.out.println("다오구간 테마타임리스트: " + themeTimeList);
		
		return themeTimeList;
	}
	
	
	
}
