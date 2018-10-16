public class AppProducer{
    public static void main(String[] args){
       Producer producer = new Producer();
       producer.init();
       producer.sendMessage("javabean-shike");
    }
}
