<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料管理</title>
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
		
		
		function deleteMaterial(id) {
			var url = ctx +'/spadmin/material/spMaterials/checkProblemMaterial?materialId='+id;
			$.ajax({
				url:url,
				async:true,
				type:'get',
				success:function(data){
					if (data == 'true') {
						art.dialog({
			  			    content: '试卷有关联的材料,如果删除将同时把试题删除，确定删除吗?',
			  			    title:'材料删除',
			  			    fixed: true,
			  			    lock:true,
			  			    width:450,
			  			    height:280,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	window.location.href=ctx+"/spadmin/material/spMaterials/deleteProblemMaterial?id="+id;
			  			    },
			  			    cancel: true 
			  			});  
					} else if (data == 'false') {
						art.dialog({
			  			    content: '材料删除操作，确定删除吗?',
			  			    title:'材料删除',
			  			    fixed: true,
			  			    lock:true,
			  			    width:450,
			  			    height:280,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	window.location.href=ctx+"/spadmin/material/spMaterials/delete?id="+id;
			  			    },
			  			    cancel: true 
			  			});  
					}
				},
				error:function(){
					art.dialog.alert('请求出错!');
				}
			});
		}
		
		
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
		<li class="active"><a href="${ctx}/spadmin/material/spMaterials/">材料列表</a></li>
		<li><a href="${ctx}/spadmin/material/spMaterials/form">材料添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spMaterials" action="${ctx}/spadmin/material/spMaterials/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>材料名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<th>材料名称</th>
				<th>材料内容</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spMaterials">
			<tr>
				<td><a href="${ctx}/spadmin/material/spMaterials/form?id=${spMaterials.id}">
					${spMaterials.name}
				</a></td>
				<td class="ct">
					${spMaterials.cotent}
				</td>
				<td>
					<fmt:formatDate value="${spMaterials.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/material/spMaterials/form?id=${spMaterials.id}">修改</a>
					<%-- <a href="${ctx}/spadmin/material/spMaterials/delete?id=${spMaterials.id}" onclick="return confirmx('确认要删除该材料吗？', this.href)">删除</a> --%>
					<a href="#" onclick="deleteMaterial('${spMaterials.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>