export interface Transformer {
  id: number;
  tag: string;
  serialNumber: string;
  manufacturer: string;
  model: string;
  yearManufacture: number;
  nominalPowerKva: number;
  primaryVoltageKv: number;
  secondaryVoltageKv: number;
  connectionType: string;
  coolingType: string;
  installationDate: string;
  location: string;
  substation: string;
  status: string;
  createdBy: number;
  updatedBy: number;
}

export interface TransformerCreate {
  tag: string;
  serialNumber: string;
  manufacturer: string;
  model: string;
  yearManufacture: number;
  nominalPowerKva: number;
  primaryVoltageKv: number;
  secondaryVoltageKv: number;
  connectionType: string;
  coolingType: string;
  installationDate: string;
  location: string;
  substation: string;
  status: string;
}