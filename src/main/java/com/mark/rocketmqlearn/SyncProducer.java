package com.mark.rocketmqlearn;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * SyncProducer
 * @author shenjy
 */
public class SyncProducer {
  private static final String SERVER_ADDR = "127.0.0.1:9876";

  public static void main(String[] args) throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("please_rename_to_unique_group_name");
    producer.setNamesrvAddr(SERVER_ADDR);

    producer.start();
    for (int i = 0; i < 100; i++) {
      /**
       * param 1: topic param 2: tag param 3: message body
       */
      Message msg = new Message("TopicTest", "TagA",
          ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      SendResult sendResult = producer.send(msg);
      System.out.printf("%s%n", sendResult);
    }
    producer.shutdown();
  }
}
