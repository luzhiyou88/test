/**
 * 课程添加/新增页面使用脚本
 * zhujie
 */
var addSection={};
var deleteSection={};
var editSection={};
$(document).ready(function() {
	// 编辑状态下，套课下拉框为不可用
	if($('#courseId').attr('backVal')!=null && $('#courseId').attr('backVal')!=''){
		$('#courseId').attr('disabled',true);
		$('#courseId').selectpicker('refresh');
	}
	// 初始化所有课程信息
	makeLessonSpan(spLessonList,true);
	
	// 如果是课程编辑，则自动弹出框
	if(spLesson.id != null){
		$(".info[id='"+spLesson.id +"'] a.edit").click();
	}
	
	// 注册提交按钮
	$("#btnSubmit").click(function(){
		$("#inputForm").submit();
	});
	// 套课切换
	$('#courseId').change(function(){
		var newCourseId = $('#courseId').val();
		if($('.show-grid .column.new').length > 0){
			return art.dialog.confirm('切换套课将删除所有编辑信息，确认切换套课吗？', 
			    function () {
					// 重新查询
					getAllListByCouresId(newCourseId);
			    }, 
			    function () {
			    	$('#courseId').val($('#courseId').attr('backVal'));
			    	$('#courseId').selectpicker('refresh');
			    }
			);
		}else{
			$('#courseId').attr('backVal',newCourseId);
			// 重新查询
			getAllListByCouresId(newCourseId);
		}
		
		return false;
	});
	
	/**
	 *  添加/编辑课程
	 */
	$('#showmodel').click(function(){				
		if($('#courseId').val()==''){
			artDialog.alert("请先选择套课");
			return false;
		}else{			
			showModal(this,'new');
			return false;
		}
		
	});
});

var spanElems = {
	"id"   : "id",	
	"courseId"   : "套课id",	
    "name"       : "课程名称",
    "lessonSource"       : "课程类型",
    "lessonSourceLabel"  : "课程类型",
    "name"       : "课程名称",
    "thumbImg"   : "图片",
    "lessonDate" : "课程日期",
    "lessonTime" : "上课时间",
    "sectionId"  : "节次",
    "lessonNumber":"上课人数",
    "teacherId"  : "上课老师",
    "teacherName"  : "上课老师",
    "teacherDesc"  : "老师简介",
    "lessonAdress"  : "上课地址",
    "lessonState": "开课状态",
    "lessonStateLabel": "开课状态",
    "publishState":"发布状态",
    "publishStateLabel":"发布状态",
    "broadcastName": "直播间名称",
    "broadcastDesc": "直播间描述",
    "broadcastPass": "讲师端密码",
    "lessonDesc": "课程描述",   
}

var hidElem = ["id","courseId","teacherId","lessonState","sectionId","thumbImg","publishState","lessonSource"];

/**
 * 课程Span
 */
function makeLessonSpan(list,isInit,button){
	
	column = $('div.column-template').children().get(0);
	control = $('div.control-template').children().get(0);
	
	
	
	$.each(list,function(){
		var lesson = this;
		var maxindex = findMaxIndex()+1;
		$newcolumn = $(column).clone();
		if(true==isInit){
			$newcolumn.addClass('info');
		}else{
			$newcolumn.addClass('new');
		}
		if(lesson.publishState=='1' || lesson.publishState=='3' || lesson.publishState=='4'){
			$newcolumn.find('a.edit,a.delete').remove();
		}else{
			$newcolumn.find('a.show').remove();
		}
		$newcolumn.attr('index',maxindex).attr('id',lesson.id);
		
		$.each(spanElems,function(k,v){
			var elem = this;			
			$newcontrol = $(control).clone();
			if($.inArray(k,hidElem)>-1){
				$newcontrol.hide();
			}
			$newcontrol.find('.control-label').text(v+":");
			$newcontrol.find('.controls').addClass(k).text(v=="课程日期" ? new Date(lesson[k]).yyyymmdd() : lesson[k]);
			$newcolumn.append($newcontrol);
				
			if(true!=isInit){
				// form传递input
				$input = $('<input type="hidden">').attr('name','lesson['+maxindex+'].'+k+'').val(lesson[k]);
				$newcolumn.append($input);
			}
			
		});
		if(lesson.id=='' && button !=null){
			$oldDiv=$(button).closest('div.column');
			$oldDiv.after($newcolumn);
			$oldDiv.remove();
		}
		else if(true==isInit || lesson.id=='' || $('div.column[id="'+lesson.id+'"]').length==0){
			$('div.show-grid').append($newcolumn);
		}else{
			$oldDiv=$('div.column[id="'+lesson.id+'"]');
			$oldDiv.after($newcolumn);
			$oldDiv.remove();
		}
		
	});
}

/**
 * 获取最大index
 * @returns {Number}
 */
function findMaxIndex(){
	var maxIndex=0;
	$.each($('div.show-grid .column'),function(){
		index = $(this).attr('index');
		if(maxIndex < index){
			maxIndex = index;
		}
	});
	return parseInt(maxIndex);
}

