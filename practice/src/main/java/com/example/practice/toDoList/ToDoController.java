package com.example.practice.toDoList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Controller
public class ToDoController {
    private ToDoService service;
    public ToDoController(ToDoService service){
        this.service=service;
    }

    @GetMapping("/create-view")
    public String createToDo(){
        return "create.html";
    }

    @PostMapping("/create")
    public String created(
            @RequestParam("task")
            String task,
            @RequestParam("deadline")
            LocalDate date,
            Model model
    ){
        service.createToDo(task,date);
        return "redirect:/create-view";
    }


    @GetMapping("/home")
    public String listTasks(
            Model model
    ){
        //Display all tasks
        List<ToDo> toDoList = service.readAllTask();
        toDoList.sort(Comparator.comparing(ToDo::getDate));
        model.addAttribute("todoList", toDoList);

        //find number of tasks been not done
        int todoLeft= service.readAllTask().stream().filter(todo->!todo.isDone()).mapToInt(i->1).sum();
        model.addAttribute("todoLeft", todoLeft);

        return "home.html";
    }



    //sorted by status

    @GetMapping("/home-task")
    public String listTasksNotDone(
            Model model
    ){
        //Display all tasks
        List<ToDo> toDoList = service.readAllTask();
        toDoList.sort(Comparator.comparing(ToDo::getDate));
        toDoList=toDoList.stream().filter(t->!t.isDone()).toList();
        model.addAttribute("todoList", toDoList);

        //find number of tasks been not done
        int todoLeft= service.readAllTask().stream().filter(todo->!todo.isDone()).mapToInt(i->1).sum();
        model.addAttribute("todoLeft", todoLeft);
        return "home-task.html";
    }


    @GetMapping("/home-done")
    public String listTasksDone(
            Model model
    ){
        //Display all tasks
        List<ToDo> toDoList = service.readAllTask();
        toDoList.sort(Comparator.comparing(ToDo::getDate));
        toDoList=toDoList.stream().filter(t->t.isDone()).toList();
        model.addAttribute("todoList", toDoList);

        //find number of tasks been not done
        int todoLeft= service.readAllTask().stream().filter(todo->!todo.isDone()).mapToInt(i->1).sum();
        model.addAttribute("todoLeft", todoLeft);
        return "home-done.html";
    }


    //readOne task
    @GetMapping("{id}")
    public String readTask(
            @PathVariable("id")
            int id,
            Model model
    ){
        ToDo todo= service.readTask(id);
        model.addAttribute("task",todo );

        long dayLeft= ChronoUnit.DAYS.between(LocalDate.now(), todo.getDate());
        model.addAttribute("dayLeft", dayLeft);
        return "read.html";
    }

    //Update

    @GetMapping("{id}/update-view")
    public String updateView(
            @PathVariable("id")
            int id,
            Model model)
    {
        ToDo toDo= service.readTask(id);
        model.addAttribute("task", toDo);
        return "update.html";
    }

    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            int id,
            @RequestParam("task")
            String task,
            @RequestParam("deadline")
            LocalDate deadline,
            @RequestParam("status")
            String status,
            Model model
    ){
        service.updateEditTask(id, task, deadline);
        if (status.equals("1")) service.updateNotDone(id);
        else if (status.equals("2")) service.updateDone(id);
        return String.format("redirect:/%s", id);
    }

    //Delete

    @GetMapping("{id}/delete-view")
    public String deleteView(
            @PathVariable("id")
            int id,
            Model model
    ){
        ToDo toDo= service.readTask(id);
        model.addAttribute("task",toDo);
        return "delete";
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            int id
    ){
        service.deleteToDo(id);
        return "redirect:/home";
    }
}
