<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var sectionOpts = ${fns:toJson(spSectionList)};
		var addSection = art.dialog.open.origin.addSection;
		var deleteSection = art.dialog.open.origin.deleteSection;
		var editSection = art.dialog.open.origin.editSection;
		
		$(document).ready(function() {
			$("#name").focus();
			changeLessonDate($("#lessonDate").val());
			if($('#publishState').val()=='1' || $('#publishState').val()=='3'|| $('#publishState').val()=='4'){
				$(':input').attr('disabled',true);
				$('a').attr('disabled',true).removeAttr('onclick');
			}
		});
		
		function changeLessonDate(lessonDate){
			$('#sectionId option:not([value=""])').remove();
			$('#sectionId').selectpicker('refresh');
			if(lessonDate==null || lessonDate==''){				
				return;
			}
			contents_getJsonForSync(
					ctx+"/spadmin/lesson/spLesson/remainSectionByDate?lessonDate="+lessonDate, 
					null, 
					"post", 
					function(data){
						initSectionOption(lessonDate,data);
					},function(){
						artDialog.alert("获取失败");
					},
					null
			);
		}
		function initSectionOption(lessonDate,remainSections){			
			$.each(sectionOpts,function(){
				if( checkInside(remainSections,this.id,'id') ){
					if(checkInside(addSection[lessonDate],this.id,'id') && !checkInside(editSection[lessonDate],this.id,'id')){
						// 已添加列表中存在，并且该节次不是当前编辑的节次，则不在选项
						return true;
					}else{
						$option=$('<option>'+this.name+'</option>').attr('value',this.id);
						if(checkInside(editSection[lessonDate],this.id,'id')){
							$option.attr('selected',true);
						}
						$('#sectionId').append($option);
					}
				}else{
					if(checkInside(deleteSection[lessonDate],this.id,'id') || checkInside(editSection[lessonDate],this.id,'id')){
						$option=$('<option>'+this.name+'</option>').attr('value',this.id);
						if(checkInside(editSection[lessonDate],this.id,'id')){
							$option.attr('selected',true);
						}
						$('#sectionId').append($option);
					}
				}
			});
			$('#sectionId').selectpicker('refresh');
		}
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/modules/spadmin/lesson/spLessonModalForm.jsp"%>
</body>
</html>