package zookeeper;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);
	//��춵ȴ� SyncConnected �¼����������ִ�� ��ǰ�߳�
	private  CountDownLatch latch = new CountDownLatch(1);
	
	public void publish(Remote remote ,String host,int port) {
		String url = publishService(remote,host,port);//����RMI���񲢷��� RMI ��ַ
		if(url != null) {
			ZooKeeper zk = connectServer();//���� ZooKeeper ����������ȡZookeeper ����
			if(zk != null) {
				createNode(zk,url);//���� ZNode ���� RMI ��ַ���� ZNode��
			}
		}
	}
	
	//���� RMI����
	private String publishService(Remote remote,String host,int port) {
		String url = null;
		try {
			url = String.format("rmi://%s:%d/%s", host,port,remote.getClass().getName());
			LocateRegistry.createRegistry(port);
			Naming.rebind(url, remote);
			LOGGER.debug("publish rmi service url:"+url);
		} catch (Exception e) {
			LOGGER.error("",e);
		}	
		return url;
	}
	
	
	//���� Zookeeper ������
	private ZooKeeper connectServer() {
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
				
				@Override
				public void process(WatchedEvent event) {
					System.out.println("ZooKeeper WatchEvent: process()������"+event);
					if(event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown();//���ѵ�ǰ����ִ�е��߳�
					}
				}
			});
			latch.await();//ʹ��ǰ�̴߳��ڵȴ�״̬
		} catch (Exception e) {
			 LOGGER.error("", e);
		}
		return zk;
	}
	
	
	//���� Znode
	private void createNode(ZooKeeper zk,String url) {
		try {
			byte[] data = url.getBytes();
			String path = zk.create(Constant.ZK_PROVIDER_PATH,data,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);//// ����һ����ʱ��������� ZNode
			 LOGGER.debug("create zookeeper node ({} => {})", path, url);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}
	
	
}
