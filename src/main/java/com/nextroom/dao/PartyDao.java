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
	//sido로 카페리스트가져오기
	public List<PartyVo> getCafeList(String sido) {
		System.out.println("다오구간시도: " + sido);
		
		return null;
	}
}