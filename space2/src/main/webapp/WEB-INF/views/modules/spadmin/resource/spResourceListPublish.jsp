<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资料库本地化管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function selectCategory(selResourceId){//资料本地化需要先选择“分类”
			art.dialog({
			    content: document.getElementById("ck"),
			    title:'请先选择资料分类',
			    fixed: true,
			    lock:true,
			    width:300,
			    height:80,
			    id: 'confirm',
			    okVal: '确认',
			    ok: function () {
			    	var selCategory = $('#selCategoryId').val();
			    	console.log("selResourceId="+selResourceId+" , selCategory="+selCategory);
			    	if (selResourceId !== null && selResourceId !== undefined && selResourceId !== '' && selCategory !== null && selCategory !== undefined && selCategory !== '') {
				    	window.location.href=ctx+"/spadmin/resource/spResource/saveResourceFromPlat?id="+selResourceId+"&categoryId="+selCategory;
			    	}
			    },
			    cancel: true 
			}); 
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/resource/spResource/getPublishResourceList">资料库本地化列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spResource" action="${ctx}/spadmin/resource/spResource/getPublishResourceList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
		<tr>
			<td><label>资料名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>具体类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('resource_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<%-- <td><label>资料分类：</label>
				<sys:treeselect id="spCategory" name="spCategory.id" value="${spResource.spCategory.id}" labelName="spCategory.name" labelValue="${spResource.spCategory.name}"
					title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spResource.currentUser.admin}"/>
			</td> --%>
		</tr>
		<tr>
			<td colspan="2"><label>上传时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spResource.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spResource.endUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</td>
			<td class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></td>
			<td class="clearfix"></td>
		</tr>
		</table>
	</form:form>
	<div class="control-group" id="ck" style="display: none; border-bottom: none;">
		<label class="control-label" style="font-size: 14px;">资料分类 ：</label>
		<div class="controls" style="display: inline-block;">
			<sys:treeselect id="selCategory" name="selCategory" value="${spResource.spCategory.id}" labelName="spCategory.name" labelValue="${spResource.spCategory.name}"
				title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spResource.currentUser.admin}"/>
		</div>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资料名称</th>
				<th>具体类型</th>
				<!-- <th>分类</th> -->
				<th>发布状态</th>
				<th>存储位置</th>
				<th>所属平台</th>
				<!-- <th>资料路径</th> -->
				<!-- <th>预览图</th> -->
				<th>资料简介</th>
				<th>上传时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spResource">
			<tr>
				<td title="${spResource.baseUrl}">
				<%-- <a href="${ctx}/spadmin/resource/spResource/form?id=${spResource.id}"> --%>
				<a href="javascript:void(0)">
					${spResource.name}
				</a></td>
				<td>
					<%-- ${fns:getDictLabel(spResource.type, 'resource_type', '')} --%>
					<c:if test="${spResource.type==1}">视频</c:if>
					<c:if test="${spResource.type==2}">音频</c:if>
					<c:if test="${spResource.type==3}">电子书</c:if>
				</td>
				<%-- <td>
					${spResource.spCategory.name}
				</td> --%>
				<td>
					<%-- ${fns:getDictLabel(spResource.publishState, 'resource_state', '')} --%>
					<c:if test="${spResource.publishState==0}"><font color="">未发布</font></c:if>
					<c:if test="${spResource.publishState==1}"><font color="orange">等待审核</font></c:if>
					<c:if test="${spResource.publishState==2}"><font color="red">审核未通过</font></c:if>
					<c:if test="${spResource.publishState==3}"><font color="green">审核通过</font></c:if>
				</td>
				<td>
					<%-- ${fns:getDictLabel(spResource.domain, 'resource_site', '')} --%>
					<c:if test="${spResource.domain==0}">本站</c:if>
					<c:if test="${spResource.domain==1}">CC</c:if>
				</td>
				<td>
					${spResource.sourceType==1?"本校":"平台"}
				</td>
				<%-- <td>
					${spResource.baseUrl}
				</td> --%>
				<%-- <td>
					${spResource.thumbImg}
				</td> --%>
				<td>
					${spResource.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spResource.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:set var="flag" value="true"></c:set> 
					<c:forEach items="${resourceList}" var="local">
						<c:if test="${ local.id eq spResource.id }">
							已本地化
							<c:set var="flag" value="false"></c:set>
						</c:if>
					</c:forEach> 
					<c:if test="${flag}">
						<%-- <a href="${ctx}/spadmin/resource/spResource/saveResourceFromPlat?id=${spResource.id}" onclick="return confirmx('确认要本地化该资料吗？', this.href)">本地化</a> --%>
						<a href="javascript:selectCategory('${spResource.id}');">本地化</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>