function thumbup(replyId,flag) {
    $.ajax({
        url:path+"/ThumbsServlet",
        data:{
            q:"ThumbsUp",
            questionId:$("#questionId").val(),
            replyId:replyId,
            flag:flag
        },
        type:"POST",
        success:function(data){
            if(data.indexOf("login")!=-1){
                window.location.href=path+"/page/login.jsp";
            }
            console.log(data);
        },
        error:function(data){

        }
    });
}
$(document).ready(function(){
	$("span.icon-sort-up").bind("click",function(){
        var count = $(this).siblings().attr("data");
		if(count<1){
			$(this).parent().siblings().find("div.shuoming").find("span").html($(this).html()-0+1);
			$(this).html($(this).html()-0+1);
            count++;
            $(this).siblings().attr("data",count);
            thumbup($(this).parent().attr("data-replyId"),count);
		}
	});
	$("span.icon-sort-down").bind("click",function(){
	    var count = $(this).attr("data");
		if(count>0){
			$(this).parent().siblings().find("div.shuoming").find("span").html($(this).siblings().html()-1);
			$(this).siblings().html($(this).siblings().html()-1);
            count--;
            $(this).attr("data",count);
            thumbup($(this).parent().attr("data-replyId"),count);
		}
	});
	$(".btnwen").click(function() {
        $("#Modal-wrapper").show(); 
    });
    $(".guanbi").click(function() {
        $("#Modal-wrapper").hide(); 
    });

    $(".answer-tijiao").click(function () {
		var answer = {
		    q:"anwser",
            myReply:$("#answer").val(),
            questionId:$("#questionId").val()
		}
        $.ajax({
            url:path+"/ReplyServlet",
            data:answer,
            type:"POST",
            success:function(data){
                if(data.indexOf("login")!=-1){
                    window.location.href=path+"/page/login.jsp";
                }else{
                    alert(data);
                    location.assign(location);
                }
            },
            error:function(data){

            }
        });
    });
});