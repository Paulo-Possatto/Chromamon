import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AnalysisService } from '../../../core/services/analysis.service';
import { Analysis } from '../../../core/models/analysis.model';

@Component({
  selector: 'app-analysis-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './analysis-form.component.html',
  styleUrls: ['./analysis-form.component.css']
})
export class AnalysisFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private analysisService = inject(AnalysisService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  analysisForm: FormGroup | null = null;
  isEditMode = false;
  isSubmitting = false;
  errorMessage: string | null = null;
  analysisId: number | null = null;

  ngOnInit(): void {
    this.initForm();
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.analysisId = +params['id'];
        this.loadAnalysis(this.analysisId);
      }
    });
  }

  initForm(): void {
    this.analysisForm = this.fb.group({
      transformerId: [null, Validators.required],
      analysisDate: ['', Validators.required],
      sampleDate: ['', Validators.required],
      laboratory: ['', Validators.required],
      method: ['', Validators.required],
      sampleTemperatureCelsius: [null],
      hydrogenH2Ppm: [null],
      methaneCh4Ppm: [null],
      acetyleneC2h2Ppm: [null],
      ethyleneC2h4Ppm: [null],
      ethaneC2h6Ppm: [null],
      carbonMonoxideCoPpm: [null],
      carbonDioxideCo2Ppm: [null],
      oxygenO2Ppm: [null],
      nitrogenN2Ppm: [null],
      observations: [''],
      sampleCondition: ['']
    });
  }

  loadAnalysis(id: number): void {
    this.analysisService.getById(id).subscribe({
      next: (analysis) => {
        this.analysisForm?.patchValue(analysis);
      },
      error: (error) => {
        console.error('Error loading analysis', error);
        this.errorMessage = 'Error loading analysis data';
      }
    });
  }

  onSubmit(): void {
    if (this.analysisForm?.valid) {
      this.isSubmitting = true;
      const formData = this.analysisForm.value;

      if (this.isEditMode && this.analysisId) {
        this.analysisService.update(this.analysisId, formData).subscribe({
          next: () => {
            this.isSubmitting = false;
            this.router.navigate(['/analyses']);
          },
          error: (error) => {
            console.error('Error updating analysis', error);
            this.errorMessage = 'Error updating analysis';
            this.isSubmitting = false;
          }
        });
      } else {
        this.analysisService.create(formData).subscribe({
          next: () => {
            this.isSubmitting = false;
            this.router.navigate(['/analyses']);
          },
          error: (error) => {
            console.error('Error creating analysis', error);
            this.errorMessage = 'Error creating analysis';
            this.isSubmitting = false;
          }
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/analyses']);
  }
}