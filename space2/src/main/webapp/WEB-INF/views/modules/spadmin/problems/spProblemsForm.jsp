<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试题管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
			 
			
			$('#problemType').change(function(){
				var check = $(this).val();
				if(check == '4'){
					$('.op').removeClass('required');
					$('.op').hide();
					$('.an').hide();
					$('.dx').hide();
					$('.ans').show();
				} else if(check == '1'){
					$('.op').addClass('required');
					$('.op').show();
					$('.ans').hide();
					$('.an').hide();
					$('.dx').show();
				} else {
					$('.op').addClass('required');
					$('.op').show();
					$('.ans').hide();
					$('.dx').hide();
					$('.an').show();
				}
			});
			
			$('#ismaterial').change(function(){
				var check = $(this).val();
				if(check == '2'){
					$('#materialId').removeClass('required');
					$('.material').hide();
				} else {
					$('#materialId').addClass('required');
					$('.material').show();
				}
			});
		});
		
		
		$(function(){
			//材料题和非材料题，题干和选项处理
			var check = $('#problemType').val();
			if(check == '4'){
				$('#optionA').val('');
				$('#optionB').val('');
				$('#optionC').val('');
				$('#optionD').val('');
				$('.op').removeClass('required');
				$('.op').hide();
				$('.an').hide();
				$('.dx').hide();
				$('.ans').show();
			} else if(check == '1'){
				$('.op').addClass('required');
				$('.op').show();
				$('.ans').hide();
				$('.an').hide();
				$('.dx').show();
			//} else {
				//$('.op').addClass('required');
				//$('.op').show();
				//$('.ans').hide();
				//$('.an').show();
				//$('.dx').hide();
				var answer = $('#answ1').val();
				if(answer != ''){
					var ck = $(':checkbox[name="answer"]');
					ck.each(function(k,v){
						for(var j=0;j<answer.length;j++){
						    if(v.value == answer[j]){
						    	$(v).attr('checked',true);
						    }
						  }
					})
				}
			}
			
			var answerValue = $('#id').val();
			var xq = $('#xq').val();
			if(answerValue == '' && xq == ''){
				$('#answer1').removeClass('disabled');
				$('#answer2').removeClass('disabled');
				$('#answer3').removeClass('disabled');
				$('#answer4').removeClass('disabled');
			} else if (answerValue != '' && xq != ''){
				$('#answer1').attr('disabled','true');
				$('#answer2').attr('disabled','true');
				$('#answer3').attr('disabled','true');
				$('#answer4').attr('disabled','true');
			}
			
			//材料题非材料题选择
			var check1 = $('#ismaterial').val();
			if(check1 == '2'){
				$('#materialId').val('');
				$('#materialId').removeClass('required');
				$('.material').hide();
			} else {
				$('#materialId').addClass('required');
				$('.material').show();
			}
			
			//校验试题题干
			$('#stem').change(function(){
				var examinationId = $('#examinationId').val();
				var stem = $('#stem').val();
				var url = ctx+"/spadmin/problems/spProblems/checkStem?stem="+stem+"&examinationId="+examinationId+"&id="+$('#id').val();
				$.ajax({
					type: 'get',
			        url: url,
			        dataType: "json",
			        async: false,
			        success:function(data){
			        	if(data == false){
			        		art.dialog.alert('试题题干已存在，不能再添加！');
			        	} 
			        	
			        },
			        error:function(){
			        	art.dialog.alert('请求出错!');
			        }
				});
			});
			
			
			//校验试卷数目
			$('#examinationId').change(function(){
				var examinationId = $('#examinationId').val();
				var url = ctx+'/spadmin/problems/spProblems/checkProblemCount?examinationId='+examinationId+"&id="+$('#id').val();
				$.ajax({
					type: 'get',
			        url: url,
			        dataType: "json",
			        async: true,
			        success:function(data){
			        	if(data == false){
			        		art.dialog.alert('试卷关联的试题已经填满，不能再添加！');
			        	} else if(data == 'err') {
			        		art.dialog.alert('试卷不能为空，请确认！');
			        	}
			        },
			        error:function(){
			        	art.dialog.alert('请求出错!');
			        }
				});
			});
			
			//校验试卷编号
			$('#number').change(function(){
				var examinationId = $('#examinationId').val();
				var number = $(this).val();
				var url = ctx+'/spadmin/problems/spProblems/checkProblemNumber?examinationId='+examinationId+'&number='+number+"&id="+$('#id').val();
				$.ajax({
					type: 'get',
			        url: url,
			        dataType: "json",
			        async: true,
			        success:function(data){
			        	if(data == false){
			        		art.dialog.alert('试卷编号已存在,请修改！');
			        	} else if(data == 'err') {
			        		art.dialog.alert('试卷或者编号不能为空,请确认！');
			        	}
			        },
			        error:function(){
			        	art.dialog.alert('请求出错!');
			        }
				});
			});
			
			//校验试卷分数
			$('#problemScore').change(function(){
				var examinationId = $('#examinationId').val();
				var problemScore = $(this).val();
				var url = ctx+'/spadmin/problems/spProblems/checkProblemScore?examinationId='+examinationId+'&problemScore='+problemScore+"&id="+$('#id').val();
				$.ajax({
					type: 'get',
			        url: url,
			        dataType: "json",
			        async: true,
			        success:function(data){
			        	if(data == false){
			        		art.dialog.alert('试卷分数超出总分数，请修改！');
			        	} else if(data == 'err') {
			        		art.dialog.alert('试卷或者分值不能为空，请确认！');
			        	}
			        },
			        error:function(){
			        	art.dialog.alert('请求出错!');
			        }
				});
			});
			
			$(':radio[name="answer"]').change(function(){
				var ck = $(':checkbox[name="answer"]').each(function(){
					$(this).attr("checked",false);
				});
			   $('textarea[name="answer"]').attr('name','kkk');
				
			})  
			
			$(':checkbox[name="answer"]').change(function(){
				var ck = $(':radio[name="answer"]').each(function(){
					$(this).attr("checked",false);
				});
				$('textarea[name="answer"]').attr('name','kkk');
			})
			
			$('#problemType').change(function(){
				var check = $(this).val();
				if(check == '4'){
					/* $(':checkbox[name="answer"]').each(function(){
						$(this).attr("checked",false);
					}); */
					$(':radio[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$('#answer').val('');
					$('#optionA').val('');
					$('#optionB').val('');
					$('#optionC').val('');
					$('#optionD').val('');
				} else if (check == '1') {
					/* $(':checkbox[name="answer"]').each(function(){
						$(this).attr("checked",false);
					}); */
					$(':radio[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$('#answer').val('');
					
				} else if (check == '2') {
					$(':checkbox[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$(':radio[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$('#answer').val('');
				} else if (check == '3') {
					$(':checkbox[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$(':radio[name="answer"]').each(function(){
						$(this).attr("checked",false);
					});
					$('#answer').val('');
				}
			})
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/spadmin/problems/spProblems/">试题管理列表</a></li>
		<li class="active"><a href="${ctx}/spadmin/problems/spProblems/form?id=${spProblems.id}&parent.id=${spProblemsparent.id}">试题管理${not empty spProblems.id?'修改':'添加'}查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="spProblems" action="${ctx}/spadmin/problems/spProblems/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="xq" value="${xq}">
		<sys:message content="${message}"/>	
		<c:if test="${empty xq }">	
		<div class="control-group">
		   <input type="hidden" id="hid" />
			<label class="control-label">所属试卷：</label>
			<div class="controls">
				<%-- <form:input path="examinationId" htmlEscape="false" maxlength="32" class="input-small "/> --%>
				<form:select path="examinationId"  items="${fncr:getSpExaminationMap(true)}" class="input-large required" ></form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题目类型 ：</label>
			<div class="controls">
				<form:select id="problemType" path="problemType" class="input-mini required">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">单选</form:option>
                   <%-- <form:option value="2">多选</form:option>
			       <form:option value="3">不定项</form:option> --%>
			       <form:option value="4">主观题</form:option>
		       </form:select>
		       <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题目编号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="11" class="input-mini required znum"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文本类型 ：</label>
			<div class="controls">
				<form:select  path="textType" class="input-mini required">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">文字</form:option>
		       </form:select>
		       <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题干：</label>
			<div class="controls">
				<form:textarea path="stem" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">A：</label>
			<div class="controls">
				<%-- <form:input path="optionA" htmlEscape="false" maxlength="200" class="input-xlarge required"/> --%>
				<form:textarea path="optionA" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">B：</label>
			<div class="controls">
				<form:textarea path="optionB" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">C：</label>
			<div class="controls">
				<form:textarea path="optionC" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">D：</label>
			<div class="controls">
				<form:textarea path="optionD" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		 <div class="control-group ans" display="none">
			<label class="control-label">正确答案：</label>
			<div class="controls">
				<form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
			</div>
		</div> 
		<%-- <div class="control-group an" display="none">
		<input type="hidden" id="answ1" value="${spProblems.answer }">
			<label class="control-label">正确答案：</label>
			<div class="controls">
				<form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<form:checkbox path="answer" value="A"/>A 
				<form:checkbox path="answer" value="B"/>B 
				<form:checkbox path="answer" value="C"/>C 
				<form:checkbox path="answer" value="D"/>D 
			</div>
		</div> --%>
		<div class="control-group dx" >
			<label class="control-label">正确答案：</label>
			<div class="controls">
				<%-- <form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/> --%>
				<form:radiobutton path="answer" value="A"  label="A" />  
                <form:radiobutton path="answer"  value="B"  label="B" />
                <form:radiobutton path="answer" value="C"  label="C" />  
                <form:radiobutton path="answer"  value="D"  label="D" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">解析：</label>
			<div class="controls">
				<form:textarea path="analysis" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">是否是材料题：</label>
			<div class="controls">
				<form:select id="ismaterial" path="ismaterial" class="input-mini required">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">材料题</form:option>
                   <form:option value="2">非材料题</form:option>
		       </form:select>
		       <span class="help-inline"><font color="red">*</font> </span> 
			</div>
		</div>
		<div class="control-group material">
			<label class="control-label">材料：</label>
			<div class="controls">
				<form:select path="materialId"  items="${fncr:getSpMaterialsMap(true)}" class="input-xlarge required" ></form:select>
			    <span class="help-inline"><font color="red">*</font> </span> 
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="problemScore" htmlEscape="false" maxlength="11" class="input-mini required znum"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</c:if>
		<c:if test="${not empty xq}">
		<div class="control-group">
			<label class="control-label">所属试卷：</label>
			<div class="controls">
				<%-- <form:input path="examinationId" htmlEscape="false" maxlength="32" class="input-small "/> --%>
				<form:select path="examinationId"  items="${fncr:getSpExaminationMap(true)}" class="input-large required" disabled="true" ></form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题目类型 ：</label>
			<div class="controls">
				<form:select id="problemType" path="problemType" class="input-mini required" disabled="true">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">单选</form:option>
                  <%--  <form:option value="2">多选</form:option>
			       <form:option value="3">不定项</form:option> --%>
			       <form:option value="4">主观题</form:option>
		       </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题目编号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="11" class="input-mini required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文本类型 ：</label>
			<div class="controls">
				<form:select  path="textType" class="input-mini required" disabled="true">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">文字</form:option>
		       </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">题干：</label>
			<div class="controls">
				<form:textarea path="stem" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">A：</label>
			<div class="controls">
				<%-- <form:input path="optionA" htmlEscape="false" maxlength="200" class="input-xlarge required"/> --%>
				<form:textarea path="optionA" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">B：</label>
			<div class="controls">
				<form:textarea path="optionB" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">C：</label>
			<div class="controls">
				<form:textarea path="optionC" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group op">
			<label class="control-label">D：</label>
			<div class="controls">
				<form:textarea path="optionD" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		 <div class="control-group ans" display="none">
		<input type="hidden" id="answ1" value="${spProblems.answer }">
			<label class="control-label">正确答案：</label>
			<div class="controls">
				<form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required ttttt" readonly="true"/>
			</div>
		</div> 
		<%-- <div class="control-group an" display="none">
		    <input type="hidden" id="answ" value="${spProblems.answer }">
			<label class="control-label">正确答案：</label>
			<div class="controls">
				<form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/>
				<form:checkbox path="answer" value="A"/>A 
				<form:checkbox path="answer" value="B"/>B 
				<form:checkbox path="answer" value="C"/>C 
				<form:checkbox path="answer" value="D"/>D 
			</div>
		</div> --%>
		<div class="control-group dx" >
			<label class="control-label">正确答案：</label>
			<div class="controls" >
				<%-- <form:textarea path="answer" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required"/> --%>
				<form:radiobutton path="answer" value="A"  label="A" />  
                <form:radiobutton path="answer"  value="B"  label="B" />
                <form:radiobutton path="answer" value="C"  label="C" />  
                <form:radiobutton path="answer"  value="D"  label="D" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">解析：</label>
			<div class="controls">
				<form:textarea path="analysis" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge required " readonly="true"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">是否是材料题：</label>
			<div class="controls">
				<form:select id="ismaterial" path="ismaterial" class="input-mini required" disabled="true">
                   <form:option value="">请选择</form:option>
			       <form:option value="1">材料题</form:option>
                   <form:option value="2">非材料题</form:option>
		       </form:select>
			</div>
		</div>
		<div class="control-group material">
			<label class="control-label">材料：</label>
			<div class="controls">
				<form:select path="materialId"  items="${fncr:getSpMaterialsMap(true)}" class="input-xlarge required" disabled="true"></form:select>
				
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="problemScore" htmlEscape="false" maxlength="11" class="input-mini required number" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="512" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="form-actions">
		  <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		  </div>
		</c:if>
	</form:form>
</body>
</html>