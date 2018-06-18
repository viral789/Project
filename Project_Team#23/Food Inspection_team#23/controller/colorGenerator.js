
var lineBackgroundColors = [];
lineBackgroundColors.push("rgba(255, 99, 132, 0.2)");
lineBackgroundColors.push("rgba(54, 162, 235, 0.2)");
lineBackgroundColors.push("rgba(255, 206, 86, 0.2)");
lineBackgroundColors.push("rgba(153, 102, 255, 0.2)");
	
class ColorGenerator {
	static generateColor(chartType,count,randomColor) {
		if(chartType == "line" && !randomColor) {
			return lineBackgroundColors[count];
		}		
		else {
			var randomR = Math.floor((Math.random() * 130) + 100);
			var randomG = Math.floor((Math.random() * 130) + 100);
			var randomB = Math.floor((Math.random() * 130) + 100);
							 
			var graphBackground = "rgb(" 
				+ randomR + ", " 
				+ randomG + ", " 
				+ randomB + ")";
				
			return graphBackground;	
		} 		
	}	
}