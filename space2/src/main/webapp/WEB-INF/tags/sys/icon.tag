<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link href="${ctxWebInf}/css/icon.css" type="text/css" rel="stylesheet" />

<%@ attribute name="formId" type="java.lang.String" required="true" description="form的id"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="name"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="icon地址"%>
<%@ attribute name="fileId" type="java.lang.String" required="false" description="icon地址"%>
<c:choose>
	<c:when test="${not empty fileId }">
		<c:set var="fileId" value="${fileId}"/>
	</c:when>
	<c:otherwise>
		<c:set var="fileId" value="fileIcon"/>
	</c:otherwise>
</c:choose>
<img src="${url }" id="imgIcon" ><br>
<input type="hidden" id="imgUrl" name="${name }" value="${url }">

 <div class="input-group-btn">
     <div class="btn btn-primary btn-file">
     	 &nbsp;上传文件
     	<input type="file" name="file" id="${fileId }" multiple="multiple" accept=".jpg,.bmp,.gif,.jpeg,.png" class="file" >
     </div>
 </div>


<script>
$(function(){
		
	$("#${fileId}").change(function(){
		var objUrl = getObjectURL(this.files[0]);
		if (objUrl) {
			var form = $(this).closest('form');
			var formData = new FormData($('form#${formId}')[0]);
			contents_getJsonForFileUpload(
					this, 
					ctx +"/spadmin/common/icon/upload", 
					formData, 
					function(data){
						if(data!=null && data.result=="true"){
							$("#imgUrl",form).val(data.data);
							$("#imgIcon",form).attr("src", objUrl) ;
						}else{
							artDialog.alert('上传图片失败');
						}					
					},
					function(){
						artDialog.alert('上传图片失败');
					},
					true
			);
		}
	}) ;
	//建立一個可存取到該file的url
	function getObjectURL(file) {
		var url = null ; 
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
	}
});
</script>