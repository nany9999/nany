import java.util.Scanner;
//一个ATM机对应一家银行
public class ATM {
	
	private Bank bank;//可通过它调用bank里的方法
	private int choice=0;
	private String id;
	private String passwd;
	private boolean flag=true;//关机标志

	public ATM() {// 初始化
		this.bank = new Bank("中国银行");//new ATM时通过ATM()把bank初始化，bank初始化里把bankCard也初始化了
	}
	
	//核心方法
	public void start() {// 开机

		System.out.println("欢迎使用中国银行ATM机");
		
		while(flag) {
	    	System.out.println(" 1.注册   2.登录  3.关机");// 界面
		    //重复部分可复用
			input(1);
			judge(choice);			
	    }
		
		System.out.println("正在关机...");
	}

	//私有方法 ，仅能在start()函数里自己调用 
	//输入判断
	private void judge(int choice) {
		switch (choice) {
		case 1:
			input(2);
			register();
			break;// 记得写以跳出case语句，不然顺序往下执行
		case 2:
			input(2);
			login();
			break;
		case 3:
			flag=false;//关机，不循环了
			break;
		default:// 不匹配才执行这里
			System.out.println("请输入正确数字（1或2或3）");// 界面
		}
	}
	
	//注册
	private void register() {//若为新用户，存用户信息到银行，提示成功
		// TODO Auto-generated method stub
		if (bank.find(id)) {
		//if (bank.search(id)==null) {
			System.out.println("用户已注册，请重试");//不应该输1重新进入注册
			judge(1);
		} else {
			bank.add(id, passwd);
			System.out.println("注册成功！");
		}
	}

	//登录
	private void login() {//账号 密码匹配，提示成功，输出卡信息
		// TODO Auto-generated method stub
		// 银行名和钱数???????????????????????????
		  if (!bank.find(id) ||  !passwd.equals(bank.getPassWd(id))) {
		//if (bank.search(id)==null ||  !passwd.equals(bank.getPassWd(id))) {
			System.out.println("账号或密码错误");
			judge(2);
		} else {
			bussiness(id);
		}
	}
	

	//登陆成功后业务         
	private void bussiness(String id) {
		
	       int index=bank.findIndex(id);//获取银行卡下标 
	       //也可以return BankCard类型的卡,下面程序用user代替userCards[i]
	       //  BankCard user=bank.search(id); 
	   
       do{
    	    System.out.println("1.取款2.存款3.转账 4.查余额 5.改密码 6.退卡");//每执行一遍给一个提示
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
                	System.out.println("欢迎下次光临");
                    break;
                default:// 不匹配才执行这里
        			System.out.println("选择有误，请重新选择：");
            }
        }while(choice!=6);
	}
	
	private void  input(int num){//num，需要读入的参数个数
		Scanner scan = new Scanner(System.in);
		if(num==1) {
			choice = scan.nextInt();
		}else if(num==2) {
			System.out.println("请输入账号和密码：");
			id = scan.next();
			passwd = scan.next();
		}
		//scan.close();  //关闭输入流，不能再通过new Scanner(System.in)来创建Scnaner对象了 
	}
}
