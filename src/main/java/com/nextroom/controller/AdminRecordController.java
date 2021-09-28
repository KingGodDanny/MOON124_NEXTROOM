package com.nextroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nextroom.service.RecordService;
import com.nextroom.vo.RecordVo;


@Controller
@RequestMapping("/admin/record")
public class AdminRecordController {
	
	@Autowired RecordService recordService;
	
		//관리자페이지 기록수정
		@RequestMapping("/addRecord")
		public String addRecord(@ModelAttribute RecordVo recordVo,
								@RequestParam(value="recordClear", required = false, defaultValue = "success") String clearState,
								@RequestParam(value="recordMin", required = false, defaultValue = "0") int minutes,
								@RequestParam(value="recordSec", required = false, defaultValue = "0") int secconds,
								@RequestParam(value="member") List<String> members) {
		   
		   recordVo.setRecordTime((minutes*60)+secconds);
		   recordVo.setRecordClear(clearState);
		   
		   System.out.println(recordVo);
		   
		   recordService.RecordAdd(recordVo,members);
		   
		   
		   return "redirect:/admin/record";
		}
}
	
	