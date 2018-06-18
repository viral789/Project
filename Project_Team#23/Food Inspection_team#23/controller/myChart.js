class MyChart {
	constructor(type, data, labels, options, optionContent) 
	{
		this.type = type;
		this.data = data;
		this.labels = labels;
		this.options = options;
		this.optionContent = optionContent;
	}
	
	createChart(canvasId) {
		var canvas = document.getElementById("chartCanvas");
		var ctx = document.getElementById("chartCanvas").getContext("2d");
		
		canvas.parentNode.removeChild(canvas);
		var resetedCanvas = document.createElement("canvas");
		resetedCanvas.id = "chartCanvas";
		if(this.type == "pie") {
			resetedCanvas.width = 1500;			
		} else {
			resetedCanvas.width = 9000;
		}
		resetedCanvas.height = 450;
		document.getElementById("chartContainer").appendChild(resetedCanvas);

		canvas = document.getElementById("chartCanvas");
		ctx = canvas.getContext("2d");
		var myChart = new Chart(ctx, {
		    type: this.type,
		    data: {
				labels: this.labels,
				datasets : this.data
		    },
		    options: this.options
		});
		
		
	}

	createConfig (labels, dataSets) {	
		this.labels = labels;
		this.data = dataSets;
	}

}
