package campominado;


import java.util.ArrayList;

public class Botao {
    boolean clicado;
    boolean minado;
    boolean revelado;
    boolean sinalizado;
   
    ArrayList<Botao> vizinhos;
    InterfaceBotao button;
   
    //construtores da classe Botao
    public Botao(){
        this.clicado = false;
        this.minado = false;
        this.revelado = false;
        this.sinalizado = false;
        this.vizinhos = new ArrayList();
    }
    
    public void AdicionaVizinho(Botao button){
        this.vizinhos.add(button);
    }
    
    //metodo para definir botões com minas
    /*Boa prática, se o boão ja estivdr minado
    o programa não deixa que ele fique minado
    mais de uma vez*/
    
    public boolean SetMina(){
       if(!this.minado){
           this.minado = true;
           return true;
       }else{
           return false;
       }    
    }
    
    //Sinalizador de marcacao do botao
    public boolean Sinalizar(){
       this.sinalizado = !this.sinalizado;
       return this.sinalizado;
    }
   
    //Varre a região de botões ao redor para dizer quem tem mina
    public int NumMinasAoRedor(){
        int nBombas = 0;
        for (Botao button : this.vizinhos){
          if(button.minado){
              nBombas++;
          } 
       } 
        return nBombas;
    }
    
    //Revela o que tem no botao depois de clicado
    //-1 == Mina
    //0 == não possui mina
    //n == possui n minas ao redor
    public int RevelaInfo(){
        this.clicado = true;
        if(this.minado){
            return -1;
        }else{
            return NumMinasAoRedor();
        }
    }
    
    //Reseta o botao
    public void Reset(){
        this.clicado = false;
        this.revelado = false;
        this.minado = false;
        this.sinalizado = false;
    }
    
    //Define se o botão ainda tem açao a executar ou nao
    public boolean Finalizado(){
        if(this.minado && this.sinalizado){
            return true;
        }
        if(!this.minado && !this.sinalizado && this.clicado){
            return true;
        }
        return false;
    }
    
    public void setButton(InterfaceBotao button){
        this.button = button;
    }
    
    @Override
    public String toString() {
        if(this.minado){
            return "-1";
        }
        return "+" + this.NumMinasAoRedor();
    }
    
}
