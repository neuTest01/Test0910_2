//头像上传方法
function upload() {
    var formData = new FormData();
    formData.append("file",$("#file")[0].files[0]);

    $.ajax({
        url:path+"/servlet/fileUploadServlet",
        contentType:false,
        type:"POST",
        data:formData,
        processData: false,
        success:function (data) {
            console.log(data);
            var returnData = JSON.parse(data);
            $("#ava").attr("src",returnData.data);
            $(".userinfo img").attr("src",returnData.data);
        }
    });
}
function save() {
    var savaData  = {
        userAddress: $("#province").val()+","+$("#city").val(),
        userGender:$("input[type=radio]:checked").val(),
        userIndustry:$("#job").val(),
        userEmail:$("#email").val(),
        userEducation:$("#education").val(),
        userBirthday:$("#birthday").val(),
        userPhone:$("#phone").val(),
        userIntroduce:$("#introduce").val(),
        topicList:$(".topic-item:checked").map(function (){ return $(this).attr("id");}).get().join(",")
    }
    $.ajax({
        url:path+"/modifyUserInfoServlet",
        type:"post",
        data:savaData,
        success:function (data) {
            if(data.indexOf("login")!=-1){
                window.location.href=path+"/page/login.jsp";
            }else{
                alert(data);
            }
        },
        error:function (data) {
            if(data.indexOf("login")!=-1){
                window.location.href=path+"/page/login.jsp";
            }
        }
    });
}
//获取所有话题
function getTopicList(){
    $.ajax({
        url:path+"/questionServlet?q=getAllTopic",
        datatype:"JSON",
        type:"POST",
        success:function(data){
            if(data){
                $("#topicContainer").empty();
                var topicList = JSON.parse(data);
                for (var a in topicList){
                    var topic = topicList[a];
                    $("#topicContainer").append('<div class="topic"><label for="'+topic.topicId+'">'+topic.topicName+'</label><input type="checkbox" id="'+topic.topicId+'" class="topic-item" disabled/></div>');
                }
                $("#topicContainer").append('<div style="clear: both"></div>');
                getAttentionList();
            }
        },
        error:function(data){

        }
    });
}
//获取已经关注话题分类列表
function getAttentionList(){
    $.ajax({
        url:path+"/questionServlet?q=getTopicList",
        datatype:"JSON",
        type:"POST",
        success:function(data){
            if(data){
                var aList = JSON.parse(data);
                for (var a in aList){
                    $("#"+aList[a].topicId).prop("checked","checked");
                }
            }
        },
        error:function(data){

        }
    });
}
$(document).ready(function(){

    getTopicList();
    //居住地赋值
    var province = address.split(",")[0];
    var city = address.split(",")[1];
    $("#province").val(province);
    $("#province").change();
    $("#city").val(city);

    $(".btnwen").click(function() {
        $("#Modal-wrapper").show(); 
    });
    $(".guanbi").click(function() {
        $("#Modal-wrapper").hide(); 
    });
    //修改按钮点击事件
    $(".modify").click(function(){
        $(".fildtext").removeAttr("readonly");
        $(".fildtext").addClass("add-border");
        $("input[name='sex']").removeAttr('disabled');
        $("input[type='checkbox']").removeAttr('disabled');
        $(".select").removeAttr('disabled');
        $("#save").show();
    });
    //保存按钮点击事件
    $("#save").click(function(){
        if(!$("#city").val()){
            alert("请选择所在地");
            $("#city").focus();
            return;
        }
        if(!$("#birthday").val()){
            alert("请输入出生日期");
            $("#birthday").focus();
            return;
        }
        if(!$("#job").val()){
            alert("请输入所在行业");
            $("#birthday").focus();
            return;
        }
        if(!$("#email").val()){
            alert("请输入邮箱");
            $("#email").focus();
            return;
        }
        if(!$("#phone").val()){
            alert("请输入电话");
            $("#phone").focus();
            return;
        }
        if(!$("#education").val()){
            alert("请输入教育经历");
            $("#education").focus();
            return;
        }
        if(!$("#introduce").val()){
            alert("请输入个人简介");
            $("#introduce").focus();
            return;
        }
        //邮箱正则表达式校验
        var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(!emailReg.test($("#email").val())){
            alert("邮箱格式不正确");
            return;
        }
        //手机正则表达式校验
        var phoneReg = /^1[34578]\d{9}$/;
        if(!phoneReg.test($("#phone").val())){
            alert("手机格式不正确");
            return;
        }

        save();

        $(".fildtext").attr("readonly","readonly");
        $(".fildtext").removeClass("add-border");
        $("input[name='sex']").attr('disabled',"disabled");
        $("input[type='checkbox']").attr('disabled',"disabled");
        $(".select").attr('disabled',"disabled");
        $("#save").hide();
    });
});