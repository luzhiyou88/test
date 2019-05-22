<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资料库管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/resource/spResource/">资料库列表</a></li>
		<li><a href="${ctx}/spadmin/resource/spResource/form">资料库添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spResource" action="${ctx}/spadmin/resource/spResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
		<tr>
			<td><label>资料名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</td>
			<td><label>具体类型：</label>
				<form:select path="type" class="input-medium">
					<option value=""></option>
					<form:options items="${fns:getDictList('resource_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>资料分类：</label>
<%-- 				<form:input path="categoryId" htmlEscape="false" maxlength="32" class="input-medium"/> --%>
				<sys:treeselect id="spCategory" name="spCategory.id" value="${spResource.spCategory.id}" labelName="spCategory.name" labelValue="${spResource.spCategory.name}"
					title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spResource.currentUser.admin}"/>
			</td>
		</tr>
		<tr>
			<td><label>发布状态：</label>
				<form:select path="publishState" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('resource_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td colspan="2"><label>上传时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spResource.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spResource.endUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</td>
			<td class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input class="btn btn-primary" type="reset" value="清空"/>
			</td>
			<td class="clearfix"></td>
		</tr>
		</table>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资料名称</th>
				<th>具体类型</th>
				<th>分类</th>
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
				<td>
					${spResource.spCategory.name}
				</td>
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
				<td class="ct">
					${spResource.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spResource.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<%-- <a href="${ctx}/spadmin/resource/spResource/form?id=${spResource.id}">修改</a> --%>
						<a href="${ctx}/spadmin/resource/spResource/delete?id=${spResource.id}" onclick="return confirmx('确认要删除该资料库吗？', this.href)">删除</a>
    				<%-- <c:if test="${not ( spResource.sourceType==2 && (spResource.publishState==1 || spResource.publishState==3) )}">
					</c:if> --%>
					<c:if test="${spResource.sourceType==2}">
						<c:if test="${spResource.publishState==0}">
							<a href="${ctx}/spadmin/resource/spResource/release2Plat?id=${spResource.id}">发布到平台</a>
						</c:if>
						<c:if test="${spResource.publishState==1}">
							<a href="${ctx}/spadmin/resource/spResource/flushPublishState?id=${spResource.id}">刷新审核状态</a>
						</c:if>
						<c:if test="${spResource.publishState==2}">
							<a href="${ctx}/spadmin/resource/spResource/release2Plat?id=${spResource.id}">重新发布到平台</a>
						</c:if>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>