<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>路演管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var sectionOpts = ${fns:toJson(spSectionList)};
		
		$(document).ready(function() {
			changeLessonDate($("#lessonDate").val(),true);
			if($('#publishState').val()=='1' || $('#publishState').val()=='3'|| $('#publishState').val()=='4'){
				$(':input:not(#btnCancel)').attr('disabled',true);
				$('a').attr('disabled',true).removeAttr('onclick');
			}
			$("#btnSubmit").click(function(){
				if($("form#localRoadshow").validate().form()){
					$("#localRoadshow").submit();
				}
				return false;
			});
		});
		
		function changeLessonDate(lessonDate,init){
			$('#sectionId option:not([value=""])').remove();
			$('#sectionId').selectpicker('refresh');
			if(lessonDate==null || lessonDate==''){				
				return;
			}
			var initSectionId = $('#sectionId').data('id');
			var initDay = $('#sectionId').attr('data-initDay');
			contents_getJsonForSync(
					ctx+"/spadmin/lesson/spLesson/remainSectionByDate?lessonDate="+lessonDate, 
					null, 
					"post", 
					function(remainSections){
						$.each(sectionOpts,function(){
							if(checkInside(remainSections,this.id,'id') || (this.id == initSectionId && initDay==lessonDate) ){
								$option=$('<option>'+this.name+'</option>').attr('value',this.id);
								$('#sectionId').append($option);
							}							
							
						});
						if(init===true){							
							$('#sectionId option[value="'+initSectionId+'"]').attr("selected", true);
						}
						$('#sectionId').selectpicker('refresh');
					},function(){
						artDialog.alert("获取失败");
					},
					null
			);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/roadshow/">路演列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/roadshow/form?id=${spLesson.id}">路演${not empty spLesson.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<%@ include file="/WEB-INF/views/modules/spadmin/roadshow/spRoadshowFormModal.jsp"%>
</body>
</html>