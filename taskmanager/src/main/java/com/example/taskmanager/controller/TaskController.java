package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepo;

    public TaskController(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskRepo.save(task);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        Task task = taskRepo.findById(id)
                .orElseThrow(()->new RuntimeException("task not found"));
        task.setDescription(updatedTask.getDescription());
        task.setDone(updatedTask.isDone());
        return taskRepo.save(task);
    }
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskRepo.deleteById(id);
        return "Task "+id+" deleted.";
    }
    @GetMapping("/{id}")
    public Task getOne(@PathVariable Long id){
        return taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
