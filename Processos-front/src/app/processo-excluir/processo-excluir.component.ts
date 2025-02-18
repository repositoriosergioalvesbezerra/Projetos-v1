import { Component, OnInit } from '@angular/core';
import { ProcessoService } from '../shared/processo.service';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-processo-excluir',
  templateUrl: './processo-excluir.component.html',
  styleUrls: ['./processo-excluir.component.scss']
})
export class ProcessoExcluirComponent implements OnInit {

   npu: any;
   resultado: any;
   excluirProcesso: boolean = false;

  constructor(private processoService: ProcessoService) {}

  ngOnInit(): void {
    
  }

  excluirProcessoPorNpu(): Observable<any> {
    this.processoService.excluirProcessoPorNpu(this.npu).subscribe({
      next: (data: any) => {
        this.resultado = JSON.stringify(data, undefined, 2);
      },
      error: (httpError: HttpErrorResponse) => {
        this.resultado = JSON.stringify(httpError.error, undefined, 2);
      }
    });
   
    this.excluirProcesso = true;
    return this.resultado;
  }


  hidePanel(){
    this.excluirProcesso = false;
  }

  clearAllFields(excluirProcessosForm: NgForm){
    excluirProcessosForm.resetForm();
    this.excluirProcesso = false;
  }

}
