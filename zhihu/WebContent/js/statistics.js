$(document).ready(function () {
    var chart  = echarts.init(document.getElementById("canvasContainer"));
    var x = [];
    var y = [];
    var option = {
        title: {
            text: '登录统计'
        },
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : x,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'登录次数',
                type:'bar',
                barWidth: '30%',
                data:y
            }
        ]
    };
    function getData() {
        $.ajax({
            url:path+"/statisticsServlet",
            type:"POST",
            success:function (data) {
                console.log(data);
                var list = JSON.parse(data);
                if(list){
                    for (var a in list){
                        x.push(list[a].loginAddress);
                        y.push(list[a].count);
                    }
                    chart.setOption(option);
                }
            },
            error:function (data) {
                
            }
        });
    }
    getData();
    $(".btnwen").click(function() {
        $("#Modal-wrapper").show();
    });
    $(".guanbi").click(function() {
        $("#Modal-wrapper").hide();
    });
});
