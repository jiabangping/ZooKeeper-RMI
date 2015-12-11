package rmi;

import demo.zookeeper.remoting.server.HelloService;

public class Client {
	public static void main(String[] args) throws Exception {
        ServiceConsumer consumer = new ServiceConsumer();
 
        while (true) {
            HelloService helloService = consumer.lookup();
            String result = helloService.sayHello("Jack");
            System.out.println(result);
            Thread.sleep(3000);
        }
	/*	long beginTime = System.currentTimeMillis();
		for(int i=0;i<1000000l;i++){//useTime=2222
			System.out.println("i="+i);
		}
		long endTime =  System.currentTimeMillis();
		System.out.println("useTime="+ (endTime-beginTime));*/
    }
}
