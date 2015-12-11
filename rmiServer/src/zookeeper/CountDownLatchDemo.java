package zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
	static boolean flag = true;
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);//2个人的协作
		final Worker worker1 = new Worker("zhang san",5000,latch);
		final Worker worker2 = new Worker("li si",8000,latch);
		
		worker1.start();
		worker2.start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(CountDownLatchDemo.flag) {
					System.out.println("worker1.status="+worker1.getState().name());
					System.out.println("worker2.status="+worker2.getState().name());
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} 
		}).start();;
		latch.await();//等待所有工人完成工作
		System.out.println("all worker done at "+sdf.format(new Date()));
		flag =false;
	}
}



