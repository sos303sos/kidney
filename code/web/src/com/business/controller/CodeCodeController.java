package com.business.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.model.CodeCode;
import com.platform.mongodb.test.Comment;
import com.platform.mongodb.test.CommentDao;

@Controller
@RequestMapping(value = "/test")
public class CodeCodeController {

	// /test/test.do
	@RequestMapping(value = "/test")
	@ResponseBody
	public CodeCode list(String a,String html) {
		CodeCode bean = new CodeCode();
		bean.setCodeName(a);
		Comment comment = new Comment();
		comment.setCommentText(a);
		comment.setCommentTime(new Date().getTime());
		comment.setHtml(html);
		new CommentDao().insert(comment);
		return bean;
	}

}
