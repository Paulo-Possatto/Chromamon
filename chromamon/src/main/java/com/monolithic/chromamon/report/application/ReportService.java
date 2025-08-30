package com.monolithic.chromamon.report.application;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
