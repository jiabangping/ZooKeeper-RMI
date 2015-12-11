package zookeeper;

public class Constant {

/*	public static String ZK_CONNECTION_STRING = "192.168.66.11:2181";
	public static int ZK_SESSION_TIMEOUT = 5000;
	public static  String ZK_REGISTRY_PATH = "/registry";
	public static String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
*/
	static {
		ZK_CONNECTION_STRING = PropertiesConfig.getPropertie("ZK_CONNECTION_STRING");
		ZK_SESSION_TIMEOUT = Integer.parseInt(PropertiesConfig.getPropertie("ZK_SESSION_TIMEOUT"));
		ZK_REGISTRY_PATH = PropertiesConfig.getPropertie("ZK_REGISTRY_PATH");
		ZK_PROVIDER_PATH = PropertiesConfig.getPropertie("ZK_PROVIDER_PATH");
		
		RMI_HOST = PropertiesConfig.getPropertie("RMI_HOST");
		RMI_PORT = Integer.parseInt(PropertiesConfig.getPropertie("RMI_PORT"));
	}
	public static String ZK_CONNECTION_STRING;//
	public static int ZK_SESSION_TIMEOUT;//超时时间
	public static  String ZK_REGISTRY_PATH;
	public static String ZK_PROVIDER_PATH;//
	
	public static String RMI_HOST;//需要发布RMI的 host
	public static int RMI_PORT;//需要发布RMI的port
}
