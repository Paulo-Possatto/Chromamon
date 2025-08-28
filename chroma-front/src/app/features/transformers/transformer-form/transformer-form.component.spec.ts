import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransformerFormComponent } from './transformer-form.component';

describe('TransformerFormComponent', () => {
  let component: TransformerFormComponent;
  let fixture: ComponentFixture<TransformerFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransformerFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransformerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
