package com.pd.patchnumberrequestapp.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/prepare")
public class PrepareController {

    @GetMapping
    public String showPrepareForm() {
        return "tasks/prepare";
    }

    @GetMapping("/download-template")
    public ResponseEntity<Resource> downloadTemplate() throws IOException {
        // Create a simple CSV template with two required columns
        Path path = Paths.get("defect_reference_template.csv");
        String content = "Task/Defect Reference ID, Task/Defect Description\n";
        Files.write(path, content.getBytes());
        
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=defect_reference_template.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePatch(
            @RequestParam("patchNumber") String patchNumber,
            @RequestParam("requestedBy") String requestedBy,
            @RequestParam(value = "transactionalFiles", required = false) MultipartFile[] transactionalFiles,
            @RequestParam(value = "reportingFiles", required = false) MultipartFile[] reportingFiles,
            @RequestParam("defectFile") MultipartFile defectFile) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        // Add Transactional Files
        if (transactionalFiles != null) {
            String indexContent = "Transaction Scripts Execution Order:\n";
            for (int i = 0; i < transactionalFiles.length; i++) {
                MultipartFile file = transactionalFiles[i];
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    zos.putNextEntry(new ZipEntry("transactional/" + fileName));
                    zos.write(file.getBytes());
                    zos.closeEntry();
                    indexContent += (i + 1) + ". " + fileName + "\n";
                }
            }
            zos.putNextEntry(new ZipEntry("transactional/index.sql"));
            zos.write(indexContent.getBytes());
            zos.closeEntry();
        }

        // Add Reporting Files
        if (reportingFiles != null) {
            String indexContent = "Reporting Scripts Execution Order:\n";
            for (int i = 0; i < reportingFiles.length; i++) {
                MultipartFile file = reportingFiles[i];
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    zos.putNextEntry(new ZipEntry("reporting/" + fileName));
                    zos.write(file.getBytes());
                    zos.closeEntry();
                    indexContent += (i + 1) + ". " + fileName + "\n";
                }
            }
            zos.putNextEntry(new ZipEntry("reporting/index.sql"));
            zos.write(indexContent.getBytes());
            zos.closeEntry();
        }

        // Add Defect File
        if (!defectFile.isEmpty()) {
            zos.putNextEntry(new ZipEntry("defect/Defect_Reference_" + defectFile.getOriginalFilename()));
            zos.write(defectFile.getBytes());
            zos.closeEntry();
        }

        // Add Readme.txt
        String readme = "PATCH RELEASE PACKAGE\n" +
                "====================================\n" +
                "Patch Number: " + patchNumber + "\n" +
                "Requested By: " + requestedBy + "\n" +
                "Release Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss")) + "\n" +
                "====================================\n" +
                "System generated bundle package.";
        zos.putNextEntry(new ZipEntry("readme.txt"));
        zos.write(readme.getBytes());
        zos.closeEntry();

        // Add Releasenote as native MS Word (.docx)
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setBold(true);
            titleRun.setFontSize(16);
            titleRun.setText("RELEASE NOTES");
            
            XWPFParagraph body = document.createParagraph();
            XWPFRun bodyRun = body.createRun();
            bodyRun.setText("====================================");
            bodyRun.addBreak();
            bodyRun.setText("Patch Number: " + patchNumber);
            bodyRun.addBreak();
            bodyRun.setText("Requested By: " + requestedBy);
            bodyRun.addBreak();
            bodyRun.setText("Release Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss")));
            bodyRun.addBreak();
            bodyRun.setText("====================================");
            bodyRun.addBreak();
            bodyRun.setText("Project Status: Certified for Production Deployment");
            
            ByteArrayOutputStream docStream = new ByteArrayOutputStream();
            document.write(docStream);
            zos.putNextEntry(new ZipEntry("releasenote.docx"));
            zos.write(docStream.toByteArray());
            zos.closeEntry();
        }

        zos.finish();
        zos.close();

        byte[] zipBytes = baos.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + patchNumber + ".zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipBytes);
    }
}
