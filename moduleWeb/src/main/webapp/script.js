function findById(source, id) {
	for (var i = 0; i < source.length; i++) {
		if (source[i].id === id) {
			return source[i];
		}
	}
	throw "Couldn't find object with id: " + id;
}

$(document).ready(function() {
	viewRequest("view", 1);
})

function addRequest(reqURL) {

	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var params = 'name=' + encodeURIComponent($('#name')[0].value)
			+ '&description=' + encodeURIComponent($('#description')[0].value)
			+ '&priority=' + encodeURIComponent($('#priority')[0].value);
	xmlhttp.open("POST", reqURL, false);
	xmlhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	xmlhttp.send(params);
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			if (xmlhttp.responseText != "") {
				var obj = jQuery.parseJSON(xmlhttp.responseText);
				if (obj.error != null) {
					document.getElementById("error").innerHTML = obj.error;
					return false;
				}
			}
		}
	}
	$('#name')[0].value = null;
	$('#description')[0].value = "";
	$('#priority')[0].value = 1;
	return true;
}

function deleteRequest(reqURL, id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var params = 'id=' + encodeURIComponent(id);
	xmlhttp.open("POST", reqURL, false);
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded')
	xmlhttp.send(params);
}

function viewRequest(reqURL, page) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var params = 'page=' + encodeURIComponent(page);
	xmlhttp.open("POST", reqURL, false);
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded')
	xmlhttp.send(params);

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var obj = jQuery.parseJSON(xmlhttp.responseText);

			var TableTitle = [ "Task", "Priority", "Action" ];

			var mytable = $('<table/>', {
				class : 'table'
			}).append($('<thead/>'), $('<tfoot/>'), $('<tbody/>'));

			var TitleCell = $('<tr/>');
			$.each(TableTitle, function(myIndex, myData) {
				TitleCell.append($('<th/>', {
					class : 'th',
					text : myData
				}));
			});
			$("thead", mytable).append(TitleCell);

			$.each(obj.tasks, function() {

				var DataCell = $('<tr/>');

				DataCell.append($('<td/>', {
					class : 'td',
				}).append(
						$('<a/>', {
							href : '#',
							text : this.name,
							onclick : 'taskRequest("task", ' + this.id + ','
									+ obj.currentPage + ');'
						})));
				DataCell.append($('<td/>', {
					class : 'td',
					text : findById(obj.priorities, this.priorityId).name
				}));
				DataCell.append($('<td/>', {
					class : 'td'
				}).append(
						$('<a/>', {
							href : '#',
							text : 'Delete',
							onclick : 'deleteRequest("delete", ' + this.id
									+ ');viewRequest("view", '
									+ obj.currentPage + ');'
						})));

				$("tbody", mytable).append(DataCell);
			});

			blockAddTask(page, obj.priorities);

			$('#message').empty();
			$('#message').append(mytable);

			if (obj.currentPage > 1) {
				$('#message').append(
						$('<a/>', {
							href : '#',
							text : 'Prev',
							onclick : 'viewRequest("view", '
									+ (obj.currentPage - 1) + ');'
						})).append(" ");
			}
			if (obj.nextPage) {
				$('#message').append(
						$('<a/>', {
							href : '#',
							text : 'Next',
							onclick : 'viewRequest("view", '
									+ (obj.currentPage + 1) + ');'
						}));
			}

		} else {
			alert('Something is wrong !!');
		}
	}
}

function show_hide() {
	var obj = document.getElementById('show');
	if (obj.style.display == 'none')
		obj.style.display = 'block';
	else
		obj.style.display = 'none';
}

function saveRequest(reqURL) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var id = $('input[name="task-id"]')[0].value;
	var name = $('input[name="task-name"]')[0].value;
	var description = $('textarea[name="task-description"]')[0].value;
	var priority = $('select[name="task-priority"]')[0].value;
	var params = 'id=' + encodeURIComponent(id) + '&name='
			+ encodeURIComponent(name) + '&description='
			+ encodeURIComponent(description) + '&priority='
			+ encodeURIComponent(priority);

	xmlhttp.open("POST", reqURL, false);
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	xmlhttp.send(params);
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			if (xmlhttp.responseText != "") {
				var obj = jQuery.parseJSON(xmlhttp.responseText);
				if (obj.error != null) {
					document.getElementById("error").innerHTML = obj.error;
					return false;
				}
			}
		}
	}
	return true;

}

function edit(id, page) {
	$('input[name="task-name"]')[0].disabled = false;
	$('textarea[name="task-description"]')[0].disabled = false;
	$('select[name="task-priority"]')[0].disabled = false;
	var aa = $('a[name="task-edit"]')[0];
}

