
$(function(){
	
	function addComma(num) {
		  var regexp = /\B(?=(\d{3})+(?!\d))/g;
		  return num.toString().replace(regexp, ',');
	}
	var chart2 = '<div id="chartdiv4"></div>';
	
	var name = [];
	var developer = [];
	var price = [];
	var positive = [];
	var tempobj = {};
	var temparr =[];
	var averagetimearr = [];	
	var averagetimearr1 = [];	
	
	$.ajax({
			
			url : "gamerank.cht",
			dataType : "text",
			success : function(data) {
				high = 20;
				low = 0;
				var cdata = JSON.parse(data);
				$.each(cdata, function(key, value){
					name.push(value.name);
					developer.push(value.developer);
					price.push(addComma(value.price+"")+"￠");
					positive.push(value.positive);
					averagetimearr.push(addComma(value.median_2weeks + "") + " min");
					averagetimearr1.push(value.median_2weeks);
				});
				

				
				for (var i = 100; i > 0; i--){
					temp1 = [];
					temp1.push(developer[i]);
					temp1.push(name[i]);
					temp1.push(price[i]);
					temp1.push(averagetimearr[i]);
					temp1.push(averagetimearr1[i]);
					tempobj[averagetimearr1[i]] = temp1;
				}
				
				

				$.each(tempobj, function(key, value){
					temparr.push(value);
				});
				

				
				var arrname = [];
				var arrtime = [];
				var arrdeveloper = [];
				var arrtime1 = [];
				var arrprice = [];
				
				
  				for(var i = temparr.length-2; i > temparr.length-13; i--){
  					arrtime.push(temparr[i][3]);
					arrname.push(temparr[i][1]);
					arrdeveloper.push(temparr[i][0]);
					arrprice.push(temparr[i][2]);
					arrtime1.push(temparr[i][4]);
				}  
				

				
				text3 = "";
				num = 0;

				for (var i = 1; i < 11; i++){
	                num++;
	                text3 += "<tr>"
	                		+ "<td>" + num + "</td>"	
	                		+ "<td>" + arrtime[i] + "</td>"
	                		+ "<td>" + arrname[i] + "</td>"
	                		+ "<td>" + arrdeveloper[i] + "</td>"	                		
	                		+ "<td>" + arrprice[i] + "</td></tr>";
	             }
				
				$("#tbody3").html(text3);
				
				
				
				$("#cardchart3").html(chart2);
				
				am4core.ready(function() {

					// Themes begin
					am4core.useTheme(am4themes_dark);
					am4core.useTheme(am4themes_animated);
					// Themes end

					// Create chart instance
					var chart = am4core.create("chartdiv4", am4charts.XYChart);
					chart.scrollbarX = new am4core.Scrollbar();

					// Add data
					chart.data = [{
					  "country": arrname[1].substring(0,15),
					  "visits": arrtime1[1]
					}, {
					  "country": arrname[2].substring(0,15),
					  "visits": arrtime1[2]
					}, {
					  "country": arrname[3].substring(0,15),
					  "visits": arrtime1[3]
					}, {
					  "country": arrname[4].substring(0,15),
					  "visits": arrtime1[4]
					}, {
					  "country": arrname[5].substring(0,15),
					  "visits": arrtime1[5]
					}, {
					  "country": arrname[6].substring(0,15),
					  "visits": arrtime1[6]
					}, {
					  "country": arrname[7].substring(0,15),
					  "visits": arrtime1[7]
					}, {
					  "country": arrname[8].substring(0,15),
					  "visits": arrtime1[8]
					}, {
					  "country": arrname[9].substring(0,15),
					  "visits": arrtime1[9]
					}, {
					  "country": arrname[10].substring(0,15),
					  "visits": arrtime1[10]
					}];

					// Create axes
					var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
					categoryAxis.dataFields.category = "country";
					categoryAxis.renderer.grid.template.location = 0;
					categoryAxis.renderer.minGridDistance = 30;
					categoryAxis.renderer.labels.template.horizontalCenter = "right";
					categoryAxis.renderer.labels.template.verticalCenter = "middle";
					categoryAxis.renderer.labels.template.rotation = 270;
					categoryAxis.tooltip.disabled = true;
					categoryAxis.renderer.minHeight = 110;

					var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
					valueAxis.renderer.minWidth = 50;

					// Create series
					var series = chart.series.push(new am4charts.ColumnSeries());
					series.sequencedInterpolation = true;
					series.dataFields.valueY = "visits";
					series.dataFields.categoryX = "country";
					series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
					series.columns.template.strokeWidth = 0;

					series.tooltip.pointerOrientation = "vertical";

					series.columns.template.column.cornerRadiusTopLeft = 10;
					series.columns.template.column.cornerRadiusTopRight = 10;
					series.columns.template.column.fillOpacity = 0.8;

					// on hover, make corner radiuses bigger
					var hoverState = series.columns.template.column.states.create("hover");
					hoverState.properties.cornerRadiusTopLeft = 0;
					hoverState.properties.cornerRadiusTopRight = 0;
					hoverState.properties.fillOpacity = 1;

					series.columns.template.adapter.add("fill", function(fill, target) {
					  return chart.colors.getIndex(target.dataItem.index);
					});

					// Cursor
					chart.cursor = new am4charts.XYCursor();

					}); // end am4core.ready()
				
				
				am4core.ready(function() {

					// Themes begin
					am4core.useTheme(am4themes_dark);
					am4core.useTheme(am4themes_animated);
					// Themes end

					var chart = am4core.create("chartdiv5", am4charts.PieChart);
					chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

					chart.data = [
					  {
					    country: arrname[1].substring(0,15),
					    value: arrtime1[1]
					  },
					  {
					    country: arrname[2].substring(0,15),
					    value: arrtime1[2]
					  },
					  {
					    country: arrname[3].substring(0,15),
					    value: arrtime1[3]
					  },
					  {
					    country: arrname[4].substring(0,15),
					    value: arrtime1[4]
					  },
					  {
					    country: arrname[5].substring(0,15),
					    value: arrtime1[5]
					  },
					  {
					    country: arrname[6].substring(0,15),
					    value: arrtime1[6]
					  }
					];
					chart.radius = am4core.percent(70);
					chart.innerRadius = am4core.percent(40);
					chart.startAngle = 180;
					chart.endAngle = 360;  

					var series = chart.series.push(new am4charts.PieSeries());
					series.dataFields.value = "value";
					series.dataFields.category = "country";

					series.slices.template.cornerRadius = 10;
					series.slices.template.innerCornerRadius = 7;
					series.slices.template.draggable = true;
					series.slices.template.inert = true;
					series.alignLabels = false;

					series.hiddenState.properties.startAngle = 90;
					series.hiddenState.properties.endAngle = 90;

					chart.legend = new am4charts.Legend();

					}); // end am4core.ready()
				
				
				am4core.ready(function() {

					// Themes begin
					am4core.useTheme(am4themes_dark);
					am4core.useTheme(am4themes_animated);
					// Themes end

					// Create chart
					var chart = am4core.create("chartdiv6", am4charts.PieChart);
					chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

					chart.data = [
					  {
					    country: arrname[1].substring(0,15),
					    value: arrtime1[1]
					  },
					  {
					    country: arrname[2].substring(0,15),
					    value: arrtime1[2]
					  },
					  {
					    country: arrname[3].substring(0,15),
					    value: arrtime1[3]
					  },
					  {
					    country: arrname[4].substring(0,15),
					    value: arrtime1[4]
					  },
					  {
					    country: arrname[5].substring(0,15),
					    value: arrtime1[5]
					  },
					  {
					    country: arrname[6].substring(0,15),
					    value: arrtime1[6]
					  }
					];

					var series = chart.series.push(new am4charts.PieSeries());
					series.dataFields.value = "value";
					series.dataFields.radiusValue = "value";
					series.dataFields.category = "country";
					series.slices.template.cornerRadius = 6;
					series.colors.step = 3;

					series.hiddenState.properties.endAngle = -90;

					chart.legend = new am4charts.Legend();

					}); // end am4core.ready()
				
			},
			error : function(xhr, status, error) {
				console.log("에러");
			}
			
		});
		
	
});