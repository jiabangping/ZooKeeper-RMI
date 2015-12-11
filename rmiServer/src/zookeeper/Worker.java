package zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String workName;
	private int workTime;
	private CountDownLatch latch;
	private int count;
	public Worker(String workName,int workTime,CountDownLatch latch) {
		this.workName = workName;
		this.workTime = workTime;
		this.latch = latch;
	}
	@Override
	public void run() {
		System.out.println("Worker "+workName +" do work begin at "+sdf.format(new Date()));
		doWork();//นคื๗มห
		
		while(true) {
			System.out.println("workName="+workName+","+count);
			count++;
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count >6) {
				break;
			}
		}
		System.out.println("Worker "+workName +" do work complete at "+sdf.format(new Date()));
		latch.countDown();
		
	}
	
	private void doWork() {
		try {
//			Thread.sleep(workTime);
			for(long i =0 ;i<900000000;i++) {
				//System.out.println("workName="+workName+",i="+i);
			}
			Thread.sleep(workTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
