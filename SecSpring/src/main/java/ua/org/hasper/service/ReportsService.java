package ua.org.hasper.service;

import ua.org.hasper.Reports.MarkStatistics;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 10.02.2017.
 */
public interface ReportsService {
    List<MarkStatistics>  getMarkStatistic(Calendar sdt, Calendar edt);
}
