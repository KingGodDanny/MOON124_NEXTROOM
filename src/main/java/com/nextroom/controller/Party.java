package com.nextroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextroom.service.PartyService;
import com.nextroom.vo.PartyVo;

@RequestMapping("/party")
@Controller
public class Party {
	
	@Autowired
	private PartyService partyService;
	
	
	//파티 읽기폼
	@RequestMapping("/partyRead")
	public String partyRead() {
		System.out.println("partyRead");
		return "party/partyRead";
	}
	
	//21-09-26 by 대니
	//sido로 카페No, 카페이름가져오기
	@ResponseBody
	@RequestMapping(value = "/sido", method = {RequestMethod.GET, RequestMethod.POST})
	public List<PartyVo> cafeList(@RequestParam("sido") String sido) {
		
		List<PartyVo> cafeList = partyService.getCafeList(sido);
		
		return cafeList;
	}
	
	
	//21-09-27 by 대니
	//cafeNo로 테마NO, 테마이름가져오기
	@ResponseBody
	@RequestMapping(value = "/cafe", method = {RequestMethod.GET, RequestMethod.POST})
	public List<PartyVo> themeList(@RequestParam("cafeNo") int cafeNo) {
		
		List<PartyVo> themeList = partyService.getThemeList(cafeNo);
		
		return themeList;
	}
	
	
	//21-09-28 by 대니
	//themeNo, reserveDate로 시간표 가져오기
	@ResponseBody
	@RequestMapping(value = "/theme", method = {RequestMethod.GET, RequestMethod.POST})
	public List<PartyVo> themeTimeList(@RequestParam("themeNo") int themeNo,
										@RequestParam("reserveDate") String reserveDate) {
		
		Map<String, Object> themeTimeMap = new HashMap<String, Object>();
		
		themeTimeMap.put("themeNo", themeNo);
		themeTimeMap.put("reserveDate", reserveDate);
		
		List<PartyVo> themeTimeList = partyService.getThemeTimeList(themeTimeMap);
		
		return themeTimeList;
	}
	
	
	//21-09-29 by 대니
	//파티등록폼에서 넘어온 정보로 파티리스트 인서트!
	@RequestMapping(value = "partyWrite", method = {RequestMethod.GET, RequestMethod.POST})
	public String partyWrite(@ModelAttribute PartyVo partyVo) {
		
		partyService.addPartyList(partyVo);
		
		return "redirect:/party/partyList";
	}
	
	
	//21-10-08 by 대니
	//파티등록 리스트 목록 출력하기(페이징 + 검색)
	@RequestMapping(value = "/partyList", method = {RequestMethod.GET, RequestMethod.POST})
	public String partyList(Model model,
							@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
							@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
							@RequestParam(value = "partyView", required = false, defaultValue = "") String partyView) {

		Map<String, Object> partyAllListMap = partyService.getPartyList(crtPage, keyword, partyView);
		
		model.addAttribute("partyAllListMap", partyAllListMap);
		
		return "party/partyList";
	}
	
	
	//21-10-03 by 대니
	//리스트에서 넘어온 파티No로 partyRead에 정보 뿌려주기
	@RequestMapping(value= "/partyRead", method = {RequestMethod.GET, RequestMethod.POST})
	public String partyRead(@RequestParam("partyNo") int partyNo, Model model) {
		
		Map<String, Object> pReadMap = partyService.getPartyReadList(partyNo);
		
		model.addAttribute("pReadMap", pReadMap);
		
		return "party/partyRead";
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 파티삭제를 위한 partyNo 받기
	@ResponseBody
	@RequestMapping(value="/partyDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public int partyDelete(@RequestParam("partyNo") int partyNo) {
		
		int count = partyService.partyDelete(partyNo);
		
		return count;
	}
	
	
	//21-10-05 by 대니
	//partyRead에서 참여자가 참여신청을 눌렀을 경우
	@ResponseBody
	@RequestMapping(value = "/addPartyApplicant", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean addPartyApplicant(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.addPartyApplicant(partyVo);
		
		return result;
	}
	
	
	//21-10-06 by 대니
	//partyRead에서 참여자가 참가취소를 눌렀을 경우
	@ResponseBody
	@RequestMapping(value = "/cancelPartyApplicant", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean cancelPartyApplicant(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.cancelPartyApplicant(partyVo);
		
		return result;
	}
	
	
	//21-10-06 by 대니
	//방장이 대기자 O버튼을 눌렀을 경우
	@ResponseBody
	@RequestMapping(value = "/agreeMember", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean agreeMember(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.agreeMember(partyVo);
		
		return result;
	}
	
	
	//21-10-06 by 대니
	//방장이 partyDetail에서 멤버취소를 눌렀을 경우
	@ResponseBody
	@RequestMapping(value = "/exceptDetailMember", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean exceptDetailMember(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.exceptDetailMember(partyVo);
		
		return result;
		
	}
	
	
	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집완료 버튼을 클릭했을경우
	@ResponseBody
	@RequestMapping(value = "/partyComplete", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean partyComplete(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.partyCompleteUdate(partyVo);
		
		return result;
	}
	
	//21-10-07 by 대니
	//방장이 자신의 파티에서 모집중 버튼을 클릭했을경우
	@ResponseBody
	@RequestMapping(value = "/partyWorking", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean partyWorking(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.partyWorkingUdate(partyVo);
		
		return result;
	}
	
	
	//21-10-08 by 대니
	//방장이 예약하기 버튼을 클릭했을때
	@ResponseBody
	@RequestMapping(value = "/partyReserveCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean partyReserveCheck(@ModelAttribute PartyVo partyVo) {
		
		boolean result = partyService.partyReserveCheck(partyVo);
		
		System.out.println(result);
		
		return result;
	}
	
	
}
