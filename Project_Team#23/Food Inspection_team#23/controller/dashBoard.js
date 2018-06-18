var dashBoard;
var resources = '{"1" : "controller/resources/NewYork.csv","2" : "controller/resources/Chicago.csv","3" : "controller/resources/LA.csv","4" : "controller/resources/Seattle.csv","5" : "controller/resources/SF.csv"}';
var selectedDataset;
class DashBoard {
	constructor(dataSetId) {
		if(typeof dataSetId == 'undefined') {
			dataSetId = 1;
		}
		this.dataSetId = dataSetId;
		this.init(dataSetId);
	}
	
	init(dataSetId) {
		var resource =  JSON.parse(resources)[dataSetId];
		var dataSet;
		selectedDataset = resource.slice(21);
		DataFrame.fromCSV(resource).then(df => {
			dataSet = new DataSet(df, [], [], []);
			this.currentDataSet = dataSet;
			var parentDiv = document.getElementById("parent-header");
			var dataContentParentDiv = document.getElementById("parent-data");
			setHeightAndWidthForDataSet(dataSetId);
			renderDataSet(parentDiv, dashBoard.currentDataSet, dataContentParentDiv);
		});
	}
}

window.onload = function () {
	var dataSetId = location.search.split('dataSetId=')[1]
	var dimension = getWindowDimension().split("||");
	var height = dimension[0];
	dashBoard = new DashBoard(dataSetId);
}

function renderDataSet(parentHeaderDiv, dataSet, parentDataDiv) {
	var df = dataSet.dataFrame;
	var columnArr = df.listColumns();
	var hiddenColumns = dataSet.hiddenColumns;
	var columnCount = 1;
	// ID Column
	var div = document.createElement("div");
	var className = "data-t1-c"+columnCount;
	div.classList.add(className);
	
	for(var i = 0; i<columnArr.length; i++) {
		// Check for hidden column first 
		var isColumnHidden = false;
		for(var j = 0 ; j < hiddenColumns.length; j++) {
			var colId = ""+(i+1);
			if(hiddenColumns[j] == colId) {
				isColumnHidden = true;
				break;
			}	
		}
		
		if(isColumnHidden) {
			columnCount++;
			continue;
		}	
		var div = document.createElement("div");
		div.classList.add("data-th");
		if(columnArr == 1) {
			div.classList.add("data-th-first");
			div.classList.add("data-t1-c1");
		} else {
			var className = "data-t1-c"+columnCount;
			div.classList.add(className);
		}
		columnCount++;
		var span = document.createElement("span");
		span.classList.add("data-container");
		
		var divNameWrap = document.createElement("div");
		divNameWrap.classList.add("data-name-combine");
		
		var spanName = document.createElement("span");
		spanName.classList.add("data-th-name");
		spanName.innerHTML = columnArr[i];
		divNameWrap.appendChild(spanName);
		span.appendChild(divNameWrap);
		div.appendChild(span);
		parentHeaderDiv.appendChild(div);
	}
	var rowCount = 1;
	var topMargin = 0;
	var dataArray = df.toArray();
 
	for(var i = 0; i < 10000; i++) {
		var div = document.createElement("div");
		div.id = "data-t1-r"+rowCount;
		div.classList.add("data-tr");
		
		var singleRowArray = dataArray[i];
		columnCount = 1;
		for(var j = 0; j < singleRowArray.length; j++) {
			// Check for hidden column first 
			var isColumnHidden = false;
			for(var k = 0 ; k < hiddenColumns.length; k++) {
				var colId = ""+(j+1);
				if(hiddenColumns[k] == colId) {
					isColumnHidden = true;
					break;
				}	
			}
			if(isColumnHidden) {
				columnCount++;
				continue;
			}
			var innerDiv = document.createElement("div");
			innerDiv.classList.add("data-td");
			innerDiv.classList.add("data-t1-c"+columnCount);
			innerDiv.classList.add("align-left");
			innerDiv.style.display = "table-cell";
			innerDiv.innerHTML = singleRowArray[j];
			innerDiv.title = singleRowArray[j];
			div.appendChild(innerDiv);
			columnCount++;
		}
		parentDataDiv.appendChild(div);
		rowCount++;
	}
}

function scrollHeader() {
	var div = document.getElementById("d1");
	var parentDiv = document.getElementById("parent-header");
	parentDiv.style.left = "-"+div.scrollLeft+"px";
}

function hideSidebar() {
	document.getElementById("d1").style.width = '100%';
	document.getElementById("inner-table").style.width = '100%';
	document.getElementById("gridSidebar").style.display = "none";
}

function setHeightAndWidthForDataSet(dataSetId) {
	if(dataSetId == '5') {
		document.getElementById("parent-header").style.width = (parseInt(document.getElementById("parent-header").style.width.slice(0,-2)) + 220)+"px"; 
		document.getElementById("data-inside").style.width = (parseInt(document.getElementById("data-inside").style.width.slice(0,-2)) + 220) + "px";
	}
}

function displaySideBar(contentId) {
	document.getElementById("gridSidebar").style.display = "";
	document.getElementById("d1").style.width = '100%';
	document.getElementById("inner-table").style.width = '80%';
	if(contentId == 'columnFilter') {
		document.getElementById("contentTitle").innerHTML = "Column Filter";
		document.getElementById("contentSubTitle").innerHTML = "Show & Hide Columns";
		displayColumnsInSideBar();
	} else if(contentId == 'rowFilter') {
		document.getElementById("contentTitle").innerHTML = "Row Filter";
		document.getElementById("contentSubTitle").innerHTML = "Filter Dataset";
		displayFiltersInSideBar();
	} else if(contentId == 'visualize') {
		document.getElementById("contentTitle").innerHTML = "Visualize";
		document.getElementById("contentSubTitle").innerHTML = "Create Charts";
		displayChartInSideBar();
	} else if(contentId == "stats") {
		document.getElementById("contentTitle").innerHTML = "Stats";
		document.getElementById("contentSubTitle").innerHTML = "View Stats";
		displayStatsInSideBar();	
	}	
}

