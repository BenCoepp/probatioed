package probatioed.daemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import probatioed.daemon.entity.CheckElement;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/doctor")
public class DoctorController {
    @GetMapping("/all")
    public ResponseEntity<ArrayList<CheckElement>> getHealth(){
        ArrayList<CheckElement> elements = new ArrayList<>();
        return ResponseEntity.ok(elements);
    }
}