class PivotChart extends MyChart {
	constructor(type, optionContent)
	{
		super(type,[],{},{}, optionContent);
	}
	
	createConfig(labels, dataSets) {
		super.createConfig(labels, dataSets);
		this.optionContent.scales = {};
		this.optionContent.scales["xAxes"] = [];
		this.optionContent.scales["xAxes"].push({"stacked" : true });
		this.options ["scales"] = this.optionContent.scales;
	}
}
