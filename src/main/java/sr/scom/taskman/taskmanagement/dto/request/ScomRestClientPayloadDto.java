package sr.scom.taskman.taskmanagement.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScomRestClientPayloadDto {
    private String preference;
    private String type;
    private String lang;
    private String subject;
    private String mail;
    private List<String> attachment;
    private String messageBody;
}
