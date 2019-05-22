<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理管理</title>
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
		
		function deleteMess(id){
			var title = $('#title').val();
			var operaterName = $('#operaterName').val();
			var url = ctx + '/spadmin/message/spMessageAlerts/delete?id='+id+'&title='+title+'&operaterName='+operaterName;
			art.dialog({
				content:"确认要删除吗？",
  			    title:'删除公告',
  			    fixed: true,
  			    lock:true,
  			    width:200,
  			    height:100,
  			    id: 'confirm',
  			    okVal: '确认',
  			    ok: function () {
  			    	window.location.href=url;
  			    },
  			    cancel: true
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/message/spMessageAlerts/">公告管理列表</a></li>
		<li><a href="${ctx}/spadmin/message/spMessageAlerts/form">公告管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spMessageAlerts" action="${ctx}/spadmin/message/spMessageAlerts/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
		    <tr>
			<td><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="128" class="input-xlarge"/>
			</td>
			<td><label>发布人名称：</label>
				<form:input path="operaterName" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</td>
			<td ><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<input class="btn btn-primary" type="reset" value="清空"/></td>
			</tr>
			</table>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>内容</th>
				<th>发布人名称</th>
				<th>消息类型 </th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spMessageAlerts">
			<tr>
				<td><a href="${ctx}/spadmin/message/spMessageAlerts/form?id=${spMessageAlerts.id}">
					${spMessageAlerts.title}
				</a></td>
				<td class="ct">
					${spMessageAlerts.content}
				</td>
				<td>
					${spMessageAlerts.operaterName}
				</td>
				<td>
					<c:if test="${spMessageAlerts.messageType == '1'}">系统</c:if>
				</td>
				<td>
    				<a href="${ctx}/spadmin/message/spMessageAlerts/form?id=${spMessageAlerts.id}">修改</a>
					<a href="#" onclick="deleteMess('${spMessageAlerts.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>