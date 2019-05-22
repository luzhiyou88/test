<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script src="${ctxWebInf}/js/spadmin/lesson/lessonList.js" type="text/javascript"></script>
	<script type="text/javascript">
		var lessonList=${fns:toJson(list)};
		
		function lessDel(id){
			var url = ctx +'/spadmin/lesson/spLesson/checkExam?lessonId='+id;
			$.ajax({
				url:url,
				async:true,
				type:'get',
				success:function(data){
					if (data == 'true') {
						art.dialog({
			  			    content: '课程有关联的试卷，确定删除吗?',
			  			    title:'课程删除',
			  			    fixed: true,
			  			    lock:true,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	//window.location.href=ctx+"/spadmin/lesson/spLesson/deleteLesson?id="+id;
			  			    	contents_getJsonForSync(
			  			    			ctx+"/spadmin/lesson/spLesson/deleteLesson?id="+id, 
			  			    			null, 
			  			    			"post", 
			  			    			function(data){
			  			    				if(data==null){
			  			    					art.alert('删除失败');
			  			    				}else{
			  			    					if(data.result=="true"){
			  			    						artDialog.alert(data.msg,function(){$('#btnSubmit').click()});
			  			    					}else{
			  			    						artDialog.alert(data.msg);
			  			    					}
			  			    				}
			  			    			},
			  			    			function(){
			  			    				art.alert('删除失败');
			  			    			},null);
			  			    },
			  			    cancel: true 
			  			});  
					} else if (data == 'false') {
						art.dialog({
			  			    content: '课程删除操作，确定删除吗?',
			  			    title:'课程删除',
			  			    fixed: true,
			  			    lock:true,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	//window.location.href=ctx+"/spadmin/lesson/spLesson/deleteLesson?id="+id;
			  			    	contents_getJsonForSync(
			  			    			ctx+"/spadmin/lesson/spLesson/deleteLesson?id="+id, 
			  			    			null, 
			  			    			"post", 
			  			    			function(data){
			  			    				if(data==null){
			  			    					art.alert('删除失败');
			  			    				}else{
			  			    					if(data.result=="true"){
			  			    						artDialog.alert(data.msg,function(){$('#btnSubmit').click()});
			  			    					}else{
			  			    						artDialog.alert(data.msg);
			  			    					}
			  			    				}
			  			    			},
			  			    			function(){
			  			    				art.alert('删除失败');
			  			    			},null);
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/lesson/spLesson/">课程列表</a></li>
		<li><a href="${ctx}/spadmin/lesson/spLesson/form">课程添加修改</a></li>
		<li><a href="${ctx}/spadmin/lesson/spLesson/schedule">课程表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spLesson" action="${ctx}/spadmin/lesson/spLesson/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
				<td><label>套课名称：</label>
					<form:input path="courseName" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<td><label>课程名称：</label>
					<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				<td>
					<label>上课老师：</label>
					<form:input path="teacherName" htmlEscape="false" maxlength="32" class="input-medium"/>
				</td>
				
			</tr>
			<tr>
				<td>
					<label>开课状态：</label>
					<form:select path="lessonState" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('curriculum_state')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</td>
				<td>
					<label>课程日期：</label>
					<input name="lessonDateFrom" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateFrom}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
					~	
					<input name="lessonDateTo" type="text" readonly="readonly" maxlength="20" class="input-date Wdate"
						value="<fmt:formatDate value="${spLesson.lessonDateTo}" pattern="yyyy/MM/dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true});"/>
				</td>
				<td>
					<label>课程时间：</label>
					<input name="lessonStarttimeFrom" type="text" readonly="readonly" maxlength="20" class="input-time Wdate"
						value="${spLesson.lessonStarttimeFrom}"
						onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});"/>
					~
					<input name="lessonEndtimeTo" type="text" readonly="readonly" maxlength="20" class="input-time Wdate"
						value="${spLesson.lessonEndtimeTo}"
						onclick="WdatePicker({dateFmt:'HH:mm',isShowClear:true});"/>
				</td>
			</tr>
		</table>
		<div class="searchFormButton">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input class="btn btn-primary" type="reset" value="清空"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>				
				<th>课程名称</th>
				<th>套课名称</th>
				<th>开课时间</th>
				<th>上课老师</th>
				<th>开课状态</th>
				<th>发布状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spLesson">
			<tr>
				<td>${spLesson.name}</td>
				<td>
					<a href="${ctx}/spadmin/lesson/spLesson/form?courseId=${spLesson.courseId}">${spLesson.courseName}</a>
				</td>
				<td>${fns:getFormatDate(spLesson.lessonDate)}&nbsp;${spLesson.lessonStarttime}~${spLesson.lessonEndtime}</td>
				<td>${spLesson.teacherName}</td>
				<td>${fns:getDictLabel(spLesson.lessonState, "curriculum_state", "-")}</td>
				<td>${fns:getDictLabel(spLesson.publishState, "sp_publish_state", "-")}</td>
				<td>
					<c:if test="${spLesson.publishState=='0' or spLesson.publishState=='2' }">
						<a href="${ctx}/spadmin/lesson/spLesson/form?id=${spLesson.id}">修改</a>
						<c:if test="${spLesson.lessonSource=='2' }">
							<a href="#" onclick="javascript:publishLesson('课程','${ctx}/spadmin/lesson/spLesson/publishLesson?id=${spLesson.id}')">发布平台</a>
						</c:if>
					</c:if>
					<c:if test="${spLesson.publishState=='1' or spLesson.publishState=='3'  or spLesson.publishState=='4' }">
						<a href="${ctx}/spadmin/lesson/spLesson/form?id=${spLesson.id}&mode=show">查看</a>
						
					</c:if>
					<c:if test="${spLesson.lessonSource=='2' and spLesson.publishState=='1'}">
						<a href="#" onclick="javascript:refreshPublishStatus('${ctx}/spadmin/lesson/spLesson/refreshPublishStatus?id=${spLesson.id}')">刷新发布状态</a>
					</c:if>
					<%-- <a href="#" onclick="javascript:lessonDelete('${ctx}/spadmin/lesson/spLesson/delete?id=${spLesson.id}')">删除</a> --%>
						<a href="#" onclick="lessDel('${spLesson.id}')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>