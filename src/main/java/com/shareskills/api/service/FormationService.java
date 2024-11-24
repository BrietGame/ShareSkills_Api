package com.shareskills.api.service;

import com.shareskills.api.model.Formation;
import com.shareskills.api.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Optional<Formation> findById(Long id) {
        return formationRepository.findById(id);
    }

    public List<Formation> findAll() {
        return (List<Formation>) formationRepository.findAll();
    }
}
