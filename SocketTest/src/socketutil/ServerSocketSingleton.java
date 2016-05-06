package socketutil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketSingleton {
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	public static int PORT = 8091;
	public static  ServerSocket getServerSocket(){
		try {
			if(serverSocket == null){
				serverSocket = new ServerSocket(PORT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return serverSocket;
	}
	
	public static Socket getSocket(){
//		
//		Socket sock = null;
//		try {
//			sock = ServerSocketSingleton.getServerSocket().accept();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return sock;
		if(socket ==null){
			try {
				ServerSocket _socketSocket = ServerSocketSingleton.getServerSocket();
				socket = _socketSocket.accept();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return socket;
	}
}
