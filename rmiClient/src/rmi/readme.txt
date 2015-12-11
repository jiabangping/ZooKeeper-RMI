参考rmi+zookeeper http://www.open-open.com/lib/view/open1416097626352.html 实例
zokeeper3.4.5 windows下的应用：http://www.cnblogs.com/shanyou/p/3221990.html

1、发布RMI服务  抽象，对事物高度统一，概括
	分三个步骤：(1)定义一个RMI接口，(2)编写RMI接口的实现类，(3)通过JNDI发布RMI服务
1.1 定义一个RMI接口，RMI接口实际上是一个普通的Java接口，只是RMI接口必须继承java.rmi.Remote，此外，每个RMI接口的方法必须声明抛出一个java.rmi.RemoteException异常
	public interface HelloService extends Remote {
		String sayHello(String name) throws RemoteException;
	}
	继承了Remote接口，实际上是让JVM得知该接口是需要用于远程调用的，抛出了RemoteException是为了让调用RMI服务的程序捕获这个异常，毕竟远程调用过程中，什么奇怪的事情都会发生（比如：断网）。需要说明的是，RemoteException 是一个“受检异常”，在调用的时候必须使用 try...catch... 自行处理。
	
1.2 编写 RMI 接口的实现类
	实现以上的HelloService ，但是还必须让实现类继承 java.rmi.server.UnicastRemoteObject 类，此外，必须提供一个构造器，并且构造器 必须抛出 java.rmi.RemoteException 异常。
	public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
		protected HelloServiceImpl() throws RemoteException {}
	
		public String sayHello(String name) throws RemoteException {
			return String.format("Hello %s", name);
		}
	}

1.3 通过JNDI 发布RMI服务
	发布RMI服务，我们需要告诉JNDI三个基本的信息：1.域名或ip地址(host)、2.端口号(port)、3.服务名(service),它们构成了RMI协议的URL(也即RMI地址)
	rmi://<host>:<port>/<service>
	如果我们是在本地发布RMI服务，那么host就是localhost，默认port是1099，也可以自行设置port值，service实际是一个基于同一个host与port下唯一的服务名，可以使用java完全类名来表示，这样也
	比较容易保证RMI地址的唯一性。
	示例中 rmi地址为 rmi://localhost:1099/demo.zookeeper.remoting.service.HelloServiceImpl
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	