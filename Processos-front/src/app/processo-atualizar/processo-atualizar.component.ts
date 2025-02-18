import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { ProcessoService } from '../shared/processo.service';
import { ProcessoDTO } from '../model/processoDTO';

@Component({
  selector: 'app-processo-atualizar',
  templateUrl: './processo-atualizar.component.html',
  styleUrls: ['./processo-atualizar.component.scss']
})
export class ProcessoAtualizarComponent implements OnInit {

  atualizaRespostaProcessoPorNpu: boolean = false;
  respostaAtualizaProcessoPorNpu: any;
  processoDTO: any = new ProcessoDTO();
  respostaUF: any = [];
  estadoId: any;
  municipioId: any;
  respostaMunicipio: any = [];
  pdf: any;

  ngOnInit(): void {
    this.listarTodosEstadosBrasil();
  }

  
  constructor(private processoService: ProcessoService){}

  atualizarProcessoPorNpu(): Observable<any>{
    this.searchEstadosBrasil();
    this.searchMunicipioBrasil();
   
    this.processoService.atualizarProcessoPorNpu(this.processoDTO).subscribe({
      next: (data: any) => {
        this.respostaAtualizaProcessoPorNpu = JSON.stringify(data, undefined, 2);
      },
      error: (httpError: HttpErrorResponse) => {
        this.respostaAtualizaProcessoPorNpu = JSON.stringify(httpError.error, undefined, 2);
      }
    });

    this.atualizaRespostaProcessoPorNpu = true;
    
    return this.respostaAtualizaProcessoPorNpu;
  }


  hidePanel(){
    this.atualizaRespostaProcessoPorNpu = false;
  }

  clearAllFields(atualizaProcessoPorNPUForm: NgForm){
    atualizaProcessoPorNPUForm.resetForm();
    this.atualizaRespostaProcessoPorNpu = false;
  }


  listarTodosEstadosBrasil(): Observable<any>{
      this.respostaUF = [];
      this.processoService.listarTodosEstadosBrasil().subscribe({
        next: (data: any) => {
          this.respostaUF = data;
          this.listarTodosMunicipiosPorEstadosBrasil();
        },
        error: (httpError: HttpErrorResponse) => {
          this.respostaUF = JSON.stringify(httpError.error, undefined, 2);
        }
      });
      
      return this.respostaUF;
    }
  
    searchEstadosBrasil(): void{
      this.processoService.searchEstadosBrasil(this.estadoId).subscribe((response) => {
        this.processoDTO.uf = response.sigla;
      });  
    }
  
    searchMunicipioBrasil(): void{
      this.processoService.searchMunicipioBrasil(this.municipioId).subscribe((response) => {
        this.processoDTO.municipio = response.nome;
      });  
    }
  
  
    listarTodosMunicipiosPorEstadosBrasil(): Observable<any>{
      this.respostaMunicipio = [];
      this.processoService.listarTodosMunicipiosPorEstadosBrasil(this.estadoId).subscribe({
        next: (data: any) => {
          this.respostaMunicipio = data;
        },
        error: (httpError: HttpErrorResponse) => {
          this.respostaMunicipio = JSON.stringify(httpError.error, undefined, 2);
        }
      });
      
      return this.respostaMunicipio;
    }


    onChangeEstado(estados: any){
      this.estadoId = estados;
      this.listarTodosMunicipiosPorEstadosBrasil(); 
    }
  
    onChangeMunicipio(municipio: any){
      this.municipioId = municipio;
    }

}
