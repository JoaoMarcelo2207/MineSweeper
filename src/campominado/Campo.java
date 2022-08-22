
package campominado;
import java.util.Random;

public class Campo {
    Botao[][] MatrizButtonL;
    
    
    //Cria o campo, adiciona os botoes e define os vizinhos
    public Campo(){
        MatrizButtonL = new Botao[Ctes.Num_Linhas][Ctes.Num_Colunas];
        for(int i=0; i<Ctes.Num_Linhas; i++){
            for(int j=0; j<Ctes.Num_Colunas; j++){
               MatrizButtonL[i][j] = new Botao(); 
            }
        }
        for(int i=0; i<Ctes.Num_Linhas; i++){
            for(int j=0; j<Ctes.Num_Colunas; j++){
               if (i>0){
                   if(j>0) {
                       MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i-1][j-1]);
                   }
                   MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i-1][j]);
                   if(j<Ctes.Num_Colunas -1){
                       MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i-1][j+1]);
                   }
               }
               if(j>0) {
                   MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i][j-1]);
               }
               if(j<Ctes.Num_Colunas -1){
                   MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i][j+1]);
               }
               if (i<Ctes.Num_Linhas-1){
                   if(j>0) {
                       MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i+1][j-1]);
                   }
                   MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i+1][j]);
                   if(j<Ctes.Num_Colunas -1){
                       MatrizButtonL[i][j].AdicionaVizinho(MatrizButtonL[i+1][j+1]);
                   }
               }
               
            }
        }
    }
    
    //Coloca a quantidade de minas randomicamente entre os botes da matriz
    public void AdicionaMinas(){
        int nMinas = Ctes.Num_Minas;
        
        while (nMinas > 0){
            Random sorteio = new Random();
            int l = sorteio.nextInt(Ctes.Num_Linhas);
            int c = sorteio.nextInt(Ctes.Num_Colunas);
            
            if(MatrizButtonL[l][c].SetMina()){
                nMinas--;
            }
        }
    }
    
    //Define o metodo clicar() da classe botao, para todos os botoes da matriz 
    public int ActionClicar(int linha, int coluna){
        return MatrizButtonL[linha][coluna].RevelaInfo();
    }
    
    //Logica para determinar que venceu
    public boolean Win(){
        for(int i=0; i<Ctes.Num_Linhas; i++){
            for(int j=0; j<Ctes.Num_Colunas; j++){
                if (!MatrizButtonL[i][j].Finalizado()){
                    return false;
                }
            }
        }
        return true; 
    }
    
    //Logica para determinar que perdeu
    public boolean Lose(){
        for(int i=0; i<Ctes.Num_Linhas; i++){
            for(int j=0; j<Ctes.Num_Colunas; j++){
                if (MatrizButtonL[i][j].clicado && MatrizButtonL[i][j].minado){
                    return true;
                }
            }
        }
        return false; 
    }
    
    public Botao getBotao(int linha, int coluna){
        return MatrizButtonL[linha][coluna];
    }
    
   
    
    @Override
    public String toString() {
        String str = "";
        
        for(int i=0; i<Ctes.Num_Linhas; i++){
            for(int j=0; j<Ctes.Num_Colunas; j++){
                str+=MatrizButtonL[i][j] + " ";
            }
            str+= "\n";
        }
        
        return str;
    }
    
    
}
