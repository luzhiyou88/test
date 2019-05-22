<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:form id="localRoadshow" modelAttribute="spLesson" action="${ctx}/spadmin/roadshow/save" method="post" class="form-horizontal" enctype="multipart/form-data">
	<input type="hidden" class="courseId" name="id" value="${spLesson.id }"/>
	<input type="hidden" id="publishState" value="${spLesson.publishState }"/>
	<sys:message content="${message}"/>		
	<div class="control-group">			
		<label class="control-label">路演名称：</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge required" checkRoadshowName="${empty spLesson or empty spLesson.id ? 'null' : spLesson.id }"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">图片:</label>
		<div class="controls">
			<%-- <form:hidden id="thumbImg" path="thumbImg" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			<sys:ckfinder input="thumbImg" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/> --%>
			<sys:icon url="${spLesson.thumbImg }" name="thumbImg" formId="localRoadshow" fileId="fileRoadshow"></sys:icon>
		</div>
	</div>
	<c:choose>
		<c:when test="${not empty spLesson.id }">
			<div class="control-group">
				<label class="control-label">路演类型：</label>
				<div class="controls" style="line-height:30px;">
					${fns:getDictLabel(spLesson.lessonSource, "sp_lesson_source", "-")}
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="control-group">
				<label class="control-label">路演类型：</label>
				<div class="controls">
					<form:select path="lessonSource" class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('sp_lesson_source')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty fromSchedule and fromSchedule }">
			<input type="hidden" name="lessonDate" value="${lessonDate }" />
			<input type="hidden" name="sectionId" value="${spSection.id }" />
			<div class="control-group" style="line-height:30px;">
				<label class="control-label">路演日期：</label>
				<div class="controls">${lessonDate } &nbsp; ${spSection.name }</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="control-group">
				<label class="control-label">路演日期：</label>
				<div class="controls">
					<input id="lessonDate" name="lessonDate" type="text" readonly="readonly" maxlength="20" class="input-date Wdate required"
						value="${fns:getFormatDate(spLesson.lessonDate)}" 
						onclick="WdatePicker({onpicking:function(dq){changeLessonDate(dq.cal.getNewDateStr(),false)},dateFmt:'yyyy-MM-dd',isShowClear:true,minDate:'%y-%M-%d'});"/>
					<span class="help-inline"><font color="red">*</font> </span>
					<form:select path="sectionId" class="input-xlarge required" data-id="${spLesson.sectionId }" data-initDay="${fns:getFormatDate(spLesson.lessonDate)}">
						<form:option value="" label="请选择" />
						<%-- <form:options items="${spSectionList}" itemLabel="name"
							itemValue="id" htmlEscape="false" /> --%>
					</form:select>
					<form:hidden path="lessonTime" />
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<div class="control-group">
		<label class="control-label">参加人数：</label>
		<div class="controls">
			<form:input path="lessonNumber" htmlEscape="false" maxlength="11" class="input-xlarge required" numberWithoutComma="1"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">演讲人：</label>
		<div class="controls">
			<c:choose>
				<c:when test="${not empty spLesson.publishState or spLesson.publishState==1 or spLesson.publishState==3 or spLesson.publishState==4 }">
					&nbsp;${spLesson.teacherName}
				</c:when>
				<c:otherwise>
					<!-- 上课老师名字 -->
					<form:hidden path="teacherName" />			
					<!-- 老师简介 -->
					<form:hidden path="teacherDesc" />
					<form:select path="teacherId" class="input-xlarge required">
		                    <form:option value="" label="请选择" />                                                                                              				<c:forEach items="${entrepreneurList}" var="item">
							<option value="${item.id }" desc="${item.remarks }" <c:if test="${item.id==spLesson.teacherId}">selected</c:if>>${item.name }</option>
						</c:forEach>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">路演地址：</label>
		<div class="controls">
			<form:input path="lessonAdress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<c:if test="${not empty spLesson.id }">
		<div class="control-group">
			<label class="control-label">路演状态 ：</label>
			<div class="controls">
				<!-- 开课状态 -->
				<input type="hidden" name="lessonStateLabel" />
				<form:select path="lessonState" class="input-xlarge required">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('curriculum_state')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</c:if>
	<div class="control-group">
		<label class="control-label">直播间名称：</label>
		<div class="controls">
			<form:input path="broadcastName" htmlEscape="false" maxlength="32" disabled="${not empty spLesson.id }" class="input-xlarge required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">直播间描述：</label>
		<div class="controls">
			<form:input path="broadcastDesc" htmlEscape="false" maxlength="255" disabled="${not empty spLesson.id }" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">讲师端密码：</label>
		<div class="controls">
			<form:input path="broadcastPass" htmlEscape="false" maxlength="32" disabled="${not empty spLesson.id }" class="input-xlarge required" alphanum="1"/>
			<span class="help-inline"><font color="red">*</font> 请输入半角字母或者数字</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">路演描述：</label>
		<div class="controls">
			<form:input path="lessonDesc" htmlEscape="false" maxlength="512" class="input-xlarge "/>
		</div>
	</div>
	<c:if test="${empty fromSchedule }">
		<div class="form-actions">
			<c:if test="${empty spLesson.publishState or spLesson.publishState==0 or spLesson.publishState==2 }">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</c:if>	
</form:form>