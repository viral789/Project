class StackedChart extends MyChart {
	constructor(type, optionContent)
	{
		super(type,[],{},{}, optionContent);
	}
	
	createConfig(labels, dataSets) {
		super.createConfig(labels, dataSets);
		this.optionContent.scales = {};
		this.optionContent.scales["yAxes"] = [];
		this.optionContent.scales["yAxes"].push({"stacked" : true });
		this.options ["scales"] = this.optionContent.scales;
	}	
}
