
package campominado;


/*Esta classe abstrata permite a modularidade
do campo, podendo alterar tanto o numero de
colunas quanto o numero de linhas, alem de conter 
variaveis responsáveis pelo layout da interface grafica*/
public abstract class Ctes {
    static int Num_Linhas = 5;
    static int Num_Colunas = 5;
    static int Num_Minas = 5;
    static int Tam_Botao = 63;
    static int Ofset_Sup = 100;
    static int Altura_Barra_Sup = 20;
    static int Corecao = Tam_Botao - Tam_Botao/2; 
}
