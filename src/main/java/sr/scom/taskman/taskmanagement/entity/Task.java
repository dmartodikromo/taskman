package sr.scom.taskman.taskmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sr.scom.taskman.common.enums.Priority;
import sr.scom.taskman.common.enums.Status;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;
    private String notes;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dueDate;
    private LocalDateTime completedDate;

}
