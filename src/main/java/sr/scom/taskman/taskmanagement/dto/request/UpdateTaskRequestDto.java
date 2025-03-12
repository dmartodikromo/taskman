package sr.scom.taskman.taskmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sr.scom.taskman.common.enums.Priority;
import sr.scom.taskman.common.enums.Status;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequestDto {
    private String title;
    private String description;
    private String notes;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
}
