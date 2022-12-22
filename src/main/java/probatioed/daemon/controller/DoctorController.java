package probatioed.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import probatioed.daemon.entity.CheckElement;
import probatioed.daemon.repository.DoctorRepository;
import java.util.List;

@Controller
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;
    @GetMapping("/all")
    public ResponseEntity<List<CheckElement>> getHealth(){
        List<CheckElement> elements = doctorRepository.findAll();
        return ResponseEntity.ok(elements);
    }
}
