package com.example.todoapp.api;

import com.example.todoapp.constant.ApiConstants;
import com.example.todoapp.api.interceptor.AuthenticationInterceptor;
import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.dto.RegisterDTO;
import com.example.todoapp.model.dto.ToDoItemDTO;
import com.example.todoapp.model.dto.Token;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@Rest(rootUrl = ApiConstants.ROOT_URL,
        converters = {GsonHttpMessageConverter.class, FormHttpMessageConverter.class},
        interceptors = AuthenticationInterceptor.class)
public interface RestApi {

    @Header(name = "Content-Type", value = "application/x-www-form-urlencoded")
    @Post(value = ApiConstants.LOGIN_PATH)
    Token login(@Body LinkedMultiValueMap<String, String> accountInfo);

    @Get(value = ApiConstants.TASK_PATH)
    List<ToDoItem> getAllToDoItems();

    @Post(value = ApiConstants.TASK_PATH)
    @Header(name = "Content-Type", value = "application/json")
    ToDoItem createToDoItem(@Body ToDoItemDTO toDoItem);

    @Header(name = "Content-Type", value = "application/json")
    @Post(value = ApiConstants.REGISTER_PATH)
    ResponseEntity register(@Body RegisterDTO registerDTO);
}
