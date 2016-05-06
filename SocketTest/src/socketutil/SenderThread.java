package socketutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderThread extends Thread {

	PrintWriter pw = null;
	Socket socket = null;
//	static ServerSocket s = null;
	@Override
	public void run() {
		super.run();
		System.out.println("SenderThread.run");
		try {
			socket = ServerSocketSingleton.getSocket();
			if(socket!=null){
				while(true){
					System.out.println("..........");
					Thread.sleep(2000);
					pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);					
					pw.println("T38.0");
					pw.flush();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			try {
				if(pw!=null)
				pw.close();
				if(socket!=null)
				socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		
		
	}
}
