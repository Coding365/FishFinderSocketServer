package socketutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;


public class ReceiverThread extends Thread {
	
	
	
	
	public static void main(String[] agrs) {
		ServerSocket s = ServerSocketSingleton.getServerSocket();
		if(s!=null){
			ReceiverThread thread = new ReceiverThread();
			thread.start();
//			SenderThread sThread = new SenderThread();
//			sThread.start();
		}
		
	}
	
	private String getPrintString(String str){
		String printStr = null;
		
		if("N10".equals(str)){
			printStr = "关闭夜间模式";
		}else if("N11".equals(str)){
			printStr = "开启夜间模式";
		}else if("F11".equals(str)){
			printStr = "探测频率每秒1次";
		}else if("F12".equals(str)){
			printStr = "探测频率每秒2次";
		}else if("F13".equals(str)){
			printStr = "探测频率每秒3次";
		}else if("F14".equals(str)){
			printStr = "探测频率每秒4次";
		}else if("F15".equals(str)){
			printStr = "探测频率每秒5次";
		}else if("B10".equals(str)){
			printStr = "LED关闭";
		}else if("B11".equals(str)){
			printStr = "LED开启红灯";
		}else if("B12".equals(str)){
			printStr = "LED开启红灯与蓝灯";
		}else if("B13".equals(str)){
			printStr = "LED开启红蓝绿灯";
		}
		
		try {
			printStr = URLEncoder.encode(printStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return printStr;
	}
	
//	public static int PORT = 8091;
	public void run(){
		ServerSocket s = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		super.run();
		try {
			//设定服务端的端口号
//			s = new ServerSocket(PORT);
			s = ServerSocketSingleton.getServerSocket();
			System.out.println("ServerSocket Start:"+s);
			//等待请求,此方法会一直阻塞,直到获得请求才往下走
			
			socket = s.accept();
			System.out.println("Connection accept socket:"+socket);
			
			while(true){
				//用于接收客户端发来的请求
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//用于发送返回信息,可以不需要装饰这么多io流使用缓冲流时发送数据要注意调用.flush()方法
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				String str = br.readLine();
				System.out.println("Client Socket Message:"+str);
				if(str.equals("END")){
					break;
				}else if("T".equals(str)){
					pw.println("T38.0");
					pw.flush();
				}else if("D".equals(str)){
					pw.println("D43.65");
					pw.flush();
					
				}else{
					String printStr = this.getPrintString(str);
					if(printStr!=null){
						pw.println(printStr);
						pw.flush();
						continue;
					}
				}
				
//				pw.flush();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("Close.....");
			try {
				br.close();
				pw.close();
				socket.close();
				s.close();
			} catch (Exception e2) {
				
			}
		}
	}
}
