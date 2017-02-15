package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.hasper.Reports.MarkStatistics;
import ua.org.hasper.repository.MarkStampRepository;
import ua.org.hasper.service.ReportsService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 10.02.2017.
 */
@Service
public class ReportServiceImpl implements ReportsService {

    @Autowired
    private MarkStampRepository markStampRepository;
    @Override
    public List<MarkStatistics> getMarkStatistic(Calendar sdt, Calendar edt){
        List<MarkStatistics> markStatisticss =markStampRepository.Statistic(sdt,edt);
        return markStatisticss;
    }
}
