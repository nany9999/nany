import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection{
    private final String DBDriver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String DBURL ="jdbc:sqlserver://localhost:1433;DatabaseName=MiddleSoftWare";
    private final String DBUSER ="sa";
    private final String DBPASSWORD ="195638";
   
    private Connection conn = null;
//������
    public DataBaseConnection(){
       try{
           Class.forName(DBDriver);
           conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
           }
        catch(Exception e){  }
                                 }

//�������
public Connection getConnection(){
return this.conn; }

//�ر�����
public void close() throws SQLException{
    this.conn.close(); }
} 
