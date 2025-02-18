import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoAtualizarComponent } from './processo-atualizar.component';

describe('ProcessoAtualizarComponent', () => {
  let component: ProcessoAtualizarComponent;
  let fixture: ComponentFixture<ProcessoAtualizarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessoAtualizarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoAtualizarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
