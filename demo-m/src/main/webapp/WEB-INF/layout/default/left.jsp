<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="left-menu" id="left-menu">
	<ul>
		<c:forEach items="${userMenus }" var="parent">
			<c:if test="${fn:startsWith(currentUrl, parent.href) }">
				<c:forEach items="${parent.childs }" var="child">
					<li><a href="${child.href }"><i class="new-photo"></i>${child.name }</a></li>
				</c:forEach>
			</c:if>
		</c:forEach>
	</ul>
</div>