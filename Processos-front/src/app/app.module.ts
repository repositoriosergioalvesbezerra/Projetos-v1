import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatExpansionModule } from '@angular/material/expansion';
import { ProcessoService } from './shared/processo.service';
import { ProcessoTodosComponent } from './processo-todos/processo-todos.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CommonModule } from '@angular/common';
import { ProcessoNpuComponent } from './processo-npu/processo-npu.component';
import { ProcessoExcluirComponent } from './processo-excluir/processo-excluir.component';
import { ProcessoAtualizarComponent } from './processo-atualizar/processo-atualizar.component';
import { ProcessoCadastroComponent } from './processo-cadastro/processo-cadastro.component';



@NgModule({
  declarations: [
    AppComponent,
    ProcessoTodosComponent,
    ProcessoNpuComponent,
    ProcessoExcluirComponent,
    ProcessoAtualizarComponent,
    ProcessoCadastroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    CommonModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [ProcessoService],
  bootstrap: [AppComponent]
})
export class AppModule { }



