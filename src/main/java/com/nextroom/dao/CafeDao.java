package com.nextroom.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextroom.vo.CafeVo;
import com.nextroom.vo.InteriorVo;
import com.nextroom.vo.PriceVo;
import com.nextroom.vo.ReviewBoardVo;
import com.nextroom.vo.ThemeVo;
import com.nextroom.vo.TimeVo;

@Repository
public class CafeDao {

	@Autowired
	private SqlSession sqlSession;

	// 카페 추가
	public int addCafe(CafeVo cafeVo) {

		System.out.println("[CafeDao.addCafe()]");

		return sqlSession.insert("cafe.addCafe", cafeVo);
	}

	// 카페 추가 및 카페번호 가져오기
	public int addCafekey(CafeVo cafeVo) {

		System.out.println("[CafeDao.addCafekey()]");

		return sqlSession.insert("cafe.addCafeKey", cafeVo);
	}

	// 카페 내부이미지 추가
	public int addInteriorImg(InteriorVo interiorVo) {
		System.out.println("[CafeDao.addInteriorImg()]");

		/*
		 * int cafeNo = cafeVo.getCafeNo(); List<InteriorVo> inteList =
		 * cafeVo.getInteList();
		 * 
		 * System.out.println("Dao cafeNo : " + cafeNo);
		 * 
		 * int count = 0;
		 * 
		 * for (int i = 0; i < inteList.size(); i++) {
		 * 
		 * Map<Object, Object> inteMap = new HashMap<Object, Object>();
		 * inteMap.put("cafeNo", cafeNo); inteMap.put("intePath", inteList.get(i));
		 * 
		 * 
		 * System.out.println(inteMap); count++; }
		 */

		return sqlSession.insert("cafe.addInteriorImg", interiorVo);
	}

	// 일반유저 --> 관리자유저로 상태 전환
	public int changeUser(int userNo) {

		System.out.println("[CafeDao.changeUser()]");

		return sqlSession.update("cafe.updateUser", userNo);
	}

	// 한개 카페 정보 가져오기
	public CafeVo getCafe(int cafeNo) {
		System.out.println("[CafeDao.getCafe()]");
		return sqlSession.selectOne("cafe.getCafe", cafeNo);
	}

	// 카페 인테리어 리스트 가져오기
	public List<InteriorVo> getInteList(int cafeNo) {
		System.out.println("[CafeDao.getInteList()]");

		System.out.println("dao cafeNo : " + cafeNo);

		List<InteriorVo> inteList = sqlSession.selectList("cafe.getInteList", cafeNo);

		System.out.println("dao inteList : " + inteList);

		return inteList;
	}

	// 카페 수정
	public int updateCafe(CafeVo cafeVo) {

		System.out.println("[CafeDao.updateCafe()]");

		return sqlSession.update("cafe.updateCafe", cafeVo);
	}

	// 카페 리스트 출력
//	public List<CafeVo> getCafeList() {
//
//		System.out.println("[CafeDao.getCafeList()]");
//
//		return sqlSession.selectList("cafe.getCafeList");
//	}

	public List<CafeVo> getCafeList(String region) {

		System.out.println("[CafeDao.getCafeList()]");

		return sqlSession.selectList("cafe.getCafeList", region);
	}

	// 카페 상세페이지 출력용 데이터 가져오기
	public CafeVo getCafeDetail(int cafeNo) {
		System.out.println("[CafeDao.getCafeDetail()]");

		return sqlSession.selectOne("cafe.getOneCafe", cafeNo);
	}

	// 테마 추가
	public int addTheme(CafeVo cafeVo) {
		System.out.println("[CafeDao.addTheme()]");
		return sqlSession.insert("cafe.addTheme", cafeVo);
	}

	// 테마 시간표 추가
	public int addThemeTime(TimeVo timeVo) {
		System.out.println("[CafeDao.addThemeTime()]");
		return sqlSession.insert("cafe.addThemeTime", timeVo);
	}

