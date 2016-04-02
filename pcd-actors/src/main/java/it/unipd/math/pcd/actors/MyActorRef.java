package it.unipd.math.pcd.actors;

import it.unipd.math.pcd.actors.exceptions.*;

public class MyActorRef<T extends Message> implements ActorRef<T> {

	private MyAbsActorSystem system;
	
	public MyActorRef(MyAbsActorSystem s) {
		this.system = s;
	}

	@Override
	public int compareTo(ActorRef o) {
		if(this == o) return 0;
		return -1;
	}

	@Override
	public void send(final T message,final ActorRef to){
		// throws a NoSuchActorException if the actor has been stopped
		system.getUnderlyingActor(this);
		Actor actor = system.getUnderlyingActor(to); 
		AbsActor absactor = (AbsActor) actor;
		absactor.setSender(MyActorRef.this);
		absactor.addMessage(message);
	}
}
