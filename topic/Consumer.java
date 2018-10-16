package topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer{
     private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
     private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
     private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
     
     ConnectionFactory connectionFactory;
     Connection connection;
     Session session;
     
     public void init(){
         try{
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
         } catch (JMSException e){
            e.printStackTrace();
         }
     }
     
     public void getMessage(String disname){
          try{
            Topic topic = session.createTopic(disname);
            MessageConsumer consumer = session.createConsumer(topic);
            // 创建一个监听器
            consumer.setMessageListener(new MessageListener(){
               public void onMessage(Message message){
                    TextMessage textMesage = (TextMessage) message;
                    try{
                       System.out.println("receive message"+textMesage.getText());
                       // 因为是Session.CLIENT_ACKNOWLEDGE, 所以不写此句， 即使consumer有收到消息，但是此条消息在MQ中依然停留。。
                       message.acknowledge();
                    } catch (JMSException e){
                       e.printStackTrace(); 
                    }
               }
            });    
          } catch (JMSException e){
             e.printStackTrace();
          }
          try{
              // 200秒后， 让监听器自动关闭。  不然， consumer会一直运行在后台， activemq的网页上，number of consumers 诡异的增加
              Thread.sleep(200000);
              connection.close();
          } catch (JMSException e){
              e.printStackTrace();
          } catch (InterruptedException e){
              e.printStackTrace();
          }
     }
}
