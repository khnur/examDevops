package kz.jusan.backend.controller;

import kz.jusan.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Map;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> downloadExcelReport() {
        ByteArrayInputStream in = reportService.generateExcelReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/nationality")
    public Map<String, Long> getNationalityReport() {
        return reportService.generateNationalityReport();
    }

    @GetMapping("/marital-status")
    public Map<String, Long> getMaritalStatusReport() {
        return reportService.generateMaritalStatusReport();
    }

    @GetMapping("/car-ownership")
    public Map<Boolean, Long> getCarOwnershipReport() {
        return reportService.generateCarOwnershipReport();
    }

    @GetMapping("/military-status")
    public Map<Boolean, Long> getMilitaryStatusReport() {
        return reportService.generateMilitaryStatusReport();
    }

    @GetMapping("/svc-status")
    public Map<Boolean, Long> getSVCStatusReport() {
        return reportService.generateSVCStatusReport();
    }

    @GetMapping("/education-list-size")
    public Map<Integer, Long> getEducationListSizeReport() {
        return reportService.generateEducationListSizeReport();
    }

    @GetMapping("/children-count")
    public Map<Integer, Long> getChildrenCountReport() {
        return reportService.generateChildrenCountReport();
    }

    @GetMapping("/combined")
    public Map<String, Object> getCombinedReport() {
        return reportService.generateCombinedReport();
    }
}
