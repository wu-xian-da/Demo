<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-box">
							<%@ include file="/WEB-INF/include/message.jsp" %>
							
							<h3>条件检索</h3>
							<form method="post">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>角色名称：</label>
								    <input type="text" class="form-control" name="name" value="${page.entity.name }" placeholder="角色名称">
								  </div>
								</div>
							</div>

							<div class="operation-box">
								<button type="submit" class="btn btn-gy btn-query">
								<span class="glyphicon glyphicon-search"></span>查询</button>
								<a href="${base }/sys/system/role/create" class="btn btn-gy btn-add">
								<span class="glyphicon glyphicon-plus-sign"></span>新增</a>
							</div>
							</form>

							<div class="box-table">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>角色名称</th>
											<th>关联用户</th>
											<th>管理</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.data }" var="d" varStatus="stat">
										<tr>
											<td>${page.beginIndex + stat.count}</td>
											<td>${d.name }</td>
											<td>${d.userList }</td>
											<td>
											<a href="${base }/sys/system/role/update/${d.id}" class="edit"><i></i>编辑</a> 
											<a href="${base }/sys/system/role/delete/${d.id}" onclick="javascript:return confirmDel();"
														class="delete"><i></i>删除</a>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="/WEB-INF/include/page.jsp" %>
							</div>
						</div>
					</div>
				</div>
