package probatioed.daemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class GeneralController {

    @GetMapping("/health")
    public ResponseEntity<Integer> getHealth(){
        return ResponseEntity.ok(200);
    }


}