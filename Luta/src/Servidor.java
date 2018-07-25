import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Servidor {

    public static void main (String[] args) throws IOException {

        ServerSocket Servidor = new ServerSocket(4242); // Definido a porta 4242 TCP.
        System.out.println("Servidor Iniciado...");
        Socket Cliente = Servidor.accept(); // Permite conexão.
        System.out.println("Conexão Estabelecida com: " + Cliente.getInetAddress().getHostAddress()); // Apresenta IP e Host.

        // Recebendo dados da maquina 2 via Socket.
        BufferedReader Input = new BufferedReader(new InputStreamReader(Cliente.getInputStream())); 
        // Enviando dados para maquina 1 via Socket.
        PrintStream Output = new PrintStream(Cliente.getOutputStream()); 

        // Instancia de teclado
        Scanner teclado = new Scanner(System.in); 
        
        System.err.print("Digite o nome do lutador: ");
        String nome = teclado.next();
        System.err.print("Digite o país do lutador: ");
        String pais = teclado.next();
        
        Luta luta = new Luta( new Lutador(nome, pais, " MMA - Servidor ") );
        
        boolean conectado = true; 
        boolean ataque = true; 
        
        while (conectado) {
     
        	if(ataque) {
        		System.out.println(" LUTA - digite um número para escolher o lado de ataque; ");
            	System.out.println(" 1 - Direita ");
            	System.out.println(" 2 - Esquerda ");
            //	System.out.println(" 3 - Infomacoes");
            	try{
            	int opcao = teclado.nextInt();
            	switch (opcao) {
    			case (1):
    				System.out.println("Atacou na direita ... ");
    				Output.println("1");
    				break;
    			case (2):
    				System.out.println("Atacou na esquerda ...");
    				Output.println("2");
    				break;
    			case (3):
    				System.out.println( luta.getLutador().toString() );
    			default:
    				break;}
    				
            	}catch(Exception erro){
            		System.err.println("pode digitar apenas opcao 1 ou 2");
            	}	
    			
        	}else {
        		System.out.println(" LUTA - digite um número para escolher o lado de DEFESA; ");
            	System.out.println(" 1 - Direita ");
            	System.out.println(" 2 - Esquerda ");
            //	System.out.println(" 3 - Infomacoes");
            	
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
            	
            	String linha = Input.readLine();
            	
            	if(luta.getLutador().vida <= 0) {
            		Output.println("0");
            		System.err.println("FUI DERROTADO!!!!");
            		Output.close();
            		conectado = false;
            	}
            	
            	if(Integer.parseInt(linha) == 0) {
            		System.err.println("GANHEIA A LUTA!!!!");
            		Output.close();
            		conectado = false;
            		break;
            	}
            	
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
        	}
        	
        	if(ataque) {
        		ataque = false;
        	}else {
        		ataque = true;
        	}
		}  
    }
}




