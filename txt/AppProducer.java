package txt;

public class AppProducer{
    public static void main(String[] args){
       Producer producer = new Producer();
       producer.init();
       producer.sendMessage("txt-shike");
    }
}
