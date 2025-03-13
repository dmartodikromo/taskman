package sr.scom.taskman.taskmanagement.service;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static sr.scom.taskman.common.enums.Status.COMPLETED;
import static sr.scom.taskman.common.enums.Status.OPEN;
import static sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto.toTaskEntity;
import static sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto.toTaskResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sr.scom.taskman.common.exceptions.ObjectNotFoundException;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.request.UpdateTaskRequestDto;
import sr.scom.taskman.taskmanagement.dto.response.TaskResponseDto;
import sr.scom.taskman.taskmanagement.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ScomRestClientService scomRestClientService;

    public List<TaskResponseDto> getTasks() {
        var allTasks = taskRepository.findAll();
        return allTasks
                .stream()
                .map(TaskResponseDto::toTaskResponseDto)
                .toList();
    }

    public TaskResponseDto getTask(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(format("Task with id %s not found", id)));
        return toTaskResponseDto(task);
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
        var taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(format("Task with id %s not found", id)));
        taskEntity.setTitle(isEmpty(dto.getTitle()) ? taskEntity.getTitle() : dto.getTitle());
        taskEntity.setDescription(isEmpty(dto.getDescription()) ? taskEntity.getDescription() : dto.getDescription());
        taskEntity.setNotes(dto.getNotes());
        taskEntity.setStatus(dto.getStatus() == null ? taskEntity.getStatus() : dto.getStatus());
        taskEntity.setPriority(dto.getPriority() == null ? taskEntity.getPriority() : dto.getPriority());
        taskEntity.setDueDate(dto.getDueDate());
        var updatedTask = taskRepository.save(taskEntity);
        return toTaskResponseDto(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public String markTaskAsCompleted(Long id) throws IOException {
        var taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(format("Task with id %s not found", id)));
        taskEntity.setStatus(COMPLETED);
        taskEntity.setCompletedDate(LocalDateTime.now());
        taskRepository.save(taskEntity);
        return scomRestClientService.sendUserAlert();
    }
}
