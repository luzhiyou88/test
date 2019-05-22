/**
 * 课程表使用脚本
 * zhujie
 */

$(document).ready(function() {
	$.each(spLessonList,function(){
		makeSpan(this);
	});
	
	//日期查询
	$('#scheduleSearch').click(function(){
		$('#startDate').val($('#scheduleDate').val());
		$('#inputForm').submit();
	});
	
	$('tbody td:not(".menu")').click(function(){
		
		if($(this).is('td:not(.radius)')){
			// 拉取平台信息
			getPublishLesson(this);
			
		}else if($(this).is('td.btn-warning')){
			// 平台套课信息
			getPlulishByCourse(this);
		}
		
	});
	
	/**
	 * 拉取平台课程信息
	 */
	function getPublishLesson(elem){
		var lessonDate = $(elem).attr('data-date');
		var sectionId = $(elem).attr('data-section');
		var url = ctx +"/spadmin/lesson/spLesson/getPublishLesson?lessonDate=" + lessonDate+"&sectionId="+sectionId;
		
		art.dialog.open(url,{
			title:'课程/路演添加',
			width:800,
			height:500,
			lock:true,
			okVal:'确定',
			cancelVal:'取消',
			cancel:function(){
				this.close();
			},
			ok:function(){
				var diaForm = $(this.iframe).contents().find('form:visible');// 弹出框
				if($(diaForm).is('#plat')){
					platOperate(this,diaForm);
				}else if($(diaForm).is('#platRoadshow')){
					platRoadshowOperate(this,diaForm);
				}else if($(diaForm).is('#local')){
					localOperate(this,diaForm);
				}else if($(diaForm).is('#localRoadshow')){
					localRoadshowOperate(this,diaForm);
				}
				return false;
			}
		},false);
	}
	
	/**
	 * 拉取套课课程信息
	 */
	function getPlulishByCourse(elem){
		var courseId = $(elem).attr('data-course');
		var url = ctx +"/spadmin/lesson/spLesson/getPublishLesson?courseId=" + courseId;
		art.dialog.open(url,{
			title:'平台套课课程列表',
			width:900,
			height:400,
			lock:true,
			cancelVal:'关闭',
			cancel:function(){
				this.close();
			}
		},false);
	}
	
	
	/**
	 * 平台订阅操作
	 * @param diaForm
	 * @returns
	 */
	function platOperate(open,diaForm){
		if($(diaForm).validate().form()){
			if($('.check-ico:visible',diaForm).length==0){
				artDialog.alert("请选择订阅的课程");
			}else{
				artDialog.confirm('确认要订阅该课程吗？', 
				    function () {
					var courseId = $('.check-ico:visible',diaForm).parent('.column-check').attr('courseId');
					var checkUrl = ctx +"/spadmin/course/spCourse/checkCourseExist";
					var courseUrl = ctx +"/spadmin/course/spCourse/getPlCourse?courseId="+courseId;
					contents_getJsonForSync(checkUrl, {'courseId':courseId}, "post", 
						function(data){
							if(data!=null && data.result=="true"){
								if(data.data=='false'){
									// 本地套课不存在，先编辑套课信息
									art.dialog.open(courseUrl,{
										title:'编辑套课信息',
										width:700,
										height:500,
										lock:true,
										okVal:'确定',
										cancelVal:'取消',
										cancel:function(){
											this.close();
										},
										ok:function(){
											var courseForm = $(this.iframe).contents().find('form');						
											if($('#spCategoryId',courseForm).val()==''){
												artDialog.alert("请选择套课分类");
												return false;
											}																
											$('[name="categoryId"]',diaForm).val($('#spCategoryId',courseForm).val());
											$('[name="courseId"]',diaForm).val(courseId);
											this.close();
											savePlatLesson(diaForm,open,'0');
										}
									},false);
									return;
								}else{
									// 平台套课本地已存在，直接订阅课程
									$('[name="courseId"]',diaForm).val(courseId);
									savePlatLesson(diaForm,open,'0');
								}
							}else if(data!=null && data.result=="false" && data.data===true){
								return art.dialog.confirm(data.msg+'<br/>是否需要删除该套课下的所有已订阅课程？', 
								    function () {
										// 重新查询
										removeLesson("2",courseId)
								    }, 
								    function () {}
								);
							}else{
								if(data==null){
									artDialog.alert("操作失败");
								}else{
									artDialog.alert(data.msg);
								}											
							}
						},null,null);
			    }, 
				    function () {}
				);
			}
			
		}
	}
	
	/**
	 * 平台路演订阅操作
	 */
	function platRoadshowOperate(open,diaForm){
		if($(diaForm).validate().form()){
			if($('.check-ico:visible',diaForm).length==0){
				artDialog.alert("请选择订阅的路演");
			}else{
				artDialog.confirm('确认要订阅该路演吗？', 
				    function () {
						this.close();
						savePlatLesson(diaForm,open,'1');}, 
				    function () {}
				);
			}
			
		}
	}
	
	/**
	 * 本地课程添加操作
	 */
	function localOperate(open,diaForm){
		if($(diaForm).validate().form()){
			contents_getJsonForSync(
				ctx +"/spadmin/lesson/spLesson/saveJson",
				$(diaForm).serialize(), 
				"post", 
				function(data){
					if(data!=null && data.result=="true"){
						artDialog.alert("保存成功");
						makeSpan(data.data,null,true);
						open.close();
					}else{
						if(data==null){
							artDialog.alert("保存失败");
						}else{
							artDialog.alert("保存失败："+data.msg);
						}
					}
				},
				function(){
					artDialog.alert("保存失败")
				},
				null				
			);
		}
	}
	
	/**
	 * 本地路演添加操作
	 */
	function localRoadshowOperate(open,diaForm){
		if($(diaForm).validate().form()){
			contents_getJsonForSync(
				ctx +"/spadmin/roadshow/saveJson",
				$(diaForm).serialize(), 
				"post", 
				function(data){
					if(data!=null && data.result=="true"){
						artDialog.alert("保存成功");
						makeSpan(data.data);
						open.close();
					}else{
						if(data==null){
							artDialog.alert("保存失败");
						}else{
							artDialog.alert("保存失败："+data.msg);
						}
					}
				},
				function(){
					artDialog.alert("保存失败")
				},
				null				
			);
		}
	}
	
	var windowwidth;
    var windowheight;
    var checkmenu;
    $('#myMenu').hide();
    initWarningTd();
	$(document).bind("click",function(){
		$('#myMenu').hide();
		checkmenu = 0;
	});
});



/**
 * 删除平台课程/套课课程
 */
function removeLesson(mode,courseId){
	var url="";
	if(courseId==null){
		if(mode=='1'){
			//删除平台课程
			url = ctx +"/spadmin/lesson/spLesson/removePublishLesson?lessonId="+$('#myMenu').attr('lessonId');
		}else if(mode=='2'){
			//删除套课课程
			url = ctx +"/spadmin/lesson/spLesson/removePublishLessonByCourse?courseId="+$('#myMenu').attr('courseId');
		}
	}else{
		//删除套课课程
		url = ctx +"/spadmin/lesson/spLesson/removePublishLessonByCourse?courseId="+courseId;
	}
	
	contents_getJsonForSync(
			url,
			null, 
			"post", 
			function(data){
				if(data!=null && data.result=="true"){
					artDialog.alert("删除成功");
					$.each(data.data,function(){
						removeSpan(this);
					});
				}else{
					if(data==null){
						artDialog.alert("删除失败");
					}else{
						artDialog.alert("删除失败："+data.msg);
					}
				}
			},
			function(){
				artDialog.alert("删除失败")
			},
			null				
		);
}

