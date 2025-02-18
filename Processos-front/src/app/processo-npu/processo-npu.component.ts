import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { ProcessoService } from '../shared/processo.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-processo-npu',
  templateUrl: './processo-npu.component.html',
  styleUrls: ['./processo-npu.component.scss']
})
export class ProcessoNpuComponent implements OnInit {

  processos: any;
  displayedColumns: string[] = ['position', 'npu', 'data_cadastro', 'uf', 'id', 'data_visualizacao', 'municipio', 'pdf', 'visualizado'];
  totalItems = 0;
  pageSize = 5;
  pageIndex = 0;
  npuProcessos: boolean = false;
  dataSource: any;
  npu: any;
  
  

  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private processoService: ProcessoService) {}

  ngOnInit(): void {
    
  }

  listarNpuProcessos(): void {
    this.processos = [];
    this.processoService.listarNpuProcessos(this.npu).subscribe((response) => {
        this.processos = response;
      });
      this.npuProcessos = true;
  }


  hidePanel(){
    this.npuProcessos = false;
  }

  clearAllFields(npuProcessosForm: NgForm){
    npuProcessosForm.resetForm();
    this.npuProcessos = false;
  }

}
