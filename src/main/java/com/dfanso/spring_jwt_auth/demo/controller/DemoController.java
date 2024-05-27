package com.dfanso.spring_jwt_auth.demo.controller;

import com.dfanso.spring_jwt_auth.demo.service.DemoService;
import com.dfanso.spring_jwt_auth.demo.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demos")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public ResponseEntity<List<DemoDto>> getAllDemos() {
        List<DemoDto> demos = demoService.getAllDemos();
        return ResponseEntity.ok(demos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoDto> getDemoById(@PathVariable Long id) {
        DemoDto demo = demoService.getDemoById(id);
        return ResponseEntity.ok(demo);
    }

    @PostMapping
    public ResponseEntity<DemoDto> createDemo(@RequestBody DemoDto demoDto) {
        DemoDto createdDemo = demoService.createDemo(demoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDemo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemoDto> updateDemo(@PathVariable Long id, @RequestBody DemoDto demoDto) {
        DemoDto updatedDemo = demoService.updateDemo(id, demoDto);
        return ResponseEntity.ok(updatedDemo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable Long id) {
        demoService.deleteDemo(id);
        return ResponseEntity.noContent().build();
    }
}
