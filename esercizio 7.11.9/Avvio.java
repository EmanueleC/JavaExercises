public class Avvio{
	public static void main(String[] args){
		Passaggio p = new Passaggio();
		Stop s1 = new Stop(p); // coda sinistra
		Stop s2 = new Stop(p); // coda destra
		Stop[] s = new Stop[2];
		s[0] = s1; s[1] = s2;
		s[0].start(); s[1].start();
		for(int i = 0; i < 100; i++){
			s[i%2].add(new Automobile(i));
			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){}
		}
	}
}