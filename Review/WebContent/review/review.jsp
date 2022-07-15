<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="review.model.*" %>    
  
<%
	//**** request.getParameter("")
	//**** request.getAttribute("")	
	List<CateVO> cateList = (List<CateVO>)request.getAttribute("catelist");
	List<FoodVO> foodList = (List<FoodVO>)request.getAttribute("foodlist");
%>

<% String pjName = "/Review"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>review.jsp</title>
<!-- Jquery 로딩 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(document).ready(function(){
		$("input.CateNo").change(function(){
			//alert("ok:"+$(this).val()); // 태그 사이의 값을 얻거나 지정할 때: text(), html()
										// value 속성의 값을 얻거나 지정할 때 : val()
			//window.location = 'reviewtest?cmd=list-page&cate='+$(this).val()
			<%-- window.location = '<%=pjName%>/reviewtest?cmd=list-page&cate='+$(this).val() --%>
			/*
				Ajax도 MVC 모델2 기반에서 가능하지만 Ajax의 쉬운 이해를 위해 모델1을 사용
			*/
			$.ajax({
				type : 'post',
				url : 'review/ajaxTest.jsp',
				data : { cate : $(this).val()}, // 보낼 파라미터
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){
					/* var objList = JSON.parse(result); */
					console.log(result);
					
					$('#foodList').empty();
					// for..in (인덱스 뽑힘)/ for..of (객체 뽑힘_)
					for(obj of result){
						var templi = $('<li/>');
						templi.text(obj['foodName']);
						$('#foodList').append(templi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});
					
		});
	});
</script>
	
</head>
<style>
</style>
<body>
	<table border=1 width="800px" heigt="300px">
		<tr>
			<td>
				[종류 선택]<br>
				<%for(CateVO list : cateList){%>
					<input type="radio" class='CateNo' name="CateNo"
						value="<%=list.getCate_no()%>"/>
					<%=list.getCate_name()%><br> 
				<%}%>				
			</td>
			<td>
				[메뉴 선택]<br>
				<ul id='foodList'>
					<%for(FoodVO list : foodList){%>
						 <li> <%=list.getFood_name()%> </li> 
					<%}%>
				</ul>
			</td>			
		</tr>
	</table>

<!-- <script>
	$("input[name='CateNo']").change(function(){
		//alert("ok:"+$(this).val()); // 태그 사이의 값을 얻거나 지정할 때: text(), html()
									// value 속성의 값을 얻거나 지정할 때 : val()
		window.location = 'reviewtest?cmd=list-page&cate='+$(this).val()
	});
</script> -->

</body>
</html>