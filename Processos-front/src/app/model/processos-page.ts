import { ProcessoDTO } from "./processoDTO";

export interface ProcessoPage {

    processos: ProcessoDTO[];
    totalProcessos: number;
    totalPaginas: number;

}