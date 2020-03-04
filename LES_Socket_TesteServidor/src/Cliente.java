import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private static final String HOSTNAME = "127.0.0.1";
	private static final int PORT = 6644;
	private Socket srv;
	
	public Cliente() {
		try {
			System.out.println("Tentando conectar o servidor");
			srv = new Socket(HOSTNAME,PORT);
			System.out.println("Conectando no servidor");
			InputStream in = srv.getInputStream();
			OutputStream out = srv.getOutputStream();
			char c;int i;
			do {
				i=System.in.read();
				out.write(i);
				out.flush();
				if(in.available()>0) {
					System.out.print(in.read());
				}
			}while(i!=27 && System.in.available()>=0);
			srv.close();
			System.out.println("Conexão Encerrada");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Cliente();

	}

}
