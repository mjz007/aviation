	function xuanzhong(cnty){
				$("#region").css("display","none");
				$("#firstbr").css("display","none");
				$("#prov").css("display","none");
	if(cnty!='' && cnty != '中国')
	{
		$.ajax({
		    			async:false,
		    　　　　type:'post',
		   　　 　　data:{cnty:cnty}, 
		    　　　　url:'menu',  
		    			 dataType:"json",
		    　　　　success:function(data){
		    				$("#city").empty();
		    				$("#city").append(
		    					"<option value=''>请选择城市</option>"
		    					)
		    				for(var i=0;i<data.length;i++){
		    					$("#city").append(
		    					"<option value=\""+data[i].city+"\">"+data[i].city+"</option>"
		    					)
		    				}		
		　　　　　},
		　　　　　　error:function(){
		 						alert("错误")
		　　　　　　}
		　　　　});
		} 
		if(cnty == '中国')
		{
				$("#city").empty();
   				$("#city").append(
   					"<option value=\"\">请选择城市</option>"
   					)
				$("#region").css("display","inline");
				$("#firstbr").css("display","inline");
				$("#prov").css("display","inline");
		}
		
	}
	
	function xuancity(prov){
		var region=document.getElementById("region");
		region = region.options[region.selectedIndex].value;
		$.ajax({
		    			async:false,
		    　　　　type:'post',
		   　　 　　data:{prov:prov,region:region}, 
		    　　　　url:'provmenu',  
		    			 dataType:"json",
		    　　　　success:function(data){
		    				$("#city").empty();
		    				$("#city").append(
		    					"<option value=\"\">请选择城市</option>"
		    					)
		    				for(var i=0;i<data.length;i++){
		    					
		    					$("#city").append(
		    					"<option value="+data[i].city+" >"+data[i].city+"</option>"
		    					)
		    				}		
		　　　　　},
		　　　　　　error:function(){
		 						alert("错误")
		　　　　　　}
		　　　　});
	}
	
		function xuanRegion(region){
		$.ajax({
		    			async:false,
		    　　　　type:'post',
		   　　 　　data:{region:region}, 
		    　　　　url:'regionmenu',  
		    			 dataType:"json",
		    　　　　success:function(data){
		    				$("#prov").empty();
		    				$("#prov").append(
		    					"<option value=\"\">请选择省份</option>"
		    					)
		    				for(var i=0;i<data.length;i++){
		    					
		    					$("#prov").append(
		    					"<option value="+data[i].prov+">"+data[i].prov+"</option>"
		    					)
		    				}		
		　　　　　},
		　　　　　　error:function(){
		 						alert("错误")
		　　　　　　}
		　　　　});
	}