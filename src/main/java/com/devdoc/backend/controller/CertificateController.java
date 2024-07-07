package com.devdoc.backend.controller;

import com.devdoc.backend.dto.CertificateDTO;
import com.devdoc.backend.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Certificate Management", description = "자격증(Certificate) 항목 관리 API")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Operation(summary = "자격증 저장 또는 수정")
    @PostMapping("/{resumeId}/certificates")
    public ResponseEntity<CertificateDTO> saveOrUpdateCertificate(@PathVariable int resumeId, @RequestBody CertificateDTO certificateDTO) {
        try {
            CertificateDTO updatedCertificate = certificateService.saveOrUpdateCertificate(resumeId, certificateDTO);
            return ResponseEntity.ok(updatedCertificate); // 업데이트한 Certificate 내용을 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "자격증 삭제")
    @DeleteMapping("/{resumeId}/certificates/{certificateId}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable int resumeId, @PathVariable int certificateId) {
        try {
            certificateService.deleteCertificate(resumeId, certificateId); // 해당 Certificate 항목을 저장소에서 삭제
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "자격증 수정")
    @PutMapping("/{resumeId}/certificates")
    public ResponseEntity<CertificateDTO> updateCertificate(@PathVariable int resumeId, @RequestBody CertificateDTO certificateDTO) {
        try {
            CertificateDTO updatedCertificate = certificateService.saveOrUpdateCertificate(resumeId, certificateDTO);
            return ResponseEntity.ok(updatedCertificate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