function displayColumnsInSideBar() {
	var dataSet = dashBoard.currentDataSet;
	var df = dashBoard.currentDataSet.dataFrame;
	var columnArr = df.listColumns();
	var hiddenColumns = dataSet.hiddenColumns;
	
	var divContent = document.createElement("div");
	divContent.classList.add("paneContent");
	
	var divFormSec = document.createElement("div");
	divFormSec.classList.add("formSection");
	divFormSec.classList.add("custom");
	
	var formLabel = document.createElement("label");
	formLabel.innerHTML = "Columns";
	formLabel.classList.add("formHeader");
	
	var divBlock = document.createElement("div");
	divBlock.classList.add("sectionContent");
	divBlock.classList.add("showHideBlock");
	
	var list = document.createElement("ul");
	list.classList.add("sectionContent");
	
	for(var i = 0; i<columnArr.length; i++) {
		var li = document.createElement("li");
		li.classList.add("columnItem");
		
		var div = document.createElement("div");
		div.classList.add("checker");
		div.classList.add("uniform");
		div.id = ""+(i+1);
		
		var span = document.createElement("span");
		var isHidden = false;
		for(var j = 0; j < hiddenColumns.length; j++) {
			if(hiddenColumns[j] == ""+(i+1)) {
				isHidden = true;
			}
		}
		if(isHidden) {
			span.classList.add("unchecked");
		} else {
			span.classList.add("checked");
		}
		
		var checkbox = document.createElement('input');
		checkbox.type = "checkbox";
		if(isHidden) {
			checkbox.checked = false;
		} else {
			checkbox.checked = true;
		}
		checkbox.id = "col_"+(i+1);
		checkbox.style.opacity = "1";
		
		var label = document.createElement("label");
		label.classList.add("text");
		
		var subSpan = document.createElement("span");
		subSpan.classList.add("name");
		subSpan.innerHTML = columnArr[i];
		
		label.appendChild(subSpan);
		span.appendChild(checkbox);
		div.appendChild(span);
		li.appendChild(div);
		li.appendChild(label);
		list.appendChild(li);
	}
	
	var buttons = document.createElement("ul");
	buttons.classList.add("finishButtons");
	buttons.classList.add("actionButtons");
	buttons.classList.add("clearfix");
	
	var applyButton = document.createElement("li");
	var applyButtonLink = document.createElement("a");
	applyButtonLink.href = "#";
	applyButtonLink.classList.add("button");
	applyButtonLink.classList.add("arrowButton");
	applyButtonLink.classList.add("submit");
	applyButtonLink.innerHTML = "Apply";
	applyButtonLink.addEventListener("click", applyShowHideColumnSettings, false);
	applyButton.appendChild(applyButtonLink);
	
	var cancelButton = document.createElement("li");
	var cancelButtonLink = document.createElement("a");
	cancelButtonLink.href = "#";
	cancelButtonLink.classList.add("button");
	cancelButtonLink.classList.add("arrowButton");
	cancelButtonLink.innerHTML = "Cancel";
	cancelButton.appendChild(cancelButtonLink);
	
	buttons.appendChild(applyButton);
	buttons.appendChild(cancelButton);
		
	divBlock.appendChild(list);
	divBlock.appendChild(buttons);
	formLabel.appendChild(divBlock);
	divFormSec.appendChild(formLabel);
	divContent.appendChild(divFormSec);
	clearChildContent(document.getElementById("columnShowHideForm"));
	document.getElementById("columnShowHideForm").appendChild(divContent);
}

function applyShowHideColumnSettings() {
	hideSidebar();
	for(var i=1; i<=dashBoard.currentDataSet.dataFrame.listColumns().length; i++) {
		
		var id = "col_"+i;
		var checkbox = document.getElementById(id);
		var index = dashBoard.currentDataSet.hiddenColumns.indexOf(""+i);
		if(!checkbox.checked) {
			if(index == -1) {
				dashBoard.currentDataSet.hiddenColumns.push(""+i);
			}	
		} else {
			if(index > -1) {
				dashBoard.currentDataSet.hiddenColumns.splice(index,1);
				console.log(dashBoard.currentDataSet.hiddenColumns);
			}	
		}	
	}
	var parentDiv = document.getElementById("parent-header");
	var dataContentParentDiv = document.getElementById("parent-data");
	clearChildContent(parentDiv);
	clearChildContent(dataContentParentDiv);
	renderDataSet(parentDiv, dashBoard.currentDataSet, dataContentParentDiv);
}

function clearChildContent(parentElement) {
	while (parentElement.firstChild) {
		parentElement.removeChild(parentElement.firstChild);
	}
}

function displayFiltersInSideBar() {
	var dataSet = dashBoard.currentDataSet;
	var df = dashBoard.currentDataSet.dataFrame;
	var columnArr = df.listColumns();
	var hiddenColumns = dataSet.hiddenColumns;
	
	var divContent = document.createElement("div");
	divContent.classList.add("paneContent");
	
	var divFormSec = document.createElement("div");
	divFormSec.classList.add("formSection");
	divFormSec.classList.add("custom");
	
	var divBlock = document.createElement("div");
	divBlock.classList.add("sectionContent");
	divBlock.classList.add("filterPane");
	divBlock.classList.add("advanced");
	
	var filterDiv = document.createElement("div");
	filterDiv.classList.add("normalFilterMode");
	filterDiv.style.display = "block";
	
	var filterConditionsDiv = document.createElement("div");
	filterConditionsDiv.classList.add("filterConditions");
	filterConditionsDiv.id = "filterConditionsDiv";
	
	var filterContentDiv = createFilterConditionContent(columnArr, true);
	filterConditionsDiv.appendChild(filterContentDiv);
	
	var newFilter = document.createElement("a");
	newFilter.classList.add("button");
	newFilter.classList.add("add");
	newFilter.classList.add("advanced");
	newFilter.classList.add("addFilterConditionButton");
	newFilter.innerHTML = "Add a New Filter Condition";
	
	var buttons = document.createElement("ul");
	buttons.classList.add("finishButtons");
	buttons.classList.add("actionButtons");
	buttons.classList.add("clearfix");
	
	var applyButton = document.createElement("li");
	var applyButtonLink = document.createElement("a");
	applyButtonLink.href = "#";
	applyButtonLink.classList.add("button");
	applyButtonLink.classList.add("arrowButton");
	applyButtonLink.classList.add("submit");
	applyButtonLink.innerHTML = "Apply";
	applyButtonLink.addEventListener("click", applyFilterConditions, false);
	applyButton.appendChild(applyButtonLink);
	
	var cancelButton = document.createElement("li");
	var cancelButtonLink = document.createElement("a");
	cancelButtonLink.href = "#";
	cancelButtonLink.classList.add("button");
	cancelButtonLink.classList.add("arrowButton");
	cancelButtonLink.innerHTML = "Cancel";
	cancelButton.appendChild(cancelButtonLink);
	
	var addNewCondidition = document.createElement("a");
	addNewCondidition.href="#";
	addNewCondidition.id = "addNewCondidition";
	addNewCondidition.innerHTML = "Add another condition";
	addNewCondidition.addEventListener("click", addNewCondition, false);
	
	buttons.appendChild(applyButton);
	buttons.appendChild(cancelButton);
	
	var hiddenCount = document.createElement("input");
	hiddenCount.type = "hidden";
	hiddenCount.id = "conditionCount";
	hiddenCount.value = 1;
		
	filterDiv.appendChild(filterConditionsDiv);
	filterDiv.appendChild(addNewCondidition);
	divBlock.appendChild(filterDiv);
	divBlock.appendChild(buttons);
	divFormSec.appendChild(divBlock);
	divContent.appendChild(divFormSec);
	clearChildContent(document.getElementById("columnShowHideForm"));
	document.getElementById("columnShowHideForm").appendChild(divContent);
	document.getElementById("columnShowHideForm").appendChild(hiddenCount);
}

