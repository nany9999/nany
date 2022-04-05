import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class BrokerServer2 implements Callable<String>{
    public static int SERVER_PORT = 10010;

    private final Socket socket;

    public BrokerServer2(Socket socket){
        this.socket = socket;
    }

   @Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
	   String str=null;
        try (BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            while (true){
                 str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("(Callable)接收到原始数据：" + str);
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
        return str;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server  = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer2 brokerServer2 = new BrokerServer2(server.accept()); 
           FutureTask<String> futureTask = new FutureTask<>(brokerServer2);
           new Thread(futureTask).start();
        }
    }


	
}
