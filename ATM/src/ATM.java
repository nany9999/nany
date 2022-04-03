import java.util.Scanner;
//һ��ATM����Ӧһ������
public class ATM {
	
	private Bank bank;//��ͨ��������bank��ķ���
	private int choice=0;
	private String id;
	private String passwd;
	private boolean flag=true;//�ػ���־

	public ATM() {// ��ʼ��
		this.bank = new Bank("�й�����");//new ATMʱͨ��ATM()��bank��ʼ����bank��ʼ�����bankCardҲ��ʼ����
	}
	
	//���ķ���
	public void start() {// ����

		System.out.println("��ӭʹ���й�����ATM��");
		
		while(flag) {
	    	System.out.println(" 1.ע��   2.��¼  3.�ػ�");// ����
		    //�ظ����ֿɸ���
			input(1);
			judge(choice);			
	    }
		
		System.out.println("���ڹػ�...");
	}

	//˽�з��� ��������start()�������Լ����� 
	//�����ж�
	private void judge(int choice) {
		switch (choice) {
		case 1:
			input(2);
			register();
			break;// �ǵ�д������case��䣬��Ȼ˳������ִ��
		case 2:
			input(2);
			login();
			break;
		case 3:
			flag=false;//�ػ�����ѭ����
			break;
		default:// ��ƥ���ִ������
			System.out.println("��������ȷ���֣�1��2��3��");// ����
		}
	}
	
	//ע��
	private void register() {//��Ϊ���û������û���Ϣ�����У���ʾ�ɹ�
		// TODO Auto-generated method stub
		if (bank.find(id)) {
		//if (bank.search(id)==null) {
			System.out.println("�û���ע�ᣬ������");//��Ӧ����1���½���ע��
			judge(1);
		} else {
			bank.add(id, passwd);
			System.out.println("ע��ɹ���");
		}
	}

	//��¼
	private void login() {//�˺� ����ƥ�䣬��ʾ�ɹ����������Ϣ
		// TODO Auto-generated method stub
		// ��������Ǯ��???????????????????????????
		  if (!bank.find(id) ||  !passwd.equals(bank.getPassWd(id))) {
		//if (bank.search(id)==null ||  !passwd.equals(bank.getPassWd(id))) {
			System.out.println("�˺Ż��������");
			judge(2);
		} else {
			bussiness(id);
		}
	}
	

	//��½�ɹ���ҵ��         
	private void bussiness(String id) {
		
	       int index=bank.findIndex(id);//��ȡ���п��±� 
	       //Ҳ����return BankCard���͵Ŀ�,���������user����userCards[i]
	       //  BankCard user=bank.search(id); 
	   
       do{
    	    System.out.println("1.ȡ��2.���3.ת�� 4.����� 5.������ 6.�˿�");//ÿִ��һ���һ����ʾ
        	input(1);
            switch (choice){
                case 1:
                	bank.withdraw(index);
                	//bank.withdraw(user);
                    break;
                case 2:
                	bank.deposit(index);
                	//bank.deposit(user);
                	break;
                case 3:
                	bank.transfer(index);
                	//bank.transfer(user);
                    break;
                case 4:
                	bank.inquire(index);
                	//bank.inquire(user);
                    break;
                case 5:
                	bank.setPasswd(index);
                	//bank.setPasswd(user);
                    break;
                case 6:
                	System.out.println("��ӭ�´ι���");
                    break;
                default:// ��ƥ���ִ������
        			System.out.println("ѡ������������ѡ��");
            }
        }while(choice!=6);
	}
	
	private void  input(int num){//num����Ҫ����Ĳ�������
		Scanner scan = new Scanner(System.in);
		if(num==1) {
			choice = scan.nextInt();
		}else if(num==2) {
			System.out.println("�������˺ź����룺");
			id = scan.next();
			passwd = scan.next();
		}
		//scan.close();  //�ر���������������ͨ��new Scanner(System.in)������Scnaner������ 
	}
}
