//跳转到关于我们页面
function toAboutUs(){
	var deep = $("#deep").val();
	
	window.location.href = ctx + "/introduction/toAboutUs?deep="+(parseInt(deep)+1);
}

//跳转到关于我们页面
function toDisclaimer(){
	var deep = $("#deep").val();
	window.location.href = ctx + "/introduction/toDisclaimer?deep="+(parseInt(deep)+1);
}

//跳转到意见反馈页面
function toFeedBack(){
	var deep = $("#deep").val();
	window.location.href = ctx + "/feedBack/toFeedBack?deep="+(parseInt(deep)+1);
}

function toQuestions(){
	var deep = $("#deep").val();
	window.location.href = ctx + "/introduction/toQuesttions?deep="+(parseInt(deep)+1);
}
