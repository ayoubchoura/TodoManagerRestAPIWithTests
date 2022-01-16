package com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="todo")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.entities.Todo.findAll",
                        query = "SELECT p FROM Todo p"
                ),
                @NamedQuery(name = "com.entities.remove", query = "DELETE FROM Todo b "
                        + "where b.id = :id")
        })
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ElementCollection
    @Column(name = "tasks")
    private List<String> tasks;



    //private Date dateOfRelease;

    public Todo() {
    }

    public Todo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Todo(String name, String description, List<String> tasks) {

        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }

    public Todo(Long id, String name, String description, List<String> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
