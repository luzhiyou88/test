/**
 * 课程列表使用脚本
 * zhujie
 */
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

function lessonDelete(url){
	return art.dialog.confirm('确认要删除该课程吗？', 
	    function () {
			contents_getJsonForSync(
					url, 
					null, 
					"post", 
					function(data){
						if(data!=null && data.result=='true'){
							artDialog.alert("删除成功",function(){
								$('#btnSubmit').click();
							});
						}else{
							if(data!=null){
								artDialog.alert("删除失败："+data.msg);
							}else{
								artDialog.alert("删除失败");
							}
							
						}
					},
					function(){
						artDialog.alert("删除失败");
					},
					null
			)
	    }, 
	    function () {}
	);
}
// 平台发布
function publishLesson(key,url){
	return art.dialog.confirm('确认要发布该'+key+'吗？', 
	    function () {
			contents_getJsonForSync(
					url, 
					null, 
					"post", 
					function(data){
						if(data!=null && data.result=='true'){
							artDialog.alert("发布成功，请等待审核",function(){
								$('#btnSubmit').click();
							});
						}else{
							artDialog.alert(data.msg);
						}
					},
					function(){
						artDialog.alert("发布失败");
					},
					null
			)
	    }, 
	    function () {
	    	$('#courseId').val($('#courseId').attr('backVal'));
	    	$('#courseId').selectpicker('refresh');
	    }
	);
}

// 刷新平台发布状态
function refreshPublishStatus(url){
	contents_getJsonForSync(
			url, 
			null, 
			"post", 
			function(data){
				if(data!=null && data.result=='true'){
					artDialog.alert("状态更新成功",function(){
						$('#btnSubmit').click();
					});
				}else{
					artDialog.alert(data.msg);
				}
			},
			function(){
				artDialog.alert("状态更新失败");
			},
			null
	)
}