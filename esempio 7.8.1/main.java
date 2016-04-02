public class main{
	public static void main(String[] args){
		T2 t2 = new T2();
		T1 t1 = new T1(t2);
		t1.start(); t2.start(); 
	}
}