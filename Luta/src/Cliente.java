
import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Cliente {

    private static int opcao;

	public static void main(String[] args) throws IOException{
    	
        // Realizando Conexão com Servidor.
        Socket Servidor = new Socket("127.0.0.1", 4242); // Conectando ao IP 127.0.0.1:4242
        System.out.println("Conexão Estabelecida.");

        // Crio duas instancias de PrintStream e BufferedRead
        // Output envia informações e a input recebe informações
        PrintStream Output = new PrintStream(Servidor.getOutputStream()); // Enviando dados para maquina 1 via Socket.
        BufferedReader Input = new BufferedReader(new InputStreamReader(Servidor.getInputStream())); // Recebendo dados da maquina 1 via Socket.

        
        
        //Pega o que for digitado no teclado
        Scanner teclado = new Scanner(System.in);
        
        // Pegamos informações do lutador
        System.err.println("Digite o nome do lutador: ");
        String nome = teclado.nextLine();
        
        System.err.println("Digite o país do lutador: ");
        String pais = teclado.nextLine();
        
        //Criamos uma instancia de luta, e colocamos um lutador na luta
        Luta luta = new Luta( new Lutador(nome, pais, " MMA - Cliente ") );
        
        boolean conectado = true; // Deixa o jogo em loop até que alguém perca
    	boolean ataque = false;   // Define o modo inicial do jogador 
        
        while (conectado) {
        	if(ataque) {  //Modo de ataque
        		System.out.println(" LUTA - digite um número para escolher o lado de ataque; ");
            	System.out.println(" 1 - Direita ");
            	System.out.println(" 2 - Esquerda ");
            	//System.out.println(" 3 - Infomacoes");
            	
            	int opcao = teclado.nextInt();
            	switch (opcao) {
    			case (1):
    				System.out.println("Atacou na direita ... ");
    			
    				// Envia 1 que significa ataque na direita
    				Output.println(1);
    				break;
    			case (2):
    				System.out.println("Atacou na esquerda ...");
    				
    				// Envia 2 que significa ataque na esquerda
    				Output.println(2);
    				break;
    			case (3):
    				
    				// Imprime informações do lutador
    				System.out.println( luta.getLutador().toString() );
    			default:
    				break;
    			}
        	}else {  // Modo de defesa
        		System.out.println(" LUTA - digite um número para escolher o lado de DEFESA; ");
            	System.out.println(" 1 - Direita ");
            	System.out.println(" 2 - Esquerda ");
            	//System.out.println(" 3 - Infomacoes");
            	
            	try{
            		
            	int opcao = teclado.nextInt();
            	switch (opcao) {
    			case (1):
    				System.out.print("Defendeu na direita ... ");
    				break;
    			case (2):
    				System.out.print("Defendeu na esquerda ...");
    				break;
    			case (3):
    				System.out.println( luta.getLutador().toString() );
    			default:
    				break;
    			}
            	}catch(Exception erro){
            		System.err.println("pode digitar apenas a opcao 1 ou 2");
            		
            	}
            	String linha = Input.readLine();
            	
            	// Verifia se algum lutador ganhou ou perdeu a luta
            	if(Integer.parseInt(linha) == 0) {
            		System.err.println("GANHEIA A LUTA!!!!");
            		Output.close();
            		conectado = false;
            		break;
            	}else {
            		if(luta.getLutador().vida <= 0) {
                		Output.println("0");
                		System.err.println("FUI DERROTADO!!!!");
                		Output.close();
                		conectado = false;
                	}
            	}
            	
            	//int opcao = 1;
				// Faz a subtração das vidas do lutador, caso não defenda o ataque
            	if(Integer.parseInt(linha) == 1) {
            		System.out.println("Atacado na direita");
            		if(opcao != 1) {
            			luta.getLutador().vida -= 1;
            			System.err.println("Fui tingido -> vidas="+ luta.lutador.vida );
            		}
            	}else if(Integer.parseInt(linha) == 2){
            		System.err.println("Atacado na esquerda");
            		if(opcao != 2) {
            			luta.getLutador().vida -= 1;
            			System.err.println("Fui atingido -> vidas="+ luta.lutador.vida );
            		}
            	}
            	System.out.println(linha);
        	}
   
        	if(ataque) {
        		ataque = false;
        	}else {
        		ataque = true;
        	}
		} 
    }
}


