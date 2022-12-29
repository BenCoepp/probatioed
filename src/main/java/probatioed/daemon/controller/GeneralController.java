package probatioed.daemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import probatioed.daemon.entity.App;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class GeneralController {

    @GetMapping("/health")
    public ResponseEntity<Integer> getHealth(){
        return ResponseEntity.ok(200);
    }

    @GetMapping("/app")
    public ResponseEntity<String> getSystem() throws IOException {
        App app = new App();
        app.init();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(app);
        return ResponseEntity.ok(json);
    }
}
