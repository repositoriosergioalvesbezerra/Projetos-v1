import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProcessoPage } from '../model/processos-page';
import { first } from 'rxjs/operators';
import { ProcessoDTO } from '../model/processoDTO';

@Injectable({
  providedIn: 'root'
})
export class ProcessoService {

  private urlBase: string = "http://localhost:8081/v1/api/processos";
  private urlEstadosBrasil = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";
  private urlMunicipiosEstadosBrasil = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
  private urlParaUmEstado = "https://servicodados.ibge.gov.br/api/v1/localidades/estados"
  private urlMunicipioBrasil = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios"

  constructor(public http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  listarTodosProcessos(page: string, size: string): Observable<any>{
    let finalUrl = this.urlBase + "/all" + "?" + "page=" + page + "&" + "size=" + size;
    return this.http.get<ProcessoPage>(finalUrl, this.httpOptions).pipe(first());
  }
  

  listarNpuProcessos(npu: string): Observable<any>{
    let finalUrl = this.urlBase + "/" + npu;
    return this.http.get<ProcessoDTO>(finalUrl, this.httpOptions).pipe(first());
  }

  listarTodosEstadosBrasil(): Observable<any>{
    return this.http.get<any>(this.urlEstadosBrasil, this.httpOptions);
  }

  searchEstadosBrasil(estado: string): Observable<any>{
    let finalUrl = this.urlParaUmEstado + "/" + estado;
    return this.http.get<any>(finalUrl, this.httpOptions);
  }

  searchMunicipioBrasil(municipio: string): Observable<any>{
    let finalUrl = this.urlMunicipioBrasil + "/" + municipio;
    return this.http.get<any>(finalUrl, this.httpOptions);
  }

  listarTodosMunicipiosPorEstadosBrasil(estado: string): Observable<any>{
    let finalUrl = this.urlMunicipiosEstadosBrasil + "/" + estado + "/municipios";
    return this.http.get<any>(finalUrl, this.httpOptions).pipe(first());
  }

  excluirProcessoPorNpu(npu: string): Observable<any>{
    let finalUrl = this.urlBase + "/" + npu;
    return this.http.delete<any>(finalUrl);
  }

  atualizarProcessoPorNpu(processoDTO: ProcessoDTO): Observable<any> {
    let finalUrl = this.urlBase + "/atualizar" ;
    return this.http.put<any>(finalUrl, processoDTO, this.httpOptions);
  }

  atualizarVisibilidade(npu : string): Observable<any> {
    let finalUrl = this.urlBase + "/atualizar/visualizacoes/" + npu;
    return this.http.put<any>(finalUrl, this.httpOptions);
  }

  cadastrarProcesso(processoDTO: ProcessoDTO): Observable<any> {
    let finalUrl = this.urlBase + "/cadastros" ;
    return this.http.post<any>(finalUrl, processoDTO, this.httpOptions);
  }


  

}
