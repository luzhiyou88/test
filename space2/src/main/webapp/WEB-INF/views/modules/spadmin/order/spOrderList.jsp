<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/spadmin/order/spOrder/">订单列表</a></li>
		<%-- <li><a href="${ctx}/spadmin/order/spOrder/form">订单添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="spOrder" action="${ctx}/spadmin/order/spOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table>
			<tr>
			<td><label>用户姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-mini"/>
			</td>
			<td><label>订单类型：</label>
				<form:select path="orderType" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>辅助ID：</label>
				<form:input path="auxiliaryId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</td>
			</tr>
			<tr>
			<td><label>支付类型：</label>
				<form:select path="payType" class="input-mini">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>订单状态：</label>
				<form:select path="orderState" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dic_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${spOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</td>
			<td><label>订单号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>订单号</th>
				<th>用户姓名</th>
				<th>所属第二空间</th>
				<th>订单类型</th>
				<th>辅助ID</th>
				<th>支付类型</th>
				<th>订单状态</th>
				<th>订单价格</th>
				<th>备注</th>
				<th>创建时间</th>
				<!-- <th>操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="spOrder">
			<tr>
			<%-- 	<td><a href="${ctx}/spadmin/order/spOrder/form?id=${spOrder.id}">
					${spOrder.orderNo}
				</a></td> --%>
				<td>
					${spOrder.orderNo}
				</td>
				<td>
					${spOrder.userName}
				</td>
				<td>
					${spOrder.spaceName}
				</td>
				<td>
					${fns:getDictLabel(spOrder.orderType, 'dic_order_type', '')}
				</td>
				<td>
					${spOrder.auxiliaryId}
				</td>
				<td>
					${fns:getDictLabel(spOrder.payType, 'dic_pay_type', '')}
				</td>
				<td>
					${fns:getDictLabel(spOrder.orderState, 'dic_order_state', '')}
				</td>
				<td>
					${spOrder.orderPrice}
				</td>
				<td class="ct">
					${spOrder.remarks}
				</td>
				<td>
					<fmt:formatDate value="${spOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
    				<a href="${ctx}/spadmin/order/spOrder/form?id=${spOrder.id}">修改</a>
					<a href="${ctx}/spadmin/order/spOrder/delete?id=${spOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>