<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/member/spMember/">会员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spMember" action="${ctx}/spadmin/member/spMember/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			<td><label>登录账号：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			</tr>
			<tr>
			<td><label>会员状态：</label>
				<form:select path="state" class="input-medium ">
					<form:option value="" label="请选择"/>
					<form:option value="0" label="正常"/>
					<form:option value="1" label="过期"/>
				</form:select>
			</td>
			<td colspan="2"><label>到期时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spMember.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spMember.endUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
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
				<th>用户名</th>
				<th>登录账号</th>
				<th>会员开始时间</th>
				<th>会员到期时间</th>
				<th>会员剩余天数</th>
				<th>会员状态</th>
				<th>备注</th>
				<th>开通会员时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spMember">
			<tr>
				<td><a href="${ctx}/spadmin/member/spMember/form?id=${spMember.id}">
					${spMember.userName}
				</a></td>
				<td>
					${spMember.loginName}
				</td>
				<td>
					<fmt:formatDate value="${spMember.startTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${spMember.endTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:set var="leaveDay" value="${(spMember.endTime.time-spMember.startTime.time)}"/>
					<fmt:formatNumber var="leaveDayNum" value="${leaveDay/1000/3600/24}" pattern="#0"/>
					<c:if test="${leaveDayNum<0}">0</c:if>
					<c:if test="${leaveDayNum>0}">${leaveDayNum}</c:if>
				</td>
				<td>
					${spMember.state==0?"<label style='color:green;'>正常</label>":"<label style='color:red;'>过期</label>"}
				</td>
				<td class="ct">
					${spMember.desc}
				</td>
				<td>
					<fmt:formatDate value="${spMember.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/member/spMember/form?id=${spMember.id}">修改</a>
					<a href="${ctx}/spadmin/member/spMember/delete?id=${spMember.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>