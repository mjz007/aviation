		$(function(){
			$("#mytbody").children().hide();
			
			for(i;i<size;i++)
			{
				$("#mytbody").children('tr').eq(i).show();
			}
			$("#myspan").append(
				    "<span id = 'ye' style=\"color:#FFF\">当前第"+page+"/共"+count+"页</span>"
			)
			
			
		});
		function xia(){
				page++;
				if(page>count)
				{
					page = count;
				}
				else
				{	
					size = size+10;		
					var z = i;
					for(z;z>=0;z--)
					{
						$("#mytbody").children('tr').eq(z).hide();
					}
					for(i;i<size;i++)
					{
						$("#mytbody").children('tr').eq(i).show();
					}
					$("#ye").remove();
					$("#myspan").append(
					    "<span id = 'ye' style=\"color:#FFF\">当前第"+page+"/共"+count+"页</span>"
					   )
				}
				
			}
			
			function shang(){
				page--;
				if(page<1)
				{
					page = 1;
				}
				else
				{				
					i = i -10;
					for(i;i<size;i++)
					{
						$("#mytbody").children('tr').eq(i).hide();
					}
					i = i -20;
					size = size - 10;
					for(i;i<size;i++)
					{
						$("#mytbody").children('tr').eq(i).show();
					}
					$("#ye").remove();
					$("#myspan").append(
					    "<span id = 'ye' style=\"color:#FFF\">当前第"+page+"/共"+count+"页</span>"
					   )
				}
			}
			
			function shou(){
				i = 0;
				size = 10;
				page = 1;
				$("#mytbody").children().hide();
				for(i;i<size;i++)
				{
					$("#mytbody").children('tr').eq(i).show();
				}
				$("#ye").remove();
				$("#myspan").append(
					    "<span id = 'ye' style=\"color:#FFF\">当前第"+page+"/共"+count+"页</span>"
				)
			}
			
			function wei(){
				i = zon;
				size = count*10;
				page = count;
				$("#mytbody").children().hide();
				for(i;i<size;i++)
				{
					$("#mytbody").children('tr').eq(i).show();
				}
				$("#ye").remove();
				$("#myspan").append(
					    "<span id = 'ye' style=\"color:#FFF\">当前第"+page+"/共"+count+"页</span>"
				)
			}