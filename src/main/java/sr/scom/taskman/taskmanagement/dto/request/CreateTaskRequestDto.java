package sr.scom.taskman.taskmanagement.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sr.scom.taskman.common.enums.Priority;
import sr.scom.taskman.taskmanagement.entity.Task;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Priority priority;
    private LocalDateTime dueDate;
    private String notes;

    public static Task toTaskEntity(CreateTaskRequestDto dto) {
        return Task.builder().title(dto.getTitle())
                .description(dto.getDescription())
                .notes(dto.getNotes())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .build();
    }
}
