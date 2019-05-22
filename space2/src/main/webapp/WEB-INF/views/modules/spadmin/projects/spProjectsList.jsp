<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
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
		<c:if test="${!myProject}">
			<li class="active"><a href="${ctx}/spadmin/projects/spProjects/">项目列表</a></li>
			<li><a href="${ctx}/spadmin/projects/spProjects/form">项目添加</a></li>
		</c:if>
		<c:if test="${myProject}">
			<li class="active"><a href="${ctx}/spadmin/projects/spProjects/listmy">我的项目列表</a></li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="spProject" action="${ctx}/spadmin/projects/spProjects/${myProject?'listmy':'list'}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>路演名称：</label>
				<select name="lessonId" class="input-medium">
					<option value="">请选择</option>
					<c:forEach items="${lessonList}" var="one">
						<option value="${one.id}" ${spProject.lessonId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</td>
			<td><label>主讲人：</label>
				<select name="speakerId" class="input-medium">
					<option value="">请选择</option>
					<c:forEach items="${speakerList}" var="one">
						<option value="${one.id}" ${spProject.speakerId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</td>
			</tr>
			<tr>
			<td colspan="2"><label>修改时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spProject.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spProject.endUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</td>
			<td>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input class="btn btn-primary" type="reset" value="清空"/>
			</td>
			</tr>
		</table>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>路演名称</th>
				<th>主讲人</th>
				<!-- <th>特点</th>
				<th>背景</th>
				<th>市场规划</th>
				<th>盈利模式</th> -->
				<th>商业计划书</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spProject">
			<tr>
				<td>
					<c:if test="${!myProject}">
						<a href="${ctx}/spadmin/projects/spProjects/form?id=${spProject.id}">${spProject.name}</a>
					</c:if>
					<c:if test="${myProject}">${spProject.name}</c:if>
				</td>
				<td>
					${spProject.lessonName}
				</td>
				<td>
					${spProject.speakerName}
				</td>
				<%-- <td>
					${spProject.characteristic}
				</td>
				<td>
					${spProject.backdrop}
				</td>
				<td>
					${spProject.outletPlaning}
				</td>
				<td>
					${spProject.profitModel}
				</td> --%>
				<td title="${spProject.businessProposalUrl}">
					${(fn:length(spProject.businessProposalUrl)>20)?(fn:substring(spProject.businessProposalUrl,0,20)):(spProject.businessProposalUrl)}${(fn:length(spProject.businessProposalUrl)>20)?"...":""}
				</td>
				<td class="ct">
					${spProject.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spProject.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${!myProject}">
	    				<a href="${ctx}/spadmin/projects/spProjects/form?id=${spProject.id}">修改</a>
						<a href="${ctx}/spadmin/projects/spProjects/delete?delFileFlag=true&id=${spProject.id}" onclick="return confirmx('确认要删除该项目吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${myProject}">
						<a href="${ctx}/spadmin/projects/spProjects/form?readonlyFlag=true&id=${spProject.id}">详情</a>
						<a href="${ctx}/spadmin/projects/spProjectUser/list?projectId=${spProject.id}">管理成员</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>