function displayChartInSideBar() {
	var dataSet = dashBoard.currentDataSet;
	var df = dashBoard.currentDataSet.dataFrame;
	var columnArr = df.listColumns();
	var hiddenColumns = dataSet.hiddenColumns;
	
	var divContent = document.createElement("div");
	divContent.classList.add("paneContent");
	
	var divFormSec = document.createElement("div");
	divFormSec.classList.add("formSection");
	divFormSec.classList.add("custom");
	
	var divBlock = document.createElement("div");
	divBlock.classList.add("sectionContent");
	divBlock.classList.add("filterPane");
	divBlock.classList.add("advanced");
	
	var filterDiv = document.createElement("div");
	filterDiv.classList.add("normalFilterMode");
	filterDiv.style.display = "block";
	
	var filterConditionsDiv = document.createElement("div");
	filterConditionsDiv.classList.add("filterConditions");
	filterConditionsDiv.id = "filterConditionsDiv";
	
	var filterContentDiv = createChartContent(columnArr, true);
	filterConditionsDiv.appendChild(filterContentDiv);
	
	var newFilter = document.createElement("a");
	newFilter.classList.add("button");
	newFilter.classList.add("add");
	newFilter.classList.add("advanced");
	newFilter.classList.add("addFilterConditionButton");
	newFilter.innerHTML = "Add a New Filter Condition";
	
	var buttons = document.createElement("ul");
	buttons.classList.add("finishButtons");
	buttons.classList.add("actionButtons");
	buttons.classList.add("clearfix");
	
	var applyButton = document.createElement("li");
	var applyButtonLink = document.createElement("a");
	applyButtonLink.href = "#";
	applyButtonLink.classList.add("button");
	applyButtonLink.classList.add("arrowButton");
	applyButtonLink.classList.add("submit");
	applyButtonLink.innerHTML = "Create";
	applyButtonLink.addEventListener("click", createAndRenderChart, false);
	applyButton.appendChild(applyButtonLink);
	
	var cancelButton = document.createElement("li");
	var cancelButtonLink = document.createElement("a");
	cancelButtonLink.href = "#";
	cancelButtonLink.classList.add("button");
	cancelButtonLink.classList.add("arrowButton");
	cancelButtonLink.innerHTML = "Cancel";
	cancelButton.appendChild(cancelButtonLink);
	
	var addNewCondidition = document.createElement("a");
	addNewCondidition.href="#";
	addNewCondidition.id = "addNewCondidition";
	addNewCondidition.innerHTML = "Add another column value";
	addNewCondidition.addEventListener("click", addNewColumnValue, false);
	
	buttons.appendChild(applyButton);
	buttons.appendChild(cancelButton);
	
	var hiddenCount = document.createElement("input");
	hiddenCount.type = "hidden";
	hiddenCount.id = "columnValueCount";
	hiddenCount.value = 1;
		
	filterDiv.appendChild(filterConditionsDiv);
	filterDiv.appendChild(addNewCondidition);
	divBlock.appendChild(filterDiv);
	divBlock.appendChild(buttons);
	divFormSec.appendChild(divBlock);
	divContent.appendChild(divFormSec);
	clearChildContent(document.getElementById("columnShowHideForm"));
	document.getElementById("columnShowHideForm").appendChild(divContent);
	document.getElementById("columnShowHideForm").appendChild(hiddenCount);
}

function displayStatsInSideBar() {
	var dataSet = dashBoard.currentDataSet;
	var df = dashBoard.currentDataSet.dataFrame;
	var columnArr = df.listColumns();
	var hiddenColumns = dataSet.hiddenColumns;
	
	var divContent = document.createElement("div");
	divContent.classList.add("paneContent");
	
	var divFormSec = document.createElement("div");
	divFormSec.classList.add("formSection");
	divFormSec.classList.add("custom");
	
	var divBlock = document.createElement("div");
	divBlock.classList.add("sectionContent");
	divBlock.classList.add("filterPane");
	divBlock.classList.add("advanced");
	
	var filterDiv = document.createElement("div");
	filterDiv.classList.add("normalFilterMode");
	filterDiv.style.display = "block";
	
	var filterConditionsDiv = document.createElement("div");
	filterConditionsDiv.classList.add("filterConditions");
	filterConditionsDiv.id = "filterConditionsDiv";
	
	var filterContentDiv = createStatsContent(columnArr, true);
	filterConditionsDiv.appendChild(filterContentDiv);
	
	var buttons = document.createElement("ul");
	buttons.classList.add("finishButtons");
	buttons.classList.add("actionButtons");
	buttons.classList.add("clearfix");
	
	var applyButton = document.createElement("li");
	var applyButtonLink = document.createElement("a");
	applyButtonLink.href = "#";
	applyButtonLink.classList.add("button");
	applyButtonLink.classList.add("arrowButton");
	applyButtonLink.classList.add("submit");
	applyButtonLink.innerHTML = "View";
	applyButtonLink.addEventListener("click", viewStats, false);
	applyButton.appendChild(applyButtonLink);
	
	var cancelButton = document.createElement("li");
	var cancelButtonLink = document.createElement("a");
	cancelButtonLink.href = "#";
	cancelButtonLink.classList.add("button");
	cancelButtonLink.classList.add("arrowButton");
	cancelButtonLink.innerHTML = "Cancel";
	cancelButton.appendChild(cancelButtonLink);
	
	buttons.appendChild(applyButton);
	buttons.appendChild(cancelButton);
		
	filterDiv.appendChild(filterConditionsDiv);
	divBlock.appendChild(filterDiv);
	divBlock.appendChild(buttons);
	divFormSec.appendChild(divBlock);
	divContent.appendChild(divFormSec);
	clearChildContent(document.getElementById("columnShowHideForm"));
	document.getElementById("columnShowHideForm").appendChild(divContent);
	document.getElementById("columnShowHideForm").appendChild(hiddenCount);
}

