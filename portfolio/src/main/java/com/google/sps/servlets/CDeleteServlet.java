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

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@WebServlet("/delete-data")
public class CDeleteServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    long id = Long.parseLong(request.getParameter("id"));
    String comment = request.getParameter("comment-container");
    int z = 0;
    String key = "Task" + z;
    z++;
    
    Key taskEntityKey = KeyFactory.createKey("Task", id);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.delete(taskEntityKey);
    response.sendRedirect("/index.html");
  }
}

/** Servlet that returns some example content. TODO: modify this file to handle comments data 
@WebServlet("/reload")
public class PartialServlet extends HttpServlet {
  public int numComments = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    
    try {
        System.out.println(request);
      System.out.println(request.getParameter("nclol"));
      System.out.println(request.getParameter("test"));
      numComments = Integer.parseInt(request.getParameter("num-comments"));//getNumComments(request);//Integer.parseInt(request.getParameter("num-comments").value); //getNumComments(request);
    } catch (NumberFormatException e) {
      //System.err.println("Could not convert to int: " + ncString);
      numComments = Integer.MAX_VALUE - 1000;
    }

    System.out.println(numComments);

    //int  = //3; //getPlayerChoice(request);
    //response.setContentType("text/html");
    //response.getWriter().println("Please enter how many comments you want to see");

    List<Task> tasks = new ArrayList<>();
    int i = 0;
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String title = (String) entity.getProperty("text-input");
      long timestamp = (long) entity.getProperty("timestamp");

      Task task = new Task(id, title, timestamp);
      tasks.add(task);
      i++;
      if (i == numComments) {
          break;
      }
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));
  }

  private int getNumComments(HttpServletRequest request) {
    // Get the input from the form.
    String ncString = request.getParameter("num-comments");

    // Convert the input to an int.
    int playerChoice;
    try {
      playerChoice = Integer.parseInt(ncString);
    } catch (NumberFormatException e) {
      System.err.println("Could not convert to int: " + ncString);
      return -1;
    }

    return playerChoice;
  }
}*/