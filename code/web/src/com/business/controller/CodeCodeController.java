package com.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.model.CodeCode;

@Controller
@RequestMapping(value = "/test")
public class CodeCodeController {

	@RequestMapping(value = "/test")
	@ResponseBody
	public CodeCode list(String a) {
		CodeCode bean=new CodeCode();
		bean.setCodeName(a);
		return bean;
	}

}