function addNewColumnValue() {
	var div = document.getElementById("columnValueDataDiv");
	var columnValueCount = parseInt(document.getElementById("columnValueCount").value)+1;
	var columnArr = dashBoard.currentDataSet.dataFrame.listColumns();
	
	var label = document.createElement("span");
	label.style.color = "grey";
	label.innerHTML = "Value Data :";
	
	var columnDropDownForValue = document.createElement("select");	
	columnDropDownForValue.style.width = "300px";
	columnDropDownForValue.style.height = "30px";
	columnDropDownForValue.id = "chart_value_data_"+columnValueCount;
		
	document.getElementById("columnValueCount").value = columnValueCount;
	
	var defaultOption = document.createElement("option");
	defaultOption.innerHTML = "-- Select --";
	for(var i = 0 ; i < columnArr.length; i++) {
		if(dashBoard.currentDataSet.hiddenColumns.indexOf(""+(i+1)) >= 0) {
			continue;
		}	
		var columnOption = document.createElement("option");
		columnOption.value = i+1;
		columnOption.innerHTML = columnArr[i];
		columnOption.style.backgroundColor = "grey";
		columnDropDownForValue.appendChild(columnOption);
	}
	div.appendChild(label);
	div.appendChild(columnDropDownForValue);
}	

function createFilterConditionContent(columnArr, firstAdd) {
	var div = document.createElement("div");
	div.classList.add("filterCondition");
	div.classList.add("sectionContent");
	div.classList.add("expanded");
	
	var closeLink = document.createElement("a");
	closeLink.classList.add("filterRemoveButton");
	closeLink.classList.add("advanced");
	closeLink.classList.add("remove");
	closeLink.href = "#close";
	closeLink.style.color = "#999999";
	
	var closeIcon = document.createElement("span");
	closeIcon.classList.add("icon");
	closeIcon.innerHTML = "Remove Filter";

	closeLink.appendChild(closeIcon);
	
	var label1 = document.createElement("span");
	label1.style.color = "grey";
	label1.innerHTML = "Column & Condition :";
	
	var filterHead = document.createElement("div");
	filterHead.classList.add("filterHeadline");

	var columnDropDown = document.createElement("select");	
	columnDropDown.style.width = "220px";
	columnDropDown.style.height = "30px";
	if(firstAdd) { 
		columnDropDown.id = "filter_col_1";
	} else {
		var conditionCount = parseInt(document.getElementById("conditionCount").value) + 1;
		columnDropDown.id = "filter_col_"+conditionCount;
	}	
	
	var filterConditionDropDown = document.createElement("select");	
	filterConditionDropDown.style.height = "30px";
	if(firstAdd) { 
		filterConditionDropDown.id = "filter_relation_1";
	} else {
		var conditionCount = parseInt(document.getElementById("conditionCount").value) + 1;
		filterConditionDropDown.id = "filter_relation_"+conditionCount;
	}	
	
	var defaultOption = document.createElement("option");
	defaultOption.innerHTML = "-- Select --";
	
	for(var i = 0 ; i < columnArr.length; i++) {
		// First Check if Column is Hidden or Not.
		if(dashBoard.currentDataSet.hiddenColumns.indexOf(""+(i+1)) >= 0) {
			continue;	
		}	
		var columnOption = document.createElement("option");
		columnOption.value = i+1;
		columnOption.innerHTML = columnArr[i];
		columnOption.style.backgroundColor = "grey";
		columnDropDown.appendChild(columnOption);
	}	
	
	var filterConditionOption1 = document.createElement("option");
	filterConditionOption1.innerHTML = "is";
	filterConditionOption1.value = "is";
	
	var filterConditionOption2 = document.createElement("option");
	filterConditionOption2.innerHTML = "is not";
	filterConditionOption2.value = "is not";
	
	var filterConditionOption3 = document.createElement("option");
	filterConditionOption3.innerHTML = "starts with";
	filterConditionOption3.value = "starts with";
	
	var filterConditionOption4 = document.createElement("option");
	filterConditionOption4.innerHTML = "contains";
	filterConditionOption4.value = "starts with";
	
	var filterConditionOption5 = document.createElement("option");
	filterConditionOption5.innerHTML = "greater than";
	filterConditionOption5.value = "greater than";
	
	var filterConditionOption6 = document.createElement("option");
	filterConditionOption6.innerHTML = "less than";
	filterConditionOption6.value = "less than";
	
	var filterConditionOption7 = document.createElement("option");
	filterConditionOption7.innerHTML = "between";
	filterConditionOption7.value = "between";
	
	filterConditionDropDown.appendChild(defaultOption);
	filterConditionDropDown.appendChild(filterConditionOption1);
	filterConditionDropDown.appendChild(filterConditionOption2);
	filterConditionDropDown.appendChild(filterConditionOption3);
	filterConditionDropDown.appendChild(filterConditionOption4);
	filterConditionDropDown.appendChild(filterConditionOption5);
	filterConditionDropDown.appendChild(filterConditionOption6);
	filterConditionDropDown.appendChild(filterConditionOption7);
	
	filterHead.appendChild(label1);
	filterHead.appendChild(columnDropDown);
	filterHead.appendChild(filterConditionDropDown);
	
	var label2 = document.createElement("span");
	label2.style.color = "grey";
	label2.innerHTML = "Value :";
	label2.style.paddingLeft = "0.5em";
	
	var filterValuesDiv = document.createElement("div");
	filterValuesDiv.classList.add("filterValues");
	filterValuesDiv.appendChild(label2);
	
	var lineDiv = document.createElement("div");
	lineDiv.classList.add("line");
	
	var textBox = document.createElement("input");
	textBox.type = "text";
	textBox.classList.add("align-right");
	textBox.style.width = "213px";
	textBox.style.height = "20px";
	if(firstAdd) { 
		textBox.id = "filter_value_1";
	} else {
		var conditionCount = parseInt(document.getElementById("conditionCount").value) + 1;
		textBox.id = "filter_value_"+conditionCount;
	}
	
	
	if(!firstAdd) {
		var conditionCount = parseInt(document.getElementById("conditionCount").value) + 1;
		document.getElementById("conditionCount").value = conditionCount;
	}	
	lineDiv.appendChild(textBox);
	filterValuesDiv.appendChild(lineDiv);
	
	div.appendChild(closeLink);
	div.appendChild(filterHead);
	div.appendChild(filterValuesDiv);
	
	return div;
}


