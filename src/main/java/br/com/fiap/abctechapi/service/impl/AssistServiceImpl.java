package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assist;
import br.com.fiap.abctechapi.repository.AssistRepository;
import br.com.fiap.abctechapi.service.AssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistServiceImpl implements AssistService {

    private final AssistRepository repository;

    @Autowired
    public AssistServiceImpl(AssistRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Assist> getServiceList() {
        return this.repository.findAll();
    }
}
