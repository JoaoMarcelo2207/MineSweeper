
package campominado;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


//HIERARQUIA
/*Possui as mesma propriedades que um JFrame 
normal, porem com algumas funcionalidades 
exclusivas para o campo minado*/
public class InterfaceCampo extends JFrame{
    JPanel panel;
    InterfaceBotao[][] MatrizB;
    Campo c;
    JButton ResetButton;
    JButton Facil;
    JButton Medio;
    JButton Dificil;
    JButton Custom;
    
    public InterfaceCampo(){
        ConfigIniciais();
    }
    
    //Reseta todos os botoes do jogo
    //metodo auxiliar para os botoes de dificuldade
    
    
    public void HardReset(){
        Apagao();
        ConfigIniciais();
    }
    
    //Metodo auxiliar para os botoes de dificuldade
    private void Apagao(){
        this.panel.removeAll();   
    }
    
    //Define como o jogo começa
    private void ConfigIniciais(){
        this.c = new Campo();
        c.AdicionaMinas();
        this.panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        MatrizB = new InterfaceBotao[Ctes.Num_Linhas][Ctes.Num_Colunas];
        int n = 0;
        
        for(int i = 0; i< Ctes.Num_Linhas; i++){
            for(int j = 0; j< Ctes.Num_Colunas; j++){
                MatrizB[i][j]= new InterfaceBotao(this.c, this);
                c.getBotao(i, j).setButton(MatrizB[i][j]);
                MatrizB[i][j].setPosicao(i, j);
                MatrizB[i][j].setSize(Ctes.Tam_Botao, Ctes.Tam_Botao);
                MatrizB[i][j].setLocation(Ctes.Tam_Botao*j, Ctes.Tam_Botao*i+Ctes.Ofset_Sup );
                
               // MatrizB[i][j].setText(Integer.toString(n++));
                panel.add(MatrizB[i][j]);
            }
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Ctes.Num_Colunas*Ctes.Tam_Botao, Ctes.Num_Linhas*Ctes.Tam_Botao + Ctes.Ofset_Sup + Ctes.Altura_Barra_Sup);
        this.setResizable(false);
        this.setVisible(true);
        
        //Botao de reset do jogo
        this.ResetButton = new JButton(":)"); 
        this.ResetButton.addActionListener((java.awt.event.ActionEvent evt)->{
            this.Reset();
        });
        this.ResetButton.setSize(Ctes.Tam_Botao, Ctes.Tam_Botao);
        this.ResetButton.setLocation((Ctes.Tam_Botao*Ctes.Num_Colunas)/2-Ctes.Tam_Botao/2, Ctes.Ofset_Sup - Ctes.Tam_Botao);
        this.panel.add(this.ResetButton);
        
        //Botao de dificuldade "Fácil" do jogo
        this.Facil = new JButton("Fácil");
        this.Facil.addActionListener((java.awt.event.ActionEvent evt)->{
           Ctes.Num_Colunas =6;
           Ctes.Num_Linhas = 6;
           Ctes.Num_Minas = 6;
           this.HardReset();
        });
        this.Facil.setSize((Ctes.Tam_Botao*Ctes.Num_Colunas)/4, Ctes.Corecao);
        this.Facil.setLocation(0,0);
        this.panel.add(this.Facil);
        
        //Botao de dificuldade "Médio" do jogo
        this.Medio = new JButton("Médio");
        this.Medio.addActionListener((java.awt.event.ActionEvent evt)->{
           Ctes.Num_Colunas = 10;
           Ctes.Num_Linhas = 10;
           Ctes.Num_Minas = 15;
           Ctes.Tam_Botao = 50;
           this.HardReset();
        });
        this.Medio.setSize((Ctes.Tam_Botao*Ctes.Num_Colunas)/4, Ctes.Corecao);
        this.Medio.setLocation((Ctes.Tam_Botao*Ctes.Num_Colunas)/4,0);
        this.panel.add(this.Medio);
        
        //Botao de dificuldade "Difícil" do jogo
        this.Dificil = new JButton("Difícil");
        this.Dificil.addActionListener((java.awt.event.ActionEvent evt)->{
           Ctes.Num_Colunas = 13;
           Ctes.Num_Linhas = 13;
           Ctes.Num_Minas = 70;
           Ctes.Tam_Botao = 45;
           this.HardReset();
        });
        this.Dificil.setSize((Ctes.Tam_Botao*Ctes.Num_Colunas)/4, Ctes.Corecao);
        this.Dificil.setLocation((Ctes.Tam_Botao*Ctes.Num_Colunas)/4*2,0);
        this.panel.add(this.Dificil);
        
        //Botao de dificuldade "Custom" do jogo
        this.Custom = new JButton("Custom");
        this.Custom.addActionListener((java.awt.event.ActionEvent evt)->{
            
            int l = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero de linhas"));
            Ctes.Num_Linhas = l;
            
            int c = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero de colunas"));
            Ctes.Num_Colunas = c;
            
            int b = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero de bombas"));
            Ctes.Num_Minas = b;
            
            
            this.HardReset();
        });
        this.Custom.setSize((Ctes.Tam_Botao*Ctes.Num_Colunas)/4, Ctes.Corecao);
        this.Custom.setLocation((Ctes.Tam_Botao*Ctes.Num_Colunas)/4*3,0);
        this.panel.add(this.Custom);
    }
    
    //Usa o metodo logico da classe JButtonBotao para resetar
    //a matriz grafica
    public void Reset(){
        for(int i = 0; i< Ctes.Num_Linhas; i++){
            for(int j = 0; j< Ctes.Num_Colunas; j++){    
                MatrizB[i][j].Reset();
            }
        }
        this.c.AdicionaMinas();
    }
    
    //Usa o metodo logico da classe JButtonBotao para revelar
    //as minas na matriz grafica
    public void RevelarMinas(){
        for(int i = 0; i< Ctes.Num_Linhas; i++){
            for(int j = 0; j< Ctes.Num_Colunas; j++){
                if(MatrizB[i][j].botaoLogico.minado){
                    MatrizB[i][j].revela("-1");    
                }
            }
        }
    }
    
    //Não deixa o botão ser clicado caso o jogo acabe
    public void desativaBotoes(){ 
         for(int i = 0; i< Ctes.Num_Linhas; i++){
            for(int j = 0; j< Ctes.Num_Colunas; j++){    
                MatrizB[i][j].setEnabled(false);
            }
        }
    }
    
    //verifica se ganhou ou não a cada clique
    public void checaStatus(){
        if(this.c.Win()){
            JOptionPane.showMessageDialog(null, "Você venceu");
            this.desativaBotoes();
        }
        if(this.c.Lose()){
            JOptionPane.showMessageDialog(null, "Você Perdeu");
            this.desativaBotoes();
        }
    }
}
