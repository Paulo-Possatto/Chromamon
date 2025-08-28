export interface Analysis {
  id: number;
  transformerId: number;
  analysisDate: string;
  sampleDate: string;
  laboratory: string;
  method: string;
  sampleTemperatureCelsius: number;
  
  // gases (ppm)
  hydrogenH2Ppm: number;
  methaneCh4Ppm: number;
  acetyleneC2h2Ppm: number;
  ethyleneC2h4Ppm: number;
  ethaneC2h6Ppm: number;
  carbonMonoxideCoPpm: number;
  carbonDioxideCo2Ppm: number;
  oxygenO2Ppm: number;
  nitrogenN2Ppm: number;
  
  // totals
  totalDissolvedGasTdgPpm: number;
  totalCombustibleGasTcgPpm: number;
  
  observations: string;
  sampleCondition: string;
  status: string;
  createdBy: number;
  updatedBy: number;
}

export interface AnalysisCreate {
  transformerId: number;
  analysisDate: string;
  sampleDate: string;
  laboratory: string;
  method: string;
  sampleTemperatureCelsius: number;
  
  hydrogenH2Ppm: number;
  methaneCh4Ppm: number;
  acetyleneC2h2Ppm: number;
  ethyleneC2h4Ppm: number;
  ethaneC2h6Ppm: number;
  carbonMonoxideCoPpm: number;
  carbonDioxideCo2Ppm: number;
  oxygenO2Ppm: number;
  nitrogenN2Ppm: number;
  
  observations: string;
  sampleCondition: string;
}