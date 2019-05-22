$(document).ready(function(){
	
	var onSubmit = false;
	
	$(window).on('submit',function(){
		if(onSubmit){
			return false;
		}
		onSubmit = true;
		$(this).submit(function(){
			return true;
		});
	});
	
	/**
	 * select2样式
	 */
	$.extend($.fn.selectpicker.defaults,{
		style: 'bootstrap-select',
		noneSelectedText : '请选择',
        noneResultsText : '没有匹配的项',
	});
	/**
	 * select校验
	 */
	$('select:visible').on('change',function(){
		if($(this).hasClass('error') || $(this).hasClass('valid')){
			var $form = $(this).closest('form');
			$form.validate().element(this);
		}		
	});
	$('select:not(#template select):not(#template)')
		.attr('data-actions-box',true)
		.attr('data-live-search',true)
		.selectpicker({
			selectAllText: '全选',
		    deselectAllText: '全取消'
		});
	/**
	 * 清空检索条件，以下条件忽略
	 * 包含class【noClear】
	 * author zhujie
	 */
	$('input[type=reset]').off('click').on('click',function(){
		$form = $(this).closest("form");
		$form.find('input:not([type=button],[type=submit], [type=reset]):not(.noClear)').val('');
		$form.find('select:not([disabled])').selectpicker('deselectAll');
		return false;
	});
});


/**
 * loading浮层显示，画面不可操作
 */
function loadingMarkShow(unix){
	if(unix!=null){
		loadingMarkShow.id=unix;
	}
    // 遮盖层    
    loadingMarkShow.protectDiv = $('<div></div>').prependTo('body').css({
        'position'        : 'fixed',
        'top'             : '0px',
        'bottom'          : '0px',
        'right'           : '0px',
        'left'            : '0px',
        'zIndex'          : 0x7FFFFFFE,
        'backgroundColor' : 'black',
        'margin'          : '0px',
        'border'          : '0px none',
        'padding'         : '0px'
    }).fadeTo(0,0.3);

    // loading层
    gif=ctxWebInf+"/img/loading1.gif";
    loadingMarkShow.loadingDiv =
        $('<div id="loading-wait-mark"><div><img src='+gif+'></div></div>');
    loadingMarkShow.loadingDiv.prependTo('body').attr('id','loading-wait').css({
        'position' : 'fixed',
        'top'      :
            ($(window).height() - loadingMarkShow.loadingDiv.height()) / 2,
        'left'     : '49%',
        'zIndex'   : 0x7FFFFFFF
    });

    return(true);
}

/**
 * loading隐藏，画面可操作
 */
function loadingMarkHide(unix){
	if(unix!=null && loadingMarkShow.id != unix){
		return;
	}
    // loading层删除
    loadingMarkShow.loadingDiv.remove();

    // 遮盖层删除
    loadingMarkShow.protectDiv.remove();

    return(true);
}
