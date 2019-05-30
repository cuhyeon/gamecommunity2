

$(function() {
	$.ajax({
		url:"ChartIndexAjax.reg",
		type:"POST",
		dataType:"json",
		success:(data)=>{
			var xDate = new Array();
			var yCnt = new Array();
			for(let i=0;i<data.length;i++){					
				xDate[i]=data[i].date;
				yCnt[i] =data[i].cnt;
			}
			var ctx = document.getElementById('myChart').getContext('2d');
			var chart = new Chart(ctx, {
				
			    type: 'line',
			    
			    // The data for our dataset
			    data: {
			        labels: xDate,
			        datasets: [{
			            label: '회원 수',
			            backgroundColor: 'rgb(210, 230, 255)',
			            borderColor: 'rgb(33, 133, 255)',
			            data: yCnt
			        }]
			    },

			    // Configuration options go here
			    options: {}
			});
			
		}
	});
	

	


}); // end am4core.ready()