/**
 * 根据套课ID获取该套课下所有课程
 */
function getAllListByCouresId(courseId){
	$('div.show-grid').empty();
	contents_getJsonForSync(
		ctx +"/spadmin/lesson/spLesson/getAllListByCouresId?courseId=" + courseId, 
		null, 
		"post", 
		function(list){
			makeLessonSpan(list,true);
		},
		function(error){
			artDialog.alert('获取失败');
		},null
	);
}

/**
 * 调用弹出框
 */
function showModal(elem,mode){
	var url="";
	editSection={};
	if(mode=='new'){
		var courseId = $('#courseId option:selected').val();
		var sectionsId="";
		url=ctx +"/spadmin/lesson/spLesson/showModal?courseId=" + courseId;
	}else if(mode=='edit'){
		if($(elem).parents(".info").length > 0){
			$column = $(elem).parents(".info")			
			url=ctx +"/spadmin/lesson/spLesson/showModal?id=" + $column.attr('id');
		}else{		
			$column = $(elem).parents(".new")
			url=ctx +"/spadmin/lesson/spLesson/showModal?"+divSerialize($column);
		}
		editSection[$column.find('.controls.lessonDate').text().trim()]=[$column.find('.controls.sectionId').text().trim()];
	}
	art.dialog.open(url,{
		title:$('#courseId option:selected').text(),
		width:800,
		height:400,
		lock:true,
		okVal:'确定',
		ok:function(){
			var diaForm = $(this.iframe).contents().find('form#local');
			if($(diaForm).find('#publishState').val()=='1' || $(diaForm).find('#publishState').val()=='3'|| $(diaForm).find('#publishState').val()=='4'){
				this.close();
				return false;
			}
			
			if($(diaForm).validate().form()){
				/*老师名字*/
				$(diaForm).find("[name='teacherName']")
					.val($(diaForm).find("[name='teacherId'] option:selected").text());
				/*老师简介*/
				$(diaForm).find("[name='teacherDesc']")
					.val($(diaForm).find("[name='teacherId'] option:selected").attr('desc'));
				/*开课状态*/
				$(diaForm).find("[name='lessonStateLabel']")
					.val($(diaForm).find("[name='lessonState'] option:selected").text());
				/*课程时间*/
				$(diaForm).find("[name='lessonTime']")
					.val($(diaForm).find("[name='sectionId'] option:selected").text());
				/*课程类型*/
				$(diaForm).find("[name='lessonSourceLabel']")
					.val($(diaForm).find("[name='lessonSource'] option:selected").text());				
				$(diaForm).find(':input').removeAttr('disabled');
				editSectionFunc(diaForm);
				
				var lesson = $(diaForm).serializeObject();
				if(mode=='new'){
					makeLessonSpan([lesson]);
				}else{
					makeLessonSpan([lesson],null,elem);
				}				
				
				this.close();
			}					
			return false;
		}
	},false);
}

/**
 * 
 * @param elem
 */
function lessonEdit(elem){
	showModal(elem,'edit');
}

/**
 * 
 * @param elem
 */
function lessonDelete(elem){
	$div = $(elem).parents(".column");
	if($div.attr("id")!=''){
		// 记录删除的课程id
		$('[name="deleteLessonIds"]').val($('[name="deleteLessonIds"]').val()+','+$div.attr("id"));
	}
	$div.remove();
}

// modal确定之后对节次的操作
function editSectionFunc(diaForm){
	if(JSON.stringify(editSection) !== JSON.stringify({})){
		var date="",sectionId="";
		$.each(editSection,function(k,v){
			date=k;
			sectionId=v[0];
		});
		deleteSectionFnc(date,sectionId);
	}
	
	addSectionFnc($(diaForm).find("[name='lessonDate']").val(),$(diaForm).find("[name='sectionId']").val());
}

// 添加引起的节次操作
function addSectionFnc(date,sectionId){
	// 删除中包含的情况下，去除
	if(deleteSection[date]!=null){
		if($.inArray(sectionId,deleteSection[date]) > -1){
			deleteSection[date].remove(sectionId);
			return;
		}		
	}
	if(addSection[date]==null){
		addSection[date]=[];
		addSection[date].push(sectionId);
	}else if($.inArray(sectionId,addSection[date]) == -1){
		addSection[date].push(sectionId);
	}
}

//删除引起的节次操作
function deleteSectionFnc(date,sectionId){
	// 删除中包含的情况下，去除
	if(addSection[date]!=null){
		if($.inArray(sectionId,addSection[date]) > -1){
			addSection[date].remove(sectionId);
			return;
		}
	}
	if(deleteSection[date]==null){
		deleteSection[date]=[];
		deleteSection[date].push(sectionId);
	}else if($.inArray(sectionId,deleteSection[date]) == -1){
		deleteSection[date].push(sectionId);
	}
}