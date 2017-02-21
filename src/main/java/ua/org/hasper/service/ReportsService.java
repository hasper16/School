package ua.org.hasper.service;

import ua.org.hasper.Reports.MarkStatistics;

import java.util.Calendar;
import java.util.List;

public interface ReportsService {
    List<MarkStatistics> getMarkStatistic(Calendar sdt, Calendar edt);
}
