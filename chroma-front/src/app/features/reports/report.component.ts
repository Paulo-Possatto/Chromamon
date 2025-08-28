import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReportService } from '../../core/services/report.service';

@Component({
  selector: 'app-report',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent {
  private reportService = inject(ReportService);

  analysisId: number | null = null;
  transformerId: number | null = null;
  generating: boolean = false;

  generateAnalysisReport(): void {
    if (this.analysisId) {
      this.generating = true;
      this.reportService.generateAnalysisReport(this.analysisId).subscribe({
        next: (blob) => {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = `analysis-report-${this.analysisId}.pdf`;
          a.click();
          window.URL.revokeObjectURL(url);
          this.generating = false;
        },
        error: (error) => {
          console.error('Error generating report', error);
          this.generating = false;
        }
      });
    }
  }

  generateTransformerReport(): void {
    if (this.transformerId) {
      this.generating = true;
      this.reportService.generateTransformerReport(this.transformerId).subscribe({
        next: (blob) => {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = `transformer-report-${this.transformerId}.pdf`;
          a.click();
          window.URL.revokeObjectURL(url);
          this.generating = false;
        },
        error: (error) => {
          console.error('Error generating report', error);
          this.generating = false;
        }
      });
    }
  }
}