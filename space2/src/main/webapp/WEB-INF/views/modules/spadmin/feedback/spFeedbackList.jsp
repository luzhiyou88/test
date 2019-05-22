<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户反馈管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			var obj = $('.ct');
			$.each(obj,function(k,v){
				var con = v.innerHTML;
				if(con.length > 100){
					v.innerHTML=v.innerHTML.substring(0,340)+'...';
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
		<li class="active"><a href="${ctx}/spadmin/feedback/spFeedback/">用户反馈列表</a></li>
		<%-- <li><a href="${ctx}/spadmin/feedback/spFeedback/form">用户反馈添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="spFeedback" action="${ctx}/spadmin/feedback/spFeedback/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<%-- <td><label>用户ID：</label>
				<sys:treeselect id="userId" name="userId" value="${spFeedback.userId}" labelName="user.name" labelValue="${spFeedback.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</td> --%>
			<td><label>提交时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spFeedback.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</td>
			<td>
			   <label>登录账号</label>
			   <input name="loginName" type="text" value="${spFeedback.loginName}" maxlength="20" class="input-medium"/>
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
				<th>用户姓名</th>
				<th>登录账号</th>
				<th>手机号</th>
				<th>反馈内容</th>
				<th>提交时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spFeedback">
			<tr>
				<td><a href="${ctx}/spadmin/feedback/spFeedback/form?id=${spFeedback.id}">
					${spFeedback.userName}
				</a></td>
				<td>
					${spFeedback.loginName}
				</td>
				<td>
					${spFeedback.mobile}
				</td>
				<td class="ct">
					${spFeedback.content}
				</td>
				<td>
					<fmt:formatDate value="${spFeedback.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/feedback/spFeedback/form?id=${spFeedback.id}">详情</a>
					<a href="${ctx}/spadmin/feedback/spFeedback/delete?id=${spFeedback.id}" onclick="return confirmx('确认要删除该用户反馈吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>