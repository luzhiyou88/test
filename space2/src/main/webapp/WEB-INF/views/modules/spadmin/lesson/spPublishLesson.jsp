<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程订阅</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxWebInf}/css/lesson/lesson.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.check-ico{
			display:none;
			position:absolute;
			right:5px;
			top:3px;
			width:26px;
			height:26px;
			background:url(${ctxWebInf}/static/artDialog/skins/icons/checked.png) 0 0 no-repeat;
			border-radius:50%;
		}
		.no-class-box{
			text-align:center;
			font-size:20px;
			line-ehgith:51px;
			margin:100px auto;
		}
		.no-class-tips{
			display:inline-block;
			width:58px;
			height:51px;
			background:url(${ctxWebInf}/static/artDialog/skins/icons/order-tip.png) 0 0 no-repeat;
			vertical-align:middle;
		}
	</style>
	<script>
		$(function(){
			$(".column-check").click(function(){
				$(".column-check").not(this).find(".check-ico").hide();
				$(this).find(".check-ico").toggle();
			})
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#plat" data-toggle="tab">选择平台课程</a></li>
		<li><a href="#platRoadshow" data-toggle="tab">选择平台路演</a></li>
		<c:if test="${isBefore==false }">
			<li><a href="#local" data-toggle="tab">添加本地课程</a></li>
			<li><a href="#localRoadshow" data-toggle="tab">添加本地路演</a></li>
		</c:if>
		
	</ul>
	<div id="myTabContent" class="tab-content">
		 <div class="tab-pane in active" id="plat">
			<form id="plat" method="post" class="form-horizontal">
				<c:choose>
				   <c:when test="${fn:length(publishLessonList)==0}">  
				       <div class="no-class-box">
				       		<em class="no-class-tips"></em>
				       		<span>暂无可添加的课程</span>
				       </div>
				   </c:when>
				   <c:otherwise> 
				   		<div class="control-group">
				   			<input type="hidden" name="sectionId" value="${spSection.id }"/>
				   			<input type="hidden" name="lessonId" />
				   			<input type="hidden" name="courseId" />
				   			<input type="hidden" name="categoryId" />
						</div>
						<div class="control-group">
							<label class="control-label">上课人数：</label>
							<div class="controls">
								<input name="lessonNumber" htmlEscape="false" maxlength="11" class="input-xlarge required" numberWithoutComma="1"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上课地址：</label>
							<div class="controls">
								<input name="lessonAdress" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
				    	<div class="row show-grid">
							<c:forEach items="${publishLessonList}" var="lesson">
								<%@ include file="/WEB-INF/views/modules/spadmin/lesson/spPublishLessonModel.jsp"%>
							</c:forEach>
						</div>
				   </c:otherwise>
				</c:choose>
			</form>
		</div>
		<div class="tab-pane" id="platRoadshow">
			<form id="platRoadshow" method="post" class="form-horizontal">
				<c:choose>
				   <c:when test="${fn:length(publishRoadshowList)==0}">  
				       <div class="no-class-box">
				       		<em class="no-class-tips"></em>
				       		<span>暂无可添加的路演</span>
				       </div>
				   </c:when>
				   <c:otherwise> 
				   		<div class="control-group">
				   			<input type="hidden" name="sectionId" value="${spSection.id }"/>
				   			<input type="hidden" name="lessonId" />
						</div>
						<div class="control-group">
							<label class="control-label">参加人数：</label>
							<div class="controls">
								<input name="lessonNumber" htmlEscape="false" maxlength="11" class="input-xlarge required" numberWithoutComma="1"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">路演地址：</label>
							<div class="controls">
								<input name="lessonAdress" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
				    	<div class="row show-grid">
							<c:forEach items="${publishRoadshowList}" var="lesson">
								<%@ include file="/WEB-INF/views/modules/spadmin/lesson/spPublishRoadshowModel.jsp"%>
							</c:forEach>
						</div>
				   </c:otherwise>
				</c:choose>
			</form>
		</div>
		<div class="tab-pane" id="local">
			<%@ include file="/WEB-INF/views/modules/spadmin/lesson/spLessonModalForm.jsp"%>
		</div>
		<div class="tab-pane" id="localRoadshow">
			<%@ include file="/WEB-INF/views/modules/spadmin/roadshow/spRoadshowFormModal.jsp"%>
		</div>
	</div>
</body>
</html>