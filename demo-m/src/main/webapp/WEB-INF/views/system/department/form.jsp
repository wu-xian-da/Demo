<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-edit">
							<h2>${empty department.id ? "新增" : "编辑" }信息</h2>
							<form method="post">
							<input type="hidden" name="id" value="${department.id }">
							
							<div class="row">
								<div class="col-md-12">
									<hr>
									<div class="row row-list">
										<div class="col-md-1">
											<label>信息名称：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="name" value="${department.name }" class="form-control" placeholder="信息名称">
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>所属部门：</label>
										</div>
										<div class="col-md-8">
											<select class="form-control" name="parent.id">
												<option value="" >请选择</option>
												<c:forEach items="${parents }" var="d">
													<option value="${d.id }"
													<c:if test="${department.parent.id eq d.id }">selected="selected"</c:if>>${d.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>显示序号：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="sort" value="${department.sort }" class="form-control" placeholder="显示序号">
										</div>
									</div>

							  	<div class="row row-list">
							  		<div class="col-md-1"></div>
							  		<div class="col-md-8">
							  			<div class="operation-box">
											<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-ok-sign"></span>保存</button>
											<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort"><span class="glyphicon glyphicon-arrow-left"></span>返回</button>
										</div>
							  		</div>
							  	</div>
								</div>
							</div>
							</form>
						</div>

					</div>
				</div>