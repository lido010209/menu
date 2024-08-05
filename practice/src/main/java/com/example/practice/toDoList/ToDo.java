package com.example.practice.toDoList;

import java.time.LocalDate;

public class ToDo {
    private String task;
    private LocalDate date;
    private boolean done;
    private int id;

    public ToDo() {}
    public ToDo(String task, LocalDate date){
        this.task=task;
        this.date=date;
    }

    public ToDo(String task, LocalDate date, boolean done){
        this.task=task;
        this.date=date;
        this.done=done;
    }

    public ToDo(String task, LocalDate date, int id) {
        this.task = task;
        this.date = date;
        this.id = id;
    }

    public ToDo(String task, LocalDate date, boolean done, int id) {
        this.task = task;
        this.date = date;
        this.done = done;
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        this.done = true;
    }

    public void notDone() {
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
