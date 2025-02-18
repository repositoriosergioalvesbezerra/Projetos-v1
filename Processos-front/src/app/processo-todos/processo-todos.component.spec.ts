import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessoTodosComponent } from './processo-todos.component';

describe('ProcessoTodosComponent', () => {
  let component: ProcessoTodosComponent;
  let fixture: ComponentFixture<ProcessoTodosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessoTodosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessoTodosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
