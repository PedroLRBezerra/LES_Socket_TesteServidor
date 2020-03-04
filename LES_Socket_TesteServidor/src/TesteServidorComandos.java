import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TesteServidorComandos {
	private final static int PORTA = 6644;
	private ServerSocket server;
	private Socket cliente;
	public TesteServidorComandos() {
		try {
			System.out.println("Iniciando Servidor ...");
			server = new ServerSocket(PORTA);
			System.out.println("Servido Ativo , Aguardando ...");
			
			cliente = server.accept();
			System.out.println("Cliente conectado");
			
			OutputStream out = cliente.getOutputStream();
			out.write("Digite Um Comando\n\r".getBytes());
			out.flush();
			
			InputStream in = cliente.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			String  l;
			do {
				l=br.readLine();
				try {
					Runtime.getRuntime().exec(l);
				} catch (Exception e) {
					out.write("Comando Invalido\n\r".getBytes());
					out.flush();					
				}
			}while(l!="sair");
			cliente.close();
			System.out.println("Conexão Encerrada");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new TesteServidorComandos();
	}

}
