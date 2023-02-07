package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.components.VersionComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/")
public class HealthCheckController {

    private VersionComponent component;

    public HealthCheckController(@Autowired VersionComponent component) {
        this.component = component;
    }

    @GetMapping
    public ResponseEntity<Object> status() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("version")
    public ResponseEntity<String> version() throws IOException {
        return ResponseEntity.ok(component.getNameVersion());
    }
}
