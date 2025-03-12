package sr.scom.taskman.taskmanagement.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sr.scom.taskman.common.enums.Priority;
import sr.scom.taskman.common.enums.Status;

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
