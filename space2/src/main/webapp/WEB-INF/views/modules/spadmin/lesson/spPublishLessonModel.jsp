<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="column span3 info column-check" style="position:relative;" lessonId="${ lesson.id }" courseId="${ lesson.courseId }">
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">课程名称：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.name }
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">课程日期：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${fns:getFormatDate(lesson.lessonDate)}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">套课名称：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.courseName}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">上课时间：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.lessonStarttime} ~ ${lesson.lessonEndtime}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">上课老师：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.teacherName}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">开课状态：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.lessonStateLabel}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">课程描述：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.lessonDesc}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">直播间名称：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.broadcastName}
		</div>
	</div>
	<div class="control-group" style="padding-top:2px;padding-bottom:2px;margin-bottom:0;">
		<label class="control-label" style="width:110px;">直播间描述：</label>
		<div class="controls" style="margin-left:120px;margin-top:5px;">
			${lesson.broadcastDesc}
		</div>
	</div>
	<em class="check-ico"></em>
</div>