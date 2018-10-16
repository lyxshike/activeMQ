package javaBean;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer{
	// ActiveMQ 的默认用户名
     private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
  // ActiveMQ 的默认登录密码
     private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
  // ActiveMQ 的链接地址
     private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
  // 链接工厂
     ConnectionFactory connectionFactory;
     // 链接对象
     Connection connection;
     // 事务管理
     Session session;
     
     public void init(){
        try{
            // 创建一个链接工厂
           connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
           // 从工厂中创建一个链接
           connection = connectionFactory.createConnection();
           //  开启链接
           connection.start();
           //  创建一个事务，这里通过参数可以设置事务的级别
           session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        } catch (JMSException e){
           e.printStackTrace();
        }
     }
     
     public void sendMessage(String disname){
        try{  
            Queue queue = session.createQueue(disname);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            for(int i=0; i<10; i++){
                Student stu = new Student(i+"");
                ObjectMessage msg = session.createObjectMessage(stu);
                System.out.println(stu.getName());
                messageProducer.send(msg);
            }
         // session.commit();  当为Session.AUTO_ACKNOWLEDGE的时候， 需要代码提交
            connection.close();
        } catch (JMSException e){
            e.printStackTrace();
        }    
     }
}
