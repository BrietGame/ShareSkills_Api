package com.shareskills.api.service;

import com.shareskills.api.mapper.FormationMapper;
import com.shareskills.api.model.Chapter;
import com.shareskills.api.model.Formation;
import com.shareskills.api.model.dto.FormationDTO;
import com.shareskills.api.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private FormationMapper formationMapper;

    @Autowired
    private UserService userService;

    public Optional<Formation> findById(Long id) {
        return formationRepository.findById(id);
    }

    public List<Formation> findAll() {
        return (List<Formation>) formationRepository.findAll();
    }

    public Formation createFormation(FormationDTO formationDTO) {
        Formation newFormation = formationMapper.toEntity(formationDTO);
        newFormation.setTeacherId(userService.getUserConnected().getId());
        return formationRepository.save(newFormation);
    }

    public Formation updateFormation(FormationDTO formationDTO) {
        Formation newFormation = formationMapper.toEntity(formationDTO);
        newFormation.setTeacherId(userService.getUserConnected().getId());
        return formationRepository.save(newFormation);
    }

    public void addChapterOnFormation(Chapter newChapter, Formation formation) {
        formation.getChapters().add(newChapter);
        formationRepository.save(formation);
    }

    public void deleteFormation(Formation formation) {
        formationRepository.delete(formation);
    }
}
