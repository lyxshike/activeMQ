public class AppConsumer{
   public static void main(String[] args){
      Consumer consumer = new Consumer();
      consumer.init();
      consumer.getMessage("queue-shike");
   }
}
