package com.push;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
public class WeslidePushBroadcastMessage {
    final static String apiKey = "7pmCCPw8R5clLBjdrQ44ICzS";
    final static String secretKey = "frUlxnotCP4pYY5PA0GkCdb5Spjv7FQV";
    private BaiduChannelClient channelClient = null;

    public static void main(String[] args) {
    	WeslidePushBroadcastMessage wBroadcastMessage = new WeslidePushBroadcastMessage();
    	//wBroadcastMessage.pushMessageToClient("[[{\"machinecode\":\"0000005\",\"category\":\"2\",\"operation\":\"0\",\"url\":\"qr/77_9_1413542073284.jpg\",\"url_big\":\"qrbig/77_9_1413542073284.jpg\"},{\"machinecode\":\"0000005\",\"category\":\"2\",\"operation\":\"0\",\"url\":\"qr/77_9_1413542073284.jpg\",\"url_big\":\"qrbig/77_9_1413542073284.jpg\"}]]");
    	wBroadcastMessage.pushMessageToClient("[[{\"taskcode\":\"0\",\"channelid\":\"4419605193163182730\",\"userid\":\"786611469875272966\",\"user_id\":\"33\"}]]", "4419605193163182730", "786611469875272966");
    }
    
    public WeslidePushBroadcastMessage() {
    	 ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
    	 channelClient = new BaiduChannelClient(pair);
    	 channelClient.setChannelLogHandler(new YunLogHandler() {
             public void onHandle(YunLogEvent event) {
                 System.out.println(event.getMessage());
             }
         });
	}
    
    /**
     * @param message 鐟曚礁褰傞柅涔痮sn濞戝牊浼�     */
    public  void pushMessageToClient(String message){
    	try {
    		PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(3); 

            request.setMessage(message);
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);

            System.out.println("push amount : " + response.getSuccessAmount());

        } catch (ChannelClientException e) {
          
        } catch (ChannelServerException e) {
             
        	System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
    }
    
    
    /**
     * @param message 鐟曚礁褰傞柅涔痮sn濞戝牊浼�     */
    public  void pushMessageToClient(String message,String channelid ,String userid){
    	try {

    		PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3); 
            request.setChannelId(Long.parseLong(channelid));
            request.setUserId(userid);
            request.setMessage(message);
            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
                   

            System.out.println("push amount : " + response.getSuccessAmount());

        } catch (ChannelClientException e) {
          
        } catch (ChannelServerException e) {
             
        	System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
    }


}
