package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.repository.MarkStampRepository;
import ua.org.hasper.service.MarkStampService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 29.09.2016.
 */
@Service
public class MarkStampServiceImpl implements MarkStampService {
    @Autowired
    MarkStampRepository markStampRepository;

    @Override
    @Transactional
    public void addOrUpdateMarkStamp(MarkStamp markStamp){
        markStampRepository.saveAndFlush(markStamp);
    }
    @Override
    @Transactional
    public void addOrUpdateMarkStamps(List<MarkStamp> markStamps){
        markStampRepository.save(markStamps);
    }
    @Override
    @Transactional
    public void delMarkStamp(MarkStamp markStamp){
        markStampRepository.delete(markStamp);
    }
    @Override
    @Transactional
    public MarkStamp findById(int id){
        return markStampRepository.findById(id);
    }
    @Override
    @Transactional
    public List<MarkStamp> findByAll(){
        return markStampRepository.findAll();
    }
    @Override
    @Transactional
    public Page<MarkStamp> findByGroupAndMarkType(StudentsGroup studentsGroup,
                                                  MarkType markType,
                                                  int page, int pageSize){
        return markStampRepository.findByGroupAndMarkType(studentsGroup,markType,new PageRequest(page, pageSize));
    }
    @Override
    @Transactional
    public Page<MarkStamp> findByMarkType(MarkType markType,
                                          int page, int pageSize){
        return markStampRepository.findByMarkType(markType, new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public List<MarkStamp> findByTypeAndStudentAndTeacherAndSubjectAndMark(MarkType markType, Student student, Teacher teacher, Subject subject, Mark mark){
        return markStampRepository.findByTypeAndStudentAndTeacherAndSubjectAndMark(markType,student,teacher,subject,mark);
    }

    @Override
    @Transactional
    public Integer countMarkStamps(){
        Long count = markStampRepository.count();
        Integer res = count.intValue();
        return res;
    }
}
