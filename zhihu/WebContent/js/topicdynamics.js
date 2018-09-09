//获取已经关注话题分类列表
function getTopicList(){
	$.ajax({
		url:path+"/questionServlet?q=getTopicList",
		datatype:"JSON",
		contentType :"application/x-www-form-urlencoded",
		type:"POST",
		success:function(data){
			if(data){
				var topicList = JSON.parse(data);
				$("#count").html(topicList.length);
				for(var a in topicList){
					var topic = topicList[a];
					//开始丧心病狂的拼接字符串
					$(".topic").append("<li><a href='"+topic.topicId+"'>"+topic.topicName+"</a></li>");
					$("#quizContainer").append(
						'<div class="topicone" id="'+topic.topicId+'">'+
						'<a href="#" class="head">'+
						'<img src="'+path+'/images/movie.jpg" alt="'+topic.topicName+'" /></a>'+
						'<a href="#" class="headtitle">'+topic.topicName+'</a><div class="order">'+
            			'<a href="#">热门排序</a>|<a href="#" style="color: #259;">时间排序</a></div><div class="topiclist-container"></div></div>');
				}
				bindClick();
			}
		},
		error:function(data){
			
		}
	});
}
//通过话题分类id获取其下的问题列表
function getQuizList(topciId,content){
	$.ajax({
		url:path+"/questionServlet",
		datatype:"JSON",
		contentType :"application/x-www-form-urlencoded",
		data:{
			q:'getTopicQuestionList',
			topciId:topciId,
			content:content
		},
		type:"POST",
		success:function(data){
			if(data){
				var quizList = JSON.parse(data);
				var _this = $("#"+topciId).find("div.topiclist-container");
				_this.empty();
				//开始丧心病狂的拼接字符串
				for(var a in quizList){
					var quiz = quizList[a];
					_this.append(
						'<div class="listitem">'+
						'<h2><a href="'+path+'/questionServlet?q=getQuizDetail&quizId='+quiz.questionId+'">'+quiz.questionTitle+'</a></h2>'+
						'<div class="expandable"><div class="zan"><a href="#">'+quiz.zanCount+'</a></div>'+
						'<div class=""><span class="summary-wrapper">'+
                        '<span  class="author-link-line"><a href="#">'+quiz.userName+'</a></span>'+
                        '<span class="wangming" title="'+(quiz.userContent?quiz.userContent:"")+'">'+(quiz.userContent?quiz.userContent:"")+'</span>'+
                        '</span><div class="article"><span class="a">'+quiz.questionIntroduce+
                        '<a href="#" class="toggle-expand b">显示全部</a></div><div class="banquan">'+
                        '<a href="#">关注问题</a><a href="#">'+quiz.commentCount+'条评论</a><a href="#">作者保留权利</a></div>'+
						'</div></div></div></div></div>');
				}
				showAll();
			}
		},
		error:function(data){
			
		}
	});
}
//显示全部和收起全部
function showAll(){
	var flag = true; 
    $(".toggle-expand").bind("click",function(e){
        e.preventDefault();
        if(flag){
            $(this).html("收起全部");
            $(this).siblings().css({"height":"auto"});
        }else{
            $(this).html("显示全部");
            $(this).siblings().css({"height":"80px"});
        }
        flag = !flag;
    });
}
//动态话题点击方法
function bindClick(){
	$(".topic").find('a').bind("click",function(e){
        e.preventDefault();
        $(this).addClass('actived').parent().siblings().find("a").removeClass("actived");
        var id = $(this).attr("href");
        $("#"+id).show();
        var content=$("#search-content").val();
        getQuizList(id,content);
        $(this).parent().siblings().find("a").each(function(index, el) {
            $("#"+$(this).attr("href")).hide();
        });
    });
	$(".topic").find("a").first().click();
}
function search(){
	var id=$(".topic a.actived").attr("href");
	console.log(id);
	var content=$("#search-content").val();
	console.log(content);
	getQuizList(id,content);
}
$(document).ready(function(){
    $(".btn").click(function() {
        $("#Modal-wrapper").show(); 
    });
    $(".guanbi").click(function() {
        $("#Modal-wrapper").hide(); 
    });
    $(".searbtn").click(function(){
    	search();
    })
    getTopicList();
});