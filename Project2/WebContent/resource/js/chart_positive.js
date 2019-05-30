
$(function(){
	
	function addComma(num) {
		  var regexp = /\B(?=(\d{3})+(?!\d))/g;
		  return num.toString().replace(regexp, ',');
	}
	var chart1 = '<div id="chartdiv"></div>';
	
	var name = [];
	var developer = [];
	var price = [];
	var price1 = [];
	var positive = [];
	var tempobj = {};
	var temparr =[];
		
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
					price1.push(value.price);
					positive.push(value.positive);
				});
				
				for (var i = 100; i > 0; i--){
					temp1 = [];
					temp1.push(developer[i]);
					temp1.push(name[i]);
					temp1.push(price[i]);
					temp1.push(positive[i]);
					tempobj[positive[i]] = temp1;

				}
				
				
				
				$.each(tempobj, function(key, value){
					temparr.push(value);
				});
				

				
				var arrname = [];
				var arrpositive = [];
				var arrdeveloper = [];
				var arrprice = [];
				
  				for(var i = 98; i > 70; i--){
					arrpositive.push(temparr[i][3]);
					arrname.push(temparr[i][1]);
					arrdeveloper.push(temparr[i][0]);
					arrprice.push(temparr[i][2]);
				}  
				
				
				text = "";
				num = 0;

				for (var i = 1; i < 11; i++){
	                num++;
	                text += "<tr>"
	                		+ "<td>" + num + "</td>"	
	                		+ "<td>" + arrpositive[i] + "</td>"
	                		+ "<td>" + arrname[i] + "</td>"
	                		+ "<td>" + arrdeveloper[i] + "</td>"	                		
	                		+ "<td>" + arrprice[i] + "</td></tr>";
	             }
				$("#tbody2").html(text);
				
				
				
				$("#cardchart2").html(chart1);
				
				am4core.ready(function() {

					// Themes begin
					am4core.useTheme(am4themes_dark);
					am4core.useTheme(am4themes_animated);
					// Themes end

					// Create chart instance
					var chart = am4core.create("chartdiv", am4charts.XYChart);
					chart.scrollbarX = new am4core.Scrollbar();

					// Add data
					chart.data = [{
					  "country": arrname[1].substring(0,15),
					  "visits": arrpositive[1]
					}, {
					  "country": arrname[2].substring(0,15),
					  "visits": arrpositive[2]
					}, {
					  "country": arrname[3].substring(0,15),
					  "visits": arrpositive[3]
					}, {
					  "country": arrname[4].substring(0,15),
					  "visits": arrpositive[4]
					}, {
					  "country": arrname[5].substring(0,15),
					  "visits": arrpositive[5]
					}, {
					  "country": arrname[6].substring(0,15),
					  "visits": arrpositive[6]
					}, {
					  "country": arrname[7].substring(0,15),
					  "visits": arrpositive[7]
					}, {
					  "country": arrname[8].substring(0,15),
					  "visits": arrpositive[8]
					}, {
					  "country": arrname[9].substring(0,15),
					  "visits": arrpositive[9]
					}, {
					  "country": arrname[10].substring(0,15),
					  "visits": arrpositive[10]
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

					// Create chart instance
					var chart = am4core.create("chartdiv2", am4charts.XYChart);
					chart.paddingRight = 20;

					// Add data
					chart.data = [{
						"year": arrname[2].substring(0,15),
						  "value": arrpositive[2]
					}, {
					"year": arrname[1].substring(0,15),
					  "value": arrpositive[1]
					}, {
					  "year": arrname[3].substring(0,15),
					  "value": arrpositive[3]
					}, {
					  "year": arrname[4].substring(0,15),
					  "value": arrpositive[4]
					}];

					// Create axes
					var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
					categoryAxis.dataFields.category = "year";
					categoryAxis.renderer.minGridDistance = 50;
					categoryAxis.renderer.grid.template.location = 0.5;
					categoryAxis.startLocation = 0.5;
					categoryAxis.endLocation = 0.5;

					// Pre zoom
					chart.events.on("datavalidated", function () {
					  categoryAxis.zoomToIndexes(Math.round(chart.data.length * 0.4), Math.round(chart.data.length * 0.55));
					});

					// Create value axis
					var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
					valueAxis.baseValue = 1500000;

					// Create series
					var series = chart.series.push(new am4charts.LineSeries());
					series.dataFields.valueY = "value";
					series.dataFields.categoryX = "year";
					series.strokeWidth = 2;
					series.tensionX = 0.77;

					var range = valueAxis.createSeriesRange(series);
					range.value = 600000;
					range.endValue = 3000000;
					range.contents.stroke = am4core.color("#FF0000");
					range.contents.fill = range.contents.stroke;

					// Add scrollbar
					var scrollbarX = new am4charts.XYChartScrollbar();
					scrollbarX.series.push(series);
					chart.scrollbarX = scrollbarX;

					chart.cursor = new am4charts.XYCursor();

					}); // end am4core.ready()
					
					
					am4core.ready(function() {

						// Themes begin
						am4core.useTheme(am4themes_dark);
						am4core.useTheme(am4themes_animated);
						// Themes end



						var chart = am4core.create("chartdiv3", am4charts.ChordDiagram);


						chart.data = [
						    { from: arrname[1].substring(0,5), to: arrname[2].substring(0,5), value: arrpositive[1] },
						    { from: arrname[2].substring(0,5), to: arrname[3].substring(0,5), value: arrpositive[2] },
						    { from: arrname[3].substring(0,5), to: arrname[4].substring(0,5), value: arrpositive[3] },
						    { from: arrname[4].substring(0,5), to: arrname[5].substring(0,5), value: arrpositive[4] },
						    { from: arrname[5].substring(0,5), to: arrname[6].substring(0,5), value: arrpositive[5] },
						    { from: arrname[6].substring(0,5), to: arrname[7].substring(0,5), value: arrpositive[6] },
						    { from: arrname[7].substring(0,5), to: arrname[8].substring(0,5), value: arrpositive[7] },
						    { from: arrname[8].substring(0,5), to: arrname[9].substring(0,5), value: arrpositive[8] },
						    { from: arrname[9].substring(0,5), to: arrname[1].substring(0,5), value: arrpositive[9] }
						];

						chart.dataFields.fromName = "from";
						chart.dataFields.toName = "to";
						chart.dataFields.value = "value";

						// make nodes draggable
						var nodeTemplate = chart.nodes.template;
						nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
						nodeTemplate.showSystemTooltip = true;

						var nodeLink = chart.links.template;
						var bullet = nodeLink.bullets.push(new am4charts.CircleBullet());
						bullet.fillOpacity = 1;
						bullet.circle.radius = 5;
						bullet.locationX = 0.5;

						// create animations
						chart.events.on("ready", function() {
						    for (var i = 0; i < chart.links.length; i++) {
						        var link = chart.links.getIndex(i);
						        var bullet = link.bullets.getIndex(0);

						        animateBullet(bullet);
						    }
						})

						function animateBullet(bullet) {
						    var duration = 3000 * Math.random() + 2000;
						    var animation = bullet.animate([{ property: "locationX", from: 0, to: 1 }], duration)
						    animation.events.on("animationended", function(event) {
						        animateBullet(event.target.object);
						    })
						}

						}); // end am4core.ready()
				
			},
			error : function(xhr, status, error) {
				console.log("에러");
			}
			
		});
		
	
});