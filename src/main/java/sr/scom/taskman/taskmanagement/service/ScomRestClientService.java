package sr.scom.taskman.taskmanagement.service;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.springframework.stereotype.Service;
import sr.scom.taskman.common.exceptions.TaskCompletionFailException;

@Service
public class ScomRestClientService {
    public String sendUserAlert() {
        File file = new File(requireNonNull(this.getClass().getClassLoader().getResource("examples/example-payload.json"))
                .getFile());
        try {
            return Request.post("https://scomtestapi.scombank.sr/api/v1/do-alert")
                    .bodyFile(file, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();
        } catch (IOException e) {
            throw new TaskCompletionFailException("Could not send user alert.");
        }
    }
}
