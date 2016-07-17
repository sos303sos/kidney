package com.platform.mongodb.test;

import java.util.HashMap;
import java.util.Map;

public class CommentTest {
	public static void main(String[] args) {
		//1.添加方法
		//testSave();
		//2.根据id删除
		//removeById();
		//3.根据id修改
		//testUpadte();
		//4.根据id查询
		//findById();
		//5.添加单属性索引
		//db.comment.ensureIndex({'commentTime':-1})
		//6.按照时间倒序排列并每次加载10条
		//findByPage();
		
		
	}

	public static void testSave() {
			Map<String, String> params = new HashMap<String, String>();
			params.put("commentText", "内容");
			params.put("html", "<html><a hred='www.baidu.com'/><input ddd='ddd'/><textarea>ddddjdjdjd</textarea></html>");
			params.put("commentTime", "" + System.currentTimeMillis());
			HttpXmlClient.post("http://localhost:8081/bx_app/comment/insert.do", params);
	}
	public static void testUpadte() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "554c5da71751681891bacf14");
		params.put("commentText", "3333");
		params.put("html", "<html><a hred='www.baidu.com'/><input ddd='ddd'/><textarea>ddddjdjdjd</textarea></html>");
		params.put("commentTime", "" + System.currentTimeMillis());
		HttpXmlClient.post("http://localhost:8081/bx_app/comment/update.do", params);
	}
	public static void removeById() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id","554c5d671751681891bacf13");
		params.put("_id","2");
		HttpXmlClient.post("http://localhost:8081/bx_app/comment/removeById.do", params);
	}
	public static void findById() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id","554c5da71751681891bacf14");
		String a=HttpXmlClient.post("http://localhost:8081/bx_app/comment/findById.do", params);
		System.out.println(a);
	}
	public static void findByPage() {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("id","554ac464014f7828d3ec0225");
		params.put("skip","0");
		params.put("limit","10");
		String a=HttpXmlClient.post("http://localhost:8081/bx_app/comment/findByPage.do", params);
		System.out.println(a);
	}
}
