import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoNpuComponent } from './processo-npu.component';

describe('ProcessoNpuComponent', () => {
  let component: ProcessoNpuComponent;
  let fixture: ComponentFixture<ProcessoNpuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessoNpuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoNpuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
