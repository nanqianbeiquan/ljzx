var filterController=function(id){
    var filter_option={
        //type:文字描述(text),组合类型(combine),时间插件(time),下拉插件(drop)
        //type=time:timeModel[range,day]
        data:[
            {title:'事件性质',type:'text',option:["全部","风险","严重","异常"]},
            {title:'事件类型',type:'text',option:["全部","工商","行政处罚","税务","司法","舆情","其他"]},
            {title:'时间筛选',type:'time',option:[{name:"全部",selected:"selected",type:'text'},{name:"最近7天",type:'text'},{name:'',type:''},{name:'自选时间',type:'time',timeModel:'range'}]}
        ]
    };
    var check_undefined=function(value){
        return ("undefined"==typeof value);
    }
    var filterController=function(id){
        this.id=id;
    }

    var build_data=function(data){
        
    }
    var build_option=function(option){

    }

    filterController.prototype={
        set_option:function(option){
            var opts=$.extend({} , filter_option, option);
            if(!check_undefined(opts.data)&&opts.data.length>0){
                build_data(opts.data);
            }else{

            }
        }
    };
    filterController.prototype.constructor = filterController;
	return filterController;
}();

var option={
    data:[
        {title:'事件性质',type:'text',option:["全部","风险","严重","异常"]},
        {title:'事件类型',type:'text',option:["全部","工商","行政处罚","税务","司法","舆情","其他"]},
        {title:'时间筛选',type:'time',option:[{name:"全部",selected:"selected",type:'text'},{name:"最近7天",type:'text'},{name:'',type:''},{name:'自选时间',type:'time',timeModel:'range'}]}
    ]
}