
package campominado;


import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



//HIERARQUIA
/*Possui as mesma propriedades que um JButton 
normal, porem com algumas funcionalidades 
exclusivas para o campo minado*/
public class InterfaceBotao extends JButton {
    int linha;
    int coluna;
    Campo campoLogico;
    Botao botaoLogico;
    InterfaceCampo campoGrafico;
    
    String texto;
    
    public InterfaceBotao(Campo c, InterfaceCampo cg){
        this.texto = "";
        this.setText(texto);
        this.campoLogico = c;
        this.campoGrafico = cg;
        
        this.addActionListener((java.awt.event.ActionEvent evt)->{
            Pressionado(false);
        });
        
        this.addMouseListener( new java.awt.event.MouseAdapter(){
            public void mousePressed(MouseEvent e){
                if(SwingUtilities.isRightMouseButton(e)){
                    Pressionado(true);
                }
            }
            
        } );
    }
   
   //Botao da interface gráfica, responsavel pelo reset
    public void Reset(){
        this.botaoLogico.Reset();
        this.texto = "";
        this.setText(texto);
        this.setEnabled(true);
        this.setIcon(null);
    }
    
    //Pega as posições da parte logica e define na interface grafica
    public void setPosicao(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.botaoLogico = campoLogico.getBotao(linha, coluna);
    }
    
    // Metodo que define o que fazer caso haja um clique com o botao direito
    // ou esquerdo
    private void Pressionado(boolean cliqueDireito){
      if(!cliqueDireito){//botão esquerdo
            if(!this.botaoLogico.sinalizado){//Se não ta marcado, pode abrir a casa
                this.clicar();
            }
      }else{
          this.marcar();
      }
      this.campoGrafico.checaStatus();
    }
    
    //Revela o que há embaixo do botão ao ser clicado
    public void revela(String codigo){ 
        if(codigo.equals("-1")){
            try{
                Image img = ImageIO.read(getClass().getResource("Mina.png"));
                img = img.getScaledInstance(Ctes.Tam_Botao, Ctes.Tam_Botao, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex){
                this.setText("-1"); 
                System.out.println("Erro!");
            }        
        }else{
           this.setText(codigo); 
        }
        this.setEnabled(false);
    }
    
    //Metodo que faz com que o numero 0 revele todos os vizinhos até achar um numero
    // maior que o proprio 0
    public void clicar(){
        int QtdVizinhosMinados = botaoLogico.RevelaInfo();        
   
        if(this.botaoLogico.minado){
            this.campoGrafico.RevelarMinas();
        }
       
        if(QtdVizinhosMinados == 0){
            /*Revela os vizinhos*/
            for(Botao vizinho : botaoLogico.vizinhos){
                if(!vizinho.clicado && !vizinho.sinalizado){    
                    vizinho.button.clicar();
                }
            } 
        }
        
        if(QtdVizinhosMinados!=0){
            this.texto = Integer.toString(QtdVizinhosMinados);
        }else{
            this.texto = "";
        }
        this.revela(this.texto);
        
    }
    
    //Coloca a "bandeira" no botao
    public void marcar(){
        if(this.botaoLogico.clicado){
            return;
        }
        
        boolean isMarcado = this.botaoLogico.Sinalizar();
        
        if (this.botaoLogico.sinalizado) {
           try{
                Image img = ImageIO.read(getClass().getResource("Flag.png"));
                img = img.getScaledInstance(Ctes.Tam_Botao, Ctes.Tam_Botao, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            }catch (Exception ex){
                this.setText("-1"); 
                System.out.println("Erro!");
            }

            }else{
                this.setIcon(null);
                this.setText("");
        } 
    }
}