function createChartContent(columnArr, firstAdd) {
	var div = document.createElement("div");
	div.classList.add("filterCondition");
	div.classList.add("sectionContent");
	div.classList.add("expanded");
	
	var closeLink = document.createElement("a");
	closeLink.classList.add("filterRemoveButton");
	closeLink.classList.add("advanced");
	closeLink.classList.add("remove");
	closeLink.href = "#close";
	closeLink.style.color = "#999999";
	
	var closeIcon = document.createElement("span");
	closeIcon.classList.add("icon");
	closeIcon.innerHTML = "Remove Filter";

	closeLink.appendChild(closeIcon);
	
	var label1 = document.createElement("span");
	label1.style.color = "grey";
	label1.innerHTML = "Chart Type :";
	
	var label2 = document.createElement("span");
	label2.style.color = "grey";
	label2.innerHTML = "Label Data :";
	
	var label3 = document.createElement("span");
	label3.style.color = "grey";
	label3.innerHTML = "Value Data :";
	
	var filterHead = document.createElement("div");
	filterHead.classList.add("filterHeadline");
	filterHead.id = "columnValueDataDiv";

	var columnDropDownForLabel = document.createElement("select");	
	columnDropDownForLabel.style.width = "300px";
	columnDropDownForLabel.style.height = "30px";
	columnDropDownForLabel.id = "chart_label_data";
	
	var columnDropDownForValue = document.createElement("select");	
	columnDropDownForValue.style.width = "300px";
	columnDropDownForValue.style.height = "30px";
	columnDropDownForValue.id = "chart_value_data_1";
			
	var chartTypeDropDown = document.createElement("select");	
	chartTypeDropDown.style.height = "30px";
	chartTypeDropDown.style.width = "300px";
	chartTypeDropDown.id = "chart_type";
	
	var defaultOption = document.createElement("option");
	defaultOption.innerHTML = "-- Select --";
	
	for(var i = 0 ; i < columnArr.length; i++) {
		if(dashBoard.currentDataSet.hiddenColumns.indexOf(""+(i+1)) >= 0) {
			continue;	
		}
		var columnOption = document.createElement("option");
		columnOption.value = i+1;
		columnOption.innerHTML = columnArr[i];
		columnOption.style.backgroundColor = "grey";
		columnDropDownForLabel.appendChild(columnOption);
	}
	for(var i = 0 ; i < columnArr.length; i++) {
		if(dashBoard.currentDataSet.hiddenColumns.indexOf(""+(i+1)) >= 0) {
			continue;	
		}
		var columnOption = document.createElement("option");
		columnOption.value = i+1;
		columnOption.innerHTML = columnArr[i];
		columnOption.style.backgroundColor = "grey";
		columnDropDownForValue.appendChild(columnOption);
	}	
	
	var chartType1 = document.createElement("option");
	chartType1.innerHTML = "Line";
	chartType1.value = "line";
	
	var chartType2 = document.createElement("option");
	chartType2.innerHTML = "Bar";
	chartType2.value = "bar";
	
	var chartType3 = document.createElement("option");
	chartType3.innerHTML = "Pie";
	chartType3.value = "pie";
	
	var chartType4 = document.createElement("option");
	chartType4.innerHTML = "Stacked";
	chartType4.value = "stacked";
	
	var chartType5 = document.createElement("option");
	chartType5.innerHTML = "Pivot";
	chartType5.value = "pivot";
	
	chartTypeDropDown.appendChild(defaultOption);
	chartTypeDropDown.appendChild(chartType1);
	chartTypeDropDown.appendChild(chartType2);
	chartTypeDropDown.appendChild(chartType3);
	chartTypeDropDown.appendChild(chartType4);
	chartTypeDropDown.appendChild(chartType5);
	
	filterHead.appendChild(label1);
	filterHead.appendChild(chartTypeDropDown);
	filterHead.appendChild(label2);
	filterHead.appendChild(columnDropDownForLabel);
	filterHead.appendChild(label3);
	filterHead.appendChild(columnDropDownForValue);
	div.appendChild(closeLink);
	div.appendChild(filterHead);
	
	return div;
}

