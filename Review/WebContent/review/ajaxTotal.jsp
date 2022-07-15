<%@page import="review.model.TotalVO"%>
<%@page import="review.model.InquiryVO"%>
<%@page import="review.model.ReviewDAO"%>
<%@page import="org.json.simple.*"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ReviewDAO dao = ReviewDAO.getInstance();
	String keyValue = request.getParameter("keyValue");
	List<TotalVO> flist = dao.getTotalList(keyValue);
	System.out.println(keyValue);
	
	JSONArray jsonList = new JSONArray();
	for(TotalVO vo : flist){
		JSONObject obj = new JSONObject();
		obj.put("no", vo.getNo());
		obj.put("title", vo.getTitle());
		jsonList.add(obj);
	}
	// 클라이언트에게 응답으로 보냄
	out.println(jsonList.toJSONString());

%>