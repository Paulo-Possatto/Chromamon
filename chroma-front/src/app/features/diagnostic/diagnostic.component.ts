import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DiagnosticService } from '../../core/services/diagnostic.service';
import { AnalysisService } from '../../core/services/analysis.service';
import { Diagnostic, DiagnosticMethod } from '../../core/models/diagnostic.model';
import { Analysis } from '../../core/models/analysis.model';

@Component({
  selector: 'app-diagnostic',
  templateUrl: './diagnostic.component.html',
  styleUrls: ['./diagnostic.component.css']
})
export class DiagnosticComponent implements OnInit {
  analysisId: number = 0;
  analysis: Analysis | null = null;
  diagnostics: Diagnostic[] = [];
  loading: boolean = false;
  generating: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private diagnosticService: DiagnosticService,
    private analysisService: AnalysisService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.analysisId = +params['analysisId'];
      this.loadAnalysis();
      this.loadDiagnostics();
    });
  }

  loadAnalysis(): void {
    this.analysisService.getById(this.analysisId).subscribe({
      next: (data) => {
        this.analysis = data;
      },
      error: (error) => {
        console.error('Error loading analysis', error);
      }
    });
  }

  loadDiagnostics(): void {
    this.loading = true;
    this.diagnosticService.getByAnalysisId(this.analysisId).subscribe({
      next: (data) => {
        this.diagnostics = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading diagnostics', error);
        this.loading = false;
      }
    });
  }

  generateDiagnostic(method: DiagnosticMethod): void {
    this.generating = true;
    this.diagnosticService.generateDiagnostic(this.analysisId, method).subscribe({
      next: (data) => {
        this.diagnostics.push(data);
        this.generating = false;
      },
      error: (error) => {
        console.error('Error generating diagnostic', error);
        this.generating = false;
      }
    });
  }

  backToAnalyses(): void {
    this.router.navigate(['/analyses']);
  }
}