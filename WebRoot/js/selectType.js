function changeMenu() {
		var mySelect = document.getElementById("type");
		var ops = mySelect.options;
		for (var i = 0; i < ops.length; i++) {
			if (ops[i].selected == true) {
				if (ops[i].value == "搜索机场") {
					document.getElementById("begincity").setAttribute("placeholder", "出发机场");
					document.getElementById("endcity").setAttribute("placeholder", "到达机场");
					document.getElementById("begincity").setAttribute("list", "iata");
					document.getElementById("endcity").setAttribute("list", "iata");
					document.getElementById("begincity").setAttribute("name", "BegAir");
					document.getElementById("endcity").setAttribute("name", "EndAir");
					document.getElementById("chaxun").setAttribute("action", "iata");
				} else if (ops[i].value == "搜索国家") {
					document.getElementById("begincity").setAttribute("placeholder", "出发国家");
					document.getElementById("endcity").setAttribute("placeholder", "到达国家");
					document.getElementById("begincity").setAttribute("list", "cnty");
					document.getElementById("endcity").setAttribute("list", "cnty");
					document.getElementById("begincity").setAttribute("name", "Ocnty");
					document.getElementById("endcity").setAttribute("name", "Dcnty");
					document.getElementById("chaxun").setAttribute("action", "Country");
				} else if (ops[i].value == "搜索城市") {
					document.getElementById("begincity").setAttribute("placeholder", "出发城市");
					document.getElementById("endcity").setAttribute("placeholder", "到达城市");
					document.getElementById("begincity").setAttribute("list", "word");
					document.getElementById("endcity").setAttribute("list", "word");
					document.getElementById("begincity").setAttribute("name", "begincity");
					document.getElementById("endcity").setAttribute("name", "endcity");
					document.getElementById("chaxun").setAttribute("action", "fightNum");
				}else if (ops[i].value == "搜索省份") {
					document.getElementById("begincity").setAttribute("placeholder", "出发省份");
					document.getElementById("endcity").setAttribute("placeholder", "到达省份");
					document.getElementById("begincity").setAttribute("list", "provs");
					document.getElementById("endcity").setAttribute("list", "provs");
					document.getElementById("begincity").setAttribute("name", "bprov");
					document.getElementById("endcity").setAttribute("name", "eprov");
					document.getElementById("chaxun").setAttribute("action", "provs");
				}else if (ops[i].value == "搜索地区") {
					document.getElementById("begincity").setAttribute("placeholder", "出发地区");
					document.getElementById("endcity").setAttribute("placeholder", "到达地区");
					document.getElementById("begincity").setAttribute("list", "regions");
					document.getElementById("endcity").setAttribute("list", "regions");
					document.getElementById("begincity").setAttribute("name", "bregion");
					document.getElementById("endcity").setAttribute("name", "eregion");
					document.getElementById("chaxun").setAttribute("action", "regions");
				}
			}
		}
	}
	;
