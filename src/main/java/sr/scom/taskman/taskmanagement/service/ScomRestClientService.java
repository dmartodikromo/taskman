package sr.scom.taskman.taskmanagement.service;

import java.io.File;
import java.io.IOException;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.springframework.stereotype.Service;

@Service
public class ScomRestClientService {
    public String sendUserAlert() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("examples/example-payload.json")
                .getFile());
        return Request.post("https://scomtestapi.scombank.sr/api/v1/do-alert")
                .bodyFile(file, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }
}
