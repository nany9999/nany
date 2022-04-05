
public class testDAO {
	public static void main(String[] args) {
		
	StudentDAO sd=new StudentDAOIMP();
	Student s0=new Student();
	s0.setID(1910300315);
	s0.setName("nany");
	//sd.insert(s0);;
	//sd.findByID(1910300315);
	System.out.println(sd.findByID(1910300315).getID());
	}
}
