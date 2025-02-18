import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoExcluirComponent } from './processo-excluir.component';

describe('ProcessoExcluirComponent', () => {
  let component: ProcessoExcluirComponent;
  let fixture: ComponentFixture<ProcessoExcluirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessoExcluirComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoExcluirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
