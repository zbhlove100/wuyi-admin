package wuyi.util;

import java.io.IOException;
import java.util.Properties;

import com.aliyun.oss.OSSClient;

public class OssClientUitl {
	private static OSSClient client;
	private static String accessKeyId = "";
	private static String accessKeySecret = "";
	
	
	public static synchronized OSSClient getClient() throws IOException{
		Properties prop = new Properties(); 
		if(client == null){
			prop.load(OSSClient.class.getClassLoader().getResourceAsStream("oss.properties"));
			accessKeyId = (String) prop.get("accessKeyId");
			accessKeySecret = (String) prop.get("accessKeySecret");
			
			client = new OSSClient(accessKeyId, accessKeySecret);
		}
		return client;
		
	}
}