function blockAddTask(page, priorities) {
	var mytable = $('<table/>', {
		class : 'table'
	}).append($('<thead/>'), $('<tfoot/>'), $('<tbody/>'));

	var DataCella = $('<tr/>');
	DataCella.append($('<td/>', {
		text : 'Task',
		class : 'th'
	}));
	DataCella.append($('<td/>').append($('<input/>', {
		type : 'text',
		id : 'name'
	})));
	$("tbody", mytable).append(DataCella);
	var DataCellb = $('<tr/>');
	DataCellb.append($('<td/>', {
		text : 'Description',
		class : 'th'
	}));
	DataCellb.append($('<td/>').append($('<textarea/>', {
		id : 'description',
		style : 'margin: 0px; width: 500px; height: 100px;'
	})));
	$("tbody", mytable).append(DataCellb);
	var DataCellc = $('<tr/>');
	DataCellc.append($('<td/>', {
		text : 'Priority',
		class : 'th'
	}));

	var myselect = $('<select/>', {
		id : 'priority'
	});
	$.each(priorities, function() {
		$('<option/>', {
			val : this.id,
			text : this.name
		}).appendTo(myselect);
	});
	DataCellc.append($('<td/>').append(myselect));
	$("tbody", mytable).append(DataCellc);

	var lh = $('<div/>').append($('<a/>', {
		href : '#',
		text : 'Add new book',
		onclick : 'show_hide()'
	}));
	var err = $('<p/>').append($('<font/>', {
		id : 'error',
		color : 'red',
		face : 'Arial'
	}));
	var saveTask = $('<p/>').append($('<a/>', {
		href : '#',
		onclick : ';checkAdd(' + page + ');',
		text : 'Save'
	}));
	var block = $('<div/>', {
		id : 'show',
		style : 'display: none;'
	}).append(err).append(mytable).append(saveTask);
	$('#addtaskform').empty();
	$('#addtaskform').append(lh).append(block);
}

function checkAdd(page) {
	if (addRequest("add")) {
		viewRequest("view", page);
	}
}

function checkEdit(page, id, page) {
	if (saveRequest("add")) {
		taskRequest("task", id, page);
	}
}

function taskRequest(reqURL, id, page) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var params = 'id=' + encodeURIComponent(id) + '&page='
			+ encodeURIComponent(page);
	xmlhttp.open("POST", reqURL, false);
	xmlhttp.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded')
	xmlhttp.send(params);

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var obj = jQuery.parseJSON(xmlhttp.responseText);

			var TableTitle = [ "Task", "Description", "Priority", "Action" ];
			var mytable = $('<table/>', {
				class : 'table'
			}).append($('<thead/>'), $('<tfoot/>'), $('<tbody/>'));

			var TitleCell = $('<tr/>');
			$.each(TableTitle, function(myIndex, myData) {
				TitleCell.append($('<th/>', {
					class : 'th',
					text : myData
				}));
			});
			$("thead", mytable).append(TitleCell);

			var DataCell = $('<tr/>');

			DataCell.append($('<td/>', {
				class : 'td',
			}).append($('<input/>', {
				disabled : true,
				type : 'text',
				value : obj.task.name,
				name : 'task-name'
			})).append($('<input/>', {
				type : 'hidden',
				value : obj.task.id,
				name : 'task-id'
			})));
			DataCell.append($('<td/>', {
				class : 'td',
			}).append($('<textarea/>', {
				disabled : true,
				text : obj.task.description,
				name : 'task-description',
				style : 'margin: 0px; width: 500px; height: 100px;'
			})));

			var myselect = $('<select/>', {
				disabled : true,
				name : 'task-priority'
			});
			$.each(obj.priorities, function() {
				$('<option/>', {
					val : this.id,
					text : this.name
				}).appendTo(myselect);
			});
			$('option[value="' + obj.task.priorityId + '"]', myselect).attr(
					'selected', 'selected');
			DataCell.append($('<td/>', {
				class : 'td'
			}).append(myselect));
			DataCell.append($('<td/>', {
				class : 'td'
			}).append($('<a/>', {
				href : '#',
				name : 'task-edit',
				text : 'Edit',
				onclick : 'edit(' + obj.task.id + ',' + page + ')'
			})).append(" ").append($('<a/>', {
				href : '#',
				name : 'task-save',
				text : 'Save',
				onclick : 'checkEdit("add", ' + obj.task.id + ',' + page + ');'
			})));

			$("tbody", mytable).append(DataCell);

			$('#addtaskform').empty();
			$('#message').empty();
			$('#message').append('<p/>').append($('<font/>', {
				id : 'error',
				color : 'red',
				face : 'Arial'
			}));
			$('#message').append(mytable);
			$('#message').append($('<a/>', {
				href : '#',
				text : 'Back',
				onclick : 'viewRequest("view", ' + (obj.currentPage) + ');'
			}));

		} else {
			alert('Something is wrong!');
		}
	}
}
