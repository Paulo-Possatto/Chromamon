import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransformerListComponent } from './transformer-list.component';

describe('TransformerListComponent', () => {
  let component: TransformerListComponent;
  let fixture: ComponentFixture<TransformerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransformerListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransformerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
