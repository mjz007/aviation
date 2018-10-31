<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>航班数据可视化</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<link href="css/select.css" rel="stylesheet" type="text/css">
	<script src = "js/echarts.js"></script>
	<script src="js/world.js"></script> 
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/page.js"></script> 
	<script type="text/javascript">
		var  i = ${fn:length(fight)};
		var count = parseInt(i/10);
		var zon = count*10;
		var last;
		var page = 1;
		if(i%10!=0)
		{
			count++;
			last=i-zon;
		}
		var i = 0;
		var size = 10;
	</script>
  </head>
  
 <body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
  <a class="navbar-brand" href="index">主页</a>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Link</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link</a>
    </li>
  </ul>
</nav>
<div class="home">&nbsp;</div>
 <div id="main" class="main"> 
 <script> 
 var ojbk = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
 

 var  myChart = echarts.init(document.getElementById('main')); 
 var z = 1;
 var yx = 55.97;
 var yy=29.71;
var allData = {
    "citys": [<c:forEach var="row" items="${cities}">
					    {
					        "name": "${row.city}",
					        "value": ["${row.lon}", "${row.lat}"],
					        "symbolSize": 6,
					        "itemStyle": {
					            "normal": {
					                "color": "#F58158",
					            }
					        }
					    },
				    </c:forEach>],
    		 "moveLines": [

	 					<c:forEach var="line" items="${lines}">
				    {
				        "fromName": "${line[0]['chufa'].city}",
				        "toName": "${line[1]['daoda'].city}",
				        "coords": [
				            [${line[0]['chufa'].lon}, ${line[0]['chufa'].lat}],
				            [${line[1]['daoda'].lon},${line[1]['daoda'].lat}]
						        ]
				    },
				    </c:forEach>

    ],

};
 myChart.on('click', function (scatter) {
    var city = scatter.data.value;
   alert(city);
});
option = {
    backgroundColor: '#404a59',
    title: {
        text: '全球航班数据',
        left: 'center',
        textStyle: {
            color: '#fff'
        }
    },

      legend: {
        orient: 'vertical',
        y: 'bottom',
        x:'right',
        data:['显示所有城市','显示所有航线'],
        textStyle: {
            color: '#fff'
        }
    },
               grid:{
							top:'80%',
							left: '4%',
					        right: '10%',
					        bottom: '2%',
							//间距
					        containLabel: true
							},
						 xAxis: {
					        type: 'value',
					        boundaryGap: [0, 0.01],
							position: 'top',
					        min: 0,
					        max: ${fn:length(fight)}+10,
					        interval: count,
					        axisLabel: {
					            formatter: '{value}',
					            textStyle: {
					                color: '#fff',  
					                fontWeight: '50'
					            }
					        }
					    },
					    yAxis: {
					        type: 'category',
					        data: ['航司数量','航线总数'],
							//X值
					        axisLabel: {
					            show: true,
					            interval: 0,
					            rotate: 0,
					            margin: 10,
					            inside: false,
					            textStyle: {
					                color: '#fff',
					                fontWeight: '0'
					            }
					        }
					    },
	toolbox: {  
        show : true,  
		orient: 'horizontal',    
		x: 'right',     
		zlevel:2,
		itemSize:50,		
        feature : {  
			myTool : {  
					show : true,  
					title : '放大', 
					icon : 'image://images/33.png',  
					onclick : function (){ 
						z=z+1;
						myChart.setOption(option,option.geo.zoom=z); 
					} ,
				 },  
				 
				 myTool2 : {  
					show : true,  
					title : '缩小',  
					
					icon : 'image://images/22.png',  
					onclick : function (){
						if(z>1){
						z=z-1;}
							if(z>1)
						myChart.setOption(option,option.geo.zoom=z); 
						if(z<=1&&z>0)
						myChart.setOption(option,option.geo.zoom=1); 
					} ,
				 },  
				 
				 myTool3 : {  
					show : true,  
					title : '上移',  
					icon : 'image://images/shang.png',  
					onclick : function (){  
						yy=yy+10;
						myChart.setOption(option,option.geo.center=[yx,yy]); 
						alert(option.geo.zoom);
					} ,
				 },  
				 
				 myTool4: {  
					show : true,  
					title : '下移',  
					
					icon : 'image://images/xia.png',  
					onclick : function (){  
						yy=yy-10;
						myChart.setOption(option,option.geo.center=[yx,yy]); 
					} ,
				 },  
				 myTool5: {  
					show : true,  
					title : '左移',  
					
					icon : 'image://images/zuo.png',  
					onclick : function (){  
						yx=yx-10;
						myChart.setOption(option,option.geo.center=[yx,yy]); 
					} ,
				 },  
				 myTool6: {  
					show : true,  
					title : '右移',  
					
					icon : 'image://images/you.png',  
					onclick : function (){  
						yx=yx+10;
						myChart.setOption(option,option.geo.zoom=z,option.geo.center=[yx,yy]); 
					} ,
				 },  
        }  
    },  
    geo: {
		Id:'daniu',
        map: 'world',
        label: {
            emphasis: {
                show: false
            }
        },
        roam: true,
		zoom:z,
		center:[yx, yy],
        itemStyle: {
            normal: {
                areaColor: '#323c48',
                borderColor: '#404a59'
            },
            emphasis: {
                areaColor: '#2a333d'
            }
        }
    },
    series: [{
        name: '显示所有城市',
        type: 'scatter',
        coordinateSystem: 'geo',
        zlevel: 5,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            emphasis: {
                show: true,
                position: 'right',
                formatter: '{b}'
            }
        },
        symbolSize: 2,
        showEffectOn: 'render',
        itemStyle: {
            normal: {
                color: '#46bee9'
            }
        },
        data: allData.citys
    }, {
        name: '显示所有航线',
        type: 'lines',
        coordinateSystem: 'geo',
        zlevel: 2,
        large: true,
        effect: {
         show: true,
	     period: 6,
	     trailLength: 0,
	     symbol: ojbk,
	     symbolSize: 15
        },
        lineStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: '#58B3CC'
                }, {
                    offset: 1,
                    color: '#F58158'
                }], false),
                width: 1,
                opacity: 0.5,
                curveness: 0.1
            }
        },
        data: allData.moveLines
    },
                        				    {
							        type: 'bar',
							        label: {
							            normal: {
							                show: true,
							                color: '#fff'
							            }
							        },
									color:'#009933',	
							        data: [${aviationNum},${fn:length(fight)}]
							    }
    ]
};
 myChart.setOption(option); 
 window.onload=function(){
myChart.on('georoam', function (pramas){
var  zoom=pramas.zoom;
if(zoom>1)
 z=z*1.1;
 else
 z=z*0.9;
});
};
 </script></div><hr>
