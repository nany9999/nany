public class BankCard {//同一个BankCard,id和passwd是对应的
	private String passwd;//私有，在别的类中不能通过 如userCard[i].passwd的形式访问
	private String     id;//比int好
    //支付密码？？？？？？？？
	
	private double    money;
	
	public BankCard(String passwd,String id) {//初始化
		this.passwd=passwd;//即当前对象的属性 用 局部变量初始化
		this.id=id;	
	}
	
	public String getId() {
		return id;
	}

	
	public double getMoney() {
		return money;
	}
	
	public void SetMoney(double  tmp) {
		money+=tmp;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String tmp) {
		 passwd=tmp;
	}
	
}