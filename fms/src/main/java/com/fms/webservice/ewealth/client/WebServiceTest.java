package com.fms.webservice.ewealth.client;

import java.net.URL;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WebServiceTest {

	public static void main(String[] args) {
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL("https://www2.union400.com/admin/main"));
			call.setOperationName("4006773977");
			Object[] param = {"15031503"};
			String sendResult = (String)call.invoke(param);
			System.out.println(sendResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
