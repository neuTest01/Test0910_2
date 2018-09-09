function getDynamicLst() {
    $.ajax({
        url:path+"/MyDynamicServlet",
        data:{"q":"getMyDynamicList"},
        type:"POST",
        success:function(data){
            generateDynamicList(JSON.parse(data));
        },
        error:function(data){

        }
    });
}
function generateDynamicList(dynamicList) {
    if(dynamicList&&dynamicList.length>0){
        for(var a in dynamicList){
            var dynamic = dynamicList[a];
            $("#dynamic").append('<div class="list-item"><div class="item-meta"><span class="meta-title">'+dynamic.typeFY+'</span></div><h2 class="item-title">' +
                '<a target="_blank" href="'+path+'/questionServlet?q=getQuizDetail&quizId='+dynamic.questionID+'">'+dynamic.questionTitle+'</a></h2>' +
                '<div class="item-meta"><img src="'+userImgUrl+'" alt="" width="50" height="50" style="float: left">' +
                '<div style="float: left"><div style="margin-left: 15px;">' +
                '<span class="meta-name">'+userName+'</span>' +
                '</div><div style="margin-left: 15px;">' +
                '<span>'+userContent+'</span></div></div><div style="clear: both"></div>' +
                '</div><div class="item-content">'+(dynamic.type=="2"?dynamic.content:(dynamic.type=="1"?dynamic.introduce:""))+'</div></div></div>');
        }
    }
}
function getReplyLst() {
    $.ajax({
        url:path+"/questionServlet",
        data:{"q":"getMyReply"},
        type:"POST",
        success:function(data){
            generateReplyList(JSON.parse(data));
        },
        error:function(data){

        }
    });
}
function generateReplyList(questionList) {
    if(questionList&&questionList.length>0){
        for(var a in questionList){
            var question = questionList[a];
            var replyList = question.list;
            for(var b in replyList){
                var reply = replyList[b];
                $("#answer").append('<div class="list-item"><h2 class="item-title">' +
                    '<a target="_blank" href="'+path+'/questionServlet?q=getQuizDetail&quizId='+question.questionId+'">'+question.questionTitle+'</a></h2>' +
                    '<div class="item-meta"><img src="'+userImgUrl+'" alt="" width="50" height="50" style="float: left">' +
                    '<div style="float: left"><div style="margin-left: 15px;">' +
                    '<span class="meta-name">'+userName+'</span>' +
                    '</div><div style="margin-left: 15px;">' +
                    '<span>'+userContent+'</span></div></div><div style="clear: both"></div>' +
                    '</div><div class="item-content">'+reply.replyContent+'</div></div></div>');
            }
        }
    }
}
function getQuestionLst() {
    $.ajax({
        url:path+"/questionServlet",
        data:{"q":"getMyQuestion"},
        type:"POST",
        success:function(data){
            generateQuestionList(JSON.parse(data));
        },
        error:function(data){

        }
    });
}
function generateQuestionList(questionList) {
    if(questionList&&questionList.length>0){
        for(var a in questionList){
            var question = questionList[a];
            $("#question").append('<div class="list-item"><h2 class="item-title">' +
                '<a target="_blank" href="'+path+'/questionServlet?q=getQuizDetail&quizId='+question.questionId+'">'+question.questionTitle+'</a></h2>' +
                '<div class="item-meta"><img src="'+userImgUrl+'" alt="" width="50" height="50" style="float: left">' +
                '<div style="float: left"><div style="margin-left: 15px;">' +
                '<span class="meta-name">'+userName+'</span>' +
                '</div><div style="margin-left: 15px;">' +
                '<span>'+userContent+'</span></div></div><div style="clear: both"></div>' +
                '</div><div class="item-content">'+question.questionIntroduce+'</div></div></div>');
        }
    }
}
$(document).ready(function(){
    getReplyLst();
    getQuestionLst();
    getDynamicLst();
    $(".btnwen").click(function() {
        $("#Modal-wrapper").show(); 
    });
    $("#tabs li").click(function () {
        var current = $(this),
            index = current.index();
        current.addClass("active").siblings("li").removeClass("active");
        $(".dongtai div.list-container").eq(index).addClass("selected").siblings("div").removeClass("selected");
    });

    $(".guanbi").click(function() {
        $("#Modal-wrapper").hide(); 
    });
    
    /** 个人信息切换功能 */
    var flag = true;
    //jQuery动画实现
    /*$(".detail").click(function() {
        var _this = $(this);
        if(flag){
            _this.children("span.gong").removeClass('icon-angle-down').addClass('icon-angle-up');
            _this.children('span.view').html("收起详细资料");
            $("#bian").animate({"height": "152px"});
        }else{
            _this.children("span.gong").removeClass('icon-angle-up').addClass('icon-angle-down');
            _this.children('span.view').html("查看详细资料");
            $("#bian").animate({"height": "60px"});
            }
        flag = !flag;
    });*/
    //jQuery滑动实现 
    //方式一
    $(".detail").click(function() {
        var _this = $(this);
        if(flag){
            _this.children("span.gong").removeClass('icon-angle-down').addClass('icon-angle-up');
            _this.children('span.view').html("收起详细资料");
            $("#bian").slideDown();
        }else{
            _this.children("span.gong").removeClass('icon-angle-up').addClass('icon-angle-down');
            _this.children('span.view').html("查看详细资料");
            $("#bian").slideUp();
            }
        flag = !flag;
    });
    //jQuery滑动实现 
    //方式二
    /*$(".detail").click(function() {
        var _this = $(this);
        $("#bian").slideToggle(function(){
            //滑动完成回调函数
            if(flag){
                _this.children("span.gong").removeClass('icon-angle-down').addClass('icon-angle-up');
                _this.children('span.view').html("收起详细资料"); 
            }else{
                _this.children("span.gong").removeClass('icon-angle-up').addClass('icon-angle-down');
                _this.children('span.view').html("查看详细资料");
            }
            flag = !flag;
        });
    });*/
});

