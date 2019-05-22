<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试题管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var obj = $('.ct');
			$.each(obj,function(k,v){
				var con = v.innerHTML;
				if(con.length > 20){
					v.innerHTML=v.innerHTML.substring(0,20)+'...';
			        } 
			});
			
			$('.sjmc').each(function(k,v){
				var name = $(v).text().trim();
				if(name.length>20){
					$(v).text(name.substring(0,20)+'...');
				}
			})
			
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
		<li class="active"><a href="${ctx}/spadmin/problems/spProblems/">试题管理列表</a></li>
		<li><a href="${ctx}/spadmin/problems/spProblems/form">试题管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="spProblems" action="${ctx}/spadmin/problems/spProblems/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			  <td><label>题干：</label>
				<form:input path="stem" htmlEscape="false" maxlength="128" class="input-medium"/>
			  </td>
			  <td>
			    <label class="control-label">所属试卷：</label>
				<form:select path="examinationId"  items="${fncr:getSpExaminationMap(true)}" class="input-large" ></form:select>
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
				<th>所属试卷名称</th>
				<th>题目类型</th>
				<th>题干</th>
				<th>题目编号</th>
				<th>文本类型 </th>
				<th>A</th>
				<th>B</th>
				<th>C</th>
				<th>D</th>
				<th>正确答案</th>
				<!-- <th>是否是材料题</th> -->
				<th>分值</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spProblems">
			<tr>
				<td class="sjmc"><a href="${ctx}/spadmin/problems/spProblems/form?id=${spProblems.id}">
					${spProblems.examinationName}
				</a></td>
				<td>
					<c:if test="${spProblems.problemType == '1'}">单选</c:if>
					<c:if test="${spProblems.problemType == '2'}">多选</c:if>
					<c:if test="${spProblems.problemType == '3'}">不定项</c:if>
					<c:if test="${spProblems.problemType == '4'}">主观题</c:if>
				</td>
				<td class="ct">
					${spProblems.stem}
				</td>
				<td>
					${spProblems.number}
				</td>
				<td>
				    <c:if test="${spProblems.textType == '1'}">文字</c:if>
				</td>
				<td class="ct">
					${spProblems.optionA}
				</td>
				<td class="ct">
					${spProblems.optionB}
				</td>
				<td class="ct">
					${spProblems.optionC}
				</td>
				<td class="ct">
					${spProblems.optionD}
				</td>
				<td>
					${spProblems.answer}
				</td>
				<%-- <td>
                    <c:if test="${spProblems.ismaterial == '1'}">材料题</c:if>
					<c:if test="${spProblems.ismaterial == '2'}">非材料题</c:if>
				</td> --%>
				<td>
					${spProblems.problemScore}
				</td>
				<td>
					<fmt:formatDate value="${spProblems.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/spadmin/problems/spProblems/form?id=${spProblems.id}">修改</a>
    				<a href="${ctx}/spadmin/problems/spProblems/form?id=${spProblems.id}&xq='1'">详情</a>
					<a href="${ctx}/spadmin/problems/spProblems/delete?id=${spProblems.id}" onclick="return confirmx('确认要删除该试题管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>