package com.nextroom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextroom.vo.PartyVo;
import com.nextroom.vo.ReserveVo;

@Repository
public class PartyDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//21-09-26 by 대니
	//sido로 카페No, 카페이름가져오기
	public List<PartyVo> getCafeList(String sido) {
		
		List<PartyVo> cafeList = sqlSession.selectList("party.cafeListSelect", sido);
		
		return cafeList;
	}
	
	
	//21-09-27 by 대니
	//cafeNo로 테마NO, 테마이름가져오기
	public List<PartyVo> getThemeList(int cafeNo) {
		
		List<PartyVo> themeList = sqlSession.selectList("party.themeListSelect", cafeNo);
		
		return themeList;
	}
	
	
	//21-09-28 by 대니
	//themeNo, reserveDate로 시간표 가져오기
	public List<PartyVo> getThemeTimeList(Map<String, Object> themeTimeMap) {
		
		List<PartyVo> themeTimeList = sqlSession.selectList("party.themeTimeListSelect", themeTimeMap);
		
		return themeTimeList;
	}
	
	
	//21-09-29 by 대니
	//파티등록폼에서 넘어온 정보로 파티리스트 인서트!
	public int addPartyList(PartyVo partyVo) {
		
		//xml 전송
		int count = sqlSession.insert("party.addPartyList", partyVo);
		
		return count;
	}
	
	
	//셀렉트키로 가져온 PARTYNO와 USERNO로 파티참여리스트 INSERT
	public void addPartyDetail(PartyVo partyDetailList) {
		
		sqlSession.insert("party.addPartyDetail", partyDetailList);
	}
	
	
	//21-10-08 by 대니
	//파티등록 리스트 목록 출력하기(페이징 + 검색)
	public List<PartyVo> getPartyList(int startRnum, int endRnum, String keyword, String partyView) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();

		pageMap.put("startRnum", startRnum);
		pageMap.put("endRnum", endRnum);
		pageMap.put("keyword", keyword);
		pageMap.put("partyView", partyView);
		
		
		return sqlSession.selectList("party.partyListSelect", pageMap);
	}
	
	
	//21-10-08 by대니
	//페이징 갯수와 페이징버튼을 위한 전체게시물갯수
	public int selectTotalCount(String keyword, String partyView) {
		
		Map<String, Object> totalCountMap = new HashMap<String, Object>();
		
		totalCountMap.put("keyword", keyword);
		totalCountMap.put("partyView", partyView);
		
		
		return sqlSession.selectOne("party.selectTotalCount", totalCountMap);
	}
	
	// 21-10-01~21-10-02 by 대니
	// 파티리스트에서 뽑아낸 cafeNo로 가져오는 정보리스트
	public PartyVo getCafeNoList(int cafeNo) {
		
		return sqlSession.selectOne("party.getCafeNoList", cafeNo);
		 
	}
	
	//21-10-02 by 대니
	//cafeNo와 themeNo 뽑아서 필요한 정보가져오기
	public PartyVo getCafeThemeNoList(PartyVo cafeThemeVo) {
		
		return sqlSession.selectOne("party.getCafeThemeNoList", cafeThemeVo);
	}
	
	//21-10-02 by 대니
	//partyNo로 유저카운트 가져오기
	public PartyVo getPartyNoList(int partyNo) {
		
		return sqlSession.selectOne("party.getPartyNoList", partyNo);
	}
	
	
	
	//21-10-03 by 대니
	//리스트에서 넘어온 파티No로 partyRead에 정보 뿌려주기
	public PartyVo getPartyReadList(int partyNo) {
		
		return sqlSession.selectOne("party.getPartyReadList", partyNo);
	}
	
	//21-10-03 by 대니
	//partyNo로 파티승인멤버 리스트 가져오기
	public List<PartyVo> partyDetailList(int partyNo) {
		
		return sqlSession.selectList("party.partyDetailList", partyNo);
	}
	
	//21-10-03 by 대니
	//partyNo로 파티대기멤버 리스트 가져오기
	public List<PartyVo> partyApplicantList(int partyNo) {
		
		return sqlSession.selectList("party.partyApplicantList", partyNo);
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 파티삭제를 위한 partyNo 받기
	public int partyDelete(int partyNo) {
		
		System.out.println(partyNo);
		
		int count = sqlSession.delete("party.partyDelete", partyNo);
		
		return count;
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 참여자가 참여신청을 눌렀을 경우
	public PartyVo selectPartyApplicant(PartyVo partyVo) {
		
		return sqlSession.selectOne("party.selectPartyApplicant", partyVo);
	}
	
	//21-10-05 by 대니
	//partyRead에서 참여자가 참여신청을 눌렀을 경우 partyDetail에 있는지 확인
	public PartyVo partyDetailCheckSelect(PartyVo partyVo) {
		
		return sqlSession.selectOne("party.partyDetailCheckSelect", partyVo);
		
	}
	
	//21-10-05 by 대니
	//partyRead에서 참여자가 참여신청을 눌렀을때 성공했을경우 INSERT
	public void insertApplicant(PartyVo partyVo) {
		
		sqlSession.insert("party.insertApplicant", partyVo);
		
	}
	
	// 21-10-06 by 대니
	// partyRead에서 참여자가 참가취소를 눌렀을 경우
	public void deleteApplicant(PartyVo partyVo) {

		sqlSession.delete("party.deleteApplicant", partyVo);

	}
	
	//21-10-06 by 대니
	//partyNo로 party reservePerson 가져오기
	public PartyVo getReservePerson(int partyNo) {
		
		return sqlSession.selectOne("party.getReservePerson", partyNo);
	}
	
	//21-10-06 by 대니
	//partyNo, userNo로 partyDetail 멤버추가하기
	public void pDetailEntryMember(PartyVo partyVo) {
		
		sqlSession.insert("party.pDetailEntryMember", partyVo);
		
	}
	
	//21-10-06 by 대니
	//방장이 partyDetail에서 멤버취소를 눌렀을 경우
	public void exceptDetailMember(PartyVo partyVo) {
		
		//partyDetail에서 멤버삭제
		sqlSession.delete("party.exceptDetailMember", partyVo);
		
	}
	
	
	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집완료 버튼을 클릭했을경우
	public void partyCompleteUdate(PartyVo partyVo) {
		
		sqlSession.update("party.partyCompleteUdate", partyVo);
		
	}
		
	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집중 버튼을 클릭했을경우
	public void partyWorkingUdate(PartyVo partyVo) {
		
		sqlSession.update("party.partyWorkingUdate", partyVo);
		
	}
	
	//21-10-15 by 대니
	//partyNo로 유저카운트 가져오기
	public ReserveVo getPartyDetailCount(int partyNo) {
		
		return sqlSession.selectOne("party.getPartyDetailCount", partyNo);
	}
	
	//21-10-15 by 대니
	//파티에서 예약하기 눌렀을때 reserve에서 넘어온 파티no로 userno찾기
	public List<ReserveVo> pDetailUserList(int partyNo) {
		
		List<ReserveVo> pDetailUserList = sqlSession.selectList("party.pDetailUserList", partyNo);
		
		return pDetailUserList;
	}
	
	
	
}
