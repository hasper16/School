package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.repository.SubjectRepository;
import ua.org.hasper.service.SubjectService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional
    public void addOrUpdateSubject(Subject subject) { subjectRepository.save(subject);

    }

    @Override
    @Transactional
    public void delSubject(Subject subject) { subjectRepository.delete(subject);
    }

    @Override
    @Transactional
    public Subject findByName(String subname){
        return subjectRepository.findByName(subname);
    }

    @Override
    @Transactional
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

}
