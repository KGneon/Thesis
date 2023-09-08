
//not working, trying new...

function createNode(element) {
	return document.createdElement(element); //Creating type of element passing in parameters
}
function append(parent, el) {
	return parent.appendChild(el); //Append second parameter(element) to first one
}

const ul = document.getElementById('students');

fetch("api/studentas")
.then((resp) => resp.json()) //Data to JSON
.then(function(data) {
	let students = data;
	return students.map(function(student){
		let li = createNode('li'),
		span = createNode('span');
		li.innerHTML = `${student.studentId} ${student.studentName}`;
		append(li, span);
		append(ul, li);
	})
})