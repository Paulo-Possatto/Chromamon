import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TransformerService } from '../../../core/services/transformer.service';
import { Transformer } from '../../../core/models/transformer.model';

@Component({
  selector: 'app-transformer-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './transformer-form.component.html',
  styleUrls: ['./transformer-form.component.css']
})
export class TransformerFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private transformerService = inject(TransformerService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  transformerForm: FormGroup | null = null;
  isEditMode = false;
  isSubmitting = false;
  errorMessage: string | null = null;
  transformerId: number | null = null;

  ngOnInit(): void {
    this.initForm();
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.transformerId = +params['id'];
        this.loadTransformer(this.transformerId);
      }
    });
  }

  initForm(): void {
    this.transformerForm = this.fb.group({
      tag: ['', Validators.required],
      serialNumber: ['', Validators.required],
      manufacturer: ['', Validators.required],
      model: [''],
      yearManufacture: [null],
      nominalPowerKva: [null, Validators.required],
      primaryVoltageKv: [null, Validators.required],
      secondaryVoltageKv: [null, Validators.required],
      connectionType: ['', Validators.required],
      coolingType: ['', Validators.required],
      installationDate: [''],
      location: ['', Validators.required],
      substation: [''],
      status: ['']
    });
  }

  loadTransformer(id: number): void {
    this.transformerService.getById(id).subscribe({
      next: (transformer) => {
        this.transformerForm?.patchValue(transformer);
      },
      error: (error) => {
        console.error('Error loading transformer', error);
        this.errorMessage = 'Error loading transformer data';
      }
    });
  }

  onSubmit(): void {
    if (this.transformerForm?.valid) {
      this.isSubmitting = true;
      const formData = this.transformerForm.value;

      if (this.isEditMode && this.transformerId) {
        this.transformerService.update(this.transformerId, formData).subscribe({
          next: () => {
            this.isSubmitting = false;
            this.router.navigate(['/transformers']);
          },
          error: (error) => {
            console.error('Error updating transformer', error);
            this.errorMessage = 'Error updating transformer';
            this.isSubmitting = false;
          }
        });
      } else {
        this.transformerService.create(formData).subscribe({
          next: () => {
            this.isSubmitting = false;
            this.router.navigate(['/transformers']);
          },
          error: (error) => {
            console.error('Error creating transformer', error);
            this.errorMessage = 'Error creating transformer';
            this.isSubmitting = false;
          }
        });
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/transformers']);
  }
}