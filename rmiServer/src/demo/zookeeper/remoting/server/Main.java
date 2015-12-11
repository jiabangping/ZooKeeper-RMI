package demo.zookeeper.remoting.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import demo.zookeeper.remoting.server.impl.HelloServiceImpl;

public class Main {
	public static void main(String[] args) {
		try {
			int port = 1099;
			String url = "rmi://192.168.64.190:1099/demo.zookeeper.remoting.server.impl.HelloServiceImpl";
			LocateRegistry.createRegistry(port);
			Naming.rebind(url, new HelloServiceImpl());
			System.out.println("∞lÅ—≥…π¶");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
