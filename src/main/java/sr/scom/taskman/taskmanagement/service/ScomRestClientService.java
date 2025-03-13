package sr.scom.taskman.taskmanagement.service;

import java.io.IOException;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.springframework.stereotype.Service;
import sr.scom.taskman.common.exceptions.TaskCompletionFailException;
import sr.scom.taskman.taskmanagement.dto.request.ScomRestClientPayloadDto;

@Service
@RequiredArgsConstructor
public class ScomRestClientService {
    private final ObjectMapper objectMapper;

    public String sendUserAlert() {
        String payload = objectConverter(getScomRestClientPayload());
        try {
            return Request.post("https://scomtestapi.scombank.sr/api/v1/do-alert")
                    .bodyString(payload, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();
        } catch (IOException e) {
            throw new TaskCompletionFailException("Could not send user alert.");
        }
    }

    private ScomRestClientPayloadDto getScomRestClientPayload() {
        return ScomRestClientPayloadDto.builder()
                .preference("email")
                .type("scom_alerts")
                .lang("en")
                .subject("some subject")
                .mail("me@gmail.com")
                .attachment(Collections.emptyList())
                .messageBody("<table style=\\\"font-family: Arial; font-size: 12px; color: #262E68;\\\"><tr><td style=\\\"font-weight:\r\nbold;\\\">Input</td><td>:</td>File1</td></tr><tr><td style=\\\"font-weight:\r\nbold;\\\">Output</td><td>:</td><td>file1_PROCESSED.csv</td></tr><tr><td style=\\\"font-weight:\r\nbold;\\\">Process Time</td><td>:</td><td>Just now</td></tr></table>")
                .build();
    }

    private String objectConverter(ScomRestClientPayloadDto payload) {
        try {
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new TaskCompletionFailException("Something went wrong while converting the payload.");
        }
    }
}
