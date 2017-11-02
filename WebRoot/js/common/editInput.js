var editInput=function(){
    var show_input=function(id){
        var target=$("."+id);
        var edit_input=$("<input type='text'/>");
        $(edit_input).width($(target).width()).height($(target).height());
        var edit_input_block=$("<div class='edit_input' style='position:absolute;z-index:90;min-width:120px;'></div>").append(edit_input);
        $(edit_input_block).css({top:$(target).offset().top,left:$(target).offset().left});
        $("body").append(edit_input_block);
        $(edit_input).focus().blur(function(){
            if($(edit_input).val().length>1){
                $(target).text($(edit_input).val());
            }
            $(edit_input_block).remove();
        });
    }
    var editInput=function(){
        
    }
    editInput.prototype={
        click:function(id){
            show_input(id);
        }
    };
    editInput.prototype.constructor = editInput;
	return editInput;
}();

(function($){
    $.fn.extend({
        editInput:function(func,call_back,val){
            var target=$(this);
            var edit_input=$("<input style='min-width:80px;padding-left:2px;' type='text'/>");
            $(edit_input).val(val||$(target).text());
            $(edit_input).width($(target).width()).height($(target).height());
            var edit_input_block=$("<div class='edit_input' style='position:absolute;z-index:90;'></div>").append(edit_input);
            $(edit_input_block).css({top:$(target).offset().top,left:$(target).offset().left});
            $("body").append(edit_input_block);
            $(edit_input).focus().blur(function(){
                var value=$(edit_input).val();
                if($(edit_input).val().length>=1){
                    if("function"==typeof(func)){
                        if(func(value)){
                            if("function"==typeof(call_back)){
                            	call_back(value)
                            }else{
                            	$(target).text(value);
                            }
                        }
                    }else{
                        $(target).text($(edit_input).val());
                    }
                }
                $(edit_input_block).remove();
            });
            
        }
    })
})(jQuery);