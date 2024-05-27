package com.dfanso.spring_jwt_auth.demo.service;


import com.dfanso.spring_jwt_auth.demo.repository.DemoRepository;
import com.dfanso.spring_jwt_auth.demo.dto.DemoDto;
import com.dfanso.spring_jwt_auth.demo.exception.ResourceNotFoundException;
import com.dfanso.spring_jwt_auth.demo.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DemoService {

    private final DemoRepository demoRepository;

    @Autowired
    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<DemoDto> getAllDemos() {
        List<Demo> demos = demoRepository.findAll();
        return demos.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public DemoDto getDemoById(Long id) {
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demo", "id", id));
        return mapToDto(demo);
    }

    public DemoDto createDemo(DemoDto demoDto) {
        Demo demo = mapToEntity(demoDto);
        Demo savedDemo = demoRepository.save(demo);
        return mapToDto(savedDemo);
    }

    public DemoDto updateDemo(Long id, DemoDto demoDto) {
        Demo existingDemo = demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demo", "id", id));
        existingDemo.setName(demoDto.getName());
        existingDemo.setDescription(demoDto.getDescription());
        Demo updatedDemo = demoRepository.save(existingDemo);
        return mapToDto(updatedDemo);
    }

    public void deleteDemo(Long id) {
        demoRepository.deleteById(id);
    }

    private DemoDto mapToDto(Demo demo) {
        DemoDto demoDto = new DemoDto();
        demoDto.setId(demo.getId());
        demoDto.setName(demo.getName());
        demoDto.setDescription(demo.getDescription());
        return demoDto;
    }

    private Demo mapToEntity(DemoDto demoDto) {
        Demo demo = new Demo();
        demo.setName(demoDto.getName());
        demo.setDescription(demoDto.getDescription());
        return demo;
    }
}