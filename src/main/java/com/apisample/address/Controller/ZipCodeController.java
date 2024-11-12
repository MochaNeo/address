package com.apisample.address.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apisample.address.DTO.ZipCodeDto;
import com.apisample.address.Service.ZipCodeService;

import jakarta.servlet.http.HttpSession;

//Controllerクラス
@Controller
public class ZipCodeController {
    //serviceをdi
    @Autowired ZipCodeService zpcService;

    /**
	 * 郵便番号入力フォーム
	 * @return "zipcode"
	 */
	@GetMapping("/zipcode")
	public String zipcodeForm(HttpSession session, Model model) {
		return "zipcode";
	}

	/**
	 * 郵便番号情報表示
	 * @return "zipcode-confirm"
	 */
	@PostMapping("/zipcode/confirm")
	public String zipcodeConfirm(HttpSession session, Model model,
        @RequestParam("zipcode") String zipcode){
		// 一応必須チェックのみ 数字・桁数チェックは省略
		// nullまたは空文字の場合、入力フォームにエラーメッセージを表示
		if (zipcode == null || zipcode.equals("")) {
			model.addAttribute("errorMessage", "郵便番号を入力してください。");
			return zipcodeForm(session, model);
		}

		// 郵便番号検索APIサービス呼び出し
		ZipCodeDto zipCodeDto = zpcService.service(zipcode);
		// thymeleafでリストを展開して表示する
        model.addAttribute("zipcodeList", zipCodeDto.getResults());
		return "zipcode-confirm";
	}
}
