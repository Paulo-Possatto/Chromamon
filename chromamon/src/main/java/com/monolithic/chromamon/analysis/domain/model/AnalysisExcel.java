package com.monolithic.chromamon.analysis.domain.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;

@Data
public class AnalysisExcel {

   @ExcelRow
   private int rowIndex;

   @ExcelCellName("Transformer Id")
   private String transformerId;

   @ExcelCellName("Analysis Date")
   private String analysisDate;

   @ExcelCellName("Sample Date")
   private String sampleDate;

   @ExcelCellName("Laboratory")
   private String laboratory;

   @ExcelCellName("Method")
   private String method;

   @ExcelCellName("Sample Temperature (celsius)")
   private String sampleTemperature;

   @ExcelCellName("H2 ppm")
   private String h2;

   @ExcelCellName("CH4 ppm")
   private String ch4;

   @ExcelCellName("C2H2 ppm")
   private String c2h2;

   @ExcelCellName("C2H4 ppm")
   private String c2h4;

   @ExcelCellName("C2H6 ppm")
   private String c2h6;

   @ExcelCellName("CO ppm")
   private String co;

   @ExcelCellName("CO2 ppm")
   private String co2;

   @ExcelCellName("O2 ppm")
   private String o2;

   @ExcelCellName("N2 ppm")
   private String n2;

   // TODO: Add remaining fields
}
