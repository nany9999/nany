import java.util.Arrays;
import java.util.Scanner;
//存信息
public class Bank {//每new一个Bank类，多有一份Bank类的成员变量
	private String bankName;
	private BankCard[] userCards;//卡类型的一维数组 可通过它调用卡信息
	private int size=0;//有效银行卡的个数  全局变量
	private int index=-1;//存放银行卡下标。免得每次遍历找
	
	//pubilc 可在ATM类里调用该方法
	public Bank(String bankName) {//初始化
		this.bankName=bankName;
		userCards=new BankCard[10];//先给10个，扩容参考MyArrayList
	}
	
	//尾插
	public void add(String id,String passwd ) {
		if(size == userCards.length) {
			userCards=Arrays.copyOf(userCards,userCards.length*2);
		}
		userCards[size++]=new BankCard(passwd,id);
	}
	
//	//也可以把find函数改进为return BankCard，有就不为空
//	public BankCard search(String id) {
//		for(int i=0;i<size;i++) {
//			if(id.equals(userCards[i].getId()))
//					return userCards[i];
//		}
//		return null;	
//	}
	
	//找用户,有就true
	public boolean find(String id) {
		boolean flag=false;//默认没有
		for(int i=0;i<size;i++) {
			if(id.equals(userCards[i].getId()))
					flag= true;
			break;
		}
		return flag;	
	}
	
	//找银行卡下标
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
	
	public String getPassWd(String id) {//大写W，是银行的方法
		String Passwd=null;
		for(int i=0;i<size;i++) {//找到id对应的下标
			if(id.equals(userCards[i].getId())){
				Passwd=userCards[i].getPasswd();//小写w,是银行卡的方法
				break;
			}
		}
		return Passwd;
	}

	//取钱
	public void withdraw(int index) {
		double count=enter_Count();
		userCards[index].SetMoney(-count);//负数
		System.out.println("请取走您的现金");
	}
	
	//存钱
	public void deposit(int index) {
		System.out.println("请将钞票叠放整齐：");
		Scanner scan = new Scanner(System.in);
		double  tmp = scan.nextInt();//只认整钱
		userCards[index].SetMoney(tmp);
		System.out.println("正在点钞，请稍后。。。\n存款成功");
	}

	//转账
	public void transfer(int index) {   
		
		int select =0;
		//合法id 和 金额
		String account=enter_Account(index);
		double count=enter_Count();
		
		System.out.println("确认给"+account+"转账"+count+"元吗？\n输入：1：确认   2：退出");
		if(select==1) {
			userCards[index].SetMoney(-count);
			userCards[findIndex(account)].SetMoney(count);
		}	
	}

	//查余额
	public void inquire(int index) {//历史交易记录？？？？？？？？？？？
		double money=userCards[index].getMoney();
		System.out.println("余额为："+money+"元");
	}
	

	//判断金额够就返回true
	public  boolean  analyze(double tmp) {
		boolean flag=true;
		if(tmp>userCards[index].getMoney())
			flag=false;	
		return flag;
	}
	
	//输入正确金额
	public  double enter_Count() {
		Scanner scan  = new Scanner(System.in);
		int select=0;
		double tmp=0;
		do {
			  System.out.println("请输入正确金额：");
			  tmp = scan.nextInt();//只认整钱
		}while(!analyze(tmp));//避免余额不足
		
		System.out.println("请再次确认金额数。输入1：确认  2：修改");
		select = scan.nextInt();
		if(select==2) {
			do {
				  System.out.println("请输入正确金额：");
				  tmp = scan.nextInt();
			}while(!analyze(tmp));
		}
			   
		return tmp;
	}

	// 输入正确账户
	public String enter_Account(int index) {
		Scanner scan = new Scanner(System.in);
		int select = 0;
		String tmp = null;
		
		do {
			System.out.println("请输入正确账户(id)：");
			tmp = scan.next();
		} while (!find(tmp) && !tmp.equals(userCards[index].getId()));//有用户 且  不能是 自己

		System.out.println("请再次确认账户(id)："+tmp+"。输入1：确认  2：修改");
		select = scan.nextInt();
		if (select == 2) {
			do {
				System.out.println("请输入正确账户(id)：");
				tmp = scan.next();
			} while (!find(tmp));
		}

		return tmp;
	}

	//修改密码
	public void setPasswd(int index) {
		
		Scanner scan = new Scanner(System.in);
		int select = 0;
		String tmp1 = null;
		String tmp2 = null;
		
		do {
			System.out.println("请输入新密码：");
			tmp1 = scan.next();
		} while (!tmp1.equals(userCards[index].getPasswd()));//不能是老密码

		
		System.out.println("请再次输入新密码以确认：");
		tmp2 = scan.next();
		while (!tmp1.equals(tmp2)) {
			System.out.println("两次密码不一致，请重新输入：");
			tmp2 = scan.next();
		}
		
		userCards[index].setPasswd(tmp1);
	}
	
}
