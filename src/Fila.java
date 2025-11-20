/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilh
 */
public class Fila {
    
    Nodo primeiro;
    Nodo ultimo;
    int totalDeSolicitacoes;
    
    public Fila(){
        primeiro = null;
        ultimo = null;
        totalDeSolicitacoes = 0;
    }
    
    public void CadstrarSolicitacao(Solicitacao solicitacao){
        Nodo novaSolicitacao = new Nodo(solicitacao);
        novaSolicitacao.solicitacao = solicitacao;
        
        // Fila vazia
        if (primeiro == null && ultimo == null){
            primeiro = novaSolicitacao;
            ultimo = primeiro;
            totalDeSolicitacoes++;
            
        } else {
            // As solicitações com maior grau vão para o inicio da fila
            Nodo atual = null; 
            
            if (solicitacao.getGrauDeEmergencia()== 4){
                atual = primeiro;
                primeiro = novaSolicitacao;
                primeiro.posterior = atual;  
                totalDeSolicitacoes++;      
                        
            } else if (solicitacao.getGrauDeEmergencia() == 1){            
                atual = ultimo;
                ultimo = novaSolicitacao;
                atual.posterior = ultimo;
                totalDeSolicitacoes++;
            }
            
            /* Se o grau não for 1 e nem 4, percorre a lista e é adicionado depois da solicitação de grau maior e antes 
            da solicitação de grau menor; */
            else {
                Nodo anterior = null;
                
                anterior = primeiro;
                atual = anterior.posterior;
                
                int grauEmergenciaNova = novaSolicitacao.solicitacao.getGrauDeEmergencia();
                int grauEmergenciaAuxiliar = atual.solicitacao.getGrauDeEmergencia();
                int grauEmergenciaAuxiliar1 = anterior.solicitacao.getGrauDeEmergencia();
                
                while (anterior != ultimo) {
                    
                    if (grauEmergenciaNova >= grauEmergenciaAuxiliar1 && grauEmergenciaNova < grauEmergenciaAuxiliar){
                        
                        anterior.posterior = novaSolicitacao;
                        novaSolicitacao.posterior = atual;
                        totalDeSolicitacoes++;
                        break;
                    }
                    
                    anterior = atual;
                    atual = anterior.posterior;
                                                            
                }
                
            }
        }
                
    }
    
    
    public void atenderSolicitacao(){
            
        if (totalDeSolicitacoes == 0){
            System.out.println("Não há solicitações de apoio pendentes!!!");
        } else {
            
            Nodo atual = primeiro;
            
            primeiro = primeiro.posterior;
            atual.posterior = null;
            
            System.out.println("Solicitação de ID "+ atual.solicitacao.getID() + "será resolvida. ");
                      
        }
        
    }
    
}
