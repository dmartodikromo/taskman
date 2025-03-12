package sr.scom.taskman.common.enums;

import lombok.Getter;

@Getter
public enum Status {
    COMPLETED("Completed"),
    IN_PROGRESS("In Progress"),
    OPEN("Open");

    Status(String name) {
        this.name = name;
    }

    private final String name;
}
