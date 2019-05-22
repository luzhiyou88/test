<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程订阅</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxWebInf}/css/lesson/lesson.css" type="text/css" rel="stylesheet" />
	<script src="${ctxWebInf}/js/spadmin/lesson/comSchedule.js" type="text/javascript"></script>
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
		.control-group{
			padding-top:2px;
			padding-bottom:2px;
			margin-bottom:0;
		}
		.control-group .control-label{
			width:80px;
		}
		.control-group .controls{
			margin-left:85px;
			margin-top:5px;
			overflow:hidden;
			text-overflow:ellipsis;
			white-space:nowrap;
		}
		.form-horizontal .control-group{
			margin-bottom:0;
		}
		.control-group.span3{
			width:205px;
		}
	</style>
	<script>
		$(function(){
			$(".btn-primary").click(function(){
				var btn = this;
				var form = $(btn).closest('form');
				// 校验
				if($(form).validate().form()){
					contents_getJsonForSync(
						ctx +"/spadmin/lesson/spLesson/subscribePublishLesson",
						$(form).serialize(), 
						'post',
						function(data){
							if(data!=null && data.result=="true"){
								artDialog.alert("订阅成功");
								$(btn).removeClass("btn-primary").addClass("disabled").val("已订阅");
								// 课程人数
								var lessonNumber = $('[name="lessonNumber"]',form).val();
								$('div.lessonNumber',form).empty().text(lessonNumber);
								
								// 课程地址
								var lessonAdress = $('[name="lessonAdress"]',form).val();
								$('div.lessonAdress',form).empty().text(lessonAdress);
								
								makeSpan(data.data,art.dialog.opener.document,true);
							}else{
								artDialog.alert(data.msg);
							}
						},function(){
							artDialog.alert("订阅失败");
						},null
					);
					$(this).closest('form').serialize()
				}
				return;
			})
		})
	</script>
</head>
<body>
	<div id="inputForm" class="form-horizontal">
		<c:choose>
		   <c:when test="${fn:length(publishLessonList)==0}">  
		       <div class="no-class-box">
		       		<em class="no-class-tips"></em>
		       		<span>暂无相关套课课程</span>
		       </div>
		   </c:when>
		   <c:otherwise> 
		   		<div>
				</div>
		    	<div class="show-grid">
					<c:forEach items="${publishLessonList}" var="lesson">
					<form>
						<input type="hidden" name="lessonId" value="${lesson.id }"/>
						<input type="hidden" name="courseId" value="${lesson.courseId }"/>
						<div class="column clearfix info column-check" style="position:relative;margin-right:95px;" lessonId="${ lesson.id }" courseId="${ lesson.courseId }">
							<div class="control-group span3">
								<label class="control-label">课程名称：</label>
								<div class="controls" title="${lesson.name }">
									${lesson.name }
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">课程日期：</label>
								<div class="controls">
									${fns:getFormatDate(lesson.lessonDate)}
								</div>
							</div>
							<div class="control-group span3" >
								<label class="control-label">套课名称：</label>
								<div class="controls" title="${lesson.courseName }">
									${lesson.courseName}
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">上课时间：</label>
								<div class="controls">
									${lesson.lessonStarttime} ~ ${lesson.lessonEndtime}
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">上课老师：</label>
								<div class="controls">
									${lesson.teacherName}
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">开课状态：</label>
								<div class="controls">
									${lesson.lessonStateLabel}
								</div>
							</div>
							<div class="control-group span3" >
								<label class="control-label">课程描述：</label>
								<div class="controls" title="${lesson.lessonDesc}">
									${lesson.lessonDesc}
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">直播间名称：</label>
								<div class="controls" title="${lesson.broadcastName}">
									${lesson.broadcastName}
								</div>
							</div>
							<div class="control-group span3">
								<label class="control-label">直播间描述：</label>
								<div class="controls" title="${lesson.broadcastDesc}">
									${lesson.broadcastDesc}
								</div>
							</div>
							<div class="control-group span12">
								<label class="control-label">上课人数：</label>
								<div class="controls lessonNumber">
									<c:choose>
										<c:when test="${lesson.subscribeState==2}">
											${ lesson.lessonNumber}
										</c:when>
										<c:otherwise>
											<input name="lessonNumber" htmlEscape="false" maxlength="11" class="input-xlarge required" style="width:100px;" numberWithoutComma="1"/>
											<span class="help-inline"><font color="red">*</font> </span>
										</c:otherwise>
									</c:choose>									
								</div>
							</div>
							<div class="control-group span12">
								<label class="control-label">上课地址：</label>
								<div class="controls lessonAdress">
									<c:choose>
										<c:when test="${lesson.subscribeState==2}">
											${ lesson.lessonAdress}
										</c:when>
										<c:otherwise>
											<input name="lessonAdress" htmlEscape="false" maxlength="255" class="input-xlarge required" style="width:400px;"/>
											<span class="help-inline"><font color="red">*</font> </span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<c:choose>
								<c:when test="${lesson.subscribeState==1 and isConflict==false}">
									<input class="btn btn-primary" style="position: absolute;right:-95px;top: 44px;" type="button" value="订阅" >
								</c:when>
								<c:when test="${lesson.subscribeState==1 and isConflict==true}">
									<input class="btn disabled" style="position: absolute;right:-95px;top: 44px;" type="button" value="无法订阅" >
								</c:when>
								<c:when test="${lesson.subscribeState==2}">
									<input class="btn disabled" style="position: absolute;right:-95px;top: 44px;" type="button" value="已订阅" >
								</c:when>
								<c:when test="${lesson.subscribeState==3}">									
									<input class="btn disabled" style="position: absolute;right:-95px;top: 44px;" type="button" value="无法订阅" >
									<span style="position: absolute;right:-80px;top: 80px;color:red">时间冲突</span>									
								</c:when>
							</c:choose>
							
						</div>
					</form>
					</c:forEach>
				</div>
		   </c:otherwise>
		</c:choose>
	</div>
</body>
</html>