function createStatsContent(columnArr, firstAdd) {
	var div = document.createElement("div");
	div.classList.add("filterCondition");
	div.classList.add("sectionContent");
	div.classList.add("expanded");
	
	var closeLink = document.createElement("a");
	closeLink.classList.add("filterRemoveButton");
	closeLink.classList.add("advanced");
	closeLink.classList.add("remove");
	closeLink.href = "#close";
	closeLink.style.color = "#999999";
	
	var closeIcon = document.createElement("span");
	closeIcon.classList.add("icon");
	closeIcon.innerHTML = "Remove Filter";

	closeLink.appendChild(closeIcon);
	
	var label2 = document.createElement("span");
	label2.style.color = "grey";
	label2.innerHTML = "Column :";
	
	var label3 = document.createElement("span");
	label3.style.color = "grey";
	label3.innerHTML = "Count :";
	
	var span3 = document.createElement("span");
	span3.id = "countValue"
	
	var label4 = document.createElement("span");
	label4.style.color = "grey";
	label4.innerHTML = "Max :";
	
	var span4 = document.createElement("span");
	span4.id = "maxValue"
	
	var label5 = document.createElement("span");
	label5.style.color = "grey";
	label5.innerHTML = "Min :";
	
	var span5 = document.createElement("span");
	span5.id = "minValue";
	
	var label6 = document.createElement("span");
	label6.style.color = "grey";
	label6.innerHTML = "Avg :";
	
	var span6 = document.createElement("span");
	span6.id = "avgValue"
	
	
	var label7 = document.createElement("span");
	label7.style.color = "grey";
	label7.innerHTML = "SD :";
	
	var span7 = document.createElement("span");
	span7.id = "sdValue";
	
	var filterHead = document.createElement("div");
	filterHead.classList.add("filterHeadline");
	filterHead.id = "columnValueDataDiv";

	var columnDropDownForLabel = document.createElement("select");	
	columnDropDownForLabel.style.width = "300px";
	columnDropDownForLabel.style.height = "30px";
	columnDropDownForLabel.id = "stat_column";
	
	var defaultOption = document.createElement("option");
	defaultOption.innerHTML = "-- Select --";
	
	for(var i = 0 ; i < columnArr.length; i++) {
		if(dashBoard.currentDataSet.hiddenColumns.indexOf(""+(i+1)) >= 0) {
			continue;	
		}
		
		var columnOption = document.createElement("option");
		columnOption.value = i+1;
		columnOption.innerHTML = columnArr[i];
		columnOption.style.backgroundColor = "grey";
		columnDropDownForLabel.appendChild(columnOption);
	}
	filterHead.appendChild(label2);
	filterHead.appendChild(columnDropDownForLabel);
	
	var br1 = document.createElement("br");	
	filterHead.appendChild(label3);
	filterHead.appendChild(span3);
	filterHead.appendChild(br1);
	
	var br2 = document.createElement("br");	
	filterHead.appendChild(label4);
	filterHead.appendChild(span4);
	filterHead.appendChild(br2);
	
	var br3 = document.createElement("br");	
	filterHead.appendChild(label5);
	filterHead.appendChild(span5);
	filterHead.appendChild(br3);
	
	var br4 = document.createElement("br");	
	filterHead.appendChild(label6);
	filterHead.appendChild(span6);
	filterHead.appendChild(br4);
	
	filterHead.appendChild(label7);
	filterHead.appendChild(span7);
	
	div.appendChild(closeLink);
	div.appendChild(filterHead);
	
	return div;
}


function hideChartContent() {
	document.getElementById("chartRenderContent").style.display = "none";
	document.getElementById("dataRenderContent").style.display = "";
}	
		
function addNewCondition() {
	var isValidationPassed = validateNewCondition();
	var columnList = dashBoard.currentDataSet.dataFrame.listColumns();
	if(!isValidationPassed) {
		alert("Please Fill Previous Conditions First before Adding New Condition");
		return false;
	}
	var filterContentDiv = createFilterConditionContent(columnList, false);
	var filterConditionsDiv = document.getElementById("filterConditionsDiv");
	filterConditionsDiv.appendChild(filterContentDiv);
}

function validateNewCondition() {
	var conditionCount = parseInt(document.getElementById("conditionCount").value);
	for(var i = 1; i <= conditionCount; i++) {
		var colId = "filter_col_"+i;
		var relationId = "filter_relation_"+i;
		var valueId = "filter_value_"+i;
		
		var column = document.getElementById(colId).value;
		var relation = document.getElementById(relationId).value;
		var value = document.getElementById(valueId).value;
		
		if(column.trim() == "" || relation.trim() == "" || value.trim() == "") {
			return false;
		}	
	}
	return true;	
}	

function applyFilterConditions() {
	hideSidebar();
	var dataSetId = dashBoard.dataSetId;
	var resource = JSON.parse(resources)[dataSetId];
	var conditions = {};
	
	
	var conditionCount = parseInt(document.getElementById("conditionCount").value);
	for(var i = 1 ; i <= conditionCount ; i++) {
		var colId = "filter_col_"+i;
		var relationId = "filter_relation_"+i;
		var valueId = "filter_value_"+i;
		
		var column = document.getElementById(colId).value;
		var relation = document.getElementById(relationId).value;
		var value = document.getElementById(valueId).value;
		
		var key = column+"_"+relation
		
		conditions[key] = value;
	}

	console.log(conditions);
	
	
	DataFrame.fromCSV(resource).then(df => {
		var columnNames = df.listColumns();
		df.sql.register('tmp2');
		
		var sql = "SELECT * FROM tmp2";
		var hasNumericConditions = false;
		var numericConditions = [];
		var count = 1;
		for(var key in conditions) {
			var columnAndCondition = key;
			var columnAndConditionArr =  columnAndCondition.split("_");
			var columnId = parseInt(columnAndConditionArr[0]);
			var relationId = columnAndConditionArr[1];
			
			console.log("KEY--> " +columnId+" AND "+relationId);
			var value = conditions[key];
			console.log("VALUE--> "+value);
			
			var columnName = columnNames[columnId - 1];
			console.log(columnName);
			var relationOperator = getRelationOperator(relationId);
			console.log(relationOperator);
			
			if(typeof relationOperator != 'undefined' && typeof columnName != 'undefined' && relationOperator != "" && columnName != "") {
				// Special Case Where We need to use Filter Methods and SQL Queries don't work.
				if(relationOperator == ">") {
					
					var str = ">"+"|"+columnName+"|"+value; 
					numericConditions.push(str);
					hasNumericConditions = true;
					continue;
				} else if(relationOperator == "<") {
					var str = "<"+"|"+columnName+"|"+value; 
					numericConditions.push(str);
					hasNumericConditions = true;
					continue;
				} else if(relationOperator == "between") {
					var val = value.split("-");
					var str1 = ">"+"|"+columnName+"|"+val[0];
					var str2 = "<"+"|"+columnName+"|"+val[1];
					numericConditions.push(str1);
					numericConditions.push(str2);
					hasNumericConditions = true;
					continue;
				}
				if(count == 1) {
					sql = sql.concat(" WHERE "+columnName+" "+relationOperator+" "+value);
				} else {
					sql = sql.concat(" AND "+columnName+" "+relationOperator+" "+value);
				}			
			}	
			count++;
		}	
		console.log(sql);
		console.log(numericConditions);
		
		if(hasNumericConditions && !sql.includes("WHERE")) {
			var hasNumericData = false;
			dashBoard.currentDataSet.dataFrame = df;
			for(var k = 0; k < numericConditions.length; k++) {
				var str =  numericConditions[k].split("|");
				var onlyColumndf = dashBoard.currentDataSet.dataFrame.select(""+str[1]);
				if(!isNumericColumn(onlyColumndf)) {
					alert("Please select only numerical columns for Conditions - greater than, less than and between");
					break;
				}	
				if(str[0] == ">") {
					var value = parseInt(str[2]);
					var df1 = dashBoard.currentDataSet.dataFrame.filter(row => row.get(""+str[1]) > value);
					if(df1.toArray().length > 0) {
						hasNumericData = true;
						dashBoard.currentDataSet.dataFrame = df1;
					} else {
						alert("No such Filtering Data Found");
						break;
					}	
				} else if(str[0] == "<") {
					var value = parseInt(str[2]);
					var df1 = dashBoard.currentDataSet.dataFrame.filter(row => row.get(""+str[1]) < value);
					if(df1.toArray().length > 0) {
						hasNumericData = true;
						dashBoard.currentDataSet.dataFrame = df1;
					} else {
						alert("No such Filtering Data Found");
						break;
					}
				}	
			}
		} else {
			
			var hasSqlData = false;
			var hasNumericData = false;
			var isValidationFailed = false;
			if(hasNumericConditions) {
				for(var k = 0; k < numericConditions.length; k++) {
					var str =  numericConditions[k].split("|");
					var onlyColumndf = df.select(""+str[1]);
					if(!isNumericColumn(onlyColumndf)) {
						alert("Please select only numerical columns for Conditions - greater than, less than and between");
						isValidationFailed = true;
						break;
					}
				}
			}
			if(!isValidationFailed) {	
				var df1 = DataFrame.sql.request(sql);
			}	
			if(df1.toArray().length > 0) {
				hasSqlData = true;
				dashBoard.currentDataSet.dataFrame = df1;
				if(!isValidationFailed && hasNumericConditions) {
					for(var k = 0; k < numericConditions.length; k++) {
						var str =  numericConditions[k].split("|");
						if(str[0] == ">") {
							var value = parseInt(str[2]);
							var df2 = dashBoard.currentDataSet.dataFrame.filter(row => row.get(""+str[1]) > value);
							if(df2.toArray.length > 0) {
								hasNumericData = true;
								dashBoard.currentDataSet.dataFrame = df2;
							} else {
								alert("No Filtering Data Found");
								break;
							}	
						} else if(str[0] == "<") {
							var value = parseInt(str[2]);
							var df2 = dashBoard.currentDataSet.dataFrame.filter(row => row.get(""+str[1]) < value);
							if(df2.toArray.length > 0) {
								hasNumericData = true;
								dashBoard.currentDataSet.dataFrame = df2;
							} else {
								alert("No Filtering Data Found");
								break;
							}	
						}	
					}
				}
			} else {
				alert("No Filtering Data Found.");
			}	
		}	
		DataFrame.sql.dropTables();
		var parentDiv = document.getElementById("parent-header");
		var dataContentParentDiv = document.getElementById("parent-data");
		clearChildContent(parentDiv);
		clearChildContent(dataContentParentDiv);
		renderDataSet(parentDiv, dashBoard.currentDataSet, dataContentParentDiv);	
	});
}

