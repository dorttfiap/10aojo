package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.service.AssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assists")
public class AssistController {

    private final AssistService service;

    @Autowired
    public AssistController(AssistService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Assist>> getAssists() {
        List<Assist> assistList = this.service.getServiceList();
        return ResponseEntity.ok(assistList);
    }
}
