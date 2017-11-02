//倒计时计数
var count = 60;
var timer;

var userConfigController=function(){
    var event_email=function(overlaps){
        $(".row_value .selector").click(function(){
            var parent = $(this).parent().parent(".row_value");
            
            $(parent).find(".selected").removeClass("selected");
            
            $(this).addClass("selected");
            
            var companyType = $(this).parent().children(".value").text();
            
            if(companyType == "企业"){
            	$("#companyType").val("1");
            }else if(companyType == "金融机构"){
            	$("#companyType").val("2");
            }
        });
    }

    var event_password=function(){
    	
    }
    
    var load_gray_pic=function(){
    	
    	$(".gray .mode_img").each(function(){
    		// $(this).attr("src_path",$(this).attr("src"));
			// $(this).attr("src",_PAGE_MODEL.gray(this));
			$(this).addClass("hidden");
		})
		$(".gray .gray_img").each(function(){
    		// $(this).attr("src_path",$(this).attr("src"));
			// $(this).attr("src",_PAGE_MODEL.gray(this));
			$(this).removeClass("hidden");
    	})
    }
    var userConfigController=function(){
    	this.overlap_list={}
        this.load_page();
    	this.init_overlap();
        this.event_bind();
    }
    userConfigController.prototype={
        load_page:function(){
        	load_gray_pic();
        },
        event_bind:function(){
            event_email(this.overlap_list);
            event_password();
        },
        init_overlap:function(){
        	this.overlap_list.message_lap=new overlap({
                mask:{
                 show:true
             },
             scroller:{
                 lock:false
             },
             position:{
                 type:"center"
             },
             close:{
                 show:false,
                 type:"close_2",
                 style:{

                 },
                 callBack:function(){
                     
                 }
             },
             content:{
                 style:{
                     width:"516px",
                     height:"200px",
                     border:"16px solid rgba(0,0,0,0.15)"
                 }
             }});
        }
    };
    userConfigController.prototype.constructor = userConfigController;
	return userConfigController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

//修改用户基本信息
function updateClientInfo(){
	
	var parameter = {
			"userName" : $("#userName").val().trim()
	}
	
	$.ajax({
		url: ctx + "/clientAccount/updateClientInfo",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
				toastr.success(result.resultMsg);
			}else if(result.resultCode == '1'){
				toastr.error(result.resultMsg);
			}
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}


