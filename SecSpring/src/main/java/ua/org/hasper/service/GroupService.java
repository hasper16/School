package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public interface GroupService {
    List<StudentsGroup> getGroupByName(String name);
    StudentsGroup getGroupById (int id);
    StudentsGroup editGroup(StudentsGroup studentsGroup);
    void addGroup(StudentsGroup studentsGroup);
    void addGroups(List<StudentsGroup> studentsGroups);
    List<StudentsGroup> getAllGroups();
    Page<StudentsGroup> getAllGroups(int page, int pageSize);
    List<StudentsGroup> allGroupsWithout(String name);
    void delGroup(StudentsGroup studentsGroup);
    Integer countGroups();
    List<StudentsGroup> top5Groups();
}
