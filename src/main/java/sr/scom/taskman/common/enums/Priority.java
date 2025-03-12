package sr.scom.taskman.common.enums;

import lombok.Getter;

@Getter
public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    Priority(String name) {
        this.name = name;
    }

    private final String name;
}
