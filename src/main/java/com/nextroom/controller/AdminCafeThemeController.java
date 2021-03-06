package com.nextroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextroom.service.CafeService;
import com.nextroom.vo.CafeVo;
import com.nextroom.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class AdminCafeThemeController {

	@Autowired
	private CafeService cafeService;

	// 관리자페이지 예약확인
	@RequestMapping("/reserve")
	public String adminReserve() {
		System.out.println("reserve");
		return "admin/reserve";
	}

	// 관리자페이지 예약관리
	@RequestMapping("/reserveTime")
	public String reserveTime() {
		System.out.println("reserveTime");
		return "admin/reserveTime";
	}

	// 관리자페이지 카페수정폼
	@RequestMapping("/cafeModifyForm")
	public String cafeModifyForm(HttpSession session, Model model) {
		System.out.println("cafeModifyForm");

		int cafeNo = ((UserVo) session.getAttribute("authUser")).getCafeNo();
		System.out.println(cafeNo);

		CafeVo cafeVo = cafeService.getCafe(cafeNo);

		model.addAttribute("cafeVo", cafeVo);
		
		System.out.println(cafeVo);

		return "admin/cafeModifyForm";
	}

	// 카페 수정
	@RequestMapping("/cafe/modify")
	public String themeModify(@ModelAttribute CafeVo cafeVo, HttpSession session) {
		System.out.println("cafeModify");

		cafeService.cafeModify(cafeVo);

		int cafeNo = ((UserVo) session.getAttribute("authUser")).getCafeNo();

		return "redirect:/cafe/" + cafeNo;
	}

	// 관리자페이지 테마 관리 리스트
	@RequestMapping("/{cafeNo}/themeList")
	public String themeList(@PathVariable int cafeNo, Model model) {
		System.out.println("themeList");

		model.addAttribute("themeList", cafeService.getCafeTheme(cafeNo));

		return "admin/themeList";
	}

	// 관리자페이지 테마삭제폼
	@RequestMapping("/themeDeleteForm")
	public String themeDeleteForm() {
		System.out.println("themeDeleteForm");
		return "admin/themeDeleteForm";
	}

	// 관리자페이지 테마수정폼
	@RequestMapping("/theme/modifyForm/{themeNo}")
	public String themeModifyForm(@PathVariable int themeNo, Model model, HttpSession session) {
		System.out.println("themeModifyForm");

		int cafeNo = ((UserVo) session.getAttribute("authUser")).getCafeNo();

		model.addAttribute("themeList", cafeService.getCafeTheme(cafeNo));
		model.addAttribute("themeVo", cafeService.getOneTheme(themeNo));
		model.addAttribute("genreList", cafeService.getGenreList());
		model.addAttribute("priceList", cafeService.getOnePrice(themeNo));
		model.addAttribute("timeList", cafeService.getOneTime(themeNo));
		
		System.out.println("테마수정 테마Vo : "+cafeService.getOneTheme(themeNo));
		
		return "admin/themeModifyForm";
	}

	// 테마수정
	@RequestMapping("/theme/modify")
	public String themeModify(HttpSession session, @ModelAttribute CafeVo cafeVo) {
		System.out.println("themeModify");

		int count = cafeService.themeModify(cafeVo);

		int cafeNo = ((UserVo) session.getAttribute("authUser")).getCafeNo();

		return "redirect:/admin/" + cafeNo + "/themeList";
	}

	// 관리자페이지 테마추가폼
	@RequestMapping("/addThemeForm")
	public String addThemeForm() {
		System.out.println("addThemeForm");
		return "admin/addThemeForm";
	}

	// 관리자페이지 테마추가
	@RequestMapping("/addTheme")
	public String addTheme(@ModelAttribute CafeVo cafeVo) {
		System.out.println("addTheme");

		int count = cafeService.addTheme(cafeVo);

		System.out.println(count + "건 저장되었습니다.");

		int cafeNo = cafeVo.getCafeNo();
		return "redirect:/admin/" + cafeNo + "/themeList";
	}

	// 관리자페이지 테마 삭제
	@ResponseBody
	@RequestMapping("/theme/delete")
	public int deleteTheme(@RequestParam int themeNo) {
		System.out.println("deleteTheme");
		return cafeService.deleteTheme(themeNo);
	}

	// 관리자페이지 인테리어 이미지 삭제
	@ResponseBody
	@RequestMapping("/cafe/inteDelete")
	public int inteDelete(@RequestParam int inteNo) {
		System.out.println("inteDelete");
		return cafeService.inteDelete(inteNo);
	}
}
