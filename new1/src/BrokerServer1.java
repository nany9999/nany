import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer1 extends Thread{//继承thread
    public static int SERVER_PORT = 10010;

    private final Socket socket;

    public BrokerServer1(Socket socket){
        this.socket = socket;
    }

   
    public void run() {
        try (BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            while (true){
                String str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("(Thread)接收到原始数据：" + str);
                if (str.equals("consume")) { 
                    String message = Broker.consume();
                    out.println (message);
                    out.flush();
                } else { 
                    Broker.produce(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server  = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer1 brokerServer1 = new BrokerServer1(server.accept());
            new Thread(brokerServer1).start();
        }
    }
}
