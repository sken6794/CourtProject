package com.court.proj.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MypageService mypageService;

	@GetMapping("/main")
	public String mypage(Model model) {
		
		int user_proper_num = 1;
		UserVO userVO = mypageService.getUser(user_proper_num);
		model.addAttribute("userVO", userVO);
		System.out.println(userVO.toString());
		
		return "mypage/main";
	}

	@GetMapping("/modify")
	public String modify(Model model) {
		
		int user_proper_num = 1;
		UserVO userVO = mypageService.getUser(user_proper_num);
		model.addAttribute("userVO", userVO);
		System.out.println(userVO.toString());
		
		return "mypage/modify";
	}
	
	@GetMapping("/history")
	public String history(Model model) {

		int user_proper_num = 1;

		ArrayList<ActiveVO> list = mypageService.getHistory(user_proper_num);
		model.addAttribute("list", list);
		
		return "mypage/history";
	}
	
	@GetMapping("/pause")
	public String pause() {
		return "mypage/pause";
	}
	
	@GetMapping("/status")
	public String status(Model model) {
		
		int user_proper_num = 1;
		ArrayList<MypageStatusVO> list = mypageService.getStatus(user_proper_num);
		System.out.println(list.toString());
		model.addAttribute("list", list);
		
		int aplcn_dtls_proper_num = 12; 
		ArrayList<MypageStatusVO> list2 = mypageService.getDocu(aplcn_dtls_proper_num);
		model.addAttribute("list2", list2);
		
		
		return "mypage/status";
	}


	@PostMapping("/deleteForm")
	public String del(UserVO vo) {
		
		int result = mypageService.deleteUpdate(vo);
		System.out.println(result);
		
		return "redirect:../main/";
	}

	@PostMapping("/modifyForm")
	public String modifyForm(UserVO vo, RedirectAttributes rra) {
		
		int result = mypageService.modifyUpdate(vo);
		String msg = result == 1? "회원정보가 변경되었습니다." : "변경실패. 관리자에게 문의하세요";
		rra.addFlashAttribute("msg", msg);
		System.out.println(result);
		return "redirect:/mypage/main";
	}

	
	@PostMapping("/pauseForm")
	public String pauseForm(PauseDataVO vo, RedirectAttributes rra) {
		
		int result = mypageService.regPause(vo);
		String msg = result == 1? "신청되었습니다." : "신청실패. 관리자에게 문의하세요";
		rra.addFlashAttribute("msg", msg);
		System.out.println(result);
		return "redirect:/mypage/main";
	}
}
