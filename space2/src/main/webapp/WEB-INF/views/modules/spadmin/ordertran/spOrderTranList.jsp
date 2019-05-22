<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单流水管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/ordertran/spOrderTran/">订单流水列表</a></li>
		<%-- <li><a href="${ctx}/spadmin/ordertran/spOrderTran/form">订单流水添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="spOrderTran" action="${ctx}/spadmin/ordertran/spOrderTran/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>流水号：</label>
				<form:input path="tranNo" htmlEscape="false" maxlength="20" class="input-medium"/>
			</td>
			<td><label>流水生成时间：</label>
				<input name="tranDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spOrderTran.tranDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</td>
			<td><label>订单号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="20" class="input-medium"/>
			</td>
			</tr>
			<tr>
			<td><label>订单类型：</label>
				<form:select path="orderType" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>订单状态：</label>
				<form:select path="orderState" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>流水号</th>
				<th>流水生成时间</th>
				<th>订单号</th>
				<th>订单类型 </th>
				<th>订单状态</th>
				<th>支付金额</th>
				<th>网关流水号</th>
				<th>网关结果</th>
				<th>网关时间</th>
				<th>备注</th>
				<th>创建时间</th>
				<!-- <th>创建者</th> -->
				<!-- <th>删除标记</th> -->
				<!-- <th>操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spOrderTran">
			<tr>
				<%-- <td><a href="${ctx}/spadmin/ordertran/spOrderTran/form?id=${spOrderTran.id}">
					${spOrderTran.tranNo}
				</a></td --%>
				<td>
					${spOrderTran.tranNo}
				</td>
				<td>
					<fmt:formatDate value="${spOrderTran.tranDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${spOrderTran.orderNo}
				</td>
				<td>
					${fns:getDictLabel(spOrderTran.orderType, 'dic_order_type', '')}
				</td>
				<td>
					${fns:getDictLabel(spOrderTran.orderState, 'dic_order_state', '')}
				</td>
				<td>
					${spOrderTran.payAmount}
				</td>
				<td>
					${spOrderTran.gwTranNo}
				</td>
				<td>
					${spOrderTran.gwResult}
				</td>
				<td>
					${spOrderTran.gwDate}
				</td>
				<td class="ct">
					${spOrderTran.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spOrderTran.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${spOrderTran.createBy.id}
				</td> --%>
				<%-- <td>
					${fns:getDictLabel(spOrderTran.delFlag, 'del_flag', '')}
				</td> --%>
				<%-- <td>
    				<a href="${ctx}/spadmin/ordertran/spOrderTran/form?id=${spOrderTran.id}">修改</a>
					<a href="${ctx}/spadmin/ordertran/spOrderTran/delete?id=${spOrderTran.id}" onclick="return confirmx('确认要删除该订单流水吗？', this.href)">删除</a>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>