<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정(memberUpdateForm)</title>
<link rel="stylesheet" href="${contextPath}/resources/css/member-style.css" type="text/css">
</head>

<script>
	function checkDropMember(){ //탈퇴하기 버튼을 누르면 checkDropMember()함수를 호출합니다.
		
		var isDrop=confirm('정말로 탈퇴하시겠습니까?'); // 확인을 먼저 받고
		console.log(isDrop);
		//확인 누름. => 탈퇴처리를 하는 url: mdelete.me를 호출...
		if(isDrop){
			
			alert('성공적으로 회원탈퇴 되었습니다.');
		}else{
			//취소 누름=> home.do 페이지로 이동후에 alert메시지를 띄웁니다...
			location.href='home.do';
			alert('회원탈퇴를 취소합니다.');
		}
		
	}
</script>	

<body>

	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">'${ loginUser.name }'님의 정보 수정</h1>
	
	<div class="centerText">
		<form action="mupdate.me" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>${ loginUser.id }<input type="hidden" name="id" value="${ loginUser.id }"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" value="${ loginUser.name }"></td>
				</tr>
				<tr>
					<th>성별</th>
					<c:if test="${ loginUser.gender eq 'M'}">
						<td>
							<input type="radio" name="gender" value="M" checked>남
							<input type="radio" name="gender" value="F">여
						</td>
					</c:if>
					<c:if test="${ loginUser.gender eq 'F'}">
						<td>
							<input type="radio" name="gender" value="M">남
							<input type="radio" name="gender" value="F" checked>여
						</td>
					</c:if>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" min="20" max="100" name="age" value="${ loginUser.age }"></td>				
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" value="${ loginUser.email }"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="phone" value="${ loginUser.phone }"></td>
				</tr>
				
				<c:forTokens var="addr" items="${ loginUser.address }" delims="/" varStatus="status">
					<c:if test="${ status.index eq 0 && addr >= '0' && addr <= '99999' }">
						<c:set var="post" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 0 && !(addr >= '0' && addr <= '99999') }">
						<c:set var="address1" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 1 }">
						<c:set var="address1" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 2 }">
						<c:set var="address2" value="${ addr }"/>
					</c:if>
				</c:forTokens>
					
				<tr>
					<th>우편번호</th>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" value="${ post }" size="6">
						<button type="button" id="postcodify_search_button">검색</button>
					</td>
				</tr>
				<tr>
					<th>도로명 주소</th>
					<td>
						<input type="text" name="address1" class="postcodify_address" value="${ address1 }" size="30">
					</td>
				</tr>
				<tr>
					<th>상세 주소</th>
					<td><input type="text" name="address2" class="postcodify_extra_info" value="${ address2 }" size="30"></td>
				</tr>	
				
				
				<!-- jQuery와 Postcodify를 로딩한다. -->
				<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				<script>
					// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
					$(function(){
						$("#postcodify_search_button").postcodifyPopUp();
					});
					
					
				</script>
				
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="location.href='mupdate.me'">수정하기</button>
						
						
						
						<%--
						<c:url var="mdelete" value="mdelete.me">
							<c:param name="id" value="${ loginUser.id }"/>
						</c:url>
						<button type="button" onclick="location.href='${mdelete}'">탈퇴하기</button>
						 --%>
						 
						<button type="button" onclick="checkDropMember();">탈퇴하기</button> 
						<button type="button" onclick="location.href='home.do'">시작 페이지로 이동</button>
					</td>
				</tr>
			</table>
		</form>		
	</div>

	
</body>
</html>