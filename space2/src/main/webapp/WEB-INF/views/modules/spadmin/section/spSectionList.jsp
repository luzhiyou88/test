<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节次管理</title>
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
		
		function deleteSec(id){
			if(id == null || id == '' || id == 'undefined'){
				return false;
			}
			art.dialog({
				content:"确认要删除该节次吗？",
  			    title:'删除节点',
  			    fixed: true,
  			    lock:true,
  			    width:200,
  			    height:100,
  			    id: 'confirm',
  			    okVal: '确认',
  			    ok: function () {
  			    	var url = ctx+"/spadmin/section/spSection/checkSectionLesson?sectionId="+id;
  					$.ajax({
  						type: 'get',
  				        url: url,
  				        dataType: "json",
  				        async: true,
  				        success:function(data){
  				        	if(data == true){
  				        		var url =ctx+"/spadmin/section/spSection/delete?id="+id;
  				        		window.location.href=url;
  				        	} else {
  				        		art.dialog.alert("课程表有该节次记录，不能删除!");
  				        		return false;
  				        		
  				        	}
  				        },
  				        error:function(){
  				        	art.dialog.alert("请求出错!");
  				        }
  					});
  			    },
  			    cancel: true 
  			});  
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/section/spSection/">节次列表</a></li>
		<li><a href="${ctx}/spadmin/section/spSection/form">节次添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spSection" action="${ctx}/spadmin/section/spSection/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>备注</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spSection">
			<tr>
				<td><a href="${ctx}/spadmin/section/spSection/form?id=${spSection.id}">
					${spSection.name}
				</a></td>
				<td>
					${spSection.startTime}
				</td>
				<td>
					${spSection.endTime}
				</td>
				<td class="ct">
					${spSection.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spSection.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/section/spSection/form?id=${spSection.id}">修改</a>
					<a href="javascript:void(0)"  onclick="deleteSec('${spSection.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>