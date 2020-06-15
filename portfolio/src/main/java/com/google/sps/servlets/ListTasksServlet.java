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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/load")
public class ListTasksServlet extends HttpServlet {
  public int numComments = 0;
  

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    
    
    //int  = //3; //getPlayerChoice(request);
    //response.setContentType("text/html");
    //response.getWriter().println("Please enter how many comments you want to see");

    List<Task> tasks = new ArrayList<>();
    int i = 0;
    if (DataServlet.ncInput != 0) {
        for (Entity entity : results.asIterable()) {
        long id = entity.getKey().getId();
        String title = (String) entity.getProperty("text-input");
        long timestamp = (long) entity.getProperty("timestamp");
        String email = (String) entity.getProperty("email");

        Task task = new Task(id, title, timestamp, email);
        tasks.add(task);
        //System.out.println(DataServlet.ncInput);
        i++;
        if (i == DataServlet.ncInput) {break;}
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
}