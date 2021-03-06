<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>파티정보</title>

        <link href="${pageContext.request.contextPath }/assets/css/common.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath }/assets/css/partyRead.css" rel="stylesheet" type="text/css">

		<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		
    </head>

    <body>
        <!--헤더영역-->
       <c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
        <!-- //헤더영역 -->


        <!-- 본문 제일 큰 틀 -->
        <div id="wrap">

            <!-- content -->
            <div id="content">
    
    
                <div id="subheader" class="clearfix">
                    <div id="subheader-title">
                        <h2>파티정보 - ${pReadMap.partyReadList.partyState }</h2>
                    </div>
                </div>
    
    
    
                <!-- main -->
                <div id="content_main" class="clearfix">
                    
                    <!-- 정보 보드 -->
                    <div id="board">
                        
                        <!-- 카페테마 메인정보 -->
                        <div id=main_informa>
                           <div class="theme_image">
                               <a href=""><img src="${pageContext.request.contextPath }/upload/${pReadMap.partyReadList.themeImg }" width="280px" height="360px"></a>

                           </div>
                           
                           <div class="title">
                                <h3 class="cafeAndTheme" data-cafeno="${pReadMap.partyReadList.cafeNo }" data-themeno="${pReadMap.partyReadList.themeNo }">카페 / 테마 : ${pReadMap.partyReadList.cafeName } / ${pReadMap.partyReadList.themeName }</h3>
                           </div>
                           
                           <div class="party_content">

                                <table>
                                    <tbody>
                                        <td class="title_width">장르 : </td> 
                                        <td>${pReadMap.partyReadList.jenre }</td>
                                    </tbody>
                                    <tbody>
                                        <td class="title_width">모집인원 : </td> 
                                        <td>${pReadMap.partyReadList.reservePerson } 인</td>
                                    </tbody>
                                    <tbody>
                                        <td class="title_width">난이도 : </td> 
                                        <td>${pReadMap.partyReadList.levels } / 5</td>
                                    </tbody>
                                    <tbody>
                                        <td class="title_width">플레이타임 : </td> 
                                        <td>${pReadMap.partyReadList.playTime } 분</td>
                                    </tbody>
                                    <tbody>
                                        <td class="title_width">유형 : </td> 
                                        <td>${pReadMap.partyReadList.themeType }</td>
                                    </tbody>
                                    <tbody>
                                        <td class="title_width">이용일시 : </td> 
                                        <td class="reserveDate" data-reservedate="${pReadMap.partyReadList.reserveDate }" data-themetimeno="${pReadMap.partyReadList.themeTimeNo }">${pReadMap.partyReadList.reserveDate } / ${pReadMap.partyReadList.themeTime }</td>
                                    </tbody>
                                    <tbody>
                                        <td id="together" class="title_width">모임글 : </td> 
                                        <td id="together_content">${pReadMap.partyReadList.content }</td>
                                        <!-- 모임글 테이블 만들때 100자 제한하기 -->
                                    </tbody>

                                </table> 
                           </div>

          				   <div class="btn_group">
                           </div>


                           <!-- 마스터 버튼 -->
          				   <div class="btn_group">
	                               <a href="${pageContext.request.contextPath }/party/partyList">
	                               		<button id="list_button" class="submit_button">목록</button>
                               	   </a>
	                               <c:choose>
	                               		<c:when test="${sessionScope.authUser.userNo eq pReadMap.partyReadList.userNo }">
			                               		<button type="button" data-partyno="${pReadMap.partyReadList.partyNo }" id="delete_button" class="submit_button">파티삭제</button>
			                               		
			                               <c:if test="${pReadMap.partyReadList.partyState eq '모집중'}">
			                               		<button type="button" data-userno="${pReadMap.partyReadList.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" id="complete_button" class="submit_button">모집완료</button>
		                               		</c:if>
		                               		
			                               <c:if test="${pReadMap.partyReadList.partyState eq '모집완료'}">
			                               		<button type="button" data-userno="${pReadMap.partyReadList.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" id="working_button" class="submit_button">모집중</button>
	                               		   </c:if>
	                               		   <form name="form" id="formform" action="${pageContext.request.contextPath}/reserve/partyReserveInfoForm" method="post">
	                               		   		<input type="hidden" name="partyNo" value="${pReadMap.partyReadList.partyNo }">
	                               		   		<input type="hidden" name="cafeNo" value="${pReadMap.partyReadList.cafeNo }">
	                               		   		<input type="hidden" name="themeNo" value="${pReadMap.partyReadList.themeNo }">
	                               		   		<input type="hidden" name="themeTimeNo" value="${pReadMap.partyReadList.themeTimeNo }">
	                               		   		<input type="hidden" name="reserveDate" value="${pReadMap.partyReadList.reserveDate }">
	                               		   		<input type="hidden" name="themeTime" value="${pReadMap.partyReadList.themeTime }">
	                               		   		
	                               		   </form>
			                               		<button type="button" data-userno="${pReadMap.partyReadList.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" id="reserve_button" class="mbutton">예약하기</button>
	                               		</c:when>
	                               </c:choose>
                           </div>	
                           <!-- //마스터 버튼 -->


                            <!-- 참가자 버튼 -->
                         	<div class="btn_group">
	                            <c:if test="${sessionScope.authUser.userNo ne pReadMap.partyReadList.userNo && sessionScope.authUser.userNo != null && pReadMap.partyReadList.partyState eq '모집중'}">
	                                <button type="button" data-userno="${sessionScope.authUser.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" id="cancel_button" class="submit_button">참가취소</button>
	                                <button type="button" data-userno="${sessionScope.authUser.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" id="join_button" class="submit_button">파티참가</button> 
	                            </c:if> 
                            </div>
                            <!-- //참가자 버튼 -->

                        </div>
                        <!-- //카페테마 메인정보 -->


                    </div>
                    <!-- //정보 보드-->


					<!-- 방장용 참여현황 -->
		         	<c:if test="${sessionScope.authUser.userNo eq pReadMap.partyReadList.userNo }">
	                    <div id="party_list">
	                        <div id="join_situ">&nbsp;&nbsp;&nbsp;&nbsp;참여현황</div>
	                        <table>
	                            <colgroup>
		                                <col style="width: 120px;">
		                                <col style="width: 160px;">
		                                <col style="width: 206px;">
		                                <col style="width: 162px;">
		                                <col style="width: 162px;">
		                                <col style="width: 162px;">
		                                <col style="width: 162px;">
		                                <col style="width: 162px;">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>No</th>
	                                    <th>프로필</th>
	                                    <th>닉네임</th>
	                                    <th>현재 순위</th>
	                                    <th>성공률</th>
	                                    <th>노힌트 성공률</th>
	                                    <th>평균 기록</th>
	                                    <th>동의여부</th>
	                                </tr>
	                            </thead>
	
	                            <tbody id="joinFinishTr">
	                            	<c:forEach items="${pReadMap.partyDetailList }" var="partyDetailList" varStatus="status">
	                                <tr>
	                                    <td>${status.count }</td>
	                                    <td><img id="imgValue" src="${pageContext.request.contextPath }/assets/image/profile/${partyDetailList.profile }" width="80px" height="80px"></td>
	                                    <td><c:if test="${partyDetailList.userState == '1' }"><img src="${pageContext.request.contextPath }/assets/image/party/crown1111.png"> </c:if>${partyDetailList.nickname }</td>
	                                    <td>${partyDetailList.rank } 위</td>
	                                    <td>${partyDetailList.winRate } %</td>
	                                    <td>${partyDetailList.noHintWinRate } %</td>
	                                    <td>${partyDetailList.showAvgClearTime }</td>
	                                    <td><c:if test="${partyDetailList.userState == '2' && pReadMap.partyReadList.partyState == '모집중'}"><button type="button" data-userno="${partyDetailList.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" class ="refuse_button">멤버취소</button></c:if></td>
	                                </tr>
	                            	</c:forEach>
									
	                            </tbody>
	                        </table>
	                    </div>
		         	</c:if>

                    <!-- //방장용 참여현황 -->


					<!-- 참가자용 참여현황 -->
					<c:if test="${sessionScope.authUser.userNo ne pReadMap.partyReadList.userNo }">
	                    <div id="party_list">
	                        <div id="join_situ">&nbsp;&nbsp;&nbsp;&nbsp;참여현황</div>
	                        <table>
	                            <colgroup>
	                                <col style="width: 120px;">
	                                <col style="width: 210px;">
	                                <col style="width: 225px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>No</th>
	                                    <th>프로필</th>
	                                    <th>닉네임</th>
	                                    <th>현재 순위</th>
	                                    <th>성공률</th>
	                                    <th>노힌트 성공률</th>
	                                    <th>평균 기록</th>
	                                </tr>
	                            </thead>
	
	                            <tbody>
	                            	<c:forEach items="${pReadMap.partyDetailList }" var="partyDetailList" varStatus="status">
	                                <tr>
	                                    <td>${status.count }</td>
	                                    <td><img src="${pageContext.request.contextPath }/assets/image/profile/${partyDetailList.profile }" width="80px" height="80px"></td>
	                                    <td><c:if test="${partyDetailList.userState == '1' }"><img src="${pageContext.request.contextPath }/assets/image/party/crown1111.png"> </c:if>${partyDetailList.nickname }</td>
	                                    <td>${partyDetailList.rank } 위</td>
	                                    <td>${partyDetailList.winRate } %</td>
	                                    <td>${partyDetailList.noHintWinRate } %</td>
	                                    <td>${partyDetailList.showAvgClearTime }</td>
	                                </tr>
	                            	</c:forEach>
									
	                            </tbody>
	                        </table>
	                    </div>
					</c:if>

                    <!-- //참가자용 참여현황 -->
                    

                    <!-- 대기현황 방장용-->
                    <c:if test="${sessionScope.authUser.userNo eq pReadMap.partyReadList.userNo }">
	                    <div id="hold_list">
	                        <div id="join_situ">&nbsp;&nbsp;&nbsp;&nbsp;대기현황</div>
	                        <table>
	                            <colgroup>
	                                <col style="width: 120px;">
	                                <col style="width: 160px;">
	                                <col style="width: 206px;">
	                                <col style="width: 162px;">
	                                <col style="width: 162px;">
	                                <col style="width: 162px;">
	                                <col style="width: 162px;">
	                                <col style="width: 162px;">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>No</th>
	                                    <th>프로필</th>
	                                    <th>닉네임</th>
	                                    <th>현재 순위</th>
	                                    <th>성공률</th>
	                                    <th>노힌트 성공률</th>
	                                    <th>평균 기록</th>
	                                    <th>동의여부</th>
	                                </tr>
	                            </thead>
	
	                            <tbody id="joinTr">
		                            <c:forEach items="${pReadMap.partyApplicantList }" var="partyApplicantList" varStatus="status">
		                                <tr>
		                                    <td>${status.count }</td>
		                                    <td><img src="${pageContext.request.contextPath }/assets/image/profile/${partyApplicantList.profile }" width="80px" height="80px"></td>
		                                    <td>${partyApplicantList.nickname }</td>
		                                    <td>${partyApplicantList.rank } 위</td>
		                                    <td>${partyApplicantList.winRate } %</td>
		                                    <td>${partyApplicantList.noHintWinRate } %</td>
		                                    <td>${partyApplicantList.showAvgClearTime }</td>
		                                    <c:if test="${pReadMap.partyReadList.partyState == '모집중' }">
		                                    	<td><button type="button" data-userno="${partyApplicantList.userNo }" data-partyno="${pReadMap.partyReadList.partyNo }" class ="agree_button">멤버승인</button></td>
		                                    </c:if>
		                                    <c:if test="${pReadMap.partyReadList.partyState == '모집완료' }">
		                                    	<td></td>
		                                    </c:if>
		                                </tr>
		        					</c:forEach>
	                            </tbody>
	                        </table>
	                    </div>
                    </c:if>
                    
					<!-- //대기현황 -->
				
                    <!-- 대기현황 참가자용-->
                    <c:if test="${sessionScope.authUser.userNo ne pReadMap.partyReadList.userNo && pReadMap.partyReadList.partyState == '모집중'}">
	                    <div id="hold_list">
	                        <div id="join_situ">&nbsp;&nbsp;&nbsp;&nbsp;대기현황</div>
	                        <table>
	                            <colgroup>
	                                <col style="width: 120px;">
	                                <col style="width: 210px;">
	                                <col style="width: 225px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                                <col style="width: 185px;">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>No</th>
	                                    <th>프로필</th>
	                                    <th>닉네임</th>
	                                    <th>현재 순위</th>
	                                    <th>성공률</th>
	                                    <th>노힌트 성공률</th>
	                                    <th>평균 기록</th>
	                                </tr>
	                            </thead>
	
	                            <tbody>
		                            <c:choose>
		                            	<c:when test="${empty pReadMap.partyApplicantList}">
		                            		<tr>
		                            		<td></td>
		                            		<td></td>
		                            		<td></td>
		                            		<td>대기중인 신청 <br>인원이 없습니다.</td>
		                            		<td></td>
		                            		<td></td>
		                            		<td></td>
		                            		</tr>
		                            	</c:when>
		                                <c:otherwise>
				                            <c:forEach items="${pReadMap.partyApplicantList }" var="partyApplicantList" varStatus="status">
				                                <tr>
				                                    <td>${status.count }</td>
				                                    <td><img src="${pageContext.request.contextPath }/assets/image/profile/${partyApplicantList.profile }" width="80px" height="80px"></td>
				                                    <td>${partyApplicantList.nickname }</td>
				                                    <td>${partyApplicantList.rank } 위</td>
				                                    <td>${partyApplicantList.winRate } %</td>
				                                    <td>${partyApplicantList.noHintWinRate } %</td>
				                                    <td>${partyApplicantList.showAvgClearTime }</td>
				                               </tr>
				                            </c:forEach>  
		                                </c:otherwise>
		                            </c:choose>
	                            </tbody>
	                        </table>
	                    </div>
                    </c:if>
				<!-- //대기현황 -->

			</div>
                <!-- //main -->
                
    
            </div>
            <!-- //content -->
    
    
          <!-- footer -->
    
          <c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
    
          <!-- //footer -->
       </div>

        <!-- //본문 제일 큰 틀 -->


    </body>
    
    <script>
    
    /* 파티마스터가 모집완료를 눌렀을경우 */
    
	$("#complete_button").on("click", function() {
    		
   		var userNo = $(this).data("userno");
   		var partyNo = $(this).data("partyno");
   		
  		var partyVo = {
   			userNo: $(this).data("userno"),
   	   		partyNo: $(this).data("partyno")
   		};
				
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티 모집을 완료하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
            if (result.isConfirmed) {
               
				//ajax서버에 요청 (partyNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/partyComplete",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						
						/*성공시 처리해야될 코드 작성*/
						
						Swal.fire({
				            title: 'NEXTROOM',
				            text: "구성된 파티로 예약하러 가시겠습니까?",
				            icon: 'question',
				            showCancelButton: true,
				            confirmButtonColor: '#3085d6',
				            cancelButtonColor: '#d33',
				            confirmButtonText: '확인',
				            cancelButtonText: '취소'
				        }).then((result) => {
				        	
				            if (result.isConfirmed) {
				            	
				            	//예약을위한 정보 넘겨야함!!!
				            	$("#formform").submit();
				            	
				            } else {
				            	location.reload();
				            }
				            
				        });
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});            	
            	
            }
            
        });
  			
				
	});
		
    /*//// 파티마스터가 모집완료를 눌렀을경우  */	
    
    
    /* 파티마스터가 모집중을 눌렀을경우 */
	$("#working_button").on("click", function() {
    		
   		var userNo = $(this).data("userno");
   		var partyNo = $(this).data("partyno");
   		
  		var partyVo = {
   			userNo: $(this).data("userno"),
   	   		partyNo: $(this).data("partyno")
   		};
				
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티 모집을 다시하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
            if (result.isConfirmed) {
            	
				//ajax서버에 요청 (partyNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/partyWorking",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						location.reload();
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});            	
            	
            }
            
        });
  			
				
	});
	
    /*//// 파티마스터가 모집중을 눌렀을경우 */
    
    
   	/*partyRead에서 파티삭제를 눌렀을때*/
   	$("#delete_button").on("click", function() {
		
   		var partyNo = $("#delete_button").data("partyno");
   			
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티를 삭제하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '삭제',
            cancelButtonText: '취소'
        }).then((result) => {
        	
            if (result.isConfirmed) {
   								
				//ajax서버에 요청 (partyNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/partyDelete",		
					type : "post",
	//	 			contentType : "application/json",
					data : {partyNo: partyNo},
	
	//	 			dataType : "json",
					success : function(count){
						/*성공시 처리해야될 코드 작성*/
						
						if(count == 1) {
							window.location.assign('http://localhost:8088/nextroom/party/partyList');
						}
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});
			
               
             }
         });
   			
   		
	});
   	/*////partyRead에서 파티삭제를 눌렀을때*/
   	
   	
   	/*참여자가 파티참가를 눌렀을 경우*/
   	$("#join_button").on("click", function () {
		
   		var userNo = $("#join_button").data("userno");
   		var partyNo = $("#join_button").data("partyno");
   		
   		var partyVo = {
   			userNo: $("#join_button").data("userno"),
   	   		partyNo: $("#join_button").data("partyno")
   		};
   		
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티에 참여하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '참여',
            cancelButtonText: '취소'
        }).then((result) => {
        	
             if (result.isConfirmed) {
   			
				//ajax서버에 요청 (partyNo,userNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/addPartyApplicant",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						if(result == true) {
							location.reload();
						} else {
							Swal.fire({
			                    icon: 'warning',
			                    title: 'NEXTROOM',
			                    text: '참가현황과 대기현황 명단을 확인해주세요.',
			                });
						
						}
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});
   			
             }
        });
   		
	});
   	/*////참여자가 파티참가를 눌렀을 경우*/
    
   	
   	/*참여자가 파티취소를 눌렀을 경우*/
   	$("#cancel_button").on("click", function () {
		
   		var userNo = $("#cancel_button").data("userno");
   		var partyNo = $("#cancel_button").data("partyno");
   		
   		var partyVo = {
   			userNo: $("#cancel_button").data("userno"),
   	   		partyNo: $("#cancel_button").data("partyno")
   		};
   		
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티참여를 취소하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
             if (result.isConfirmed) {
	
				//ajax서버에 요청 (partyNo,userNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/cancelPartyApplicant",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						if(result == true) {
							location.reload();
							
						} else {
							
							Swal.fire({
			                    icon: 'warning',
			                    title: 'NEXTROOM',
			                    text: '참가현황과 대기현황 명단을 확인해주세요.',
			                });
						}
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});

             }
        });
   		
	});
   	/*////참여자가 파티취소를 눌렀을 경우*/
   	
   	
   	/*방장이 대기자현황 멤버승인버튼을 눌렀을 경우*/
   	$("#joinTr").on("click", ".agree_button", function () {
		
   		var userNo = $(this).data("userno");
   		var partyNo = $(this).data("partyno");
   		
  		var partyVo = {
   			userNo: $(this).data("userno"),
   	   		partyNo: $(this).data("partyno")
   		};
   		
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티멤버로 수락하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
             if (result.isConfirmed) {
   			
				//ajax서버에 요청 (partyNo,userNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/agreeMember",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						if(result == false) {
							Swal.fire({
			                    icon: 'warning',
			                    title: 'NEXTROOM',
			                    text: '모집인원을 확인해주세요.',
			                });
						} else {
							location.reload();
						}
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});
   			
             }
        });
		
	});
   	/*////방장이 대기자현황 멤버승인버튼을 눌렀을 경우*/
   	
   	
   	/*방장이 참여현황에서 멤버취소버튼을 눌렀을 경우*/
   	$("#joinFinishTr").on("click", ".refuse_button", function () {
		
   		var userNo = $(this).data("userno");
   		var partyNo = $(this).data("partyno");
   		
  		var partyVo = {
   			userNo: $(this).data("userno"),
   	   		partyNo: $(this).data("partyno")
   		};
   		
		Swal.fire({
            title: 'NEXTROOM',
            text: "파티멤버에서 추방하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
             if (result.isConfirmed) {
   			
				//ajax서버에 요청 (partyNo,userNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/exceptDetailMember",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						if(result == true) {
							location.reload();
						} 
						
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
					
				});
   			
             }
        });
   		
   		
	});
   	/*////방장이 참여현황에서 멤버취소버튼을 눌렀을 경우*/
   	
   	
   	/* 방장이 예약하기 버튼을 눌렀을 경우 */
   	$("#reserve_button").on("click", function () {
		
   		var userNo = $(this).data("userno");
   		var partyNo = $(this).data("partyno");
   		console.log("유저번호 :" + userNo);
   		console.log("파티번호 :" + partyNo)
   		
  		var partyVo = {
   			userNo: $(this).data("userno"),
   	   		partyNo: $(this).data("partyno")
   		};
				
    	
		Swal.fire({
            title: 'NEXTROOM',
            text: "방탈출 예약을 진행하시겠습니까?",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
        	
            if (result.isConfirmed) {
            	
				//ajax서버에 요청 (partyNo,userNo 전달)
				$.ajax({
					
					url : "${pageContext.request.contextPath }/party/partyReserveCheck",		
					type : "post",
	//	 			contentType : "application/json",
					data : partyVo,
	
	//	 			dataType : "json",
					success : function(result){
						/*성공시 처리해야될 코드 작성*/
						
						if(result == true) {
							$("#formform").submit();
						}
						
						else {
							Swal.fire({
			                    icon: 'warning',
			                    title: 'NEXTROOM',
			                    text: '모집완료를 클릭해주세요.',
			                });
							
						}
					},
					
				});
            
            }
            
        });
   		
	});
   	/*////방장이 예약하기 버튼을 눌렀을 경우*/
   	
	</script>


</html>