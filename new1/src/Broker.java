import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class Broker {

    private final static int  MAX_SIZE = 5;
    private static final BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>(MAX_SIZE);

    public static void produce(String message){
        if(messageQueue.offer(message)){
            System.out.println("�ɹ�Ͷ����Ϣ��"+message+",��ǰ�ݴ���Ϣ������"+messageQueue.size());
        }else{
            System.out.println("Broker�Ѿ��治����");
        }
    }
    public static String consume(){
        String message = messageQueue.poll();
        if(message != null){
            System.out.println("��������Ϣ��" + message + ",��ǰ�ݴ���Ϣ������"+messageQueue.size());
        }else {
            System.out.println("Broker��û����Ϣ��������");
        }
        return message;
    }
}