<span class="font"><h3>航线详情：</h3><br>
	<div class="container">         
  <table id="scroll_bar" class="table table-striped">
    <thead>
      <tr id="bar_head">
        <th><font class="font">航班</font></th>
        <th><font class="font">机型</font></th>
        <th><font class="font">出发地</font></th>
        <th><font class="font">目的地</font></th>
        <th><font class="font">飞行日期</font></th>
        <th><font class="font">起飞时间</font></th>
        <th><font class="font">到达时间</font></th>
        <th><font class="font">飞行时间</font></th>
      </tr>
    </thead>
    <tbody id = "mytbody">
    <c:forEach var="f" items="${fight}" >
      <tr>
        <td><font class="font">${f.fightNum}</font></td>
        <td><font class="font">${f.fightmodel}</font></td>
        <td><font class="font">${f.chufadi}</font></td>
	    <td><font class="font">${f.mudi}</font></td>
        <td><font class="font">${f.fightDate}</font></td>
        <td><font class="font">${f.actFly}</font></td>
        <td><font class="font">${f.actLand}</font></td>
        <td><font class="font">${f.cha}</font></td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>
</span>
	<table style="margin: 0px auto;" >
		<tr>
		<td class="td2">
		   <span id = "myspan" > 
		           <a href="javascript:void(0)" onclick="shou()" id = "sou" style="color:#FFF">[首页]</a>&nbsp;&nbsp;
		           <a href="javascript:void(0)" onclick="shang()" style="color:#FFF">[上一页]</a>&nbsp;&nbsp;
		           <a href="javascript:void(0)" onclick = "xia()" style="color:#FFF">[下一页]</a>&nbsp;&nbsp;
		            <a href="javascript:void(0)" onclick="wei()" id="wei" style="color:#FFF">[尾页]</a>&nbsp;&nbsp;
		   </span>
		</td>
		</tr>
	</table>
 </body>
</html>
