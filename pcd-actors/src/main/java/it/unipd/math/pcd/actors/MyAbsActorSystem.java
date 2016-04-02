package it.unipd.math.pcd.actors;

import it.unipd.math.pcd.actors.exceptions.*;

import java.util.Map;

import java.util.HashMap;

public class MyAbsActorSystem extends AbsActorSystem {

	public MyAbsActorSystem() {
		super();
	}

	@Override
	public void stop(ActorRef<?> actor) {
		this.StopActor(actor);
		this.RemoveActor(actor);
	}

	@Override
	public void stop() {
		for(Map.Entry<ActorRef<?>, Actor<?>> entry : actors.entrySet()) {
			this.StopActor(entry.getKey());
		}
		actors.clear();
	}

	@Override
	protected ActorRef createActorReference(ActorMode mode) {
		if(mode != ActorMode.LOCAL) throw new IllegalArgumentException();
		MyActorRef reference = new MyActorRef(this);
		return reference;
	}

}
