package com.shareskills.api.controller;

import com.shareskills.api.model.Formation;
import com.shareskills.api.model.dto.FormationDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.FormationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/formation")
public class AdminFormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/list")
    public ResponseEntity<ResponseJson<List<Formation>>> getFormations() {
        List<Formation> formations = formationService.findAll();
        ResponseJson<List<Formation>> response = new ResponseJson<>(formations, HttpStatus.OK.value());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJson<Formation>> getFormationById(@PathVariable Long id) {
        Optional<Formation> formation = formationService.findById(id);
        ResponseJson<Formation> response = formation.map(value -> new ResponseJson<>(value, HttpStatus.OK.value())).orElseGet(() -> new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseJson<Formation>> createFormation(@RequestBody @Valid FormationDTO formationDTO) {
        Formation formation = formationService.createFormation(formationDTO);
        ResponseJson<Formation> response = new ResponseJson<>(formation, HttpStatus.CREATED.value());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseJson<Formation>> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        Optional<Formation> formationOptional = formationService.findById(id);
        if (formationOptional.isPresent()) {
            formationDTO.setId(id);
            Formation formation = formationService.updateFormation(formationDTO);
            return new ResponseEntity<>(new ResponseJson<>(formation, HttpStatus.OK.value()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseJson<Formation>> deleteFormation(@PathVariable Long id) {
        Optional<Formation> formation = formationService.findById(id);
        if (formation.isPresent()) {
            formationService.deleteFormation(formation.get());
            return new ResponseEntity<>(new ResponseJson<>(null, HttpStatus.NO_CONTENT.value()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

}
