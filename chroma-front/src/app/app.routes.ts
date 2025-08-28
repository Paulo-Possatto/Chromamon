import { Routes } from '@angular/router';

// Components
import { LoginComponent } from './features/auth/login/login.component';
import { AnalysisListComponent } from './features/analyses/analysis-list/analysis-list.component';
import { AnalysisFormComponent } from './features/analyses/analysis-form/analysis-form.component';
import { TransformerListComponent } from './features/transformers/transformer-list/transformer-list.component';
import { TransformerFormComponent } from './features/transformers/transformer-form/transformer-form.component';
import { DiagnosticComponent } from './features/diagnostic/diagnostic.component';
import { ReportComponent } from './features/reports/report.component';

// Guards
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'analyses', component: AnalysisListComponent, canActivate: [authGuard] },
  { path: 'analyses/new', component: AnalysisFormComponent, canActivate: [authGuard] },
  { path: 'analyses/:id/edit', component: AnalysisFormComponent, canActivate: [authGuard] },
  { path: 'transformers', component: TransformerListComponent, canActivate: [authGuard] },
  { path: 'transformers/new', component: TransformerFormComponent, canActivate: [authGuard] },
  { path: 'transformers/:id/edit', component: TransformerFormComponent, canActivate: [authGuard] },
  { path: 'diagnostic/:analysisId', component: DiagnosticComponent, canActivate: [authGuard] },
  { path: 'reports', component: ReportComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '/login' }
];