// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

function loadComments() {
  fetch('/load').then(response => response.json()).then((tasks) => {
    const taskListElement = document.getElementById('greeting-container');
    //var nc = document.getElementById("num-comments").value;
    
    tasks.forEach((task) => {
      taskListElement.appendChild(createComment(task['title']));
      //taskListElement.appendChild(createComment(nc));
    })
  });
}

function deleteTask(task) {
  const params = new URLSearchParams();
  params.append('id', task.id);
  fetch('/delete-task', {method: 'POST', body: params});
}

function deleteComments() {
  tasks.forEach((task) => {
      taskListElement.deleteTask((task['title']));
      //taskListElement.appendChild(createComment(nc));
    })
  
}

/*function loadLimitedComments() {
  fetch('/reload').then(response => response.json()).then((tasks) => {
    const taskListElement = document.getElementById('greeting-container');
    //var nc = document.getElementById("num-comments").value;
    
    tasks.forEach((task) => {
      taskListElement.appendChild(createComment(task['title']));
      //taskListElement.appendChild(createComment(nc));
    })
  });
}*/

/*
function loadLimitedComments() {
  var num = 0;
  fetch('/load').then(response => response.json()).then((tasks) => {
    const taskListElement = document.getElementById('greeting-container');
    //num = Integer.parseInt(document.getElementById('number').value);
    //ftft = numComments;
    //num = 3;

    //for (i = 0; i < num; i++) {
    //    taskListElement.appendChild(createComment(tasks.get(i)['title']));
    //}

    tasks.forEach((task) => {
      taskListElement.appendChild(createComment(task['title']));
      num++;
      if (num == 3) {break;}
      //taskListElement.appendChild(createComment(nc));
    })
  });  
}*/

/** Creates an element that represents a task, including its delete button. */
function createComment(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

/**
 * Adds a random greeting to the page.
 
function addRandomGreeting() {
  const greetings =
      ['Whats up?', 'lmao', 'this is fun so far', 'cant wait for CSS'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function addQuoteToDomx(data) {
  console.log('Adding quote to dom: ' + data);

  const quoteContainer = document.getElementById('greeting-container');
  quoteContainer.innerText = data;
}

function addQuoteToDom(data) {
  fetch('/data').then(response => response.text()).then((data) => {
    document.getElementById('greeting-container').innerText = data;
  });
}

function getComments(data) {
  fetch('/data').then(response => response.text()).then((quote) => {
    document.getElementById('greeting-container').innerText = quote;
  });
}

function getRandomQuoteUsingArrowFunctions() {
  fetch('/random-quote').then(response => response.text()).then((quote) => {
    document.getElementById('greeting-container').innerText = quote;
  });
}*/