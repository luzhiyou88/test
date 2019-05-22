<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资料库管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.fla_btn {
			position: relative;
		}
		.fla_btn embed {
			position: absolute;
			z-index: 1;
		}
		#swfDiv{*position:absolute; z-index:2;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
		});
		function changeType(val){
			if(val=='1'){//说明是视频
				$("#otherType").hide();
				$("#thumbImgDiv").hide();
				$("#videoType").show();
			}else{
				$("#videoType").hide();
				$("#otherType").show();
				$("#thumbImgDiv").show();
				if(val=="2"){//音频
					$("#file").attr("accept",".mp3,.aac,.wav");
				}else{//电子书
					$("#file").attr("accept",".txt,.pdf,.doc,.docx");
				}
			}
		}
		function toSubmit(){
			var resourceId=$("#id").val();
			if(resourceId==""){//说明是新增
				var resourceType=$("#resourceType").val();
				if(resourceType=="1"){//说明是视频
					var isFinish=$("#up").html();
					if(isFinish=="100% (上传完成)"){//说明上传完成
						$("#inputForm").submit();
					}else{
						alert("请等待视频上传完成！")
						return false;
					}
				}else{
					$("#inputForm").submit();
				}
			}else{
				$("#inputForm").submit();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/resource/spResource/">资料库列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/resource/spResource/form?id=${spResource.id}">资料库${not empty spResource.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spResource" action="${ctx}/spadmin/resource/spResource/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" id="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">资料名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体类型：</label>
			<div class="controls">
				<form:select id="resourceType" path="type" class="input-medium required" onchange="changeType(this.value);" disabled="${empty spResource.id?'false':'true'}">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('resource_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				<label style="color:blue;">格式要求：音频(.mp3,.aac,.wav); 电子书(.txt,.pdf,.doc,.docx);</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资料路径：</label>
			<div class="controls">
				<form:input id="baseUrl" path="baseUrl" htmlEscape="false" maxlength="256" class="input-medium required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span id="otherType" style="display:none;">
					<input type="file" name="file" id="file" class="input-mini " onchange="$('#baseUrl').val(this.value);" accept=".txt,.pdf,.doc,.docx"/>
				</span>
				<div class="fla_btn" id="videoType" style="display:none;">
					<span style="float:left;"><input id="videofile" name="videofile" type="text" value="真实文件名..." readonly="readonly" style="width:163px"/></span> 
					<div id="swfDiv"></div> 
					<input type="button" value="选择视频" id="btn_width" style="width:80px; height:30px; border:solid #DDDDDD 1px;" />
					<input type="button" value="开始上传" style="width:80px; height:30px; border:solid #DDDDDD 1px;" onclick="submitvideo();">
					<label id="jindu" style="display:none;">进度：<span id="up"></span></label>
				</div>
			</div>
		</div>
		<div class="control-group" id="thumbImgDiv" style="display:none;">
			<label class="control-label">资料图片：</label>
			<div class="controls">
				<form:input id="thumbImg" path="thumbImg" htmlEscape="false" maxlength="256" class="input-medium required" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<input type="file" name="fileImg" id="fileImg" class="input-mini " onchange="$('#thumbImg').val(this.value);" accept="image/*"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
<%-- 				<form:input path="categoryId" htmlEscape="false" maxlength="32" class="input-medium "/> --%>
				<sys:treeselect id="spCategory" name="categoryId" value="${spResource.spCategory.id}" labelName="spCategory.name" labelValue="${spResource.spCategory.name}"
					title="类型" url="/spadmin/category/spCategory/treeData" cssClass="required" notAllowSelectParent="true" allowClear="${spResource.currentUser.admin}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" style="display:none;">
			<label class="control-label">存储位置：</label>
			<div class="controls">
				<form:radiobuttons path="domain" items="${fns:getDictList('resource_site')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属平台：</label>
			<div class="controls">
				<form:radiobutton path="sourceType" value="1" label="本校" checked="true"/>  
				<form:radiobutton path="sourceType" value="2" label="平台" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资料简介：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="toSubmit();"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<script type="text/javascript" src="${ctxStatic}/../cc/js/swfobject.js"></script>
<script type="text/javascript">
// 加载上传flash ------------- start
	var thisurl=window.location.href;
	thisurl=thisurl.substring(0,thisurl.indexOf('/spResource/'));
	thisurl='${ccNotifyUrl}';
	console.log("CC视频回调url="+thisurl);
	var swfobj=new SWFObject('http://union.bokecc.com/flash/api/uploader.swf', 'uploadswf', '80', '25', '8');
	swfobj.addVariable( "progress_interval" , 1);	//	上传进度通知间隔时长（单位：s）
	//swfobj.addVariable( "notify_url" , thisurl+"/spResource/saveVideoImg?videoId="+document.getElementById("baseUrl").value);	//上传视频后回调接口
	swfobj.addVariable( "notify_url" , thisurl);	//上传视频后回调接口
	swfobj.addParam('allowFullscreen','true');
	swfobj.addParam('allowScriptAccess','always');
	swfobj.addParam('wmode','transparent');
	swfobj.write('swfDiv');
// 加载上传flash ------------- end

//	-------------------
//	调用者：flash
//	功能：选中上传文件，获取文件名函数
//	时间：2010-12-22
//	说明：用户可以加入相应逻辑
//	-------------------
	function on_spark_selected_file(filename) {
		document.getElementById("videofile").value = filename;
	}
	
//	-------------------
//	调用者：flash
//	功能：验证上传是否正常进行函数
//	时间：2010-12-22
//	说明：用户可以加入相应逻辑
//	-------------------
	function on_spark_upload_validated(status, videoid) {
		if (status == "OK") {
			//alert("上传正常,videoid:" + videoid);
			document.getElementById("baseUrl").value = videoid;
		} else if (status == "NETWORK_ERROR") {
			alert("网络错误");
		} else {
			alert("api错误码：" + status);
		}
	}
	
//	-------------------
//	调用者：flash
//	功能：通知上传进度函数
//	时间：2010-12-22
//	说明：用户可以加入相应逻辑
//	-------------------
	function on_spark_upload_progress(progress) {
		document.getElementById("jindu").style.display="block";
		var uploadProgress = document.getElementById("up");
		if (progress == -1) {
			uploadProgress.innerHTML = "上传出错：" + progress;
		} else if (progress == 100) {
			uploadProgress.innerHTML = "100% (上传完成)";
		} else {
			uploadProgress.innerHTML = progress + "%";
		}
	}
	
	function positionUploadSWF() {
		document.getElementById("swfDiv").style.width = document.getElementById("btn_width").style.width;
		document.getElementById("swfDiv").style.height = document.getElementById("btn_width").style.height;
	}
	
	//控制上传
	function submitvideo() {
		var title = encodeURIComponent(document.getElementById("name").value, "utf-8");
		var tag = title;
		var description = encodeURIComponent(document.getElementById("remarks").value, "utf-8");
		var subCategory = "8017C8B1CE1A2569";
		var url = ctxStatic+"/../cc/getuploadurl.jsp?title=" + title + "&tag=" + tag + "&description=" + description;
		if (subCategory != null) {
			url = url + "&categoryid=" + subCategory;
		}
		console.log("视频上传路径："+url);
		var req = getAjax();
		req.open("GET", url, true);
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				if (req.status == 200) {
					var re = req.responseText;//获取返回的内容
					console.log(re);
					document.getElementById("uploadswf").start_upload(re); //	调用flash上传函数
					console.log(re);
				}
			}
		};
		req.send(null);
	}
	function getAjax() {
		var oHttpReq = null;
		if (window.XMLHttpRequest) {
			oHttpReq = new XMLHttpRequest;
			if (oHttpReq.overrideMimeType) {
				oHttpReq.overrideMimeType("text/xml");
			}
		} else if (window.ActiveXObject) {
			try {
				oHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				oHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} else if (window.createRequest) {
			oHttpReq = window.createRequest();
		} else {
			oHttpReq = new XMLHttpRequest();
		}

		return oHttpReq;
	}
	
	//控制视频分类显示
	function show() {
		subCategorys = document.getElementsByName("sub_category");
		for ( var i = 0; i < document.getElementsByName("sub_category").length; i++) {
			subCategorys[i].style.display = 'none';
		}
	}
</script>
</body>
</html>