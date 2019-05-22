<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>讲义管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/upload.jsp"%>
	<script type="text/javascript">
	    var fileList = ${fns:toJson(fileList)};
		$(document).ready(function() {
			
			initialPreviewArr=[];
			initialPreviewConfigArr=[];
			if(fileList!=null){
				$.each(fileList,function(){
					initialPreviewArr.push(this.url);
					initialPreviewConfigArr.push({'caption':this.fileName,'key':this.id})
				});
			}
			$("#file-1").fileinput({
		        uploadUrl: '${ctx}/spadmin/handouts/spHandouts/upload',
		        allowedFileExtensions : ['pptx', 'doc','docx','pdf'],
		        overwriteInitial: false,
		        maxFileSize: 10240,//TODO
		        //maxFileCount: 1,// 最大文件数
		        validateInitialCount: true,
		        showDownload:false,
		        initialPreview:initialPreviewArr,
			    initialPreviewAsData: true,
			    initialPreviewFileType: 'image',
			    msgFilesTooMany:1,
			    initialPreviewConfig: initialPreviewConfigArr,
			    uploadExtraData:data,// 需要和资源一起上传的参数
		        slugCallback: function(filename) {
		            return filename.replace('(', '_').replace(']', '_');
		        },
		        
			}).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        }).on("fileuploaded", function(event, data, previewId, index) {
	            if(data.response && data.response.result=='true') {
	            	if($('.lessonId').val()==''){
	            		var resourceTemp = $('input.addHandoutsId');
		                var resourceNew = $(resourceTemp).clone().val(data.response.msg);
		                $('form').append(resourceNew);
	            	}
	                
	                $('#'+previewId).attr('rid',data.response.msg);
	                window.location.href=ctx+"/spadmin/handouts/spHandouts/list";
	            }
	        }).on("filesuccessremove", function(vKey, jqXHR, rid, extraData,func) {
	        	if(rid==''){
	        		artDialog.alert("数据错误，请刷新页面");
	        		return false;
	        	}
	        	 var dialog1 = art.dialog({
	        		content:'<img width="250" height="100" src="${ctxStatic}/img/link.gif" />',
	        		lock:true,
	        		width:250,
	        		height:100,
	        		title:false,
	        		cancel:false,
	        		dblclickHide:false
	        	}); 
	        	//var dialog1 = art.dialog.open("${ctxStatic}/img/link.gif",{title:false,lock:true,cancel:false,dblclickHide:false,height:100,width:250,scrollbars:false},'scrollbars=no, resizable=yes');
	        	/* ({
	        		content:'<img width="100%" height="100%" src="${ctxStatic}/img/link.gif" />',
	        		lock:true,
	        		width:250,
	        		height:100,
	        		title:'讲义删除',
	        		cancel:false,
	        		dblclickHide:false
	        	}); */
	        	contents_getJsonForSync(
		        		'${ctx}/spadmin/handouts/spHandouts/deleteHandouts', 
		        		{'lessonId':$('.lessonId').val()}, 
		        		"post", 
			        	function(data){
		        			dialog1.close();
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
		})
		
		var data = function getUploadData(){
				return {'lessonId':$('.lessonId').val(),'title':$('#title').val()};
			}
			
			function goBack(){
				window.location.href= ctx + '/spadmin/handouts/spHandouts/';
			}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="spLesson" action="${ctx}/spadmin/handouts/spHandouts/saveHandouts" enctype="multipart/form-data" class="form-horizontal">
		<input type="hidden" class="lessonId" name="id" value="${spLesson.id }"/>
		<input type="hidden" class="title" name="title" />
		<form:hidden path="id"/>
		<form:hidden path="publishState"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<!-- 套课ID -->
			<form:hidden path="courseId" />
			
			<label class="control-label">课程名称：</label>
			<div class="controls">
				${spLesson.name }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程日期：</label>
			<div class="controls">
				<input id="lessonDate" name="lessonDate" type="text" readonly="readonly" maxlength="20" class="input-date Wdate required"
					value="<fmt:formatDate value="${spLesson.lessonDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({onpicking:function(dq){changeLessonDate(dq.cal.getNewDateStr())},dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上课人数：</label>
			<div class="controls">
				${spLesson.lessonNumber}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">上课地址：</label>
			<div class="controls">
				${spLesson.lessonAdress }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开课状态 ：</label>
			<div class="controls">
				<c:if test="${spLesson.lessonState == '0' }">未开始</c:if>
				<c:if test="${spLesson.lessonState == '1' }">已开始</c:if>
				<c:if test="${spLesson.lessonState == '2' }">已结束</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">直播间名称：</label>
			<div class="controls">
				${spLesson.broadcastName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">直播间描述：</label>
			<div class="controls">
				${spLesson.broadcastDesc }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程描述：</label>
			<div class="controls">
				${spLesson.lessonDesc }
			</div>
		</div>

		 <div class="control-group">
		       <label class="control-label">讲义标题：</label>
				<div class="controls">
				 <form:input path="title" htmlEscape="false" maxlength="32" class="input-xlarge" style="height:30px;"/>
				</div>
			</div>	
		<div class="control-group">
			<label class="control-label">上传讲义：</label>
			<div class="controls">
				<!-- <input type="file" name="file" id="file"/> -->
				<input type="hidden" name="addHandoutsId" class="addHandoutsId"/>
				<input type="hidden" name="deleteHandoutsId" class="deleteHandoutsId"/>
				<input id="file-1" name="file" type="file" multiple class="file" data-overwrite-initial="false">
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="goBack();"/>
		</div>
	</form:form>
</body>
</html>