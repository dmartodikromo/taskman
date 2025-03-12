package sr.scom.taskman.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.request.UpdateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto;
import sr.scom.taskman.taskmanagement.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public List<TaskResponseDto> getTasks() {
        return null;
    }

    public TaskResponseDto getTask(Long id) {
        return null;
    }

    public TaskResponseDto createTask(CreateTaskRequestDto dto) {
        return null;
    }

    public TaskResponseDto updateTask(UpdateTaskRequestDto dto) {
        return null;
    }
}
