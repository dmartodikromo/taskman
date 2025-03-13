package sr.scom.taskman.taskmanagement.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.request.UpdateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto;
import sr.scom.taskman.taskmanagement.service.TaskService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public TaskResponseDto createTask(@RequestBody @Valid CreateTaskRequestDto dto) {
        return taskService.createTask(dto);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable("id") Long id, @RequestBody @Valid UpdateTaskRequestDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/completed")
    public String completeTask(@PathVariable("id") Long id) {
        return taskService.markTaskAsCompleted(id);
    }
}
