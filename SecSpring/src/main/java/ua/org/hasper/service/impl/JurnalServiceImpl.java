package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.JurnalRepository;
import ua.org.hasper.service.JurnalService;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JurnalServiceImpl implements JurnalService {
    @Autowired
    JurnalRepository jurnalRepository;

    @Override
    @Transactional
    public void addOrUpdateJurnal(Jurnal jurnal) {
        jurnalRepository.save(jurnal);

    }

    @Override
    @Transactional
    public void addOrUpdateJurnals(List<Jurnal> jurnals) {
        jurnalRepository.save(jurnals);
    }

    @Override
    @Transactional
    public void delJurnal(Jurnal jurnal) {
        jurnalRepository.delete(jurnal);

    }

    @Override
    @Transactional
    public void delJurnals(List<Jurnal> jurnals) {
        jurnalRepository.delete(jurnals);
    }

    @Override
    @Transactional
    public List<Jurnal> findByLogin(String login, Calendar sdt, Calendar edt) {
        return jurnalRepository.findByLogin(login, sdt, edt);
    }

    @Override
    @Transactional
    public List<Jurnal> findByLoginSubject(String login, Calendar sdt, Calendar edt, Subject subject) {
        return jurnalRepository.findByLoginSubject(login, sdt, edt, subject);
    }

    @Override
    @Transactional
    public Map<Subject, Jurnal> findByLoginForMap(String login, Calendar sdt, Calendar edt) {

        List<Jurnal> jurnals = findByLogin(login, sdt, edt);
        Map<Subject, Jurnal> subjectJurnalMap = new HashMap<>();

        for (Jurnal j : jurnals) {
            subjectJurnalMap.put(j.getSubject(), j);
        }
        return subjectJurnalMap;

    }

    @Override
    @Transactional
    public List<Jurnal> findByTeacher(Teacher teacher) {
        return jurnalRepository.findByTeacher(teacher);
    }

    @Override
    @Transactional
    public List<Jurnal> findByStudent(Student student) {
        return jurnalRepository.findByStudent(student);
    }

    @Override
    @Transactional
    public List<Jurnal> findByGroup(StudentsGroup studentsGroup) {
        return jurnalRepository.findByGroup(studentsGroup);
    }

    @Override
    @Transactional
    public Page<Jurnal> findByGroup(StudentsGroup studentsGroup, int page, int pageSize) {
        return jurnalRepository.findByGroup(studentsGroup, new PageRequest(page, pageSize));
    }

    @Override
    @Transactional
    public List<Jurnal> findBySubject(Subject subject) {
        return jurnalRepository.findBySubject(subject);
    }

    @Override
    @Transactional
    public List<Jurnal> getAllJurnals() {
        return jurnalRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Jurnal> getAllJurnals(int page, int pageSize) {
        return jurnalRepository.findAll(new PageRequest(page, pageSize));
    }
}
