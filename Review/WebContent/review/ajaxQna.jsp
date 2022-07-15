<%@page import="review.model.QnaVO"%>
<%@page import="review.model.ReviewDAO"%>
<%@page import="org.json.simple.*"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ReviewDAO dao = ReviewDAO.getInstance();

	List<QnaVO> flist = dao.getQnaList();
	
	JSONArray jsonList = new JSONArray();
	for(QnaVO vo : flist){
		JSONObject obj = new JSONObject();
		obj.put("qna_no", vo.getQna_no());
		obj.put("qna_title", vo.getQna_title());
		jsonList.add(obj);
	}
	// 클라이언트에게 응답으로 보냄
	out.println(jsonList.toJSONString());
%>