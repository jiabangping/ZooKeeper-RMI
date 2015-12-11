package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import demo.zookeeper.remoting.server.HelloService;

public class RmiClient {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url = "rmi://192.168.64.190:1099/demo.zookeeper.remoting.server.impl.HelloServiceImpl";
		HelloService service = (HelloService) Naming.lookup(url);
		String result = service.sayHello("张三");
		System.out.println("result="+result);
	}
}
