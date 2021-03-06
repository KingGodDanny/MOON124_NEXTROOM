package com.nextroom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextroom.dao.PartyDao;
import com.nextroom.dao.RankDao;
import com.nextroom.dao.UserDao;
import com.nextroom.vo.PartyVo;
import com.nextroom.vo.ReserveVo;
import com.nextroom.vo.UserVo;

@Service
public class PartyService {

	@Autowired 
	PartyDao partyDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RankDao rankDao;
	
	//21-09-26 by 대니
	//sido로 카페No, 카페이름가져오기
	public List<PartyVo> getCafeList(String sido) {
		
		List<PartyVo> cafeList = partyDao.getCafeList(sido);
		
		return cafeList;
	}
	
	
	//21-09-27 by 대니
	//cafeNo로 테마NO, 테마이름가져오기
	public List<PartyVo> getThemeList(int cafeNo) {
		
		List<PartyVo> themeList = partyDao.getThemeList(cafeNo);
		
		return themeList;
	}
	
	
	//21-09-28 by 대니
	//themeNo, reserveDate로 시간표 가져오기
	public List<PartyVo> getThemeTimeList(Map<String, Object> themeTimeMap) {
		
		List<PartyVo> themeTimeList = partyDao.getThemeTimeList(themeTimeMap);
		
		return themeTimeList;
	}
	
	
	//21-09-29 by 대니
	//파티등록폼에서 넘어온 정보로 파티리스트 인서트!
	public int addPartyList(PartyVo partyVo) {
		
		int count = partyDao.addPartyList(partyVo);
		
		//셀렉트키로 가져온 PARTYNO와 USERNO로 파티참여리스트 INSERT
		int userNo = partyVo.getUserNo();
		int partyNo = partyVo.getPartyNo();
		PartyVo partyDetailList = new PartyVo();
		partyDetailList.setUserNo(userNo);
		partyDetailList.setPartyNo(partyNo);
		
		partyDao.addPartyDetail(partyDetailList);
		
		
	
		return count;
	}
	
	
	
	
	//21-10-08 by 대니
	//파티등록 리스트 목록 출력하기(페이징 + 검색)
	public Map<String, Object> getPartyList(int crtPage, String keyword, String partyView) {
		
		////////////////////////////////////////////
		//리스트 가져오기
		////////////////////////////////////////////
		
		int listCnt = 5;
		
		//crtPage 계산(- 값일때 1page 처리)
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1); //삼항연산자; 아래랑 같은 논리
		
		//시작번호 계산하기
		int startRnum = (crtPage-1) * listCnt + 1;
		
		//끝번호 계산하기
		int endRnum = crtPage * listCnt;
		
		//파티리스트 전체 출력
		List<PartyVo> partyList = partyDao.getPartyList(startRnum, endRnum, keyword, partyView);
		
		//cafeNo 뽑아서 필요한 정보가져오기
		for(int i=0; i<partyList.size(); i++) {
			int cafeNo = partyList.get(i).getCafeNo();
			
			PartyVo partyVo = new PartyVo();
			
			partyVo = (PartyVo) partyDao.getCafeNoList(cafeNo);
			
			String sidoDetail = partyVo.getSidoDetail();
			String cafeName = partyVo.getCafeName();
			
			partyList.get(i).setSidoDetail(sidoDetail);
			partyList.get(i).setCafeName(cafeName);
		
			
		//cafeNo와 themeNo 뽑아서 필요한 정보가져오기
			int themeNo = partyList.get(i).getThemeNo();
			
			PartyVo cafeThemeVo = new PartyVo(cafeNo, themeNo);
			
			partyVo = (PartyVo) partyDao.getCafeThemeNoList(cafeThemeVo);
			
			String themeName = partyVo.getThemeName();
			String themeImg = partyVo.getThemeImg();
			
			partyList.get(i).setThemeName(themeName);
			partyList.get(i).setThemeImg(themeImg);
			
		//partyNo로 유저카운트 가져오기
			int partyNo = partyList.get(i).getPartyNo();
			
			partyVo = (PartyVo) partyDao.getPartyNoList(partyNo);
			
			int userCount = partyVo.getUserCount();
			
			partyList.get(i).setUserCount(userCount);
			
		}		
		
		
		//////////////////////////////////////////////
		// 페이징 계산하기
		//////////////////////////////////////////////
		
