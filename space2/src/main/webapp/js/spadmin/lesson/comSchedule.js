/**
 * 课程表共通使用脚本
 * zhujie
 */

/**
 * 课程表Span制作
 * elem:值集合
 * doc:操作页面
 */
function makeSpan(elem,doc,reloadAll){
	if(doc==null){
		$td = $("table.scheduleTable td[data-date='"+new Date(elem.lessonDate).yyyymmdd()+"'][data-section='"+elem.sectionId+"']");
	}else{
		$td = $("table.scheduleTable td[data-date='"+new Date(elem.lessonDate).yyyymmdd()+"'][data-section='"+elem.sectionId+"']",doc);
	}
	
	if($td.length==0){
		return true;
	}
	if(typeof localSpaceId !="undefined" && localSpaceId == elem.spaceId){
		$td.addClass('btn-success radius');
	}else{
		$td.addClass('btn-warning radius');
	}
	$td.attr('data-lesson',elem.id);
	// TD内容
	$td.find('span').text(elem.name);
	
	if(elem.lessonType=='0'){
		// 课程
		//courseId
		$td.attr('data-course',elem.courseId);	
		// Tooltip
		$tooltip = $td.find('.tooltip-inner');
		$tooltip.append($('<div>').text("课程名称："+elem.name));
		$tooltip.append($('<div>').text("套课："+elem.courseName));
		//$tooltip.append($('<div>').text("学校："+elem.spaceName));
		$tooltip.append($('<div>').text("老师："+elem.teacherName));
		$tooltip.append($('<div>').text("套课课程数："+elem.courseNumber));
		
		if(reloadAll){
			// 更新其余课程的课程数
			$('td[data-course='+elem.courseId+'] div:contains("套课课程数：")',getCurrentIframeContents())
				.filter(function(){ return $(this).children().length === 0;})
				.each(function(){
					$(this).text("套课课程数："+elem.courseNumber);
				});
		}
	}else if(elem.lessonType=='1'){
		// 路演
		// Tooltip
		$tooltip = $td.find('.tooltip-inner');
		$tooltip.append($('<div>').text("路演名称："+elem.name));
		//$tooltip.append($('<div>').text("学校："+elem.spaceName));
		$tooltip.append($('<div>').text("演讲人："+elem.teacherName));
	}
	initWarningTd();
}

/**
 * 课程移除
 */
function removeSpan(elem,doc){
	if(doc==null){
		$td = $("table.scheduleTable td[data-date='"+new Date(elem.lessonDate).yyyymmdd()+"'][data-section='"+elem.sectionId+"']");
	}else{
		$td = $("table.scheduleTable td[data-date='"+new Date(elem.lessonDate).yyyymmdd()+"'][data-section='"+elem.sectionId+"']",doc);
	}
	
	if($td.length==0){
		return true;
	}
	$td.removeAttr('class');
	$td.removeAttr('data-lesson');
	$td.removeAttr('data-course');
	$td.find('span').text('');
	$tooltip = $td.find('.tooltip-inner');
	$tooltip.empty();
	
	// 更新其余课程的课程数
	$('td[data-course='+elem.courseId+'] div:contains("套课课程数：")',getCurrentIframeContents())
		.filter(function(){ return $(this).children().length === 0;})
		.each(function(){
			var courseNumber = parseInt($(this).text().split("套课课程数：")[1])-1;
			$(this).text("套课课程数："+courseNumber);
		});
}

function goTo(date,addDays){
	$('#inputForm #startDate').val(addDate(new Date(date),addDays));
	$('#inputForm').submit();
}

/**
 * 保存操作
 * @param diaForm
 * @param open
 */
function savePlatLesson(diaForm,open,modal){
	var saveUrl = "";
	if(modal=='0'){
		// 课程
		saveUrl=ctx +"/spadmin/lesson/spLesson/subscribePublishLesson";
	}else if(modal=='1'){
		// 路演
		saveUrl=ctx +"/spadmin/roadshow/subscribePublishRoadshow";
	}
	// 订阅信息
	$('[name="lessonId"]',diaForm).val($('.check-ico:visible',diaForm).parent('.column-check').attr('lessonId'));
	contents_getJsonForSync(
		saveUrl, 
		$(diaForm).serialize(), 
		"post", 
		function(data){
			if(data!=null && data.result=='true'){
				artDialog.alert("订阅成功",function(){
					open.close();
					makeSpan(data.data);
				});
			}else{
				artDialog.alert("订阅失败");
			}
		},
		function(){
			artDialog.alert("订阅失败");
		},
		null
	)
}

function initWarningTd(){
	$('.btn-warning',getCurrentIframeContents()).each(function(){
		$(this).unbind('contextmenu').bind("contextmenu",function(e){
			windowwidth = $(window).width();
			windowheight = $(window).height();
			checkmenu = 1;
			$('#myMenu').show(); 
			$('#myMenu').css({
				'top':e.pageY+'px',
				'left':e.pageX+'px'
			})
			.attr('lessonId',$(this).attr('data-lesson'));
			if($(this).attr('data-course')!=null){
				$('#myMenu').attr('courseId',$(this).attr('data-course'));
				$('#myMenu .course').show();
				$('#myMenu .lesson').text("删除该课程");
			}else{
				$('#myMenu').attr('courseId','');
				$('#myMenu .course').hide();
				$('#myMenu .lesson').text("删除该路演");
			}
			
			return false;
		})
	});
}
