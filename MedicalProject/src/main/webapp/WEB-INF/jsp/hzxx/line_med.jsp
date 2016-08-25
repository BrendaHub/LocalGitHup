<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>

<head>
    <title>Line Chart</title>
    <script src="/dist/Chart.bundle.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <style>
    canvas{
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
</head>

<body><!-- type="hidden" pageCount:${pageCount }|pageSize:${pageSize }|pageIndex:${pageIndex }|pageTotal:${pageTotal }  -->
	<input type="hidden"  name="pageNo" id="pageNo" value=""/>
    <div style="width:90%;">
        <canvas id="canvas"></canvas>
    </div>
    <br>
    
    <script>
    	//日期
        var MONTHS = ["2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33"];
        
        var randomScalingFactor = function() {
            return Math.round(Math.random() * 100);
            //return 0;
        };
        var randomColorFactor = function() {
            return Math.round(Math.random() * 255);
        };
        var randomColor = function(opacity) {
            return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
        };

        var config = {
            type: 'line',
            data: {
                labels: ["2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33"],
                datasets: [
                   
                ]
            },
            options: { 
            	scaleFontSize : 72,
            	scaleShowGridLines : false,
                scaleSteps : 15,
                responsive: true,
                title:{
                    display:true,
                    text:'糖宝随访，血糖随访数据走势分析'
                },
                tooltips: {
                    mode: 'label',
                    callbacks: {
                        // beforeTitle: function() {
                        //     return '...beforeTitle';
                        // },
                        // afterTitle: function() {
                        //     return '...afterTitle';
                        // },
                        // beforeBody: function() {
                        //     return '...beforeBody';
                        // },
                        // afterBody: function() {
                        //     return '...afterBody';
                        // },
                        // beforeFooter: function() {
                        //     return '...beforeFooter';
                        // },
                        // footer: function() {
                        //     return 'Footer';
                        // },
                        // afterFooter: function() {
                        //     return '...afterFooter';
                        // },
                    }
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            show: true,
                            labelString: 'Month'
                        },
	                    ticks: {
	                    }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            show: true,
                            labelString: 'Value'
                        },
                        ticks: {
                            suggestedMin: 3,
                            suggestedMax: 11,
                            //max: 3,
                            //min: 15,
                            //stepSize: 3
                        }
                    }]
                }
            }
        };
        
      	

        $.each(config.data.datasets, function(i, dataset) {
            dataset.borderColor = randomColor(0.4);
            dataset.backgroundColor = randomColor(0.5);
            dataset.pointBorderColor = randomColor(0.7);
            dataset.pointBackgroundColor = randomColor(0.5);
            dataset.pointBorderWidth = 1;
        });

        window.onload = function() {
            var ctx = document.getElementById("canvas").getContext("2d");
            window.myLine = new Chart(ctx, config);
        };
        
       
        $(document).ready(function(){
     				config.data = {
     						labels: ["2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33"],
     		                datasets: [{
     		                    label: "最大值MAX",
     		                    data: [11.0,12.2,8.5 ,10.1,9.4 ,10.7,14.0,9.2 ,10.4,10.7,10.0,11.1,10.3,12.4,12.2,10.9,10.3,10.5,11.3],
     		                    fill: false,
     		                },{
     		                    label: "中位值",
     		                    data: [9.6,8.7,7.1,7.2,7.8,7.2,7.1,7.0,7.6,7.2,7.4,7.2,7.5,7.5,7.1,6.9,7.5,7.2,6.9],
     		                    fill: false,
     		                },{
     		                    label: "最小值MIN",
     		                    data: [6.6,6.4,6.3,6.0,5.8,5.5,4.5,5.3,5.1,4.8,5.1,4.6,5.5,5.0,5.4,5.5,5.3,5.1,5.1],
     		                    fill: false,
     		                }]
     		            };

     		            $.each(config.data.datasets, function(i, dataset) {
     		                dataset.borderColor = randomColor(0.4);
     		                dataset.backgroundColor = randomColor(0.5);
     		                dataset.pointBorderColor = randomColor(0.7);
     		                dataset.pointBackgroundColor = randomColor(0.5);
     		                dataset.pointBorderWidth = 1;
     		            });

     		            // Update the chart
     		            window.myLine.update();
            });
    </script>
</body>

</html>
