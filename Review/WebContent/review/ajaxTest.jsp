<%@page import="review.model.FoodVO"%>
<%@page import="java.util.List"%>
<%@page import="review.model.ReviewDAO"%>
<%@page import="org.json.simple.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<%
	ReviewDAO dao = ReviewDAO.getInstance();

	int cateNo = 1;
	String cate = request.getParameter("cate");
	if(cate != null) cateNo = Integer.parseInt(cate);
	List<FoodVO> flist = dao.getFoodList(cateNo);
	
	JSONArray jsonList = new JSONArray();
	for(FoodVO vo : flist){
		JSONObject obj = new JSONObject();
		obj.put("cateNo", vo.getCate_no());
		obj.put("foodNo", vo.getFood_no());
		obj.put("foodName", vo.getFood_name());
		jsonList.add(obj);
	}
	// 클라이언트에게 응답으로 보냄
	out.println(jsonList.toJSONString());

%>