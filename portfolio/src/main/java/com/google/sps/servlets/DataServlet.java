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
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;



/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/add")
public class DataServlet extends HttpServlet {

  //private List<String> messages;
  //private List<String> comments = new ArrayList<>();
  public static int ncInput;

  

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);
    // Get the input from the form.
    String text = request.getParameter("text-input");
    try {
        int plzjustwork = Integer.parseInt(request.getParameter("nclol"));
        ncInput = plzjustwork;
    } catch (NumberFormatException e) {
        ncInput = -1;
    }
    if (text.length() > 0) {
        //int z = 0;
        //String key = "Task" + z;
        //z++;
        UserService userService = UserServiceFactory.getUserService();

        long timestamp = System.currentTimeMillis();
        //Key taskEntityKey = KeyFactory.createKey("Task", id);
        String userEmail = userService.getCurrentUser().getEmail();

        Entity taskEntity = new Entity("Task"); 
        taskEntity.setProperty("text-input", text);
        taskEntity.setProperty("timestamp", timestamp);
        taskEntity.setProperty("email", userEmail);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        //PreparedQuery results = datastore.prepare(query);
        datastore.put(taskEntity);
    }
    //response.getWriter().println(text);
    response.sendRedirect("/index.html");
  }
}

    /**response.sendRedirect("/index.html");
    //taskEntity.setProperty("timestamp", timestamp);
    //boolean upperCase = Boolean.parseBoolean(getParameter(request, "upper-case", "false"));
    //boolean sort = Boolean.parseBoolean(getParameter(request, "sort", "false"));

    // Convert the text to upper case.
    //if (upperCase) {
    //  text = text.toUpperCase();
    //}

    // Break the text into individual words.
    //String[] words = text.split("\\s*,\\s*");

    // Sort the words.
    //if (sort) {
    //  Arrays.sort(words);
    //}

    List<Task> tasks = new ArrayList<>();
    
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String oldTitle = (String) entity.getProperty("text-input");
      long oldTimestamp = (long) entity.getProperty("timestamp");

      Task task = new Task(id, oldTitle, oldTimestamp);
      tasks.add(task);
      comments.add(oldTitle);
    }

    Gson gson = new Gson();

    response.setContentType("text/html;");
    for (String c: comments) {
        response.getWriter().println(c);
    }
    //response.sendRedirect("/index.html");
    
    // Respond with the result.
    //response.setContentType("text/html;");
    //response.getWriter().println(Arrays.toString(tasks));
  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

  /**@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String title = request.getParameter("title");
    long timestamp = System.currentTimeMillis();

    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("title", title);
    taskEntity.setProperty("timestamp", timestamp);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

    response.sendRedirect("/index.html");
  }

  

  /**@Override
  public void init() {
    messages = new ArrayList<>();
    /**messages.add("lol");
    messages.add("lmao");
    messages.add("haha");
    /**String json = convertToJson(messages);
    response.getWriter().println(json);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "text-input", "");
    //response.getWriter().println(text);

    // Break the text into individual words.
    String[] words = text.split("\\s*,\\s*");

    //for (w : words) {
    //    messages.add(w);
    //}

    String json = convertToJson(messages);

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(Arrays.toString(words));
  }

  private String convertToJson(List<String> messages) {
    String json = "{";
    for (int i = 1; i < messages.size()-1; i++) {
        json += messages.get(i) + ", ";
    }
    json += messages.get(messages.size()-1);
    json += "}";
    return json;
  }


  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //doPost(request, response);
    String text = getParameter(request, "text-input", "");
    response.getWriter().println(text);
    response.setContentType("text/html;");
    response.getWriter().println("Hey, Akin!");
  } 

  /**@Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String msg = messages.get((int) (Math.random() * messages.size()));

    String json = convertToJson(messages);
    response.getWriter().println(json);

    /**response.setContentType("text/html;");
    response.getWriter().println(msg);
  } */



