function getAllTopic(){
    $.ajax({
        url:path+"/questionServlet?q=getAllTopic",
        datatype:"JSON",
        type:"POST",
        success:function(data){
            if(data){
                var topicList = JSON.parse(data);
                for (var a in topicList){
                    var topic = topicList[a];
                    $("<option></option>").val(topic.topicId).text(topic.topicName).appendTo("#questionTopic");
                }

            }
        },
        error:function(data){

        }
    });
}
$(document).ready(function(){
    getAllTopic();
   $(".tijiaobtn").on("click",function () {
       var question = {
           q:"askQuestion",
           questionIntroduce:$("#questionIntroduce").val(),
           questionType:"1",
           questionTitle:$("#questionTitle").val(),
           questionTopic:$("#questionTopic").val()
       }
       $.ajax({
           url:path+"/questionServlet",
           type:"post",
           data:question,
           success:function (data) {
               if(data.indexOf("login")!=-1){
                   window.location.href=path+"/page/login.jsp";
               }else{
                   alert(data);
                   $("#questionIntroduce").val("");
                   $("#questionTitle").val("");
                   $("#questionTopic").val("");
                   $("#Modal-wrapper").hide();
               }
           },
           error:function (data) {
               alert(data);
           }
       });
   })
});