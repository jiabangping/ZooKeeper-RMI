package zookeeper;

import java.rmi.RemoteException;

import demo.zookeeper.remoting.server.HelloService;
import demo.zookeeper.remoting.server.impl.HelloServiceImpl;

public class Server {
	public static void main(String[] args) throws RemoteException, InterruptedException {
		/*if(args.length != 2) {
			System.err.println("please using command : java Server <rmi_host> <rmi_port>");
			System.exit(-1);
		}*/
		
//		String host = args[0];
		String host = Constant.RMI_HOST;
//		int port = Integer.parseInt(args[1]);
		int port = Constant.RMI_PORT;
		ServiceProvider provider = new ServiceProvider();
		HelloService helloService = new HelloServiceImpl();
		
		provider.publish(helloService, host, port);
		
		Thread.sleep(Long.MAX_VALUE);
	}
}
