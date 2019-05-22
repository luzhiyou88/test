<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>套课管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var obj = $('.ct');
			$.each(obj,function(k,v){
				var con = v.innerHTML;
				if(con.length > 180){
					v.innerHTML=v.innerHTML.substring(0,180)+'...';
			        } 
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/course/spCourse/">套课列表</a></li>
		<li><a href="${ctx}/spadmin/course/spCourse/form">套课添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spCourse" action="${ctx}/spadmin/course/spCourse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
				<td>
					<label>套课名称：</label>
					<form:input path="courseName" htmlEscape="false" maxlength="128" class="input-medium"/>
				</td>
				<td>
					<label>套课分类：</label>
								<sys:treeselect id="spCategory" name="categoryId" value="${spCourse.categoryId }" labelName="categoryName" labelValue="${spCourse.categoryName}"
				title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spCourse.currentUser.admin}"/>
				</td>
				<td>
					<label>套课类型：</label>
					<form:select path="courseType" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('cr_fee_level')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</td>
			</tr>
		</table>
		<div class="searchFormButton">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-primary" type="reset" value="清空"/>
		</div>
		
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>套课名称</th>
				<th>套课分类</th>
				<th>套课类型</th>
				<th>课程数量</th>
				<th>套课来源</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spCourse">
			<tr>
				<td>
					<a href="${ctx}/spadmin/course/spCourse/form?id=${spCourse.id}">${spCourse.courseName}</a>
				</td>
				<td>${spCourse.categoryName}</td>
				<td>
					${fns:getDictLabel(spCourse.courseType, "cr_fee_level", "-")}
				</td>
				<td>${spCourse.courseNumber}</td>
				<td>
					${fns:getDictLabel(spCourse.courseSource, "sp_lesson_source", "-")}
				</td>
				<td class="ct">${spCourse.remarks}</td>
				<td>
					<fmt:formatDate value="${spCourse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/course/spCourse/form?id=${spCourse.id}">修改</a>
    				<c:if test="${spCourse.courseNumber == 0}">
    					<a href="${ctx}/spadmin/course/spCourse/delete?id=${spCourse.id}" onclick="return confirmx('确认要删除该套课以及该套课下的所有课程吗？', this.href)">删除</a>
    				</c:if>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>