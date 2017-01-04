package ua.org.hasper.service;

import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Jurnal;
import ua.org.hasper.Entity.Subject;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel.Eremenko on 29.08.2016.
 */
public interface JurnalService {
    void addOrUpdateJurnal(Jurnal jurnal);
    void delJurnal(Jurnal jurnal);
    List<Jurnal> findByLogin (String login, Calendar sdt, Calendar edt);
    Map<Subject,Jurnal> findByLoginForMap(String login, Calendar sdt, Calendar edt);
    List<Jurnal> findByLoginSubject (String login, Calendar sdt, Calendar edt, Subject subject);

}
