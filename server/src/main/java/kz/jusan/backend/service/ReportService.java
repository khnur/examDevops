package kz.jusan.backend.service;

import kz.jusan.backend.entity.AnketaEntity;
import kz.jusan.backend.repository.AnketaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final AnketaRepository anketaRepository;

    public ByteArrayInputStream generateExcelReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Anketa Report");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("IIN");
            headerRow.createCell(1).setCellValue("Previous Name");
            headerRow.createCell(2).setCellValue("Birth Date");
            headerRow.createCell(3).setCellValue("Birth Place");
            headerRow.createCell(4).setCellValue("Nationality");
            headerRow.createCell(5).setCellValue("Citizenship");
            headerRow.createCell(6).setCellValue("Passport Serie");
            headerRow.createCell(7).setCellValue("Passport Number");
            headerRow.createCell(8).setCellValue("Passport Issued By");
            headerRow.createCell(9).setCellValue("Passport Issued At");
            headerRow.createCell(10).setCellValue("Home Phone");
            headerRow.createCell(11).setCellValue("Work Phone");
            headerRow.createCell(12).setCellValue("Relative Phone");
            headerRow.createCell(13).setCellValue("Relative FIO");
            headerRow.createCell(14).setCellValue("Relative Level");
            headerRow.createCell(15).setCellValue("Permanent City");
            headerRow.createCell(16).setCellValue("Permanent Region");
            headerRow.createCell(17).setCellValue("Permanent District");
            headerRow.createCell(18).setCellValue("Permanent Street");
            headerRow.createCell(19).setCellValue("Permanent House");
            headerRow.createCell(20).setCellValue("Permanent Corpus");
            headerRow.createCell(21).setCellValue("Permanent Apartment");
            headerRow.createCell(22).setCellValue("Address Matches");
            headerRow.createCell(23).setCellValue("Factual Region");
            headerRow.createCell(24).setCellValue("Factual District");
            headerRow.createCell(25).setCellValue("Factual Street");
            headerRow.createCell(26).setCellValue("Factual House");
            headerRow.createCell(27).setCellValue("Factual Corpus");
            headerRow.createCell(28).setCellValue("Factual Apartment");
            headerRow.createCell(29).setCellValue("Marriage Status");
            headerRow.createCell(30).setCellValue("Relative Jusan Employee");
            headerRow.createCell(31).setCellValue("Car Owner");
            headerRow.createCell(32).setCellValue("Military");
            headerRow.createCell(33).setCellValue("SVC");
            headerRow.createCell(34).setCellValue("SVC Answer");
            headerRow.createCell(35).setCellValue("Expired Loan");
            headerRow.createCell(36).setCellValue("Expired Loan Answer");
            headerRow.createCell(37).setCellValue("Criminal");
            headerRow.createCell(38).setCellValue("Criminal Answer");
            headerRow.createCell(39).setCellValue("Relative Criminal");
            headerRow.createCell(40).setCellValue("Relative Criminal Answer");
            headerRow.createCell(41).setCellValue("Criminal Delo");
            headerRow.createCell(42).setCellValue("Criminal Delo Answer");
            headerRow.createCell(43).setCellValue("Aliment Payer");
            headerRow.createCell(44).setCellValue("Aliment Payer Answer");
            headerRow.createCell(45).setCellValue("Hooligan");
            headerRow.createCell(46).setCellValue("Hooligan Answer");
            headerRow.createCell(47).setCellValue("Additional Info");
            headerRow.createCell(48).setCellValue("Extra Income");

            int rowIdx = 1;
            for (AnketaEntity anketa : anketaEntities) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(anketa.getIin());
                row.createCell(1).setCellValue(anketa.getPreviousName());
                row.createCell(2).setCellValue(anketa.getBirthDate());
                row.createCell(3).setCellValue(anketa.getBirthPlace());
                row.createCell(4).setCellValue(anketa.getNationality());
                row.createCell(5).setCellValue(anketa.getCitizenship());
                row.createCell(6).setCellValue(anketa.getPassportSerie());
                row.createCell(7).setCellValue(anketa.getPassportNumber());
                row.createCell(8).setCellValue(anketa.getPassportIssuedBy());
                row.createCell(9).setCellValue(anketa.getPassportIssuedAt());
                row.createCell(10).setCellValue(anketa.getHomePhone());
                row.createCell(11).setCellValue(anketa.getWorkPhone());
                row.createCell(12).setCellValue(anketa.getRelativePhone());
                row.createCell(13).setCellValue(anketa.getRelativeFIO());
                row.createCell(14).setCellValue(anketa.getRelativeLevel());
                row.createCell(15).setCellValue(anketa.getPermanentCity());
                row.createCell(16).setCellValue(anketa.getPermanentRegion());
                row.createCell(17).setCellValue(anketa.getPermanentDistrict());
                row.createCell(18).setCellValue(anketa.getPermanentStreet());
                row.createCell(19).setCellValue(anketa.getPermanentHouse());
                row.createCell(20).setCellValue(anketa.getPermanentCorpus());
                row.createCell(21).setCellValue(anketa.getPermanentApartment());
                row.createCell(22).setCellValue(anketa.isAddressMatches());
                row.createCell(23).setCellValue(anketa.getFactualRegion());
                row.createCell(24).setCellValue(anketa.getFactualDistrict());
                row.createCell(25).setCellValue(anketa.getFactualStreet());
                row.createCell(26).setCellValue(anketa.getFactualHouse());
                row.createCell(27).setCellValue(anketa.getFactualCorpus());
                row.createCell(28).setCellValue(anketa.getFactualApartment());
                row.createCell(29).setCellValue(anketa.getMarriageStatus());
                row.createCell(30).setCellValue(anketa.isRelativeJusanEmployee());
                row.createCell(31).setCellValue(anketa.isCarOwner());
                row.createCell(32).setCellValue(anketa.isMilitary());
                row.createCell(33).setCellValue(anketa.isSVC());
                row.createCell(34).setCellValue(anketa.getIsSVCAnswer());
                row.createCell(35).setCellValue(anketa.isExpiredLoan());
                row.createCell(36).setCellValue(anketa.getIsExpiredLoanAnswer());
                row.createCell(37).setCellValue(anketa.isCriminal());
                row.createCell(38).setCellValue(anketa.getIsCriminalAnswer());
                row.createCell(39).setCellValue(anketa.isRelativeCriminal());
                row.createCell(40).setCellValue(anketa.getIsRelativeCriminalAnswer());
                row.createCell(41).setCellValue(anketa.isCriminalDelo());
                row.createCell(42).setCellValue(anketa.getIsCriminalDeloAnswer());
                row.createCell(43).setCellValue(anketa.isAlimentPayer());
                row.createCell(44).setCellValue(anketa.getIsAlimentPayerAnswer());
                row.createCell(45).setCellValue(anketa.isHooligan());
                row.createCell(46).setCellValue(anketa.getIsHooliganAnswer());
                row.createCell(47).setCellValue(anketa.getAdditionalInfo());
                row.createCell(48).setCellValue(anketa.getExtraIncome());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to generate Excel report", e);
        }
    }

    public Map<String, Long> generateNationalityReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(AnketaEntity::getNationality, Collectors.counting()));
    }

    public Map<String, Long> generateMaritalStatusReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(AnketaEntity::getMarriageStatus, Collectors.counting()));
    }

    public Map<Boolean, Long> generateCarOwnershipReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(AnketaEntity::isCarOwner, Collectors.counting()));
    }

    public Map<Boolean, Long> generateMilitaryStatusReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(AnketaEntity::isMilitary, Collectors.counting()));
    }

    public Map<Boolean, Long> generateSVCStatusReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(AnketaEntity::isSVC, Collectors.counting()));
    }

    public Map<Integer, Long> generateEducationListSizeReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(e -> e.getEducationList().size(), Collectors.counting()));
    }

    public Map<Integer, Long> generateChildrenCountReport() {
        List<AnketaEntity> anketaEntities = anketaRepository.findAll();
        return anketaEntities.stream()
                .collect(Collectors.groupingBy(e -> e.getChilrenList() != null ? e.getChilrenList().size() : 0, Collectors.counting()));
    }

    public Map<String, Object> generateCombinedReport() {
        return Map.of(
                "nationalityReport", this.generateNationalityReport(),
                "maritalStatusReport", this.generateMaritalStatusReport(),
                "carOwnershipReport", this.generateCarOwnershipReport(),
                "militaryStatusReport", this.generateMilitaryStatusReport(),
                "svcStatusReport", this.generateSVCStatusReport(),
                "educationListSizeReport", this.generateEducationListSizeReport(),
                "childrenCountReport", this.generateChildrenCountReport()
        );
    }
}
