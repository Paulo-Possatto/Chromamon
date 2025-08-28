export interface Diagnostic {
  analysisId: number;
  method: string;
  conclusion: string;
  generatedAt: string;
}

export enum DiagnosticMethod {
  DUVAL = 'DUVAL',
  ROGERS = 'ROGERS',
  IEC = 'IEC',
  LABORELEC = 'LABORELEC'
}