		//페이징 갯수와 페이징버튼을 위한 전체게시물갯수
		int totalCount = partyDao.selectTotalCount(keyword, partyView);
		
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount))* pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		
		//다음 화살표 펴현 유무
		boolean next = false;
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if(startPageBtnNo !=1) {
			prev = true;
		}
		
		
		//////////////////////////////////////////////
		// Map으로 리턴하기
		//////////////////////////////////////////////		
		
		//리턴하기
		Map<String, Object> partyAllListMap = new HashMap<String, Object>();
		partyAllListMap.put("partyList", partyList);
		partyAllListMap.put("prev", prev);
		partyAllListMap.put("startPageBtnNo", startPageBtnNo);
		partyAllListMap.put("endPageBtnNo", endPageBtnNo);
		partyAllListMap.put("next", next);
		
		return partyAllListMap;
	}
	
	
	//21-10-03 by 대니
	//리스트에서 넘어온 파티No로 partyRead에 정보 뿌려주기
	public Map<String, Object> getPartyReadList(int partyNo) {
		
		//partyNo로 PATRYREAD 정보 가져오기
		PartyVo partyReadList = partyDao.getPartyReadList(partyNo);
		
		int cafeNo = partyReadList.getCafeNo();
		
		//각종 No로 받아줄 생성자 새로 열기
		PartyVo getNoList = new PartyVo();
		
		//cafeNo로 정보가져와서 List에 추가
		getNoList = partyDao.getCafeNoList(cafeNo);
		
		partyReadList.setCafeName(getNoList.getCafeName());
		
		//cafeNo와 themeNo로 정보가져와서 List에 추가
		int themeNo = partyReadList.getThemeNo();
		PartyVo cafeThemeVo = new PartyVo(cafeNo, themeNo);
		
		cafeThemeVo = partyDao.getCafeThemeNoList(cafeThemeVo);
		
		partyReadList.setThemeName(cafeThemeVo.getThemeName());
		partyReadList.setThemeImg(cafeThemeVo.getThemeImg());
		partyReadList.setJenre(cafeThemeVo.getJenre());
		partyReadList.setLevels(cafeThemeVo.getLevels());
		partyReadList.setPlayTime(cafeThemeVo.getPlayTime());
		partyReadList.setThemeType(cafeThemeVo.getThemeType());
		
		
		//partyNo로 파티승인멤버 리스트 가져오기
		List<PartyVo> partyDetailList = partyDao.partyDetailList(partyNo); 
		
		for(int i=0; i<partyDetailList.size(); i++) {
			
			int userNo = partyDetailList.get(i).getUserNo();
			
			PartyVo partyUserRank = rankDao.partySelectRating(userNo);
			UserVo userNickName = userDao.userNoGetUser(userNo);
			
			if(partyUserRank != null) {
			
				//평균기록
				int cleartime = partyUserRank.getAvgClearTime();
				
				int min = (int) cleartime / 60;
				int sec = cleartime % 60;
				System.out.println(min + "" + sec);
				String clearTime = min + "분" + " " + sec + "초";
				
				partyDetailList.get(i).setShowAvgClearTime(clearTime);
				
				
				//성공률
				double winRate = (double) partyUserRank.getWonGame() / partyUserRank.getTotalGame();
	
				winRate = Math.round(winRate * 100) / 100.00;
				
				partyDetailList.get(i).setWinRate(winRate * 100);
				
				
				//노힌트성공률
				double noHintWinRate = (double) partyUserRank.getNoHintGame() / partyUserRank.getTotalGame();
				
				noHintWinRate = Math.round(noHintWinRate * 100) / 100.00;
				
				partyDetailList.get(i).setNoHintWinRate(noHintWinRate * 100);
	
				
				//유저 현재순위, 닉네임, 프로필가져오기
				PartyVo partySelectRankList = rankDao.partySelectRankList(userNo);
				
				String profile = userNickName.getProfile();
				partyDetailList.get(i).setProfile(profile);
				String nickname = userNickName.getNickname();
				int rank = partySelectRankList.getRank();
				
				partyDetailList.get(i).setRank(rank);
				partyDetailList.get(i).setNickname(nickname);
			
			} else {
				
				String clearTime = "0분 0초";
				partyDetailList.get(i).setShowAvgClearTime(clearTime);
				
				double winRate = 0;
				partyDetailList.get(i).setWinRate(winRate);
				
				double noHintWinRate = 0;
				partyDetailList.get(i).setNoHintWinRate(noHintWinRate);
				
				String nickname = userNickName.getNickname();
				int rank = 0;
				partyDetailList.get(i).setRank(rank);
				partyDetailList.get(i).setNickname(nickname);
				
				String profile = userNickName.getProfile();
				partyDetailList.get(i).setProfile(profile);

			}
			
		}
		
		
		//partyNo로 파티대기멤버 리스트 가져오기
		List<PartyVo> partyApplicantList = partyDao.partyApplicantList(partyNo);
		
		
		for(int i=0; i<partyApplicantList.size(); i++) {
			
			int userNo = partyApplicantList.get(i).getUserNo();
			
			PartyVo partyUserRank = rankDao.partySelectRating(userNo);
			UserVo userNickName = userDao.userNoGetUser(userNo);
			
			if(partyUserRank != null) {
				
				//평균기록
				int cleartime = partyUserRank.getAvgClearTime();
				
				int min = (int) cleartime / 60;
				int sec = cleartime % 60;
				System.out.println(min + "" + sec);
				String clearTime = min + "분" + " " + sec + "초";
				
				partyApplicantList.get(i).setShowAvgClearTime(clearTime);
				
				//성공률
				double winRate = (double) partyUserRank.getWonGame() / partyUserRank.getTotalGame();
	
				winRate = Math.round(winRate * 100) / 100.00;
				
				partyApplicantList.get(i).setWinRate(winRate * 100);
				
				//노힌트성공률
				double noHintWinRate = (double) partyUserRank.getNoHintGame() / partyUserRank.getTotalGame();
	
				noHintWinRate = Math.round(noHintWinRate * 100) / 100.00;
				
				partyApplicantList.get(i).setNoHintWinRate(noHintWinRate * 100);
				
				//유저 현재순위, 닉네임, 프로필가져오기
				PartyVo partySelectRankList = rankDao.partySelectRankList(userNo);
				
				String nickname = userNickName.getNickname();
				int rank = partySelectRankList.getRank();
				
				partyApplicantList.get(i).setRank(rank);
				partyApplicantList.get(i).setNickname(nickname);
				
				String profile = userNickName.getProfile();
				partyApplicantList.get(i).setProfile(profile);

			} else {
				
				String clearTime = "0분 0초";
				partyApplicantList.get(i).setShowAvgClearTime(clearTime);
				
				double winRate = 0;
				partyApplicantList.get(i).setWinRate(winRate);
				
				double noHintWinRate = 0;
				partyApplicantList.get(i).setNoHintWinRate(noHintWinRate);
				
				String nickname = userNickName.getNickname();
				int rank = 0;
				partyApplicantList.get(i).setRank(rank);
				partyApplicantList.get(i).setNickname(nickname);
				
				String profile = userNickName.getProfile();
				partyApplicantList.get(i).setProfile(profile);

			}
			
		}
		
		
		//Map 열어서 각종 Vo와 List 담아주기
		Map<String, Object> pReadMap = new HashMap<String, Object>();
		
		pReadMap.put("partyReadList", partyReadList);
		pReadMap.put("partyDetailList", partyDetailList);
		pReadMap.put("partyApplicantList", partyApplicantList);
		
		return pReadMap;
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 파티삭제를 위한 partyNo 받기
	public int partyDelete(int partyNo) {
		
		int count = partyDao.partyDelete(partyNo);
		
		return count;
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 참여자가 참여신청을 눌렀을 경우
	public boolean addPartyApplicant(PartyVo partyVo) {
		
		//파티대기멤버 확인
		PartyVo appPartyVo = partyDao.selectPartyApplicant(partyVo);
		
		//파티승인멤버 확인
		PartyVo partyDetailCheck = partyDao.partyDetailCheckSelect(partyVo);
		
		
		if(appPartyVo == null && partyDetailCheck == null) {
			
			partyDao.insertApplicant(partyVo);
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	
	// 21-10-06 by 대니
	// partyRead에서 참여자가 참가취소를 눌렀을 경우
	public boolean cancelPartyApplicant(PartyVo partyVo) {

		PartyVo appPartyVo = partyDao.selectPartyApplicant(partyVo);

		if(appPartyVo != null) {
			
			partyDao.deleteApplicant(partyVo);
			
			return true;
			
		} else {

			return false;
			
		}
		
	}

	
	//21-10-06 by 대니
	//방장이 대기자 O버튼을 눌렀을 경우
	public boolean agreeMember(PartyVo partyVo) {
		
		//partyDetail에 있는지 없는지 체크
		PartyVo partyDetailCheck = partyDao.partyDetailCheckSelect(partyVo);
		
		//인원수 확인을위한 파티No 추출
		int partyNo = partyVo.getPartyNo();
		
		//파티승인 총 멤버수
		PartyVo pDetailCount = partyDao.getPartyNoList(partyNo);
		
		//해당 파티의 모임인원수
		PartyVo partyReserveP = partyDao.getReservePerson(partyNo);
		
		if(pDetailCount.getUserCount() >= partyReserveP.getReservePerson() || partyDetailCheck != null) {
			
			return false;
			
		} else {
			
			//파티대기멤버에서 삭제
			partyDao.deleteApplicant(partyVo);
			
			//파티승인멤버에 추가
			partyDao.pDetailEntryMember(partyVo);
			
			return true;
		}
		
	}

	
	//21-10-06 by 대니
	//방장이 partyDetail에서 멤버취소를 눌렀을 경우
	public boolean exceptDetailMember(PartyVo partyVo) {
		
		//partyDetail에서 멤버삭제
		partyDao.exceptDetailMember(partyVo);
		
		//추방된 멤버 partyApplicant로 다시 추가
		partyDao.insertApplicant(partyVo);
		
		return true;
	}


	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집완료 버튼을 클릭했을경우
	public boolean partyCompleteUdate(PartyVo partyVo) {
		
		//partyState을 "모집완료"로 업데이트
		partyDao.partyCompleteUdate(partyVo);
		
		return true;
	}

	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집중 버튼을 클릭했을경우
	public boolean partyWorkingUdate(PartyVo partyVo) {
		
		//partyState을 "모집완료"로 업데이트
		partyDao.partyWorkingUdate(partyVo);
		
		return true;
	}


	//21-10-08 by 대니
	//방장이 예약하기 버튼을 클릭했을때
	public boolean partyReserveCheck(PartyVo partyVo) {
		
		int partyNo = partyVo.getPartyNo();
		
		PartyVo partyStateCheck = partyDao.getPartyReadList(partyNo);
		
		String partyState = partyStateCheck.getPartyState();
		
		if(partyState.equals("모집완료")) {
			
			return true;
			
		} else {
			
			return false;
			
		}  
		
	}

	
	//21-10-15 by 대니
	//파티에서 예약하기 눌렀을때 reserve컨트롤러에서 넘어온 파티No
	public ReserveVo getPartyDetailCount(int partyNo) {
		
		ReserveVo partyCount = partyDao.getPartyDetailCount(partyNo);
		
		return partyCount;
		
	}
	
	
	//21-10-15 by 대니
	//파티에서 예약하기 눌렀을때 reserve에서 넘어온 파티no로 userno찾기
	public List<ReserveVo> pDetailUserList(int partyNo) {
		
		List<ReserveVo> pDetailUserList = partyDao.pDetailUserList(partyNo);
		
		return pDetailUserList;
	}
	
	
}
