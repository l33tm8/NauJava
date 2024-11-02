package ru.ilya.NauJava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ilya.NauJava.DTO.ReportDTO;
import ru.ilya.NauJava.service.ReportService;

@RestController
@RequestMapping("/custom/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public String getReportInfo(@PathVariable long id) {
        return reportService.getInfoById(id);
    }

    @PostMapping
    public ResponseEntity<ReportDTO> saveReport() {
        Long id = reportService.createReport();
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(id);
        return ResponseEntity.ok(reportDTO);
    }
}
