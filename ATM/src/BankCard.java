public class BankCard {//ͬһ��BankCard,id��passwd�Ƕ�Ӧ��
	private String passwd;//˽�У��ڱ�����в���ͨ�� ��userCard[i].passwd����ʽ����
	private String     id;//��int��
    //֧�����룿��������������
	
	private double    money;
	
	public BankCard(String passwd,String id) {//��ʼ��
		this.passwd=passwd;//����ǰ��������� �� �ֲ�������ʼ��
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