	// 테마 가격 추가
	public int addThemePrice(PriceVo priceVo) {
		System.out.println("[CafeDao.addThemePrice()]");
		return sqlSession.insert("cafe.addThemePrice", priceVo);
	}

	// 카페 내 전체 테마 리스트 가져오기
	public List<CafeVo> getCafeTheme(int cafeNo) {
		System.out.println("[CafeDao.getCafeTheme()]");
		System.out.println("getCafeTheme cafeNo : " + cafeNo);
		return sqlSession.selectList("cafe.getCafeTheme", cafeNo);
	}

	// 전체 테마 가격 가져오기
	public List<Object> getAllPrice(CafeVo getPriceVo) {
		System.out.println("[CafeDao.getAllPrice()]");

		List<Object> priceList = new ArrayList<Object>();

		int pMax = getPriceVo.getpMax();
		int pMin = getPriceVo.getpMin();

		for (int i = pMin; i <= pMax; i++) {
			getPriceVo.setpMin(i);
			priceList.add(sqlSession.selectList("cafe.getAllPrice", getPriceVo));
			System.out.println("DAO priceList : " + priceList);

		}
		// List<PriceVo> priceList = sqlSession.selectList("cafe.getAllPrice", cafeNo);

		System.out.println(priceList);

		return priceList;
	}

	// 테마 체감난이도, 별점 가져오기
	public List<ReviewBoardVo> getRating(int cafeNo) {
		System.out.println("[CafeDao.getRating()]");
		return sqlSession.selectList("cafe.getRating", cafeNo);
	}

	// 카페 내 전체 테마 최대인원 최소인원 구하기
	public CafeVo getMinMaxP(int cafeNo) {
		System.out.println("[CafeDao.getMinMaxP()]");
		CafeVo minMaxVo = sqlSession.selectOne("cafe.getMinMaxP", cafeNo);
		System.out.println("Dao min max 가져옴");
		return minMaxVo;
	}

	// 테마당 인원별 가격 한개 가져오기
	public Integer getPriceOne(PriceVo priceVo) {
		System.out.println("[CafeDao.getPriceOne()]");
		return sqlSession.selectOne("cafe.getPriceOne", priceVo);
	}

	//////////////////////////// 테마 상세페이지////////////////////////////
	// 테마 출력용 한개 정보 가져오기(리뷰포함)
	public CafeVo getOneTheme(int themeNo) {
		System.out.println("[CafeDao.getOneTheme()]");
		System.out.println("카페한개정보 가져오기 themeNo : " + themeNo);
		return sqlSession.selectOne("cafe.getOneTheme", themeNo);
	}

//	// 테마 수정용 한개 정보 가져오기(리뷰 미포함)
//		public CafeVo getModiTheme(int themeNo) {
//			System.out.println("[CafeDao.getModiTheme()]");
//			System.out.println("카페한개정보 가져오기 themeNo : "+themeNo);
//			return sqlSession.selectOne("cafe.getModiTheme", themeNo);
//		}

	// 테마 한개 시간표 가져오기
	public List<TimeVo> getOneTime(int themeNo) {
		System.out.println("[CafeDao.getOneTime()]");
		return sqlSession.selectList("cafe.getOneTime", themeNo);
	}

	// 테마 한개 가격표 가져오기
	public List<PriceVo> getOnePrice(int themeNo) {
		System.out.println("[CafeDao.getOnePrice()]");
		return sqlSession.selectList("cafe.getOnePrice", themeNo);
	}

	// 테마 한개 리뷰 가져오기
	public List<ReviewBoardVo> getReviewList(int themeNo) {
		System.out.println("[CafeDao.getReviewList()]");
		return sqlSession.selectList("cafe.getReviewList", themeNo);
	}

	// 테마 한개 리뷰 개수 가져오기
//	public ThemeVo getReviewCount(int themeNo) {
//		System.out.println("[CafeDao.getReviewList()]");
//		return sqlSession.selectOne("cafe.getReviewCountAvg", themeNo);
//	}

