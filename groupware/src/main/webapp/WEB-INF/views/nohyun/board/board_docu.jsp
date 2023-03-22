<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>JaeHee Group</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="../../resources/css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="../../resources/css/board/board.css">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>

<!-- 상단/왼쪽 메뉴 include -->
<%@ include file="/WEB-INF/views/intro.jsp" %>

        <div id="layoutSidenav_content">
            <div class="content_box">
                <i class="fa-regular fa-folder-open"></i>자료실
                    <a href="/board_write">
                    <button class="board_write_btn">게시글 작성</button></a>
            </div>
            
            <!-- 테이블과 페이징 감싸는 div -->
            	<div class="board_con">
             
                     <!-- 검색 폼 영역 -->
                    <div class="board_search">
                        <select id='board_search_select' name="board_type">
                            <option class="board_search_option" value='board_all'>전체</option>
                            <option class="board_search_option" value='board_title'>제목</option>
                            <option class="board_search_option" value='emp_name'>작성자</option>
                        </select>
                        <input type="text" id="board_search_input" placeholder="검색 키워드를 입력하세요" name="keyword" value="${keyword}">
                        <input type="button" id="board_search_btn" value="검색">
                    </div>
         
                    <div class="board_table_position">
                     <div class="board_container">
                    <table class="board_table">
                        <tr class="table_bg">
                            <td>글번호</td>
                            <td class="board_title_size">제목</td>
                            <td>작성자</td>
                            <td>작성일</td>
                            <td>조회수</td>
                        </tr>
                        <form action="/board_detial" method="post">
                        <c:forEach var="board_normal" items="${boardDocuList}">
                        <tr class="board_table_hover">
                            <td>${board_normal.board_num}</td>
                            <td class="table_title_center">
                            	<a href="/board_detail?board_num=${board_normal.board_num}" class="board_title_view_count">${board_normal.board_title}
                            	 <input type="hidden" name="board_num">
                            	 	<c:if test="${board_normal.comment_count > 0}">
	                             		<span style="color:blue">[${board_normal.comment_count}]</span>
	                             	</c:if>
                            	 </a></td>
                            <td>${board_normal.dept_name} ${board_normal.emp_name}</td>
                             <fmt:parseDate value="${board_normal.board_write_date}" pattern="yyyy-MM-dd'T'HH:mm" var="board_normal_date" type="both" />
                            <td><fmt:formatDate value="${board_normal_date}" pattern="yyyy-MM-dd" /></td>
                            <td>${board_normal.board_view_count}</td>
                        </tr> 
                        </c:forEach> 
                       </form>               
                    </table>
                    </div>
                    
                    <!-- 페이징 영역 -->
					<ul class="btn-group pagination">
					    <c:if test="${pageMaker.prev }">
						    <li>
						        <a class="line" href='<c:url value="/board_docu?page=${pageMaker.startPage-1}"/>'>prev</a>
						    </li>
					    </c:if>
					    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
						    <li>
						        <a class="line" href='<c:url value="/board_docu?page=${pageNum}"/>'>${pageNum}</a>
						    </li>
					    </c:forEach>
					    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
						    <li>
						        <a class="line" href='<c:url value="/board_docu?page=${pageMaker.endPage+1}"/>'>next</a>
						    </li>
					    </c:if>
					</ul>

					
               </div>
           </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>   
    <script src="../../resources/js/board/board.js"></script>
</body>

</html>