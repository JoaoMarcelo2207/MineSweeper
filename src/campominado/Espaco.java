/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominado;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jmcos
 */

public class Espaco {
    
    boolean minado; // o espaço com mina
    boolean marcado; // o espaço marcado com a bandeira
    boolean clicado; // o espaço clicado
    boolean revelado; // o espaço revelado
    
    //Espaco vizinhos[]; //vetor com os espaços vizinhos de um espaço
    ArrayList<Espaco> vizinhos;
    
    public Espaco() {
        this.minado = false;
        this.marcado = false;
        this.clicado = false;
        this.revelado = false;
        this.vizinhos = new ArrayList();
    }
    
    public void adicionaVizinhos(Espaco e){
        this.vizinhos.add(e);
    }
    
    //indica que o espaco tem uma mina
    //se o espaco ja tiver uma mina, ele retorna false
    // se o espaco não tinha uma mina antes , retorna verdadeiro
    public boolean minar(){
        if(this.minado == false){
            this.minado = true;
            return true;
        }
        else return false; // o espaço ja esta minado 
    }
    
    public boolean marcarBandeira(){ // marca ou desmarca
       this.marcado = !this.marcado;
       return this.marcado;
    }
    
    // -1 = tem bomba
    // 0 = não tem bombas
    // n = num de bombas vizinhas
    public int clicar(){
        this.clicado = true;
        if(this.minado == true){
            return -1; //tinha bomba retorna -1
        }
        
        else return numMinasVizinhas(); // o numero que aparece e quantas bombas tem ao redor dele 
    }
    
    public int numMinasVizinhas(){
        int n = 0;
        for (Espaco e : this.vizinhos) { //percorre todos os vizinhos e se encontrar uma bomba aumenta o contador
            if(e.minado) n++;
        }
        return n; // retorna o numero de bombas
    }
    
    public void renicia(){ // renicia o jogo
        this.clicado = false;
        this.marcado = false;
        this.minado = false;
        this.revelado = false;
    }
    
    public boolean cabou(){ // ve se o jogo acabou
        if(this.minado == true && this.marcado == true) return true;
        if(this.minado == false && this.marcado == false  && this.clicado == true) return true;
        return false;
    }

    @Override
    public String toString() { // printar
        if (this.minado) return "-1"; 
        return "+" + this.numMinasVizinhas();
    }
    
    
    
}
