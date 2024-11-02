package ru.ilya.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ilya.NauJava.model.Report;
import ru.ilya.NauJava.model.ReportStatus;
import ru.ilya.NauJava.repository.ReportRepository;
import ru.ilya.NauJava.service.ReportService;

@SpringBootTest
public class ReportTest {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportTest(ReportService reportService, ReportRepository reportRepository) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }

    @Test
    void testProceedReport() throws InterruptedException {
        long id = reportService.createReport();
        String info = reportService.getInfoById(id);
        Assertions.assertNull(info);
        reportService.proceedReport(id);
        Thread.sleep(2000);
        info = reportService.getInfoById(id);
        System.out.println(info);
        Assertions.assertNotNull(info);
    }

    @Test
    void testProceedNotWaiting() {
        long id = reportService.createReport();
        reportService.proceedReport(id);
        Report report = reportRepository.findById(id).orElse(null);
        Assertions.assertNotNull(report);
        Assertions.assertEquals(ReportStatus.CREATED, report.getStatus());
    }
}
