<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<script>

$(function(){
	
	function xmlToJson(xml) {

		// Create the return object
		var obj = {};

		if (xml.nodeType == 1) { // element
			// do attributes
			if (xml.attributes.length > 0) {
			obj["@attributes"] = {};
				for (var j = 0; j < xml.attributes.length; j++) {
					var attribute = xml.attributes.item(j);
					obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
				}
			}
		} else if (xml.nodeType == 3) { // text
			obj = xml.nodeValue;
		}

		// do children
		// If just one text node inside
		if (xml.hasChildNodes() && xml.childNodes.length === 1 && xml.childNodes[0].nodeType === 3) {
			obj = xml.childNodes[0].nodeValue;
		}
		else if (xml.hasChildNodes()) {
			for(var i = 0; i < xml.childNodes.length; i++) {
				var item = xml.childNodes.item(i);
				var nodeName = item.nodeName;
				if (typeof(obj[nodeName]) == "undefined") {
					obj[nodeName] = xmlToJson(item);
				} else {
					if (typeof(obj[nodeName].push) == "undefined") {
						var old = obj[nodeName];
						obj[nodeName] = [];
						obj[nodeName].push(old);
					}
					obj[nodeName].push(xmlToJson(item));
				}
			}
		}
		return obj;
	}
	
	function getXmlFromString(xmlStr) {
	    var parseXml;
	 
	    if (window.DOMParser) {
	        var dp = new window.DOMParser();
	        return dp.parseFromString(xmlStr, "text/xml");
	    } else if (typeof window.ActiveXObject != "undefined" 
	        && new window.ActiveXObject("Microsoft.XMLDOM")) {
	        var xmlDoc = new window.ActiveXObject("Microsoft.XMLDOM");
	        xmlDoc.async = "false";
	        xmlDoc.loadXML(xmlStr);
	        
	        return xmlDoc;
	    }
	 
	    return null;
	}
	$.ajax({
		url : "gamerank.cht",
		dataType : "text",
		success : function(data) {
			var xmldata = getXmlFromString(data);
			console.log(xmldata)
			var jsondata = xmlToJson(xmldata);
			console.log(jsondata.response.items.item[0])
			
			var dataarr = jsondata.response.items.item;
			
			$.each(dataarr, function(index,obj){
				
			});
			
		}
	});
			
		});


</script>



<!-- BEGIN card -->
					<div class="card m-b-15">
						<!-- BEGIN card-header -->
						<div class="card-header card-header-inverse">
							<h4 class="card-header-title">Table Hover</h4>
							<div class="card-header-btn">
								<a href="#" data-toggle="card-expand" class="btn btn-success"><i class="fa fa-expand"></i></a>
								<a href="#" data-toggle="card-refresh" class="btn btn-warning"><i class="fa fa-redo"></i></a>
								<a href="#" data-toggle="card-remove" class="btn btn-danger"><i class="fa fa-trash-alt"></i></a>
							</div>
						</div>
						<!-- END card-header -->
						<!-- BEGIN card-body -->
						<div class="card-body">
							<table class="table table-hover m-b-0">
								<thead>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>Developer</th>
										<th>Publisher</th>
										<th>Positive</th>
										<th>Negative</th>
										<th>Average time</th>
										<th>Price</th>
									</tr>
								</thead>
								<tbody id= "tbody">
								</tbody>
							</table>
							<br>
							<div>
							<ul class="pagination m-b-10 m-t-0">
								<li class="page-item" id='lp'>
									<a href="#" class="page-link" id="btnpre">
										<span>&laquo;</span>
									</a>
								</li>
								<li class="page-item active" id='l1'><a href="#" class="page-link" id ="btn1">1</a></li>
								<li class="page-item" id='l2'><a href="#" class="page-link" id ="btn2">2</a></li>
								<li class="page-item" id='l3'><a href="#" class="page-link" id ="btn3">3</a></li>
								<li class="page-item" id='l4'><a href="#" class="page-link" id ="btn4">4</a></li>
								<li class="page-item" id='l5'><a href="#" class="page-link" id ="btn5">5</a></li>
								<li class="page-item" id='ln'>
									<a href="#" class="page-link" id ="btnnext">
										<span>&raquo;</span>
									</a>
								</li>
							</ul>
							</div>
						</div>
						<!-- END card-body -->
					</div>
					<!-- END card -->
<!-- 콘텐츠영역 끝 -->

<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>