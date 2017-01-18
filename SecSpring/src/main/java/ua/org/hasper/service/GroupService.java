package ua.org.hasper.service;

import ua.org.hasper.Entity.StudentsGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public interface GroupService {
    StudentsGroup getGroupByName(String name);
    StudentsGroup getGroupById (int id);
    StudentsGroup editGroup(StudentsGroup studentsGroup);
    void addGroup(StudentsGroup studentsGroup);
    List<StudentsGroup> getAllGroups();
    List<StudentsGroup> allGroupsWithout(String name);
    void delGroup(StudentsGroup studentsGroup);
}
