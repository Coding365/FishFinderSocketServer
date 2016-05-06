package socketutil;

import java.io.DataInputStream;  

import java.io.IOException;  
  
import java.io.InputStream;  
  
import java.io.OutputStream;  
  
import java.io.PrintStream;  
  
import java.net.InetAddress;  
  
import java.net.ServerSocket;  
  
import java.net.Socket;

public class ClientServer {

	 public static void main(String[] args) throws IOException {
		 ServerSocket serverSocket= new ServerSocket(8091);
		 Socket socket=serverSocket.accept();
		 while(true){//保持长连接
		    	try {
		        		Thread.sleep(2000);//等待时间
		    	} catch (InterruptedException e1) {
		        		e1.printStackTrace();
		    	}
		 	if (socket !=null){
		    		try {
		       			String ip = socket.getInetAddress().toString().replace("/", "");
		    	      		System.out.println("====socket.getInetAddress()====="+ip);
		           		socket.setKeepAlive(true);
		           		InputStream is = socket.getInputStream();
		           		OutputStream os = socket.getOutputStream();
		           		System.out.println("服务器端接受请求");
		           		DataInputStream in=new DataInputStream(is);  
		           		String str=in.readLine();
		 			System.out.println("接收到的数据为："+str);
		 			if(in.equals("stop")){
		 				is.close();
		 				os.close();
		 			}
		 			os.flush();
		 		}catch(Exception e){
		 			System.out.println("出现了错误");
		 		}
		 	}
		 }
	 } 
}
