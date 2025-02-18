import { Component, OnInit } from '@angular/core';
import { ProcessoService } from '../shared/processo.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ProcessoDTO } from '../model/processoDTO';

@Component({
  selector: 'app-processo-cadastro',
  templateUrl: './processo-cadastro.component.html',
  styleUrls: ['./processo-cadastro.component.scss']
})
export class ProcessoCadastroComponent implements OnInit {

  isProcessoDTO: boolean = false;
  respostaUF: any = [];
  respostaMunicipio: any = [];
  respostaCadastro: any;
  estadoId: any;
  processoDTO: any = new ProcessoDTO();
  municipioId: any;
  pdf: any;

    
  ngOnInit(): void {
    this.listarTodosEstadosBrasil();
  }

  constructor(private processoService: ProcessoService){}

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


  cadastrarProcesso():Observable<any>{
    this.searchEstadosBrasil();
    this.searchMunicipioBrasil();

    console.log(this.processoDTO);

    this.processoService.cadastrarProcesso(this.processoDTO).subscribe({
      next: (data: any) => {
        this.respostaCadastro = JSON.stringify(data, undefined, 2);
      },
      error: (httpError: HttpErrorResponse) => {
        this.respostaCadastro = JSON.stringify(httpError.error, undefined, 2);
      }
    });

    this.isProcessoDTO = true;
    
    return this.respostaCadastro;
  }


  hidePanel(){
    this.isProcessoDTO  = false;
  }

  clearAllFields(ufForm: NgForm){
    ufForm.resetForm();
    this.isProcessoDTO = false;
  }

  onChangeEstado(estados: any){
    this.estadoId = estados;
    this.listarTodosMunicipiosPorEstadosBrasil(); 
  }

  onChangeMunicipio(municipio: any){
    this.municipioId = municipio;
  }
  

}
