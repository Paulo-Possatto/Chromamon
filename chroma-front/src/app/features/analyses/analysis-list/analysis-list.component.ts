import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnalysisService } from '../../../core/services/analysis.service';
import { Analysis } from '../../../core/models/analysis.model';

@Component({
  selector: 'app-analysis-list',
  templateUrl: './analysis-list.component.html',
  styleUrls: ['./analysis-list.component.css']
})
export class AnalysisListComponent implements OnInit {
  analyses: Analysis[] = [];
  loading: boolean = false;

  constructor(
    private analysisService: AnalysisService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadAnalyses();
  }

  loadAnalyses(): void {
    this.loading = true;
    this.analysisService.getAll().subscribe({
      next: (data) => {
        this.analyses = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading analyses', error);
        this.loading = false;
      }
    });
  }

  addAnalysis(): void {
    this.router.navigate(['/analyses/new']);
  }

  editAnalysis(id: number): void {
    this.router.navigate(['/analyses', id, 'edit']);
  }

  deleteAnalysis(id: number): void {
    if (confirm('Are you sure you want to delete this analysis?')) {
      this.analysisService.delete(id).subscribe({
        next: () => {
          this.loadAnalyses();
        },
        error: (error) => {
          console.error('Error deleting analysis', error);
        }
      });
    }
  }

  viewDiagnostic(analysisId: number): void {
    this.router.navigate(['/diagnostic', analysisId]);
  }
}