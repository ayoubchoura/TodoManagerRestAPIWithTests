package com.resources;

import com.dao.TodoDao;
import com.entities.Todo;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/TodoManager")
@Produces(MediaType.APPLICATION_JSON)
public class TodoManagerResource {
    private final TodoDao todoDao;

    public TodoManagerResource(TodoDao todoDao) {
        this.todoDao = todoDao;
    }


    @GET
    @Path("")
    public String getGreeting(@PathParam("name") String name){
        return "Welcome to Todo Manager Rest API ";
    }

    @POST
    @Path("/todos")
    @UnitOfWork
    public Todo PostTodo(@Valid Todo todo){
        return todoDao.addNewTodo(todo);
    }

    @GET
    @Path("/todos")
    @UnitOfWork
    public List<Todo> returnTodos(){
        return todoDao.findAllTodos();
    }

    @GET
    @Path("/todos/{id}")
    @UnitOfWork
    public Todo returnTodo(@PathParam("id") Long id){
        return todoDao.findTodoById(id);
    }

    @PUT
    @Path("todos/{id}")
    @UnitOfWork
    public void UpdateTodo(@PathParam("id") Long id, @Valid Todo todo){
        Todo todo1 = todoDao.findTodoById(id);
        todo1.setDescription(todo.getDescription());
        todo1.setName(todo.getName());
        todo1.setTasks(todo.getTasks());
        todoDao.addNewTodo(todo1);
    }

    @DELETE
    @Path("todos/{id}")
    @UnitOfWork
    public void DelTodo(@PathParam("id") Long id){
         Todo todo= todoDao.findTodoById(id);
         todoDao.delTodo(todo);

    }





}
