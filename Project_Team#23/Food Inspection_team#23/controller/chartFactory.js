
class ChartFactory {
	static createChartTypeObject(chartType) {
		var chart;
		var optionContent = new Option();
		if(chartType == 'line') {
			chart = new LineChart("line",optionContent);
		} else if(chartType == 'bar') {
			chart = new BarChart("bar",optionContent);
		} else if(chartType == 'pie') {
			chart = new PieChart("pie",optionContent);
		} else if(chartType == 'stacked') {
			chart = new StackedChart("bar",optionContent);
		} else if(chartType == 'pivot') {
			chart = new PivotChart("bar",optionContent);
		}
		return chart;
	}	
}