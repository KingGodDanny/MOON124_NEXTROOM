<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${pageContext.request.contextPath }/assets/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mypage.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath }/assets/css/userInformation.css" rel="stylesheet" type="text/css">

<title>자유게시판</title>
</head>
<body>

	<!-- 헤더영역 -->
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<!-- //헤더영역 -->

	<div id="wrap">


		<!-- content -->
		<div id="content" >
			
			<div id="subheader" class="clearfix">
				<div id="subheader-title">
				   <h2>마이페이지</h2>
				</div>
				<div id="subheader-mintitle">마이페이지 > 개인정보 변경</div>
	
	
	
			 </div>

			

			 <div id="contentMain" class="clearfix">
                 <!-- aside -->
                <div id="aside">
                    <img id="aside_img" src="${pageContext.request.contextPath }/assets/image/asideImg.png" width="35px" height="35px">
                    
                    <div id="aside_cate">
                       <!-- <p>마이페이지</p> -->
                       <ul>
                          <li><a class="active" href="">개인정보 변경</a></li>
                          <li>결제내역</li>
                          <li>파티관리</li>
                          <li>나의 문의내역</li>
                          <li>관심 카페/테마</li>
                          <li>포인트 적립/사용내역</li>
                          <li>내가 쓴 글 보기
                             <ul>
                                <li>- 커뮤니티 글</li>
                                <li>- 커뮤니티 댓글</li>
                             </ul>
                          </li>
                          <li>내 쪽지
                             <ul>
                                <li>- 받은 쪽지함</li>
                                <li>- 보낸 쪽지함</li>
                                <li>- 쪽지 보내기</li>
                             </ul>
                          </li>
                          <li>회원 탈퇴</li>
                       </ul>
              
                    </div>
                 </div>
                 <!-- //aside -->
	



				<!-- main -->
				<div id="mypageMain" class="clearfix">
				   
                    <form id="user_change">
                        <!-- 상단여백 -->
                        <div id="top_blank"></div>
                        <!-- //상단여백 -->

                        <!-- 유저 이미지 -->
                        <div id="user_image">
                            <div>
                                <img src="${pageContext.request.contextPath }/assets/image/profile/red.jpg">
                                <button class="change_button">변경</button>
                            </div>
                        </div>
                        <!-- //유저 이미지 -->
                        
                        <!-- 정보변경 테이블 -->
                        <table id="change_informaiton">

                                <tr>
                                    <th>아이디</th>
                                    <td>wonho</td>
                                </tr>
    
                                <tr>
                                    <th>이름</th>
                                    <td>흥선대원군</td>
                                </tr>

                                <tr>
                                    <th>닉네임</th>
                                    <td><input type="text"></td>
                                    <td><button>중복확인</button></td>
                                </tr>

                                <tr>
                                    <th>기존 비밀번호</th>
                                    <td><input type="password"></td>
                                </tr>

                                <tr>
                                    <th>신규 비밀번호</th>
                                    <td><input type="password"></td>
                                </tr>

                                <tr>
                                    <th>신규 비밀번호(확인)</th>
                                    <td><input type="password"></td>
                                </tr>

                                <tr>
                                    <th>전화번호</th>
                                    <td><input type="text" placeholder="'-'없이 입력해주세요"></td>
                                </tr>

                                <tr>
                                    <th>이메일</th>
                                    <td><input type="text"></td>
                                </tr>

                                <tr>
                                    <th>비밀번호 확인 질문</th>
                                    <td>
                                        <select>
                                            <option>2031.07.03은 무슨날이게?</option>
                                            <option>나의 보물 1호는?</option>
                                            <option>가장 친한 친구 이름은?</option>
                                            <option>쇄국정책의 뜻은?</option>
                                        </select>
                                    </td>
                                    <td><input type="text"></td>
                                </tr>

                        </table>
                        <!-- //정보변경 테이블 -->

                    </form>

                    <div id="user_infom_finish">
                        <span class="finish_button"><a href="${pageContext.request.contextPath }/hello">수정 완료</a></span>
                        <span class="finish_button"><a href="${pageContext.request.contextPath }/hello">취 소</a></span>
                    </div>

				</div>
				<!-- //main -->

			 </div>



		</div>
		<!-- //content -->






		<!-- footer -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

		<!-- //footer -->


	</div>
</body>
</html>