package com.nextroom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nextroom.service.CafeService;
import com.nextroom.vo.CafeVo;

@RequestMapping("/cafe")
@Controller
public class Cafe {

	@Autowired
	private CafeService cafeService;


	// 카페 메인
	@RequestMapping(value="", method = { RequestMethod.GET, RequestMethod.POST })
	public String cafeMain(HttpSession session, Model model, 
						   @RequestParam(value="region", required = false, defaultValue = "전국") String region) {
	   System.out.println("cafe/main");
	   	   
	   List<CafeVo> cafeList = cafeService.getCafeList(region);
	   System.out.println(cafeList);
	   model.addAttribute("cafeList", cafeList);
	   
	   return "cafe/cafeMain";
	}

	// 카페 상세페이지
	@RequestMapping(value = "/{cafeNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public String cafeDetail(@PathVariable("cafeNo") int cafeNo, Model model) {
		System.out.println("cafeDetail");

		Map<Object, Object> detailMap = cafeService.getCafeDetail(cafeNo);

		model.addAttribute("detailMap", detailMap);

		return "cafe/cafeDetail";
	}

	// 테마 메인
	@RequestMapping(value = "/theme", method = { RequestMethod.GET, RequestMethod.POST })
	public String themeMain(Model model) {
		System.out.println("themeMain");

		model.addAttribute("themeList", cafeService.getThemeList());

		return "theme/themeMain";
	}

	// 테마 상세페이지
	@RequestMapping(value = "/theme/{themeNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public String themeDetail(@PathVariable("themeNo") int themeNo, Model model) {
		System.out.println("themeDetail");

//	   Map<Object, Object> themeMap = cafeService.getTheme(themeNo);
//	   model.addAttribute("themeMap", themeMap);

		model.addAttribute("themeVo", cafeService.getOneTheme(themeNo));
		model.addAttribute("timeList", cafeService.getOneTime(themeNo));
		model.addAttribute("priceList", cafeService.getOnePrice(themeNo));
		model.addAttribute("reviewList", cafeService.getReviewList(themeNo));

		return "theme/themeDetail";
	}

}
