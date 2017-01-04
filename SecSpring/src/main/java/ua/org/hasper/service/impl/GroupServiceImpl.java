package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.repository.GroupRepository;
import ua.org.hasper.service.GroupService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public StudentsGroup getGroupByName(String name) { StudentsGroup studentsGroup = groupRepository.findByName(name);
        studentsGroup.getStudents().iterator();
        return studentsGroup;
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
    public List<StudentsGroup> allGroupsWithout(String name){
        return groupRepository.allGroupsWithout(name);
    }
}
