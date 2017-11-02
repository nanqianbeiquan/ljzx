/*数据验证*/

//验证是否为空
function isNotEmpty(str){
	if("" == str){
		return false;
	}
	
	if(null == str){
		return false;
	}
	
	return true;
}

function isEmpty(str){
	if("" == str){
		return true;
	}
	
	if(null == str){
		return true;
	}
	
	if(undefined == str){
		return true;
	}
	
	return false;
}

//验证固定电话传真是否有误
function validateTelephone(telephone){
	
	var regex = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
	
	if(!regex.test(telephone)){
		return false;
	}
	
	return true;
}

//验证手机号码是否有误
function validateCelephone(celephone){
	
	var regex = /^1[3|4|5|7|8]\d{9}$/;
	
	if(!regex.test(celephone)){
		return false;
	}
	
	return true;
}
//验证sort只能输入数字
function validateSort(sort){
	
	var regex = /^[0-9]*$/;
	
	if(!regex.test(sort)){
		return false;
	}
	
	return true;
}

//验证邮箱是否有误
function validateEmail(email){
	
	var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	if(!regex.test(email)){
		return false;
	}
	
	return true;
}

//验证邮编是否有误
function validatePostCode(postCode){
	
	var regex = /^[1-9][0-9]{5}$/;
	
	if(!regex.test(postCode)){
		return false;
	}
	
	return true;
	
}

//验证身份证号是否有误
function validateIDNumber(IDNumber){
	
	var regex = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	
	if(!regex.test(IDNumber)){
		return false;
	}
	
	return true;
	
}


