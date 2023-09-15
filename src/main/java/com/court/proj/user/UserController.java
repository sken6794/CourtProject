package com.court.proj.user;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DefaultMessageService messageService;

	@GetMapping("/join")
	public String join() {
		return "user/userjoin";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String err, Model model) {

		if (err != null) {
			model.addAttribute("msg", err);
		}
		return "user/login";
	}

	@GetMapping("/test")
	public String test() {
		return "user/test";
	}

	@GetMapping("/test2")
	public String test2() {
		return "user/test2";
	}

	@GetMapping("/error")
	public String error() {
		return "user/403error";
	}

	@GetMapping("/agree")
	public String agree() {
		return "user/agree";
	}

	@GetMapping("/idSearch")
	public String idSearch() {
		return "user/idSearch";
	}

	@GetMapping("/pwSearch")
	public String pwSearch() {
		return "user/pwSearch";
	}

	// 회원가입
	@PostMapping("/joinForm")
	public String joinForm(@Valid @ModelAttribute("vo") UserVO vo, Errors errors, Model model) {

		if (errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				System.out.println(err.getDefaultMessage());
			}
			return "user/userjoin";
		}
		System.out.println(vo.toString());
		String pw = bCryptPasswordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(pw);
		userService.joinUser(vo);
		return "redirect:/login";
	}

	// 아이디 중복확인
	@PostMapping("/checkDuplicateUsername")
	public @ResponseBody ResponseEntity<Boolean> checkDuplicate(@RequestParam("userid") String userid) {

		boolean bool = false;
		int re = userService.checkId(userid);
		if (re == 0) {
			bool = true;
		}

		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
	}

	// 휴대폰 인증번호
	@PostMapping("/send-one")
	public @ResponseBody ResponseEntity<Integer> sendOne(@RequestParam("phone") String phone) {
		//Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		//message.setFrom("01073251252");
		//message.setTo(phone);

		Random random = new Random();

		int randomNumber = random.nextInt(900000) + 100000;

		//message.setText("인증번호 : " + randomNumber);

		//SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		//System.out.println(response);

		return new ResponseEntity<Integer>(randomNumber, HttpStatus.OK);
	}

	// ID 찾기
	@PostMapping("/searchId")
	public @ResponseBody ResponseEntity<String> searchId(@RequestParam("phone") String phone) {

		// 어차피 성공했을 때 이 요청을 실행할거니까 괜찮음
		// 실행되면 전달받은 휴대폰으로 아이디 찾아서 전달하기

		return new ResponseEntity<>(userService.searchId(phone), HttpStatus.OK);
	}

	// PW 찾기
	@PostMapping("/updatePw")
	public @ResponseBody ResponseEntity<Integer> searchPw(@RequestParam("phone") String phone,
			@RequestParam("newPw") String newPw) {
		String newPassword = bCryptPasswordEncoder.encode(newPw);

		return new ResponseEntity<>(userService.updatePw(phone, newPassword), HttpStatus.OK);
	}

}
