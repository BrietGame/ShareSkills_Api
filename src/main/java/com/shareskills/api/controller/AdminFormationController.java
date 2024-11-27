package com.shareskills.api.controller;

import com.shareskills.api.model.Formation;
import com.shareskills.api.model.dto.FormationDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/formation")
public class AdminFormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/list")
    public ResponseJson<List<Formation>> getFormations() {
        return new ResponseJson<>(formationService.findAll(), HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ResponseJson<Formation> getFormationById(@PathVariable Long id) {
        Optional<Formation> formation = formationService.findById(id);
        return formation.map(value -> new ResponseJson<>(value, HttpStatus.OK.value())).orElseGet(() -> new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping("/create")
    public ResponseJson<Formation> createFormation(@RequestBody FormationDTO formationDTO) {
        return new ResponseJson<>(formationService.createFormation(formationDTO), HttpStatus.CREATED.value());
    }

    @PutMapping("/update/{id}")
    public ResponseJson<Formation> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        Optional<Formation> formationOptional = formationService.findById(id);
        if (formationOptional.isPresent()) {
            formationDTO.setId(id);
            return new ResponseJson<>(formationService.updateFormation(formationDTO), HttpStatus.OK.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseJson<Formation> deleteFormation(@PathVariable Long id) {
        Optional<Formation> formation = formationService.findById(id);
        if (formation.isPresent()) {
            formationService.deleteFormation(formation.get());
            return new ResponseJson<>(null, HttpStatus.NO_CONTENT.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

}
