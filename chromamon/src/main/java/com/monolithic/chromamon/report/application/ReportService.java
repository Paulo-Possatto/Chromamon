package com.monolithic.chromamon.report.application;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportService {

   public byte[] generate(String templateName, Map<String, Object> params) {
      try {
         var jrxml = new ClassPathResource("reports/" + templateName + ".jrxml").getInputStream();
         JasperReport report = JasperCompileManager.compileReport(jrxml);
         JRDataSource ds = new JREmptyDataSource();
         JasperPrint print = JasperFillManager.fillReport(report, params, ds);
         return JasperExportManager.exportReportToPdf(print);
      } catch (Exception e) {
         throw new RuntimeException("Error generating report: " + templateName, e);
      }
   }
}
