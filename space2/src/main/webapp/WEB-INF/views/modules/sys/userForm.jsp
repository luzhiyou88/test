<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					loginName: {remote: {
											url:"${ctx}/sys/user/checkLoginName",
											data:{ oldLoginName:function(){return $("#oldLoginName").val();} 
												,loginName:function(){return $("#loginName").val();}
											}
										}
								},
					   mobile: {remote: {
											url:"${ctx}/sys/user/checkMobile",
											data:{ mobile:function(){return $("#mobile").val();} 
												,oldMobile:function(){return $("#oldMobile").val();} 
											} 
									   }
							   },
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"},
					mobile: {remote: "手机号已存在"},
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
		});
		
		
		jQuery.validator.addMethod("isMobile", function(value, element) {
			var length = value.length;
			var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
			return this.optional(element) || (length == 11 && mobile.test(value));
		}, "请正确填写您的手机号码");
		
		function changeUserType(val){
			if(val=="4"){
				$("#teamId").show();
				$("#teamId").addClass("required");
			}else{
				$("#teamId").hide();
				$("#teamId").removeClass("required");
			}
		}
		$(function(){
			var userType = $("#userType").val();
			//changeUserType(userType);
		})
		//提交之前做验证
		function toSubmit(){
			if($("input[name=roleIdList]:checked").length==0){
				return false;
			}else{
				return true;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal" enctype="multipart/form-data" onsubmit="return toSubmit()">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
			<c:if test="${not empty user.id}"><img alt="" src="${user.photo}" width="100" height="100"></c:if>
			<input type="file" name="file">
			</div>
		</div>
		<%-- <c:if test="${empty schoolId }">
		<div  class="control-group">
			<label class="control-label">归属学校:</label>
			<div class="controls">
			<form:select path="schoolId" items="${fncr:getCrSchoolMap(true)}" class="input-xlarge required" />
			<!-- <input name="company.id" id="company" type="hidden"> -->
                <sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
					title="公司" url="/sys/office/treeData?type=1" cssClass="required"/>
			</div>
		</div> 
		</c:if>
		<c:if test="${not empty schoolId }">
		<input name="schoolId" value="${schoolId }" type="hidden">
		</c:if> --%>
		<%-- <div style="display:none" class="control-group">
			<label class="control-label">归属班级:</label>
			<div class="controls">
			<input name="office.id" id="office" type="hidden">
                <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">学号:</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="50"/>
				<span class="help-inline"> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" readonly='${!empty user.id}'/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">座机:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100" class="phone required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<input id="oldMobile" name="oldMobile" type="hidden" value="${user.mobile}">
				<form:input path="mobile" id="mobile" htmlEscape="false" maxlength="100" class="mobilenum required" readonly='${!empty user.id}'/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否允许登录:</label>
			<div class="controls">
				<form:select path="loginFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<form:select path="userType" id="userType" onchange="changeUserType(this.value)" class="input-xlarge required" >
					<%-- <form:option value="" label="请选择"/> --%>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	<%-- 	<div style="display:none" id="teamId" class="control-group">
			<label class="control-label">项目团队：</label>
			<div class="controls">
			<form:select id="teamId" path="teamId" class="input-small required">
            <option value="">请选择</option>
			<form:options items="${teamList }" itemLabel="teamName" itemValue="id" htmlEscape="false" />
		   </form:select>
		   <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<c:if test="${not empty user.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后登陆:</label>
				<div class="controls">
					<label class="lbl">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></label>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>