package sr.scom.taskman.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.request.UpdateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto;
import sr.scom.taskman.taskmanagement.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
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
    public TaskResponseDto createTask(@RequestBody CreateTaskRequestDto dto) {
        return taskService.createTask(dto);
    }

    @PutMapping
    public TaskResponseDto updateTask(@RequestBody UpdateTaskRequestDto dto) {
        return taskService.updateTask(dto);
    }
}