function getRelationOperator(relationId) {
	if(relationId == 'is') {
		return "=";
	} else if(relationId == 'is not') {
		return "!=";
	} else if(relationId == 'starts with') {
		return "LIKE";
	} else if(relationId == 'contains') {
		return "LIKE";
	} else if(relationId == 'greater than')	{
		return ">";
	} else if(relationId == 'less than') {
		return "<";
	} else if(relationId == 'between') {
		return "between";
	}	
}

function createAndRenderChart() {
	hideSidebar();
	var chartType = document.getElementById("chart_type").value;
	var labelColumnId = parseInt(document.getElementById("chart_label_data").value);
	var columnValueCount = parseInt(document.getElementById("columnValueCount").value);
	var df = dashBoard.currentDataSet.dataFrame;
	var columnArray = df.listColumns();
	
	var labelColumnName = columnArray[labelColumnId-1];
	var labelData;
	if(chartType == 'pie') {
		labelData = df.distinct(labelColumnName).toArray();
	} else {
		labelData = df.select(labelColumnName).toArray();
	}
	
	var labelDataArray = [];
	var size = labelData.length;
	if(size > 1000) {
			size = 1000;
	}	
	for(var i = 0; i < size; i++) {
		var singleLabelArray = labelData[i];
		labelDataArray.push(singleLabelArray[0]);		
	}
	
	var backgroundColors = [];
	
	var dataSets = [];
	var backgroundColorCount = 0;
	var statDivs = document.getElementsByClassName("statDiv");
		if(typeof statDivs != "undefined") {
				for(var p = 0; p < statDivs.length; p++) {
					statDivs[p].parentNode.removeChild(statDivs[p]);
				}		
		}
	for(var i = 1; i <= columnValueCount; i++) {
		var id = "chart_value_data_"+i;
		var valueColumnId = parseInt(document.getElementById(id).value);
		var valueColumnName = columnArray[valueColumnId-1];
		
		var onlyColumndf = df.select(valueColumnName);
		if(!isNumericColumn(onlyColumndf)) {
			alert("Please select only numerical columns for Value Data");
			return false;
		}
		var valueDataArray = [];
		if(chartType == "pie") {
			var pieBackgroundColors = [];
			for(var p = 0; p < labelDataArray.length; p++) {
				var pieChartDf = df.filter(row => row.get(""+labelColumnName) == labelDataArray[p]);
				var size = pieChartDf.toArray().length;
				if(size>1000) {
					size = 1000;
				}	
				valueDataArray.push(size);
				
				var graphBackground = ColorGenerator.generateColor(chartType, 0 , true);
					
				pieBackgroundColors.push(graphBackground);	
					
			}
			var singleColumnData = {};
			singleColumnData["data"] = valueDataArray;				
			singleColumnData["backgroundColor"] = pieBackgroundColors; 
			dataSets.push(singleColumnData);
		}	
		var valueData = onlyColumndf.toArray();
		var size = valueData.length;
		if(size > 1000) {
			size = 1000;
		}
		
		if(chartType != "pie") {
			for(var j = 0; j < size; j++) {	
				var singleValueArray = valueData[j];
				valueDataArray.push(singleValueArray[0]);
				
				if(columnValueCount == 1 && chartType != "line") {
					var graphBackground = ColorGenerator.generateColor(chartType, 0 , true);
					backgroundColors.push(graphBackground);
				}
			}
		
			var statDiv = createStatDiv(valueColumnName, valueDataArray);
			document.getElementById("chartContainer").insertBefore(statDiv, document.getElementById("chartCanvas"));
			var singleColumnData = {};
			singleColumnData["label"] = valueColumnName;
			singleColumnData["data"] = valueDataArray;
			
			if(backgroundColorCount > lineBackgroundColors.length) {
				backgroundColorCount = 0;
			}
			if(columnValueCount > 1 || chartType == "line") {
				if(chartType == "line")	 {
					var graphBackground = ColorGenerator.generateColor(chartType, backgroundColorCount , false);
					singleColumnData["backgroundColor"] = graphBackground;
					backgroundColorCount++;
				} else {
					var graphBackground = ColorGenerator.generateColor(chartType, 0 , true);
					singleColumnData["backgroundColor"] = graphBackground;
				}	
			}
			if(columnValueCount == 1) {	
				singleColumnData["backgroundColor"] = backgroundColors;
			}	
				
			backgroundColorCount = backgroundColorCount + 1;
			dataSets.push(singleColumnData);
		}	
	}
	console.log(dataSets);	
	
	
	var chart = ChartFactory.createChartTypeObject(chartType);
	chart.createConfig(labelDataArray, dataSets);
	chart.createChart("chartCanvas");
	document.getElementById("chartRenderContent").style.display = "";
	document.getElementById("dataRenderContent").style.display = "none";
}	

