<%@page import="review.model.NoticeVO"%>
<%@page import="review.model.ReviewDAO"%>
<%@page import="org.json.simple.*"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ReviewDAO dao = ReviewDAO.getInstance();

	List<NoticeVO> flist = dao.getNoticeList();
	
	JSONArray jsonList = new JSONArray();
	for(NoticeVO vo : flist){
		JSONObject obj = new JSONObject();
		obj.put("notice_no", vo.getNotice_no());
		obj.put("notice_title", vo.getNotice_title());
		jsonList.add(obj);
	}
	// 클라이언트에게 응답으로 보냄
	out.println(jsonList.toJSONString());

%>