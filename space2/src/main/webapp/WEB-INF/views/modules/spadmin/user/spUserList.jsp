<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
			
			$("#searchForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
		<li class="active"><a href="${ctx}/spadmin/user/spUser/">用户列表</a></li>
<%-- 		<li><a href="${ctx}/spadmin/user/spUser/form">用户添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="spUser" action="${ctx}/spadmin/user/spUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
		<tr>
			<td><label>用户姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>手机号码：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="11" class="input-medium number"/>
			</td>
			<td><label>入学年份：</label>
				<form:input path="enterYear" htmlEscape="false" maxlength="4" class="input-medium number" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true});" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td><label>用户类型：</label>
				<form:select path="userType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sp_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>所属专业：</label>
				<select name="specialtyId" class="input-medium">
					<option value=""></option>
					<c:forEach items="${specialtyList}" var="one">
						<option value="${one.id}" ${spUser.specialtyId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</td>
			<td><label>所在班级：</label>
				<select name="classId" class="input-medium">
					<option value=""></option>
					<c:forEach items="${classList}" var="one">
						<option value="${one.id}" ${spUser.classId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><label>当前状态：</label>
				<select name="delFlag" class="input-medium">
					<option value="">请选择</option>
					<option value="0" ${spUser.delFlag==0?"selected":""}>已启用</option>
					<option value="1" ${spUser.delFlag==1?"selected":""}>已关闭</option>
				<select>
			</td>
			<td class="btns">
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
				<th>用户姓名</th>
				<th>登录名</th>
				<th>当前状态</th>
				<th>手机</th>
				<th>邮箱</th>
				<th>用户类型</th>
				<th>入学年份</th>
				<th>所属专业</th>
				<th>所在班级</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spUser">
			<tr>
				<td><a href="${ctx}/spadmin/user/spUser/form?id=${spUser.id}">
					${spUser.name}
				</a></td>
				<td>${spUser.loginName}</td>
				<td>${spUser.delFlag==0?"<font color='green'>已启用</font>":"<font color='red'>已关闭</font>"}</td>
				<td>${spUser.mobile}</td>
				<td>${spUser.email}</td>
				<td>${fns:getDictLabel(spUser.userType, 'sp_user_type', '')}</td>
				<td>${spUser.enterYear}</td>
				<td>${spUser.spSpecialty.name}</td>
				<td>${spUser.spClass.name}</td>
				<td class="ct">
					${(fn:length(spUser.remarks)>20)?(fn:substring(spUser.remarks,0,20)):(spUser.remarks)}${(fn:length(spUser.remarks)>20)?"...":""}
				</td>
				<td>
					<fmt:formatDate value="${spUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/user/spUser/form?id=${spUser.id}">修改</a>
					<c:if test="${spUser.delFlag==0}">
						<a href="${ctx}/spadmin/user/spUser/delete?id=${spUser.id}" onclick="return confirmx('确认要关闭该用户吗？', this.href)">关闭</a>
					</c:if>
					<c:if test="${spUser.delFlag!=0}">
						<a href="${ctx}/spadmin/user/spUser/revert?id=${spUser.id}" onclick="return confirmx('确认要启用该用户吗？', this.href)">启用</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>