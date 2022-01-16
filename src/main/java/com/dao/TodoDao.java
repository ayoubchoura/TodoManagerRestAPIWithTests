package com.dao;

import com.entities.Todo;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TodoDao extends AbstractDAO<Todo> {
    public TodoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Todo findTodoById(Long id){
        return get(id);
    }

    public Todo findTodByName(String name){
        return get(name);
    }

    public List<Todo> findAllTodos(){
        return list(namedTypedQuery("com.entities.Todo.findAll"));
    }

    public Todo addNewTodo(Todo todo){
        return persist(todo);
    }


    public void delTodo(Todo todo){

         currentSession().delete(todo);
    }




}
