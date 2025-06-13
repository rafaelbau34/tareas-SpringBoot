package com.demo.service;

import com.demo.model.Tarea;
import com.demo.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public List<Tarea> findAll() {
        return repository.findAll();
    }

    public Tarea findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Tarea tarea) {
        repository.save(tarea);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
