package gateway;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class InvokeMethod {

	
	private HttpClient getHttpClient(){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		return httpclient;
	}
	
	public HttpResponse getBySite(String url,Map<String, String> params) throws ClientProtocolException, IOException{
		HttpClient httpclient=getHttpClient();
		String targetURL=getTargetURL(url, params);
		HttpGet httpGet = new HttpGet(targetURL);
		HttpResponse response = httpclient.execute(httpGet);
		return response;
	}
	
	private String getTargetURL(String url,Map<String, String> params){
		String targetURL=url;
		Set<String> keyParam=params.keySet();
		if(!keyParam.isEmpty()){
			targetURL=url+"?";
		}
		Iterator<String> it=keyParam.iterator();
		int length=keyParam.size();
		for(int i=0;i<length;i++){
			String key=it.next();
			targetURL=targetURL+key+"="+params.get(key);
			if(i<length-1){
				targetURL=targetURL+"&";
			}
		}
		return targetURL;
	}
}
