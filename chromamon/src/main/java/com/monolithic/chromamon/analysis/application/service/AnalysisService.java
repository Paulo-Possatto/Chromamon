package com.monolithic.chromamon.analysis.application.service;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import com.monolithic.chromamon.analysis.domain.model.CreateAnalysisRequest;
import com.monolithic.chromamon.analysis.domain.port.AnalysisRepository;
import com.monolithic.chromamon.analysis.domain.port.AuditService;
import com.monolithic.chromamon.analysis.domain.port.TransformerService;
import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.domain.security.Permission;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisService {

  private final AnalysisRepository analysisRepository;
  private final TransformerService transformerService;
  private final AuditService auditService;

  @HasPermission(Permission.ANALYSIS_CREATE)
  public Analysis create(CreateAnalysisRequest req) {
    if (req == null || !req.toAnalysis().isValidForCreate()) {
      throw new IllegalArgumentException("Invalid request for creating analysis");
    }
    if (!transformerService.existsById(req.getTransformerId())) {
      throw new IllegalArgumentException("Transformer not found: " + req.getTransformerId());
    }
    Analysis a = req.toAnalysis();
    a.setCreatedBy(currentUserId());
    Analysis saved = analysisRepository.save(a);
    auditService.logAnalysisCreated("Created analysis id=" + saved.getId());
    return saved;
  }

  @HasPermission(Permission.ANALYSIS_CREATE)
  public void readAnalysesInExcel(MultipartFile file){
     log.info("Reading analysis data from file {}", file.getOriginalFilename());
     auditService.logAnalysisCreated("Adding analyses in excel file");
     try{
        if(!isExcelFile(file)) {
           throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "File must be in .xls or .xlsx format");
        }
        // TODO: List<AnalysisExcel> analyses = Poiji.fromExcel(file, AnalysisExcel.class);
        // TODO: Use spring batch to save all data
     } catch (Exception e) {
        auditService.logAnalysisCreated("Error reading excel file");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
     }
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public Page<Analysis> list(Pageable pageable) {
    List<Analysis> analysisList = analysisRepository.findAll();
    return new PageImpl<>(analysisRepository.findAll(), pageable, analysisList.size());
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public Analysis get(Long id) {
    return analysisRepository.findById(id).orElseThrow();
  }

  @HasPermission(Permission.ANALYSIS_UPDATE)
  public Analysis update(Long id, Analysis patch) {
    Analysis current = analysisRepository.findById(id).orElseThrow();
    patch.setId(id);
    patch.setCreatedBy(current.getCreatedBy());
    patch.setUpdatedBy(currentUserId());
    Analysis saved = analysisRepository.save(patch);
    auditService.logAnalysisUpdated("Updated analysis id=" + id);
    return saved;
  }

  @HasPermission(Permission.ANALYSIS_DELETE)
  public void delete(Long id) {
    if (!analysisRepository.existsById(id))
      throw new IllegalArgumentException("Analysis not found: " + id);
    analysisRepository.deleteById(id);
    auditService.logAnalysisDeleted("Deleted analysis id=" + id);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public List<Analysis> findByTransformer(Long transformerId) {
    return analysisRepository.findByTransformerId(transformerId);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public List<Analysis> findByDate(LocalDate start, LocalDate end) {
    return analysisRepository.findByAnalysisDateBetween(start, end);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public List<Analysis> findByStatus(AnalysisStatus st) {
    return analysisRepository.findByStatus(st);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public List<Analysis> findByLaboratory(String lab) {
    return analysisRepository.findByLaboratory(lab);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public long getAnalysisCountByTransformerId(Long transformerId) {
    return analysisRepository.countByTransformerId(transformerId);
  }

  @HasPermission(Permission.ANALYSIS_READ)
  public Analysis getLatestAnalysisByTransformerId(Long transformerId) {
    return analysisRepository.findLatestByTransformerId(transformerId).orElseThrow();
  }

  private Long currentUserId() {
    try {
      Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return jwt.getClaim("userId");
    } catch (Exception e) {
      return 0L;
    }
  }

   private boolean isExcelFile(MultipartFile file) {
      String contentType = file.getContentType();
      return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType) ||
         "application/vnd.ms-excel".equals(contentType);
   }
}
