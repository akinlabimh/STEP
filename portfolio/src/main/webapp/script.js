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

function load() {
    fetch('/logincheck').then(response => response.json()).then((status) => {
    console.log(status);
    //window.alert(`gLS(): ${status}`);

    if (status == 0) {
        //window.alert(`if: ${status}`);
        //window.alert(lstatus);
        //window.alert("login");
        //document.getElementById('comment-section').style.visibility="none";
        document.getElementById('login-link').style.visibility="visible";
    } else {
        //document.getElementById('login-link').style.visibility="none";
        document.getElementById('comment-section').style.visibility="visible";
        loadComments();
    }


  });

    
    //loadComments();
    //hideform();
}

/* /function load() {
    const lstatus = getLoginStatus();
    //window.alert(lstatus);
    window.alert(`L(): ${lstatus}`);

    if (lstatus == 0) {
        window.alert(`if: ${lstatus}`);
        //window.alert(lstatus);
        //window.alert("login");
        document.getElementById('comment-container').style.display="none";
    } else {
        loadComments();
    }

    //loadComments();
    //hideform();
}*/

/** Creates comments as list items and puts them onto the page */
function loadComments() {
  fetch('/load').then(response => response.json()).then((tasks) => {
    const taskListElement = document.getElementById('comment-container');
    //var nc = document.getElementById("num-comments").value;
    
    tasks.forEach((task) => {
      taskListElement.appendChild(createComment(`${task['email']} says: ${task['title']}`));
      //taskListElement.appendChild(createComment(nc));
    })
  });
}

function getLoginStatus() {
  fetch('/login').then(response => response.json()).then((status) => {
    console.log(status);
    window.alert(`gLS(): ${status}`);
    //return status;
    /** /console.log(status);
    console.log(status);
    console.log(status);
    console.log(status);
    //window.alert(status);
    console.log(status);
    console.log(status);
    console.log(status);
    const statusElement = document.getElementById('status-container');
    //var nc = document.getElementById("num-comments").value;
    const liElement = document.createElement('li');
    liElement.innerText = status;
    statusElement.appendChild(liElement);*/
  });
  return status;
}    


/** UNDER CONSTRUCTION */
function deleteTask(task) {
  const params = new URLSearchParams();
  params.append('comment', task.title);
  fetch('/delete-data', {method: 'POST', body: params});
}

/** UNDER CONSTRUCTION */
function deleteComments() {
  fetch('/load').then(response => response.json()).then((tasks) => {
  const taskListElement = document.getElementById('comment-container');

  tasks.forEach(task => {
      taskListElement.deleteTask(task);
      //taskListElement.appendChild(createComment(nc));
    })
  });
}


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