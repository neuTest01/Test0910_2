var times = 500;
function updatePassword(){
	$("#cover").fadeIn(times,function(){
		$(".model-container").fadeIn(100);
	});
    $("#account").val("");
    $("#oldPassword").val("");
    $("#newPassword").val("");
    $("#confirmPassword").val("");
}
function hide(){
    $("#cover").fadeOut(times);
    $(".model-container").fadeOut(times);
}
window.addEventListener("keydown",function(e){
    if(e.keyCode===27){
      hide();
    }
});
function login(){
	
	if(!$("#username").val()){
		alert("请输入用户名");
	    return;
	}
	if(!$("#password").val()){
		alert("请输入密码");
	    return;
	}
	if(!$("#captcha").val()){
		alert("请输入验证码");
	    return;
	}
	if($("#password").val().length<6){
		alert("密码最小长度应大于6位");
	    return;
	}
	if($("#password").val().length>128){
		alert("密码最大长度应小于128位");
	    return;
	}
}
function check(){

	
	var password = $("#password").val();
	var confirmpassword =$("#confirmpassword").val();
	var registerData = {
			account:$("#username").val(),
			password:$("#password").val(),
			userName:$("#captcha").val()
		};
	if(!$("#username").val()){
		alert("请输入用户名");
		return;
	}
	if(!password){
		alert("请输入密码");
		return;
	}
	if(!$("#name").val()){
		alert("请输入姓名");
		return;
	}
	if(password.length<6){
	    alert("密码最小长度应大于6位");
	    return;
	}
	if(password.length>128){
	    alert("密码最大长度应小于128位");
	    return;
	}
	if(password!=confirmpassword){
	    alert("确认密码与新密码必须一致");
	    return;
	}
	$.ajax({
		url:"registerServlet",
		type:"POST",
		data:registerData,
		success:function(data){
			if(data==1){
				window.location.href="page/login.jsp";
			}else{
				alert();
			}
		},
		error:function(data){
			console.log(data);
		}
	});
    
}
function doUpdatePassword(){
  var account = $("#account").val();
  var oldPassword = $("#oldPassword").val();
  var newPassword = $("#newPassword").val();
  var confirmPassword = $("#confirmPassword").val();

  if(!account){
    alert("请输入账号");
    return;
  }
  if(!$("#oldPassword").val()){
    alert("请输入原始密码");
    return;
  }
  if(!newPassword){
    alert("请输入新密码");
    return;
  }
  if(!$("#confirmPassword").val()){
    alert("请输入确认密码");
    return;
  }
  if(newPassword!=confirmPassword){
    alert("确认密码应与新密码一致");
    return;
  }
  if(newPassword.length<6){
    alert("密码最小长度应大于6位");
    return;
  }
  if(newPassword.length>128){
    alert("密码最大长度应小于128位");
    return;
  }
  $.ajax({
		url:path+"/modifyPasswordServlet",
		type:"POST",
		data:{
			account:account,
			oldPassword:oldPassword,
			newPassword:newPassword
		},
		datatype:"json",
		success:function(data){
			
			switch(data){
				case "0":hide();alert("修改成功");break;
				case "1":alert("原密码错误");break;
				case "2":alert("用户不存在");break;
				default:break;
			}
		},
		error:function(data){
			console.log("error");
			console.log(data);
		}
	});
}
$(document).ready(function(){
    init();
    function init(){
      particlesJS('particles',
      
      {
        "particles": {
          "number": {
            "value": 5,
            "density": {
              "enable": true,
              "value_area": 100
            }
          },
          "color": {
            "value": "#e7f1f8"
          },
          "shape": {
            "type": "circle",
            "stroke": {
              "width": 0,
              "color": "#000000"
            },
            "polygon": {
              "nb_sides": 5
            },
            "image": {
              "src": "img/github.svg",
              "width": 100,
              "height": 100
            }
          },
          "opacity": {
            "value": 0.5,
            "random": true,
            "anim": {
              "enable": true,
              "speed": 1,
              "opacity_min": 0.1,
              "sync": false
            }
          },
          "size": {
            "value": 10,
            "random": true,
            "anim": {
              "enable": false,
              "speed": 40,
              "size_min": 0.1,
              "sync": false
            }
          },
          "line_linked": {
            "enable": true,
            "distance": 150,
            "color": "#ffffff",
            "opacity": 0.4,
            "width": 1
          },
          "move": {
            "enable": true,
            "speed": 3,
            "direction": "none",
            "random": false,
            "straight": false,
            "out_mode": "out",
            "attract": {
              "enable": false,
              "rotateX": 600,
              "rotateY": 1200
            }
          }
        },
        "interactivity": {
          "detect_on": "canvas",
          "events": {
            "onhover": {
              "enable": false,
              "mode": "repulse"
            },
            "onclick": {
              "enable": false,
              "mode": "push"
            },
            "resize": true
          },
          "modes": {
            "grab": {
              "distance": 400,
              "line_linked": {
                "opacity": 1
              }
            },
            "bubble": {
              "distance": 400,
              "size": 40,
              "duration": 2,
              "opacity": 8,
              "speed": 3
            },
            "repulse": {
              "distance": 200
            },
            "push": {
              "particles_nb": 4
            },
            "remove": {
              "particles_nb": 2
            }
          }
        },
        "retina_detect": true,
        "config_demo": {
          "hide_card": false,
          "background_color": "#b61924",
          "background_image": "",
          "background_position": "50% 50%",
          "background_repeat": "no-repeat",
          "background_size": "cover"
        }
      });
    }
});