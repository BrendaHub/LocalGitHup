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
    <br>
    	<button id="gouppage">&gt;上一组（5人）</button>
    	<button id="godownpage">下一组（5人）&lt;</button>
    
    <script>
    	//日期
        var MONTHS = ["2016-13","2016-14","2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33","2016-34","2016-35"];
        
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
                labels: ["2016-13","2016-14","2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33","2016-34","2016-35"],
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
	                        min: "2016/5/17",
	                        max: "2016/9/17"
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
                            suggestedMax: 15,
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
        
        $("#gouppage").click(function(){//上
        	if(parseInt($("#pageNo").val()) == 1){
        		alert("已是第一组了");
        		return false;
        	}
        	$.ajax({
        		type: "get",
        		cache:false,
        		url: "/HZXX/toWeekfenxi/5",
        		data: {
        			pageNo:	parseInt($("#pageNo").val()) - 1 ,
        			pageSize:5
        		},
        		success: function (data) {
        			if(data) {
        				$("#pageNo").val(data.pageNo);
        				var hzname0 = "";
        				var itemvalue0 = new Array();
        				var hzname1 = "";
        				var itemvalue1 = new Array();
        				var hzname2 = "";
        				var itemvalue2 = new Array();
        				var hzname3 = "";
        				var itemvalue3 = new Array();
        				var hzname4 = "";
        				var itemvalue4 = new Array();
        				var dataarray = data.result;
        				for(var i = 0;i < dataarray.length; i++) {
        					var _dataarray = dataarray[i].data;
        					if(i == 0){
        						hzname0 = dataarray[i].hzname;
        					}
        					if(i == 1){
        						hzname1 = dataarray[i].hzname;
        					}
        					if(i == 2){
        						hzname2 = dataarray[i].hzname;
        					}
        					if(i == 3){
        						hzname3 = dataarray[i].hzname;
        					}
        					if(i == 4){
        						hzname4 = dataarray[i].hzname;
        					}
        					for(var j = 0 ; j < _dataarray.length; j++){
        						console.log(_dataarray[j].hzname);
        						if(i == 0 ){
        							itemvalue0.push(_dataarray[j].itemvalue);
        						}if(i == 1 ){
        							itemvalue1.push(_dataarray[j].itemvalue);
        						}if(i == 2 ){
        							itemvalue2.push(_dataarray[j].itemvalue);
        						}if(i == 3 ){
        							itemvalue3.push(_dataarray[j].itemvalue);
        						}if(i == 4 ){
        							itemvalue4.push(_dataarray[j].itemvalue);
        						}
        					}
       					}
        				
        				config.data = {
        						labels: ["2016-13","2016-14","2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33","2016-34","2016-35"],
        		                datasets: [{
        		                    label: hzname0,
        		                    data: itemvalue0,
        		                    fill: false,
        		                },{
        		                    label: hzname1,
        		                    data: itemvalue1,
        		                    fill: false,
        		                },{
        		                    label: hzname2,
        		                    data: itemvalue2,
        		                    fill: false,
        		                },{
        		                    label: hzname3,
        		                    data: itemvalue3,
        		                    fill: false,
        		                },{
        		                    label: hzname4,
        		                    data: itemvalue4,
        		                    fill: false,
        		                },]
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
        			}
        		}
        	});
        });
        $("#godownpage").click(function(){//下
        	if(parseInt($("#pageNo").val()) == 10){
        		alert("已是最后一组了");
        		return false;
        	}
        	$.ajax({
        		type: "get",
        		cache:false,
        		url: "/HZXX/toWeekfenxi/5",
        		data: {
        			pageNo:	parseInt($("#pageNo").val()) + 1 ,
        			pageSize:5
        		},
        		success: function (data) {
        			if(data) {
        				$("#pageNo").val(data.pageNo);
        				var hzname0 = "";
        				var itemvalue0 = new Array();
        				var hzname1 = "";
        				var itemvalue1 = new Array();
        				var hzname2 = "";
        				var itemvalue2 = new Array();
        				var hzname3 = "";
        				var itemvalue3 = new Array();
        				var hzname4 = "";
        				var itemvalue4 = new Array();
        				var dataarray = data.result;
        				for(var i = 0;i < dataarray.length; i++) {
        					var _dataarray = dataarray[i].data;
        					if(i == 0){
        						hzname0 = dataarray[i].hzname;
        					}
        					if(i == 1){
        						hzname1 = dataarray[i].hzname;
        					}
        					if(i == 2){
        						hzname2 = dataarray[i].hzname;
        					}
        					if(i == 3){
        						hzname3 = dataarray[i].hzname;
        					}
        					if(i == 4){
        						hzname4 = dataarray[i].hzname;
        					}
        					for(var j = 0 ; j < _dataarray.length; j++){
        						console.log(_dataarray[j].hzname);
        						if(i == 0 ){
        							itemvalue0.push(_dataarray[j].itemvalue);
        						}if(i == 1 ){
        							itemvalue1.push(_dataarray[j].itemvalue);
        						}if(i == 2 ){
        							itemvalue2.push(_dataarray[j].itemvalue);
        						}if(i == 3 ){
        							itemvalue3.push(_dataarray[j].itemvalue);
        						}if(i == 4 ){
        							itemvalue4.push(_dataarray[j].itemvalue);
        						}
        					}
       					}
        				
        				config.data = {
        						labels: ["2016-13","2016-14","2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33","2016-34","2016-35"],
        		                datasets: [{
        		                    label: hzname0,
        		                    data: itemvalue0,
        		                    fill: false,
        		                },{
        		                    label: hzname1,
        		                    data: itemvalue1,
        		                    fill: false,
        		                },{
        		                    label: hzname2,
        		                    data: itemvalue2,
        		                    fill: false,
        		                },{
        		                    label: hzname3,
        		                    data: itemvalue3,
        		                    fill: false,
        		                },{
        		                    label: hzname4,
        		                    data: itemvalue4,
        		                    fill: false,
        		                },]
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
        			}
        		}
        	});
        });
        
        $(document).ready(function(){
            	$.ajax({
            		type: "get",
            		cache:false,
            		url: "/HZXX/toWeekfenxi/5",
            		data: {
            		},
            		success: function (data) {
            			if(data) {
            				$("#pageNo").val(data.pageNo);
            				var hzname0 = "";
            				var itemvalue0 = new Array();
            				var hzname1 = "";
            				var itemvalue1 = new Array();
            				var hzname2 = "";
            				var itemvalue2 = new Array();
            				var hzname3 = "";
            				var itemvalue3 = new Array();
            				var hzname4 = "";
            				var itemvalue4 = new Array();
            				var dataarray = data.result;
            				for(var i = 0;i < dataarray.length; i++) {
            					var _dataarray = dataarray[i].data;
            					if(i == 0){
            						hzname0 = dataarray[i].hzname;
            					}
            					if(i == 1){
            						hzname1 = dataarray[i].hzname;
            					}
            					if(i == 2){
            						hzname2 = dataarray[i].hzname;
            					}
            					if(i == 3){
            						hzname3 = dataarray[i].hzname;
            					}
            					if(i == 4){
            						hzname4 = dataarray[i].hzname;
            					}
            					for(var j = 0 ; j < _dataarray.length; j++){
            						console.log(_dataarray[j].hzname);
            						if(i == 0 ){
            							itemvalue0.push(_dataarray[j].itemvalue);
            						}if(i == 1 ){
            							itemvalue1.push(_dataarray[j].itemvalue);
            						}if(i == 2 ){
            							itemvalue2.push(_dataarray[j].itemvalue);
            						}if(i == 3 ){
            							itemvalue3.push(_dataarray[j].itemvalue);
            						}if(i == 4 ){
            							itemvalue4.push(_dataarray[j].itemvalue);
            						}
            					}
           					}
            				
            				config.data = {
            						labels: ["2016-13","2016-14","2016-15","2016-16","2016-17","2016-18","2016-19","2016-20","2016-21","2016-22","2016-23","2016-24","2016-25","2016-26","2016-27","2016-28","2016-29","2016-30","2016-31","2016-32","2016-33","2016-34","2016-35"],
            		                datasets: [{
            		                    label: hzname0,
            		                    data: itemvalue0,
            		                    fill: false,
            		                },{
            		                    label: hzname1,
            		                    data: itemvalue1,
            		                    fill: false,
            		                },{
            		                    label: hzname2,
            		                    data: itemvalue2,
            		                    fill: false,
            		                },{
            		                    label: hzname3,
            		                    data: itemvalue3,
            		                    fill: false,
            		                },{
            		                    label: hzname4,
            		                    data: itemvalue4,
            		                    fill: false,
            		                },]
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
            			}
            		}
            	});
            });
    </script>
</body>

</html>
