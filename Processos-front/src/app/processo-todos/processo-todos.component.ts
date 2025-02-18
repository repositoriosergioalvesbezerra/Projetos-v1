import { Component,  ViewChild, OnInit } from '@angular/core';
import { ProcessoService } from '../shared/processo.service';
import { NgForm } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { ProcessoDTO } from '../model/processoDTO';
import { Observable } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'app-processo-todos',
  templateUrl: './processo-todos.component.html',
  styleUrls: ['./processo-todos.component.scss']
})
export class ProcessoTodosComponent implements OnInit {

  processos: any = [];
  displayedColumns: string[] = ['position', 'npu', 'data_cadastro', 'uf', 'id', 'data_visualizacao', 'municipio', 'pdf', 'visualizado'];
  totalItems = 0;
  pageSize = 5;
  pageIndex = 0;
  todosProcessos: boolean = false;
  dataSource: any;
  page: any;
  size: any;
  processoDTO: any = new ProcessoDTO();

  

  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private processoService: ProcessoService) {}

  ngOnInit(): void {
    
  }

  listarTodosProcessos(): void {
    console.log("estou aqui listarTodosProcessos()");
    this.processoService.listarTodosProcessos(this.page, this.size).subscribe((response) => {
        this.processos = response;
        this.totalItems = response.totalProcessos;
      });
      this.todosProcessos = true;
  }

  onPageChange(event: PageEvent): void {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.listarTodosProcessos();
  }


  hidePanel(){
    this.todosProcessos = false;
  }

  clearAllFields(todosProcessosForm: NgForm){
    todosProcessosForm.resetForm();
    this.todosProcessos = false;
  }

  onRowClick(rowValue: any): void{
    this.atualizarVisibilidade(rowValue);
  }

  atualizarVisibilidade(rowValue: any): void{
      this.processoService.atualizarVisibilidade(rowValue.npu).subscribe({
        next: (data: any) => {
          alert(JSON.stringify(data, undefined, 2));
        },
        error: (httpError: HttpErrorResponse) => {
          alert(JSON.stringify(httpError.error, undefined, 2));
        }
      });
      
    }


}



