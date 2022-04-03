import java.util.Arrays;
import java.util.Scanner;
//����Ϣ
public class Bank {//ÿnewһ��Bank�࣬����һ��Bank��ĳ�Ա����
	private String bankName;
	private BankCard[] userCards;//�����͵�һά���� ��ͨ�������ÿ���Ϣ
	private int size=0;//��Ч���п��ĸ���  ȫ�ֱ���
	private int index=-1;//������п��±ꡣ���ÿ�α�����
	
	//pubilc ����ATM������ø÷���
	public Bank(String bankName) {//��ʼ��
		this.bankName=bankName;
		userCards=new BankCard[10];//�ȸ�10�������ݲο�MyArrayList
	}
	
	//β��
	public void add(String id,String passwd ) {
		if(size == userCards.length) {
			userCards=Arrays.copyOf(userCards,userCards.length*2);
		}
		userCards[size++]=new BankCard(passwd,id);
	}
	
//	//Ҳ���԰�find�����Ľ�Ϊreturn BankCard���оͲ�Ϊ��
//	public BankCard search(String id) {
//		for(int i=0;i<size;i++) {
//			if(id.equals(userCards[i].getId()))
//					return userCards[i];
//		}
//		return null;	
//	}
	
	//���û�,�о�true
	public boolean find(String id) {
		boolean flag=false;//Ĭ��û��
		for(int i=0;i<size;i++) {
			if(id.equals(userCards[i].getId()))
					flag= true;
			break;
		}
		return flag;	
	}
	
	//�����п��±�
	public int findIndex(String id) {
		for(int i=0;i<size;i++) {
			if(id.equals(userCards[i].getId()))
					index=i;
			break;
		}
		return index;	
	}
		
	
	public String getBankName() {
		return bankName;
	}
	
	public String getPassWd(String id) {//��дW�������еķ���
		String Passwd=null;
		for(int i=0;i<size;i++) {//�ҵ�id��Ӧ���±�
			if(id.equals(userCards[i].getId())){
				Passwd=userCards[i].getPasswd();//Сдw,�����п��ķ���
				break;
			}
		}
		return Passwd;
	}

	//ȡǮ
	public void withdraw(int index) {
		double count=enter_Count();
		userCards[index].SetMoney(-count);//����
		System.out.println("��ȡ�������ֽ�");
	}
	
	//��Ǯ
	public void deposit(int index) {
		System.out.println("�뽫��Ʊ�������룺");
		Scanner scan = new Scanner(System.in);
		double  tmp = scan.nextInt();//ֻ����Ǯ
		userCards[index].SetMoney(tmp);
		System.out.println("���ڵ㳮�����Ժ󡣡���\n���ɹ�");
	}

	//ת��
	public void transfer(int index) {   
		
		int select =0;
		//�Ϸ�id �� ���
		String account=enter_Account(index);
		double count=enter_Count();
		
		System.out.println("ȷ�ϸ�"+account+"ת��"+count+"Ԫ��\n���룺1��ȷ��   2���˳�");
		if(select==1) {
			userCards[index].SetMoney(-count);
			userCards[findIndex(account)].SetMoney(count);
		}	
	}

	//�����
	public void inquire(int index) {//��ʷ���׼�¼����������������������
		double money=userCards[index].getMoney();
		System.out.println("���Ϊ��"+money+"Ԫ");
	}
	

	//�жϽ��ͷ���true
	public  boolean  analyze(double tmp) {
		boolean flag=true;
		if(tmp>userCards[index].getMoney())
			flag=false;	
		return flag;
	}
	
	//������ȷ���
	public  double enter_Count() {
		Scanner scan  = new Scanner(System.in);
		int select=0;
		double tmp=0;
		do {
			  System.out.println("��������ȷ��");
			  tmp = scan.nextInt();//ֻ����Ǯ
		}while(!analyze(tmp));//��������
		
		System.out.println("���ٴ�ȷ�Ͻ����������1��ȷ��  2���޸�");
		select = scan.nextInt();
		if(select==2) {
			do {
				  System.out.println("��������ȷ��");
				  tmp = scan.nextInt();
			}while(!analyze(tmp));
		}
			   
		return tmp;
	}

	// ������ȷ�˻�
	public String enter_Account(int index) {
		Scanner scan = new Scanner(System.in);
		int select = 0;
		String tmp = null;
		
		do {
			System.out.println("��������ȷ�˻�(id)��");
			tmp = scan.next();
		} while (!find(tmp) && !tmp.equals(userCards[index].getId()));//���û� ��  ������ �Լ�

		System.out.println("���ٴ�ȷ���˻�(id)��"+tmp+"������1��ȷ��  2���޸�");
		select = scan.nextInt();
		if (select == 2) {
			do {
				System.out.println("��������ȷ�˻�(id)��");
				tmp = scan.next();
			} while (!find(tmp));
		}

		return tmp;
	}

	//�޸�����
	public void setPasswd(int index) {
		
		Scanner scan = new Scanner(System.in);
		int select = 0;
		String tmp1 = null;
		String tmp2 = null;
		
		do {
			System.out.println("�����������룺");
			tmp1 = scan.next();
		} while (!tmp1.equals(userCards[index].getPasswd()));//������������

		
		System.out.println("���ٴ�������������ȷ�ϣ�");
		tmp2 = scan.next();
		while (!tmp1.equals(tmp2)) {
			System.out.println("�������벻һ�£����������룺");
			tmp2 = scan.next();
		}
		
		userCards[index].setPasswd(tmp1);
	}
	
}