	// 장르 리스트 가져오기
	public List<CafeVo> getGenreList() {
		System.out.println("[CafeDao.getGenreList()]");
		return sqlSession.selectList("cafe.getGenreList");
	}

	// 전체 테마 리스트 가져오기
	public List<CafeVo> getThemeList(Map<String, Object> getThemeMap) {
		System.out.println("[CafeDao.getThemeList()]");
		return sqlSession.selectList("cafe.getThemeList", getThemeMap);
	}
	
	// 테마 검색하기
	public List<CafeVo> themeSearchList(Map<String, Object> getThemeMap) {
		System.out.println("[CafeDao.themeSearchList()]");
		return sqlSession.selectList("cafe.themeSearchList", getThemeMap);
	}

	// 탑 10 테마 리스트 가져오기
	public List<CafeVo> get10Theme() {
		System.out.println("[CafeDao.get10Theme()]");
		return sqlSession.selectList("cafe.get10Theme");
	}

	// ajax 카페 목록 가져오기

	public List<CafeVo> cafeList(Map<String, Object> map) {
		System.out.println("[CafeDao.cafeList()]");
		System.out.println(map);
		List<CafeVo> cafeList = sqlSession.selectList("cafe.getApiCafeList", map);

		return cafeList;
	}

	// ajax 테마 목록 가져오기
	public List<CafeVo> getApiThemeList(Map<String, Object> map) {
		System.out.println("[CafeDao.getApiThemeList()]");
		System.out.println(map);
		List<CafeVo> themeList = sqlSession.selectList("cafe.getApiThemeList", map);

		return themeList;
	}

	// 카페 수정 - 파일이 있을때
	public int cafeModify_basic(CafeVo cafeVo) {
		System.out.println("[CafeDao.themeModify_basic()]");
		return sqlSession.update("cafe.updateCafe-basic", cafeVo);
	}

	// 카페 수정 - 파일이 없을때
	public int cafeModify_noFile(CafeVo cafeVo) {
		System.out.println("[CafeDao.themeModify_noFile()]");
		System.out.println("업데이트 전 cafeVo : " + cafeVo);
		return sqlSession.update("cafe.updateCafe-noFile", cafeVo);
	}

	// 테마 수정 - 파일이 있을때
	public int updateTheme_basic(CafeVo cafeVo) {
		System.out.println("[CafeDao.themeModify_basic()]");
//		sqlSession.update("cafe.updateThemePrice", cafeVo);
//		sqlSession.update("cafe.updateThemeTime", cafeVo);
		return sqlSession.update("cafe.updateTheme-basic", cafeVo);
	}

	// 테마 수정 - 파일이 없을때
	public int updateTheme_noFile(CafeVo cafeVo) {
		System.out.println("[CafeDao.themeModify_noFile()]");
//		sqlSession.update("cafe.updateThemePrice", cafeVo);
//		sqlSession.update("cafe.updateThemeTime", cafeVo);
		return sqlSession.update("cafe.updateTheme-noFile", cafeVo);
	}

	// 테마 가격 삭제
	public int deleteThemePrice(int themeNo) {
		System.out.println("[CafeDao.deleteThemePrice()]");

		return sqlSession.update("cafe.deleteThemePrice", themeNo);
	}

	// 테마 시간표 삭제
	public int deleteThemeTime(int themeNo) {
		System.out.println("[CafeDao.deleteThemeTime()]");

		return sqlSession.update("cafe.deleteThemeTime", themeNo);
	}

	// 테마 삭제(업데이트)
	public int updateTheme(int themeNo) {
		System.out.println("[CafeDao.updateTheme()]");
		return sqlSession.update("cafe.updateTheme-delete", themeNo);
	}

	// 인테리어 이미지 삭제
	public int inteDelete(int inteNo) {
		System.out.println("[CafeDao.inteDelete()]");
		return sqlSession.delete("cafe.inteDelete", inteNo);
	}

}
