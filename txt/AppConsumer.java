package txt;

public class AppConsumer{
   public static void main(String[] args){
	   System.out.println(AppConsumer.class.getResource(""));
      Consumer consumer = new Consumer();
      consumer.init();
      consumer.getMessage("txt-shike");
      
   }
}