function createStatDiv(columnName, valueDataArray) {
	var div = document.createElement("div");
	div.style.display = "block";
	div.classList.add("statDiv");
	
	var label = document.createElement("label");
	label.innerHTML = columnName + "-";
	
	var label1 = document.createElement("label");
	label1.innerHTML = "Count : ";
	
	var span1 = document.createElement("span");
	span1.innerHTML = valueDataArray.length;
	
	var label2 = document.createElement("label");
	label2.innerHTML = ",Max : ";
	
	var span2 = document.createElement("span");
	span2.innerHTML = dashBoard.currentDataSet.dataFrame.stat.max(columnName);
	
	var label3 = document.createElement("label");
	label3.innerHTML = ",Min : ";
	
	var span3 = document.createElement("span");
	span3.innerHTML = dashBoard.currentDataSet.dataFrame.stat.min(columnName);
	
	var label4 = document.createElement("label");
	label4.innerHTML = ",Avg : ";
	
	var span4 = document.createElement("span");
	span4.innerHTML = dashBoard.currentDataSet.dataFrame.stat.mean(columnName);
	
	div.appendChild(label);
	div.appendChild(label1);
	div.appendChild(span1);
	div.appendChild(label2);
	div.appendChild(span2);
	
	div.appendChild(label3);
	div.appendChild(span3);
	div.appendChild(label4);
	div.appendChild(span4);
	
	return div;
}

function isNumericColumn(dataFrame) {
	var arr = dataFrame.toArray();
	
	for(var i = 0;  i < arr.length; i++) {
		var singleRowArray = arr[i];
		var singleValue = singleRowArray[0];
		if(typeof singleValue == "undefined") {
			return false;
		}
		if(!singleValue) {
			return false;
		}	
		if(isNaN(singleValue)) {
			return false;
		}	
	}
	return true;	
}

function viewStats() {
	var statColumn = parseInt(document.getElementById("stat_column").value);
	var columnName = dashBoard.currentDataSet.dataFrame.listColumns()[statColumn-1];
	var onlyColumndf = dashBoard.currentDataSet.dataFrame.select(""+columnName);
	if(!isNumericColumn(onlyColumndf)) {
		alert("Please select only numerical columns for Conditions - greater than, less than and between");
		return false;
	}
	var df = dashBoard.currentDataSet.dataFrame;
	var size = onlyColumndf.toArray().length;
	if(size > 1000) {
		size = 1000;
	}	
	document.getElementById("countValue").innerHTML = "  "+size;
	document.getElementById("maxValue").innerHTML = "     "+df.stat.max(""+columnName);
	document.getElementById("minValue").innerHTML = "    "+df.stat.min(""+columnName);
	document.getElementById("avgValue").innerHTML = "    "+df.stat.mean(""+columnName);
	document.getElementById("sdValue").innerHTML = "     "+df.stat.sd(""+columnName);
}	


function exportDataset(format){
	var df = dashBoard.currentDataSet.dataFrame;
	var datasetArray = df.toArray();
	
	var columnArray = df.listColumns();
	
	var headers = {};
	var contents = [];
	var content = {};
	
	for(var i in columnArray) {
		headers[i] = columnArray[i];
	}
	
	for(var j in datasetArray){
		for(var k in columnArray){
			content[k] = datasetArray[j][k]
		}
		contents.push(content);
		content = {};
	}
	if(format == "csv"){
		exportCSVFile(headers, contents, selectedDataset) // to export data in csv format 
	}
	if(format == "json"){
		exportjsonFile(headers, contents)
	}
	
}

function convertToCSV(objArray) {
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    var str = '';

    for (var i = 0; i < array.length; i++) {
        var line = '';
        for (var index in array[i]) {
            if (line != '') line += ','

            line += array[i][index];
        }

        str += line + '\r\n';
    }

    return str;
}

function exportCSVFile(headers, contents, fileTitle) {
    if (headers) {
    	contents.unshift(headers);
    }

    // Convert Object to JSON
    var jsonObject = JSON.stringify(contents);

    var csv = this.convertToCSV(jsonObject);

    var exportedFilenmae = fileTitle || 'export.csv';

    var blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    if (navigator.msSaveBlob) { // IE 10+
        navigator.msSaveBlob(blob, exportedFilenmae);
    } else {
        var link = document.createElement("a");
        if (link.download !== undefined) { // feature detection
            // Browsers that support HTML5 download attribute
            var url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            link.setAttribute("download", exportedFilenmae);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
}

function exportjsonFile(headers, contents){
	
	if(headers){
		contents.unshift(headers);
	}
	
	var jsonObject = JSON.stringify(contents);
	
	var blob = new Blob([jsonObject], { type: 'text/json;charset=utf-8;' });
    if (navigator.msSaveBlob) { // IE 10+
        navigator.msSaveBlob(blob, exportedFilenmae);
    } else {
        var link = document.createElement("a");
        if (link.download !== undefined) { // feature detection
            // Browsers that support HTML5 download attribute
            var url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            link.setAttribute("download", "dataset.json");
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
}
