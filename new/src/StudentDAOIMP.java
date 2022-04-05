import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	      String sql = "INSERT INTO student (id, name) values (?,?)";
	    PreparedStatement pstmt = null;
	    DataBaseConnection conn = null;
	    //针对数据库的具体操作
	    try{
	        conn = new DataBaseConnection();
	        
	        pstmt = conn.getConnection().prepareStatement(sql);
	        pstmt.setLong(1,s.getID());
	        //pstmt.setString(1,s.getID());
	        pstmt.setString(2,s.getName());
	  
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        }
	     catch(Exception e){  }
	      }

	    public void update(Student s){  
	    	   String sql = "update Student set id=?,name=? where id=?";//
	   	    PreparedStatement pstmt = null;
	   	    DataBaseConnection conn = null;
	   	   
	   	    try{
	   	        conn = new DataBaseConnection();
	   	         pstmt = conn.getConnection().prepareStatement(sql);
	   	        
	   	        pstmt.setLong(1,s.getID());     //
	   	        pstmt.setString(2,s.getName()); //	   	  
	   	        pstmt.setLong(3,s.getID());
	   	        
	   	        pstmt.executeUpdate();
	   	        pstmt.close();
	   	        conn.close();
	   	        }
	   	     catch(Exception e){  }
	   	      }
	    

           public void delete(long iD){ 
        	  
        	   String sql = "delete from Student where id=?";//
   	   	    
        	   PreparedStatement pstmt = null;
   	   	    DataBaseConnection conn = null;
   	   	  
   	   	    try{
   	   	        conn = new DataBaseConnection();
   	   	        pstmt = conn.getConnection().prepareStatement(sql);
   	   	       
   	   	        pstmt.setLong(1,iD);         //
   	   	        
   	   	        pstmt.executeUpdate();
   	   	        pstmt.close();
   	   	        conn.close();
   	   	        }
   	   	     catch(Exception e){  }
   	   	      }   
           
	    
	    public List findAll(){  
	    	ArrayList<Student> stulist=new ArrayList<Student>();
	    	Student temp=new Student();
	    	 String sql = "select from Student";//
		   	    PreparedStatement pstmt = null;
		   	    DataBaseConnection conn = null;
		   	    ResultSet rs=null;
		   	    
		   	    try{ 
		   	    	conn = new DataBaseConnection();
		   	    	pstmt = conn.getConnection().prepareStatement(sql);
		   	        conn = new DataBaseConnection();
		   	        rs=pstmt.executeQuery();
		   	       
		   	        while(rs!=null&&rs.next()) {
		   	        	temp.setID(rs.getInt("id"));
		   	        	temp.setName(rs.getString("name"));
		   	        	stulist.add(temp);
		   	        }
		 
		   	        
		   	        pstmt.executeUpdate();
		   	        pstmt.close();
		   	        conn.close();
		   	        }
		   	     catch(Exception e){  }
	    	
	    	
	    	return stulist;
	    }   
	          
	   

                    public Student findByID(long iD){  
                    	Student temp=new Student();
                    	String sql = "select from Student where id=?";//
        		   	    PreparedStatement pstmt = null;
        		   	    DataBaseConnection conn = null;
        		   	    ResultSet rs=null;
        		   	    
        		   	    try{ pstmt.setLong(1,iD);   
        		   	    	conn = new DataBaseConnection();
        		   	    	pstmt = conn.getConnection().prepareStatement(sql);
        		   	        conn = new DataBaseConnection();
        		   	        rs=pstmt.executeQuery();
        		   	       
        		   	       
        		   	        	temp.setID(rs.getInt("id"));
        		   	        	temp.setName(rs.getString("name"));
        		   	        	
        		   	      
        		 
        		   	        
        		   	        pstmt.executeUpdate();
        		   	        pstmt.close();
        		   	        conn.close();
        		   	        }
        		   	     catch(Exception e){  }
                    	return temp;
                  }
                    
}
