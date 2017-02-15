package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.*;
import ua.org.hasper.repository.GroupRepository;
import ua.org.hasper.repository.HomeWorkRepository;
import ua.org.hasper.repository.HomeWorkStudentStatusRepository;
import ua.org.hasper.service.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;

    @Override
    @Transactional
    public List<StudentsGroup> getGroupByName(String name) { List<StudentsGroup> studentsGroup = groupRepository.findByName(name);
        return studentsGroup;
    }

    @Override
    @Transactional
    public void addGroups(List<StudentsGroup> studentsGroups) {
        groupRepository.save(studentsGroups);
    }

    @Override
    @Transactional
    public void addGroup(StudentsGroup studentsGroup) {
        groupRepository.save(studentsGroup);
    }

    @Override
    @Transactional
    public StudentsGroup editGroup(StudentsGroup studentsGroup) {
        return groupRepository.saveAndFlush(studentsGroup);
    }

    @Override
    @Transactional
    public List<StudentsGroup> getAllGroups(){
     return groupRepository.findAll();
    }

    @Override
    @Transactional
    public Page<StudentsGroup> getAllGroups(int page, int pageSize){

        return groupRepository.findAll(new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public List<StudentsGroup> allGroupsWithout(String name){
        return groupRepository.allGroupsWithout(name);
    }

    @Override
    @Transactional
    public StudentsGroup getGroupById (int id){

        StudentsGroup studentsGroup= groupRepository.findById(id);
        studentsGroup.getStudents().iterator();
        return studentsGroup;
    }

    @Override
    @Transactional
    public void delGroup(StudentsGroup studentsGroup){
        for (Student s:
             studentsGroup.getStudents()) {
            s.setStudentsGroup(null);
            s.setHomeWorkStudentStatuses(new LinkedList<HomeWorkStudentStatus>());
        }
       /* for (HomeWork h:
             studentsGroup.getHomeWorks()) {
            homeWorkService.delHomeWork(h);
        }*/
        groupRepository.delete(studentsGroup);
    }

    @Override
    @Transactional
    public Integer countGroups(){
        Long count = groupRepository.count();
        Integer res = count.intValue();
        return res;
    }

    @Override
    @Transactional
    public List<StudentsGroup> top5Groups(){
        return groupRepository.top5Groups(new PageRequest(0,5));
    }
}
