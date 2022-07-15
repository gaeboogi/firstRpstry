<%@page import="review.model.InquiryVO"%>
<%@page import="review.model.ReviewDAO"%>
<%@page import="org.json.simple.*"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ReviewDAO dao = ReviewDAO.getInstance();

	List<InquiryVO> flist = dao.getInquiryList();
	
	JSONArray jsonList = new JSONArray();
	for(InquiryVO vo : flist){
		JSONObject obj = new JSONObject();
		obj.put("inquiry_no", vo.getInquiry_no());
		obj.put("inquiry_title", vo.getInquiry_title());
		jsonList.add(obj);
	}
	// 클라이언트에게 응답으로 보냄
	out.println(jsonList.toJSONString());

%>