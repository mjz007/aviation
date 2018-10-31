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
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/themeDark.css" rel="stylesheet" type="text/css">
<!--颜色   -->
<link href="css/load.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/echarts.js"></script>
<script src="js/world.js"></script>
<script src="js/layui/layui.js"></script>
<script src="js/selectType.js"></script>
<script src="js/ajax.js"></script>
</head>
<body>
		<div class="header" style="width:100%;height:100%;">
			<div id="main" style="width: 100%;height:100%;">
				<div id="map" style="width:100%;height:100%;">
					<script> 
						var myChart = echarts.init(document.getElementById('main'));
						var okj = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
						//缩放zoom值初始化
						var z = 1;
						//中心点位置
						var yx = 10;
		 				var yy=20;
						//用于判断两次点击的值
						var v = 0;
						//创建两个变量用与放置两个地点的值
						var one=null;
						var tow=null;
						var begcity=null;
						var endcity=null;
						var kai = 0;
						var jie = 1500;
						var allData = {
						    "citys": [
							    <c:forEach var="row" items="${cities}">
						    {
							        "name": "${row.city}",
							        "value": ["${row.lon}", "${row.lat}"],
							        "symbolSize": 8,
							        "itemStyle": {
							            "normal": {
							                "color": "#F58158",
							            }
							        }
							    },
						    </c:forEach>
						    ],
						   
						};
						 myChart.on('click', function (effectScatter) {
									//点击连线处理
									if(v==0){
								 		var o = effectScatter.data.value;
										if(o==undefined){
											alert("不能选择重复地点");
											v=0;
										}else{
											/* alert("你的出发城市是"+effectScatter.data.name); */
											begcity=effectScatter.data.name;
											one=o;
											v++;
										}
									}else{
										var t = effectScatter.data.value;
										if(t!=undefined&&t!==one){
											tow=t;
											/* alert("你目的城市是"+effectScatter.data.name); */
											endcity=effectScatter.data.name;
												if(confirm('您已选择两地,是否跳转显示详情?')) 
													  { 
													  	window.location.href="fightNum?begincity="+begcity+"&endcity="+endcity+"&date="+"&date1=";
													    return true; 
													  }else{
													  		begcity=null;
													  		endcity=null;
													  }
											
										}else{
											alert("请点击正确的地点位置");
										}
								}
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
							      width:900,
							        orient: 'vertical',
							        y: 'bottom',
							        x:'right',
							        data:['繁忙航班','显示所有城市','显示所有航线'],
							        textStyle: {
							            color: '#fff',
							            fontSize:20
							        }
							    },
							     tooltip:{
							    	trigger:'item',		
							    	formatter:function(params,ticket){
							    		console.dir(params);
							    	if(params.seriesType=="scatter"){
							    	return "位置: "+params.data.name;
							    	}else if(params.seriesType=="lines"){ 				    	
							    		return  "航班号："+"${fight.get(0).fightNum}"+"<br/>"+"始发地: "+params.data.fromName+"<br/>"+"目的地: "+params.data.toName;
							    		}
							    		},
							    		confine:true,
							    		textStyle:{
							    		fontSize:9
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
								        max: ${fn:length(lines)},
								        interval: 1000,
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
								        data: ['繁忙航班', '航空公司','中国航班', '航线总数'],
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
								itemSize:30,		
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
											},
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
												myChart.setOption(option,option.geo.center=[yx,yy]); 
											} ,
										 },  
						        }  
		   					 },  
		    
							    geo: {
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
		            								}
		       										 },
		       							silent:true
		   										 },
							    series: [{
							        name: '显示所有城市',
							        type: 'scatter',
							        coordinateSystem: 'geo',
							        zlevel: 2,
							        silent:false,
		        					large: true,
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
							    },
							     {
							        name: '显示所有航线',
							        type: 'lines',
							        coordinateSystem: 'geo',
							        zlevel: 1,
							        silent:false,
		        					large: true,
							        effect: {
							            show: true,
			            				period: 6,
			            				trailLength: 0,
			           					symbol: okj,
			            				symbolSize: 15
							        },
							        
							        lineStyle: {
							            normal: {
							                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
							                    offset: 0,
							                    color: '#33CC70'
							                }, {
							                    offset: 1,
							                    color: '#33CC70'
							                    
							                }], false),
							                width: 1,
							                opacity: 0.5,
							                curveness: 0.1
							            }
							        },
							        data: [
			 					<c:forEach var="line" items="${lines}">
						    {
						        "fromName": "${line.ocity}",
						        "toName": "${line.dcity}",
						        "coords": [
						            [${line.olon}, ${line.olat}],
						            [${line.dlon},${line.dlat}]
								        ]
						    },
						    </c:forEach>
						    ]
							    },
							{
							        name: '繁忙航班',
							        type: 'lines',
							        coordinateSystem: 'geo',
							        zlevel: 3,
							        silent:false,
		        					large: true,
							        effect: {
							            show: true,
			            				period: 6,
			            				trailLength: 0,
			           					symbol: okj,
			            				symbolSize: 15
							        },
							        lineStyle: {
							            normal: {
							                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
							                    offset: 0,
												color: 'red'
							                }, {
							                    offset: 1,
							                    color: 'red'
							                }], false),
							                width: 1,
							                opacity: 0.5,
							                curveness: 0.1
							            }
							        },
							        data: [
							        <c:forEach var="fan" items="${fanman}">
						    {
						        "fromName": "${fan.ocity}",
						        "toName": "${fan.dcity}",
						        "coords": [
						            [${fan.olon}, ${fan.olat}],
						            [${fan.dlon},${fan.dlat}]
								        ]
						    },
						    </c:forEach>
						    ]
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
							        data: [${fn:length(fanman)}, ${aviationNum}, ${China},${fn:length(lines)}]
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
 		</script>
				</div>
			</div>
		</div>
		<div class="main-panel">
			<div class="main-nav-bar">
				<ul>
					<li data-id="mainNav"><i class="iconfont icon-caidan"></i><span
						class="hover-title">菜单</span></li>
					<li data-id="searchNav"><i class="iconfont icon-chaxun"></i><span
						class="hover-title">航线查询</span></li>
				</ul>
			</div>
			<!-- main nav -->
			<div class="main-nav move-box" id="mainNav">
				<h2 style="color:#fff;">
					航班数据可视化<i class="iconfont icon-left" data-id="mainNav"></i>
				</h2>
				<ul>
					<li data-id="searchNav"><i class="iconfont icon-chaxun"></i><span>航线查询</span></li>
					<li id="tongji"><i class="iconfont icon-tongji"></i><span>航线统计</span></li>
				</ul>
			</div>
			<!--end  main nav -->
			<!--search-->
			<div class="search-box move-box" id="searchNav">
				<div id="searchBox">
					<!--Simple search-->
					<div class="search-simple">
						<h3 class="stitle">搜索</h3>
						<div class="simple-box">
							<form action="fight" method="post">
								<div>
									<input type="text" id="fightNum" name="fightNum" required
										list="num"
										style="background:none;border-bottom:1px solid #0c3167!important;color:#fff;text-align:center"
										placeholder="请输入航班号"> <input type="submit"
										value="查询航班"
										style="border-radius:20px;width:100px;height:30px;line-height: 30px;background: #0b5dd1;margin-left:20px;color:#fff;">
								</div>
							</form>
							<br> <select name="type" id="type" onChange="changeMenu();"
								style="padding-left:13px;margin-bottom:10px;background-color:transparent;border-bottom:1;border-bottom-color:#0a2c5d;border-left:0;border-right:0;border-top:0;width:150px;height30px;font-size:24px;color:#0b5dd1;font-size: 15px;">
								<option selected>搜索城市</option>
								<option>搜索机场</option>
								<option>搜索省份</option>
								<option>搜索地区</option>
								<option>搜索国家</option>
							</select>
							<!--select city-->
							<form action="fightNum" method="post" id="chaxun">
								<div class="select-city select-normal">
									<div class="select-type-md fl " id="select-type-dep">

										<input type="text" name="begincity" id="begincity"  required list="word"
											class="type-item autocomplete-input autocomplete  ac_input active"
											autocomplete="off" id="route_dep" data-id="route_dep"
											placeholder="出发城市">
										<i class="iconfont icon-shanchu" onclick="delValue(this)"
											style="display: inline;"></i>
									</div>
									<i class="iconfont icon-change" id="change_btn"></i>

									<div class="select-type-md fr" id="select-type-arr">
										<input type="text" name="endcity" id="endcity" required list="word"
											class="type-item autocomplete-input active autocomplete  ac_input"
											autocomplete="off" id="route_arr" data-id="route_arr"
											onblur="search_input_blur()" placeholder="到达城市" filter="city"
											macth="3">
										<datalist id="word"> <!-- 获取城市列表 --> <c:forEach
											var="row" items="${result}">
											<option value="${row}" />
										</c:forEach> </datalist>
										<datalist id="num"> <!-- 获取航班号列表 --> <c:forEach
											var="h" items="${hanbanhao}">
											<option value="${h}" />
										</c:forEach> </datalist>
										<!-- 获取国家列表 -->
										<datalist id="cnty"> <c:forEach var="cn"
											items="${cnty }">
											<option value="${cn }">${cn }</option>
										</c:forEach> </datalist>
										<!-- 获取机场列表 -->
										<datalist id="iata"> <c:forEach var="air"
											items="${airports }">
											<option value="${air }">${air }</option>
										</c:forEach> </datalist>
										<i class="iconfont icon-shanchu" onclick="delValue(this)"
											style="display: inline;"></i>
									</div>
									<div class="clear"></div>
								</div>
								<!--end select city-22->
								<!--select date-->
								<div class="select-date  select-normal">
									<div class="select-type-md fl input-append date">
										<input type="text" name="date" id="date" lay-verify="date"
											placeholder="起始时间" autocomplete="off" class="layui-input">
									</div>
									<div class="select-type-md fr input-append date">
										<input type="text" name="date1" id="date1" lay-verify="date"
											placeholder="结束时间" autocomplete="off" class="layui-input">
									</div>
									<div class="clear"></div>
								</div>
								<!--end select date-->
						</div>
					</div>
					<!--end Simple search-->
					<input type="submit" id="searchBtn" class="search-btn" value="查询">
				</div>
				<!--end search-->
			</div>
			<!--Map-->
			<div class="map-contenter" id="map_container"
				_echarts_instance_="ec_1525180960030"
				style="-webkit-tap-highlight-color: transparent; user-select: none; background: transparent; overflow: hidden;"></div>
			<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
		</div>
		<script src="./js/app.js"></script>
		<script>
			layui.use([ 'form', 'laydate' ], function() {
				var form = layui.form,
					laydate = layui.laydate;
				//日期
				laydate.render({
					elem : '#date'
				});
				laydate.render({
					elem : '#date1'
				});
			});
		</script>
		<script type="text/javascript">
			$(function() {
				$("#tongji").click(function() {
					$("#tong").removeClass("search-box move-box");
					$("#tong").addClass("search-box move-box move-left");
					$("#mainNav").removeClass("main-nav move-box move-left");
					$("#mainNav").addClass("main-nav move-box");
		
				});
			});
		</script>
		</form>
		<div id="tong" class="search-box move-box">
			<div id="searchBox">
				<!--Simple search-->
				<div class="search-simple">
					<h3 class="stitle">航班统计</h3>
					<div class="simple-box">
								
						<form action="all" method="post">
							<div style="margin-top:20px;margin-left:30px;">
								<select id="cnty" name="cnty"
									style="background:none;border-bottom:1px solid #0c3167!important;color:#fff;text-align:center;width:120px;"  onchange="xuanzhong(this.options[this.selectedIndex].value)">
									<option value="">--请选择国家--</option>
									<c:forEach var="cn" items="${cnty }">
										<option value="${cn }" >${cn }</option>
									</c:forEach>
								</select>
								<select id="region" name="region"   style="background:none;border-bottom:1px solid #0c3167!important;color:#fff;text-align:center;width:120px;display: none"  onchange="xuanRegion(this.options[this.selectedIndex].value)">
									<option value="">--请选择大区--</option>
									<option value="华东地区" onclick="xuanRegion('华东地区')">华东地区</option>
									<option value="华北地区" onclick="xuanRegion('华北地区')">华北地区</option>
									<option value="华中地区" onclick="xuanRegion('华中地区')">华中地区</option>
									<option value="华南地区" onclick="xuanRegion('华南地区')">华南地区</option>
									<option value="西南地区" onclick="xuanRegion('西南地区')">西南地区</option>
									<option value="西北地区" onclick="xuanRegion('西北地区')">西北地区</option>
									<option value="西北地区" onclick="xuanRegion('东北地区')">东北地区</option>
									<option value="内蒙古自治区" onclick="xuanRegion('内蒙古自治区')">内蒙古自治区</option>
								</select>
								<br style="display: none" id = "firstbr"/>
								<select id="prov" name="prov"   style="background:none;border-bottom:1px solid #0c3167!important;color:#fff;text-align:center;width:120px;display: none" onchange="xuancity(this.options[this.selectedIndex].value)">
									<option value="">--请选择省份--</option>
								</select> 
								<select id="city" name="city"
									style="background:none;border-bottom:1px solid #0c3167!important;color:#fff;text-align:center;width:120px;">
									<option value="">--请选择城市--</option>		
								</select> 
								 <input type="submit" value="航班统计"
									style="border-radius:20px;width:200px;height:30px;line-height: 30px;background: #0b5dd1;margin-left:20px;color:#fff;margin-top:20px">
							</div>
						</form>


						
								<datalist id="provs">
									<option value="">--请选择省份--</option>
									<option value="河北">河北</option>
									<option value="山西">山西</option>
									<option value="内蒙古">内蒙古</option>
									<option value="辽宁">辽宁</option>
									<option value="吉林">吉林</option>
									<option value="黑龙江">黑龙江</option>
									<option value="江苏">江苏</option>
									<option value="浙江">浙江</option>
									<option value="安徽">安徽</option>
									<option value="福建">福建</option>
									<option value="江西">江西</option>
									<option value="山东">山东</option>
									<option value="河南">河南</option>
									<option value="湖北">湖北</option>
									<option value="湖南">湖南</option>
									<option value="广东">广东</option>
									<option value="广西">广西</option>
									<option value="海南">海南</option>
									<option value="四川">四川</option>
									<option value="贵州">贵州</option>
									<option value="云南">云南</option>
									<option value="西藏">西藏</option>
									<option value="陕西">陕西</option>
									<option value="甘肃">甘肃</option>
									<option value="青海">青海</option>
									<option value="宁夏">宁夏</option>
									<option value="新疆">新疆</option>
									<option value="台湾">台湾</option>
								</datalist>
								
								<datalist id="regions">
									<option value="华东地区">华东地区</option>
									<option value="华北地区">华北地区</option>
									<option value="华中地区">华中地区</option>
									<option value="华南地区">华南地区</option>
									<option value="西南地区">西南地区</option>
									<option value="西北地区">西北地区</option>
									<option value="西北地区">东北地区</option>
									<option value="内蒙古自治区">内蒙古自治区</option>
								</datalist>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
