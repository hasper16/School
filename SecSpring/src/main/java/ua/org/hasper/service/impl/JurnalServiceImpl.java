package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.Jurnal;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.repository.JurnalRepository;
import ua.org.hasper.service.JurnalService;

import java.util.*;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
@Service
public class JurnalServiceImpl implements JurnalService {
    @Autowired
    JurnalRepository jurnalRepository;

    @Override
    @Transactional
    public void addOrUpdateJurnal(Jurnal jurnal) { jurnalRepository.save(jurnal);

    }

    @Override
    @Transactional
    public void delJurnal(Jurnal jurnal) { jurnalRepository.delete(jurnal);

    }

    @Override
    @Transactional
    public List<Jurnal> findByLogin (String login, Calendar sdt, Calendar edt){
        return jurnalRepository.findByLogin(login,sdt,edt);
    }
    @Override
    @Transactional
    public List<Jurnal> findByLoginSubject (String login, Calendar sdt, Calendar edt, Subject subject){
        return jurnalRepository.findByLoginSubject(login,sdt,edt,subject);
    }

    @Override
    public Map<Subject,Jurnal> findByLoginForMap(String login, Calendar sdt, Calendar edt){

        List<Jurnal> jurnals = findByLogin(login,sdt,edt);
        Map<Subject,Jurnal> subjectJurnalMap = new HashMap<>();

        for (Jurnal j:jurnals) {
           subjectJurnalMap.put(j.getSubject(),j);
        }
        return subjectJurnalMap;

    }
}
