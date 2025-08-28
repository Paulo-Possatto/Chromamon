import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TransformerService } from '../../../core/services/transformer.service';
import { Transformer } from '../../../core/models/transformer.model';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-transformer-list',
  standalone: true,
  imports: [NgClass],
  templateUrl: './transformer-list.component.html',
  styleUrls: ['./transformer-list.component.css']
})
export class TransformerListComponent implements OnInit {
  private transformerService = inject(TransformerService);
  private router = inject(Router);

  transformers: Transformer[] = [];
  loading: boolean = false;

  ngOnInit(): void {
    this.loadTransformers();
  }

  loadTransformers(): void {
    this.loading = true;
    this.transformerService.getAll().subscribe({
      next: (data) => {
        this.transformers = data;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading transformers', error);
        this.loading = false;
      }
    });
  }

  addTransformer(): void {
    this.router.navigate(['/transformers/new']);
  }

  editTransformer(id: number): void {
    this.router.navigate(['/transformers', id, 'edit']);
  }

  deleteTransformer(id: number): void {
    if (confirm('Are you sure you want to delete this transformer?')) {
      this.transformerService.delete(id).subscribe({
        next: () => {
          this.loadTransformers();
        },
        error: (error) => {
          console.error('Error deleting transformer', error);
          alert('Error deleting transformer');
        }
      });
    }
  }

  getStatusBadgeClass(status: string): any {
    return {
      'bg-success': status === 'ACTIVE',
      'bg-secondary': status === 'INACTIVE',
      'bg-warning': status === 'MAINTENANCE'
    };
  }
}