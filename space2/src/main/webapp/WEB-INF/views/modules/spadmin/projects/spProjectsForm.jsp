<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/upload.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					"name": {
						remote: {
							url:"${ctx}/spadmin/projects/spProjects/checkProjectName",
							data:{ projectName : function(){return $("#projectName").val();} 
								,projectNameOld : function(){return $("#projectNameOld").val();} 
							}
						}
					}
				},
				messages: {
					"name": {remote: "项目名称已存在"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
			
			var fileList = ${fns:toJson(fileList)};
			initialPreviewArr=[];
			initialPreviewConfigArr=[];
			if(fileList!=null){
				$.each(fileList,function(){
					initialPreviewArr.push(this.url);
					console.log(this.url);
					initialPreviewConfigArr.push({'caption':this.fileName,'key':this.id})
				});
			}
			$("#file-1").fileinput({
		        uploadUrl: '${ctx}/spadmin/projects/spProjects/addResource',//TODO
		        //allowedFileExtensions : ['jpg', 'png','gif'],
		        overwriteInitial: false,
		        maxFileSize: 10240,//TODO
		        //maxFileCount: 1,// 最大文件数
		        validateInitialCount: true,
		        initialPreview:initialPreviewArr,//TODO
			    initialPreviewAsData: true,
			    initialPreviewFileType: 'image',
			    initialPreviewConfig: initialPreviewConfigArr,//TODO
			    uploadExtraData:{'projectId':$('.projectId').val()},// 需要和资源一起上传的参数
		        slugCallback: function(filename) {
		            return filename.replace('(', '_').replace(']', '_');
		        },
		        showUploadedThumbs:true
		        ,showUpload:true
			}).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        }).on("fileuploaded", function(event, data, previewId, index) {
	            if(data.response && data.response.result=='true') {
	            	if($('.projectId').val()==''){
	            		console.log('--------------------');
	            		console.log(previewId);
	            		console.log(index);
	            		console.log(data.response.msg);//spadmin/projects/spProjects/addResource 的返回值
		                $('form').append("<input type='hidden' name='addResourceId' class='addResourceId' value='"+data.response.msg+"'/>");
	            	}
	                $('#'+previewId).attr('rid',data.response.msg);
	                console.log($('#'+previewId).attr('rid'));
	            }
	        }).on("filesuccessremove", function(vKey, jqXHR, rid, extraData,func) {
	        	if(rid==''){
	        		artDialog.alert("数据错误，请刷新页面");
	        		return false;
	        	}
	        	contents_getJsonForSync(
		        		'${ctx}/spadmin/projects/spProjects/delResource', //TODO
		        		{'resourceId':rid}, 
		        		"post", 
			        	function(data){
		        			if(data!=null && data.result=='true'){
		        				artDialog.alert("删除成功");
		        				func();
		        			}else{
		        				artDialog.alert("删除失败");
		        			}		        		
			        	},
			        	function(){
			        		artDialog.alert("删除失败");
			        	},
			        	null
			        );
	        });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${!readonlyFlag}">
			<li><a href="${ctx}/spadmin/projects/spProjects/">项目列表</a></li>
			<li class="active"><a href="${ctx}/spadmin/projects/spProjects/form?id=${spProject.id}&parent.id=${spProjectparent.id}">项目${not empty spProject.id?'修改':'添加'}查看</a></li>
		</c:if>
		<c:if test="${readonlyFlag}">
			<li><a href="${ctx}/spadmin/projects/spProjects/listmy">我的项目列表</a></li>
			<li class="active"><a href="${ctx}/spadmin/projects/spProjects/form?readonlyFlag=true&id=${spProject.id}&parent.id=${spProjectparent.id}">我的项目详情</a></li>
			<li><a href="${ctx}/spadmin/projects/spProjectUser/list?projectId=${spProject.id}">项目成员列表</a></li>
			<li><a href="${ctx}/spadmin/projects/spProjectUser/form?projectId=${spProject.id}">项目成员添加</a></li>
		</c:if>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spProject" action="${ctx}/spadmin/projects/spProjects/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" class="projectId" value="${spProject.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<input id="projectNameOld" name="projectNameOld" type="hidden" value="${spProject.name}">
				<form:input path="name" id="projectName" htmlEscape="false" maxlength="128" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">路演名称：</label>
			<div class="controls">
				<select name="lessonId" class="input-medium required">
					<c:forEach items="${lessonList}" var="one">
						<option value="${one.id}" ${spProject.lessonId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主讲人：</label>
			<div class="controls">
				<select name="speakerId" class="input-medium required">
					<c:forEach items="${speakerList}" var="one">
						<option value="${one.id}" ${spProject.speakerId==one.id?"selected":""}>${one.name }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商业计划书：</label>
			<div class="controls">
				<form:input id="businessProposalUrl" path="businessProposalUrl" htmlEscape="false" maxlength="256" class="input-medium required" readonly="true" onclick="$('#fileImg').click()"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<c:if test="${not empty spProject.id}">
					<a href="${spProject.businessProposalUrl}" class="btn">下载查看</a>
				</c:if>
				<input type="file" name="fileImg" id="fileImg" class="input-mini " onchange="$('#businessProposalUrl').val(this.value);" accept=".ppt,.pps,.pptx"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目资料：</label>
			<div class="controls">
				<input id="file-1" name="file" type="file" multiple class="file" data-overwrite-initial="false" >
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">特点：</label>
			<div class="controls">
				<form:textarea path="characteristic" htmlEscape="false" rows="3" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">背景：</label>
			<div class="controls">
				<form:textarea path="backdrop" htmlEscape="false" rows="3" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场规划：</label>
			<div class="controls">
				<form:textarea path="outletPlaning" htmlEscape="false" rows="3" maxlength="2048" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">盈利模式：</label>
			<div class="controls">
				<form:textarea path="profitModel" htmlEscape="false" rows="3" maxlength="1024" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<c:if test="${readonlyFlag}">
		<div class="control-group">
			<label class="control-label">项目成员：</label>
			<div class="controls">
				${usernameList}
			</div>
		</div>
		<div class="form-actions">
			<input class="btn btn-primary" type="button" value="管理成员" onclick="location.href='${ctx}/spadmin/projects/spProjectUser/list?projectId=${spProject.id}'"/>
		</div>
		</c:if>
		<c:if test="${!readonlyFlag}">
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</c:if>
	</form:form>
</body>
</html>