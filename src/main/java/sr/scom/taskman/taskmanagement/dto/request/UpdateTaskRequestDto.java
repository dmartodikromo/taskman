package sr.scom.taskman.taskmanagement.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sr.scom.taskman.common.enums.Priority;
import sr.scom.taskman.common.enums.Status;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String notes;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
    private LocalDateTime dueDate;
}
