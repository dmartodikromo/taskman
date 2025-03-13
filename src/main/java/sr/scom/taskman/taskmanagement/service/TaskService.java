package sr.scom.taskman.taskmanagement.service;

import static java.lang.String.format;
import static sr.scom.taskman.common.enums.Status.COMPLETED;
import static sr.scom.taskman.common.enums.Status.OPEN;
import static sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto.toTaskEntity;
import static sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto.toTaskResponseDto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sr.scom.taskman.common.exceptions.ObjectNotFoundException;
import sr.scom.taskman.common.exceptions.TaskCompletionFailException;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.request.UpdateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto;
import sr.scom.taskman.taskmanagement.entity.Task;
import sr.scom.taskman.taskmanagement.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ScomRestClientService scomRestClientService;

    public List<TaskResponseDto> getTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponseDto::toTaskResponseDto)
                .toList();
    }

    public TaskResponseDto getTask(Long id) {
        Task foundTask = findTaskById(id);
        return toTaskResponseDto(foundTask);
    }

    @Transactional
    public TaskResponseDto createTask(CreateTaskRequestDto dto) {
        var taskEntity = toTaskEntity(dto);

        taskEntity.setStatus(OPEN);

        var savedTask = taskRepository.save(taskEntity);
        return toTaskResponseDto(savedTask);
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, UpdateTaskRequestDto dto) {
        var taskEntity = findTaskById(id);

        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setNotes(dto.getNotes());
        taskEntity.setStatus(dto.getStatus());
        taskEntity.setPriority(dto.getPriority());
        taskEntity.setDueDate(dto.getDueDate());

        var updatedTask = taskRepository.save(taskEntity);

        return toTaskResponseDto(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public String markTaskAsCompleted(Long id) {
        var taskEntity = findTaskById(id);
        if (taskEntity.getStatus() == COMPLETED) {
            throw new TaskCompletionFailException("Task already completed");
        }

        taskEntity.setStatus(COMPLETED);
        taskEntity.setCompletedDate(LocalDateTime.now());

        taskRepository.save(taskEntity);

        return scomRestClientService.sendUserAlert();
    }

    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(format("Task with id %s not found", id)));
    }
}
