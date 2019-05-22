<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>套课管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/upload.jsp"%><!-- TODO -->
	<script type="text/javascript">
		var fileList = ${fns:toJson(fileList)};
			
		$(document).ready(function() {
			if(${publishedLessonNum > 0}){
				$(':input:not(#btnCancel)').attr('disabled',true);
				$('a').attr('disabled',true).removeAttr('onclick');
			}
			
			// 课程类型：会员不需要输入价格
			$('#courseType').change(function(){
				if($(this).val()=='1'){
					// 会员
					$('div.coursePrice').hide();
				}else{
					// 收费
					$('div.coursePrice').show();
				}
			});
			$("#inputForm").validate({
				rules: {
					courseName: {remote: "${ctx}/spadmin/course/spCourse/checkCourseName?id=${spCourse.id}"},
            	},		
            	messages: {		
            		courseName: {remote: "套课名称已存在"}
            	},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox"
			});
			initialPreviewArr=[];
			initialPreviewConfigArr=[];
			if(fileList!=null){
				$.each(fileList,function(){
					initialPreviewArr.push(this.url);
					initialPreviewConfigArr.push({'caption':this.fileName,'key':this.id})
				});
			}
			$("#file-1").fileinput({
		        uploadUrl: '${ctx}/spadmin/course/spCourse/upload',//TODO
		        //allowedFileExtensions : ['jpg', 'png','gif'],
		        overwriteInitial: false,
		        maxFileSize: 10240,//TODO
		        //maxFileCount: 1,// 最大文件数
		        validateInitialCount: true,
		        initialPreview:initialPreviewArr,//TODO
			    initialPreviewAsData: true,
			    initialPreviewFileType: 'image',
			    initialPreviewConfig: initialPreviewConfigArr,//TODO
			    uploadExtraData:{'courseId':$('.courseId').val()},// 需要和资源一起上传的参数
		        slugCallback: function(filename) {
		            return filename.replace('(', '_').replace(']', '_');
		        },
		        
			}).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        }).on("fileuploaded", function(event, data, previewId, index) {
	            if(data.response && data.response.result=='true') {
	            	if($('.courseId').val()==''){
	            		// 新增套课情况下，上传的文件不带courseId
	            		var resourceTemp = $('input.addCourseResourceId');
		                var resourceNew = $(resourceTemp).clone().val(data.response.msg);
		                $('form').append(resourceNew);
	            	}
	                
	                $('#'+previewId).attr('rid',data.response.msg);
	            }
	        }).on("filesuccessremove", function(vKey, jqXHR, rid, extraData,func) {
	        	if(rid==null || rid==''){
	        		artDialog.alert("数据错误，请刷新页面");
	        		return false;
	        	}
	        	contents_getJsonForSync(
		        		'${ctx}/spadmin/course/spCourse/deleteResource', //TODO
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
	        }).on("filedownload", function(vKey, rid) {
	        	if(rid==null || rid==''){
	        		artDialog.alert("没有可下载的文件");
	        		return false;
	        	}
	        	window.open('${ctx}/spadmin/course/spCourse/download?fileKey='+rid,'download');
	        });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/course/spCourse/">套课列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/course/spCourse/form?id=${spCourse.id}">套课${not empty spCourse.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spCourse" action="${ctx}/spadmin/course/spCourse/save" method="post" class="form-horizontal">
		<input type="hidden" class="courseId" name="id" value="${spCourse.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">套课名称：</label>
			<div class="controls">
				<form:input path="courseName" htmlEscape="false" maxlength="128" class="input-xlarge required" 
				disabled="${spCourse.courseSource==2 ? 'true':''}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程总数：</label>
			<div class="controls">
				<label class="input-xlarge">${spCourse.courseNumber}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套课分类：</label>
			<div class="controls">
				<sys:treeselect id="spCategory" name="categoryId" value="${spCourse.categoryId }" labelName="categoryName" labelValue="${spCourse.categoryName}" disabled="${ publishedLessonNum>0?'disabled':''}" 
				title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spCourse.currentUser.admin}" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">
				<form:select path="courseType" class="input-xlarge required" disabled="${spCourse.courseSource==2 ? 'true':''}">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('cr_fee_level')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group coursePrice" style="<c:if test="${spCourse.courseType == 1}">display:none</c:if>">
			<label class="control-label">套课价格：</label>
			<div class="controls">
				<form:input path="coursePrice" htmlEscape="false" class="input-xlarge required" required="1" number="1" greaterThan="0" disabled="${spCourse.courseSource==2 ? 'true':''}"/>（￥）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge " disabled="${spCourse.courseSource==2 ? 'true':''}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套课资料：</label>
			<div class="controls">
				<input type="hidden" name="addCourseResourceId" class="addCourseResourceId"/>
				<input type="hidden" name="deleteCourseResourceId" class="deleteCourseResourceId"/>
				<input id="file-1" name="file" type="file" multiple class="file" data-overwrite-initial="false" <c:if test="${spCourse.courseSource==2}">disabled</c:if> >
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>