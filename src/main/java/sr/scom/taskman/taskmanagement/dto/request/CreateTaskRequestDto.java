package sr.scom.taskman.taskmanagement.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sr.scom.taskman.common.enums.Priority;

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
}
