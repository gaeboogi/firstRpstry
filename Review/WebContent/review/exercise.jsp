<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exercise.jsp</title>
<!-- Jquery 로딩 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(document).ready(function(){
		// 페이지 로딩과 동시에 각 카테고리 전체 내용을 가져오기 위한 ajax
			// 공지사항
			$.ajax({
				type : 'post',
				url : 'review/ajaxNotice.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){	
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['notice_no']);
						tempLi2.text(obj['notice_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});
			
			// 질문과 답변
			$.ajax({
				type : 'post',
				url : 'review/ajaxQna.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){
	
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['qna_no']);
						tempLi2.text(obj['qna_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});	
			
			// 저자문의
			$.ajax({
				type : 'post',
				url : 'review/ajaxInquiry.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){
					
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['inquiry_no']);
						tempLi2.text(obj['inquiry_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});	
		// end------------------------------------------------------
		
	
		
		
		
		
		
		
		// a태그 '공지사항'이 눌렸을 때 공지사항 내용 불러오기
		$("dt.tab_btn1").click(function(){
			$.ajax({
				type : 'post',
				url : 'review/ajaxNotice.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){					
					$('#titleHead').text("공지사항 글")
					$('.titleHead').nextAll().remove(); // 기존내용 지우기
					
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['notice_no']);
						tempLi2.text(obj['notice_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});					
		});
		
		// a태그 '질문과답변'이 눌렸을 때 공지사항 내용 불러오기
		$("dt.tab_btn2").click(function(){
			$.ajax({
				type : 'post',
				url : 'review/ajaxQna.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){
					$('#titleHead').text("질문과답변 글")
					$('.titleHead').nextAll().remove(); // 기존내용 지우기
					
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['qna_no']);
						tempLi2.text(obj['qna_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});					
		});
		
		// a태그 '저자문의'가 눌렸을 때 공지사항 내용 불러오기
		$("dt.tab_btn3").click(function(){
			$.ajax({
				type : 'post',
				url : 'review/ajaxInquiry.jsp',
				dataType : 'json', // 받은 결과의 자료형태
				success : function(result){					
					$('#titleHead').text("저자문의 글")
					$('.titleHead').nextAll().remove(); // 기존내용 지우기
					for(obj of result){
						var openLi = $('<tr>');
						var tempLi = $('<td>');
						var tempLi2 = $('<td>');
						
						tempLi.text(obj['inquiry_no']);
						tempLi2.text(obj['inquiry_title']);
						
						openLi.append(tempLi);
						openLi.append(tempLi2);
						$('#titleList').append(openLi);
					}
				},
				error : function(err){
					alert('Ajax 통신 실패:'+err);
					console.log(err);
				}
			});					
		});
		
		$("#totalPost").click(function(){
			location.href="reviewtest?cmd=exercise";
		});	
	});
	
	
	
	
	
</script>

<link rel="stylesheet" type="text/css" href="css/exercise.css" />
</head>
<body>
	<div>
		<dl id="tabmenu">
		     <dt class="tab_btn	tab_btn1">
		        <a href="#">공지사항</a>
		     </dt>
		     <dt class="tab_btn	tab_btn2">
		        <a href="#">질문과답변</a>
		     </dt>
		
			 <dt class="tab_btn	tab_btn3">
		        <a href="#">저자문의</a>
		     </dt>
	    </dl>
    </div>

     <div id="titleFrame">
     	<table id="titleList">
     		<tr class="titleHead">
     			<th id="titleHead" colspan="2"> 전체글 </th>
     		</tr>		
     	</table>
     </div>
     <button id="totalPost">전체글</button>
</body>
</html>