<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!-- 消息提醒 -->
<c:if test="${not empty message }">
	<div class="alert alert-auto alert-${message.status.name } alert-dismissible fade in" role="alert">
		${message.info }
	</div>
</c:if>