<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" href="../../resources/css/worktime/worktime_ing.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<!-- 상단/왼쪽 메뉴 include -->
<%@ include file="/WEB-INF/views/intro.jsp" %>

        <div id="layoutSidenav_content">
            <div class="content_box">
               	 승인진행중
            </div>

            <!-- 진행중문서 리스트 테이블 -->
            <div class="worktime_ing_docu_list">
                <p>근태 진행 문서</p>
                <div class="worktime_ing_docu_table_list">
	                <table class="worktime_ing_docu_table">
	                    <tr class="worktime_ing_table_bg">
	                        <td>문서번호</td>
	                        <td>기안일</td>
	                        <td>결재양식</td>
	                        <td style="width:40%">제목</td>
	                        <td>첨부</td>
	                    </tr>
	                    <tr class="worktime_ing_table_hover">
	                        <td>20203086</td>
	                        <td>2021-12-10</td>
	                        <td>연차신청서</td>
	                        <td>휴가갈래요</td>
	                        <td><i class="xi-paper"></i></td>
	                    </tr>
	                    <tr class="worktime_ing_table_hover">
	                        <td>20203086</td>
	                        <td>2021-12-10</td>
	                        <td>연차신청서</td>
	                        <td>휴가갈래요</td>
	                        <td><i class="xi-paper"></i></td>
	                    </tr>
	                </table>
        		</div>

           <!-- 페이지 넘버 -->
           
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script src="<c:url value='resources/js/officemap.js'/>"></script>
   
</body>

</html>