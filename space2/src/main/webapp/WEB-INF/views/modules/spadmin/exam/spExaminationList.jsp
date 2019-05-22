<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
			
		function deleteExam(id){
			var url = ctx +'/spadmin/exam/spExamination/checkProblem?examId='+id;
			$.ajax({
				url:url,
				async:true,
				type:'get',
				success:function(data){
					if (data == 'true') {
						art.dialog({
			  			    content: '试卷有关联的试题,如果删除将同时把试题删除，确定删除吗?',
			  			    title:'试卷删除',
			  			    fixed: true,
			  			    lock:true,
			  			    width:450,
			  			    height:280,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	window.location.href=ctx+"/spadmin/exam/spExamination/deleteProblem?id="+id;
			  			    },
			  			    cancel: true 
			  			});  
					} else if (data == 'false') {
						art.dialog({
			  			    content: '试卷删除操作，确定删除吗?',
			  			    title:'试卷删除',
			  			    fixed: true,
			  			    lock:true,
			  			    width:450,
			  			    height:280,
			  			    id: 'confirm',
			  			    okVal: '确认',
			  			    ok: function () {
			  			    	window.location.href=ctx+"/spadmin/exam/spExamination/delete?id="+id;
			  			    },
			  			    cancel: true 
			  			});  
					}
				},
				error:function(){
					art.dialog.alert('请求出错!');
				}
			});
		};
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        };
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/spadmin/exam/spExamination/">试卷管理列表</a></li>
		<li><a href="${ctx}/spadmin/exam/spExamination/form">试卷管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spExamination" action="${ctx}/spadmin/exam/spExamination/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<%-- <td><label>课程ID：</label>
				<form:input path="lessonId" htmlEscape="false" maxlength="32" class="input-small"/>
			</td> --%>
			<td><label>试卷名称：</label>
				<form:input path="examinationName" htmlEscape="false" maxlength="128" class="input-small"/>
			</td>
			<%-- <td><label>试卷总分数：</label>
				<form:input path="examinationTotalScore" htmlEscape="false" maxlength="11" class="input-small"/>
			</td>
			<td><label>题目总数：</label>
				<form:input path="examinationNumber" htmlEscape="false" maxlength="11" class="input-small"/>
			</td> --%>
			<td><label>出题人：</label>
				<form:input path="originators" htmlEscape="false" maxlength="200" class="input-small"/>
			</td>
			<%-- <td><label>试卷用时：</label>
				<form:input path="timing" htmlEscape="false" maxlength="11" class="input-small"/>
			</td> --%>
			<%-- <td><label>试卷状态 ：</label>
				<form:input path="state" htmlEscape="false" maxlength="1" class="input-small"/>
			</td> --%>
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
				<th>试卷名称</th>
				<th>关联课程名称</th>
				<th>试卷分数</th>
				<th>题目数</th>
				<th>出题人</th>
				<th>考试用时</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spExamination">
			<tr>
				<td><a href="${ctx}/spadmin/exam/spExamination/form?id=${spExamination.id}">
					${spExamination.examinationName}
				</a></td>
				<td>${spExamination.lessonName }</td>
				<td>
					${spExamination.examinationTotalScore}
				</td>
				<td>
					${spExamination.examinationNumber}
				</td>
				<td>
					${spExamination.originators}
				</td>
				<td>
					${spExamination.timing}
				</td>
				<td>
    				<a href="${ctx}/spadmin/exam/spExamination/form?id=${spExamination.id}">修改</a>
					<a href="#" onclick="deleteExam('${spExamination.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>