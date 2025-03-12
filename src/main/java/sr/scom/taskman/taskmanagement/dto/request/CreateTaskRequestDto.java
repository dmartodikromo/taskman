package sr.scom.taskman.taskmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private String priority;
    private String dueDate;
    private String notes;
}
