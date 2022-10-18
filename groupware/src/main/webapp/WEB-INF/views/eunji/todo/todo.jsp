<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>JaeHee Group</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<!-- <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"> -->
<!-- <link rel="stylesheet" href="<c:url value="/resources/css/todo.css"/>"> -->
<link rel="stylesheet" href="../../resources/css/styles.css">
<link rel="stylesheet" href="../../resources/css/todo/todo.css">

<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<!-- 상단/왼쪽 메뉴 include -->
<%@ include file="/WEB-INF/views/intro.jsp" %>

		<div id="layoutSidenav_content">
			<div class="content_box_first">
				<i class="xi-library-books"></i>
				<p>ToDo+</p>
				<button class="todo_add my">Create Todo</button>
			</div>

			<!-- 즐겨찾는 보드  -->
			<div class="content_box_two">
				<p>즐겨찾는 보드</p>
				<div class="box_position">
				
					<c:forEach var="todo" items="${todoMapList}">
						<!-- 즐겨찾기가 true일 경우 표시되도록  -->
						<c:if test="${todo.todo_favorite eq true}">
					<div class="my_borad_box">
						<div class="my_content_box" id="my_board_modal">
						<form action="/todo" method="post">
								<input type="hidden" name="todo_emp_num" value="${emp_num}">
								<p class="todo_title" id="todo_title">${todo.todo_title}</p>
								<p class="my_borad_box_font todo_contents" id="todo_contents">${todo.todo_contents}</p>
						</form>
						<form action="/todo_favorite" method="post">
							<i class="xi-star favorite_icon" title="즐겨찾기" style="color:darkorange"></i>
							<input type="hidden" name="todo_num" value="${todo.todo_num}">
							<input type="hidden" name="todo_favorite" value="${todo.todo_favorite}">
						</form>
						<form action="/todo_delete" method="post">
							<i class="xi-close-min" title="삭제"></i>
							<!-- name은 항상 컬럼명과 일치시키기 -->
							<input type="hidden" name="todo_num" id="todoDelete" class="todoDelete" value="${todo.todo_num}">
						</form>
						</div>
					</div>
						</c:if>
					</c:forEach>
				
				</div>
			</div>
			
			<!-- 내 보드  -->
			<div class="content_box_three">
				<p>내 보드</p>
				<div class="box_position my_board_list">

					<!-- todo 생성 -->
					
					<c:forEach var="todo" items="${todoMapList}">
					<div class="my_borad_box">
						<div class="my_content_box" id="my_board_modal">
						<form action="/todo" method="post">
							<input type="hidden" name="todo_emp_num" value="${emp_num}">
								<p class="todo_title" id="todo_title">${todo.todo_title}</p>
								<p class="my_borad_box_font todo_contents" id="todo_contents">${todo.todo_contents}</p>
						</form>
						<form action="/todo_favorite" method="post">
							<c:if test="${todo.todo_favorite eq false}">
							<i class="xi-star-o favorite_icon" title="즐겨찾기"></i>
							</c:if>
							<c:if test="${todo.todo_favorite eq true}">
							<i class="xi-star favorite_icon" title="즐겨찾기"></i>
							</c:if>
							<input type="hidden" name="todo_num" id="todoFavo" value="${todo.todo_num}">
							<input type="hidden" name="todo_favorite" value="${todo.todo_favorite}">
						</form>
						<form action="/todo_delete" method="post">
							<i class="xi-close-min" title="삭제"></i>
							<!-- name은 항상 컬럼명과 일치시키기 -->
							<input type="hidden" name="todo_num" id="todoDelete" class="todoDelete" value="${todo.todo_num}">
						</form>
						</div>
					</div>
					
					</c:forEach>	
				</div>
			</div>
			
		</div>

	<!-- 모달 창 -->
	<script> 
	// script 사용 이유 : js파일에서는 string으로 인식되어 jsp파일에서 작성해야 함 
		let emp_num=${sessionScope.emp_num};
	</script>  
	<div class="todo_modal_back">
		<div id="todo_modal" class="tdo_modal">
			<div id="create_todo">Create my Todo</div>
			<div id="create_todo_title">제목</div> 
			<input type="text" id="todo_modal_title" name="todo_title" size="25"><br> 
			<div id="create_todo_contents">내용</div> 
			<textarea name="todo_contents" id="todo_modal_text" cols="30"
				rows="10"></textarea>
			<br>
			<div class="todo_btn_center">
				<input type="button" value="수정" class="todo_btn todo_modify">
				<input type="button" value="저장" class="todo_btn todo_save">
				<input type="button" value="취소" class="todo_btn todo_cancel">
			</div>
		</div>
	</div>
	
	

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="resources/js/todo/todo.js" /></script>
	<script src="<c:url value='resources/js/officemap.js'/>"></script>
</body>

</html>