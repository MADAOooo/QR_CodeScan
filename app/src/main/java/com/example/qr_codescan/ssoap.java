package com.example.qr_codescan;



import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.os.Build;
import android.os.StrictMode;
 
  
public class ssoap
{  

	private ArrayList<String> Str = new ArrayList<String>();

    public ArrayList<String> GetWebServer(String MethodName,String kahao) 
    {             
               try    
               {    
            	   //如果本系统为4.0以上（Build.VERSION_CODES.ICE_CREAM_SANDWICH为android4.0）
            	            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            	                // 详见StrictMode文档
            	                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            	                        .detectDiskReads().detectDiskWrites().detectNetwork()
            	                        .penaltyLog().build());
            	                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
            	                        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
            	                        .penaltyLog().penaltyDeath().build());
            	           }
            	    
            	   
            	   String serviceUrl = "http://100.100.100.33:803/JHService.asmx";
            	   //String serviceUrl = "http://10.11.7.30:802/JHService.asmx";
            	   String SERVICE_NAMESPACE="http://tempuri.org/";    
            	   String SOAP_ACTION = SERVICE_NAMESPACE + MethodName;          
                   
                   // 创建HttpTransportSE对象
                                HttpTransportSE ht = new HttpTransportSE(serviceUrl);
                           
                                ht.debug = true;
                                // 使用soap协议创建Envelop对象
                                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER10);
                                // 实例化SoapObject对象
                                SoapObject object = new SoapObject(SERVICE_NAMESPACE, MethodName);
                                // 将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
                                object.addProperty("kahao",kahao);                            
                               envelope.bodyOut = object;
                               envelope.dotNet = true;
                                envelope.setOutputSoapObject(object);
                                   // 调用webService
                                    ht.call(SOAP_ACTION, envelope);
                                    System.out.println("envelope.getResponse()---"
                                           + envelope.getResponse());
                                    if (envelope.getResponse()!= null) {
                                
                                        SoapObject result = (SoapObject) envelope.bodyIn;
                                      //取值
                                       SoapObject message = (SoapObject)result.getProperty(0);
                                       for(int i=0; i <200; i++)
                                       {
                                    	   if(message.getPropertyAsString(i).equals("anyType{}"))                                 
                                           {
                                    		   Str.add("");
                                           }
                                           else
                                           {
                                    	       Str.add(message.getPropertyAsString(i));
                                           }
                                       }
                                       //Str.add(message.getPropertyAsString(1));
                                      // Str.add(message.getPropertyAsString(2));
                                       //Str.add(message.getPropertyAsString(3));
                                       //Str.add(message.getPropertyAsString(4));
                                       //Str.add(message.getPropertyAsString(5));
                                       //Str.add(message.getPropertyAsString(6));
                                       //Str.add(message.getPropertyAsString(7));
                                       //Str.add(message.getPropertyAsString(8));
                                     
                                     
                                     //Str[0] = message.getPropertyAsString(0);
                                    // Str[1] = message.getPropertyAsString(1);
                                     System.out.println(Str);
                                     
                                    } else {
                                    	System.out.println("无返回值");
                                    }
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                   e.printStackTrace();
                                   System.out.println("无");
                                }
			return Str; 
                          
    }
    
}  
