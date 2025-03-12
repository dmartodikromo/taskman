package sr.scom.taskman.taskmanagement.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sr.scom.taskman.taskmanagement.dto.request.CreateTaskRequestDto;
import sr.scom.taskman.taskmanagement.entity.Task;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime completedDate;

    public static TaskResponseDto toTaskResponseDto(Task task) {
        return builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority().getName())
                .status(task.getStatus().getName())
                .dueDate(task.getDueDate())
                .completedDate(task.getCompletedDate())
                .build();
    }

    public static Task toTaskEntity(CreateTaskRequestDto dto) {
        return Task.builder().title(dto.getTitle())
                .description(dto.getDescription())
                .notes(dto.getNotes())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .createdDate(LocalDateTime.now())
                .build();
    }
}