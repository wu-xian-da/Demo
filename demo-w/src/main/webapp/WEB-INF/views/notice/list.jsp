<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/include/header.jsp"%>
	<%@ include file="/WEB-INF/include/css.jsp"%>
</head>	
<body>
    <div id="app">
        <style>
            #html,body{ background: #FFF;}
        </style>
        <div class="app-bannar">
            <span class="header-arrow" onclick="javascript:history.go(-1);"></span>
            <h2>紧急公告列表</h2>
        </div>
        
        <div class="app-container">
           <div class="app-container-news">
               <c:forEach items="${noticeList }" var="notice">
               		<a href="${base }/notice/toDetail/${notice.id }">
	               		<p>${notice.title }</p>
	               		<span class="new-arrow"></span>
	                </a>
               </c:forEach>
            </div>
        </div>

        <%@ include file="/WEB-INF/include/footer.jsp"%>
    </div>
</body>
</html>