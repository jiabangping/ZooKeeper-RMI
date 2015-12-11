package demo.zookeeper.remoting.server.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import demo.zookeeper.remoting.server.HelloService;
//signtlon ÜŒåç¿˝
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
	int count;
	public HelloServiceImpl() throws RemoteException {
	}

	@Override
	public String sayHello(String name)  throws RemoteException {
		System.out.println("count="+count);
		count++;
		return String.format("Hello %s", name);
	}
}
