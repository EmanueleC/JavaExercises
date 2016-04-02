import java.util.concurrent.locks.*;

class BoundedBuffer{
	final Object[] items = new Object[100]; 
	int count, putptr, takeptr; 
	
	final Lock l = new ReentrantLock(); // lock esplicito
	// condizioni sul buffer
	final Condition notFull = l.newCondition();
	final Condition notEmpty = l.newCondition();
	
	public void put(Object o) throws InterruptedException{
		l.lock();
		try{
			while(count == items.length){
				System.out.print("Attendo: buffer pieno");
				notFull.await();
			}
			items[putptr] = o;
			putptr++;
			if(putptr == items.length) putptr = 0;
			count++;
			notEmpty.signalAll();
		}
		finally{ l.unlock(); }
	}
	
	public Object take() throws InterruptedException{
		l.lock();
		try{
			while(count == 0){
				System.out.print("Attendo: buffer vuoto");
				notEmpty.await();
			}
			Object ob = items[takeptr];
			takeptr++;
			if(takeptr == items.length) takeptr = 0;
			count--;
			notFull.signalAll();
			return ob;
			}
			finally{ l.unlock(); }
	}
}
