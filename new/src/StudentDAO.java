import java.util.List;

public interface StudentDAO{
    public void insert(Student s);
    public void update(Student s);
    public void delete(long iD);
    public Student findByID(long iD);
    public List<Student> findAll();
} 
