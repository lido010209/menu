package com.example.practice.toDoList;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    private final List<ToDo> toDoList=new ArrayList<>();
    private int id=1;



    public ToDo createToDo(String task, LocalDate date){
        ToDo newTask= new ToDo(task, date, id);
        id++;
        toDoList.add(newTask);
        return newTask;
    }

    public ToDo updateEditTask(int id, String task, LocalDate date){
        int targetIdx=-1;
        for (int i = 0; i < toDoList.size(); i++) {
            ToDo toDo=toDoList.get(i);
            if (toDo.getId()==id){
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1){
            toDoList.get(targetIdx).setTask(task);
            toDoList.get(targetIdx).setDate(date);
        }
        return toDoList.get(targetIdx);
    }

    public boolean updateDone(int id){
        int targetIdx=-1;
        for (int i = 0; i < toDoList.size(); i++) {
            ToDo toDo=toDoList.get(i);
            if (toDo.getId()==id){
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1) {
            toDoList.get(targetIdx).done();
            return true;
        }
        return false;
    }

    public boolean updateNotDone(int id){
        int targetIdx=-1;
        for (int i = 0; i < toDoList.size(); i++) {
            ToDo toDo=toDoList.get(i);
            if (toDo.getId()==id){
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1) {
            toDoList.get(targetIdx).notDone();
            return true;
        }
        return false;
    }


    public boolean deleteToDo(int id){
        int targetIdx=-1;
        for (int i = 0; i < toDoList.size(); i++) {
            ToDo toDo=toDoList.get(i);
            if (toDo.getId()==id){
                targetIdx=i;
                break;
            }
        }
        if (targetIdx!=-1) {
            toDoList.remove(targetIdx);
            return true;
        }
        return false;
    }


    public List<ToDo> readAllTask(){
        return toDoList;
    }

    public ToDo readTask(int id){
        for (ToDo todo:toDoList){
            if (todo.getId()==id) return todo;
        }
        return null;
    }

}
