/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Riccardo Cardin
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p/>
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */

/**
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
package it.unipd.math.pcd.actors;

import it.unipd.math.pcd.actors.exceptions.*; 

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Defines common properties of all actors.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActor<T extends Message> implements Actor<T> {

    /**
     * Self-reference of the actor
     */
    protected ActorRef<T> self;

    /**
     * Sender of the current message
     */
    protected ActorRef<T> sender;
    
    /**
     * Actor's mailbox
     */
    protected final ConcurrentLinkedQueue<Message> mailbox = new ConcurrentLinkedQueue<Message>();
    
    /**
     * Represents the current state of the actor
     */
    private volatile boolean stopped = false;
    
    /**
     * Thread that performs add and pop actions
     */
    private ActorThread actorThread = new ActorThread();
    
    public AbsActor(){
    	actorThread.start();
    }
    
    private class ActorThread extends Thread{
    	
    	public ActorThread(){
    		this.setDaemon(true);
    	}
    	
    	public void run(){
    		while(!stopped){
    			try{
    				synchronized(mailbox){
    					while(mailbox.size() == 0 && !stopped) mailbox.wait(); 
    					while(mailbox.size() != 0)
    					{
    						Message message = mailbox.poll();
    						receive((T) message);
    					}
    				}
    			}
    			catch(InterruptedException e){
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	/**
         * Adds a message to mailbox
         *
         * @param message The message to send
         */
        public void addNewMessage(Message message){ 
        	synchronized(mailbox){
        		if(stopped) throw new NoSuchActorException();
        		mailbox.add(message);
        		mailbox.notifyAll();
        	}
        }
        
    }

    /**
     * Sets the self-referece.
     *
     * @param self The reference to itself
     * @return The actor.
     */
    protected final Actor<T> setSelf(ActorRef<T> self) {
        this.self = self;
        return this;
    }
    
    /**
     * Sets the sender of a message.
     *
     * @param ref The sender
     */
    public void setSender(ActorRef ref){
    	this.sender = ref;
    }
    
    /**
     * Add a new message to mailbox
     */
    public void addMessage(Message message){
    	actorThread.addNewMessage(message);
    }
    
    /**
     * Stops the actor
     * 
     */
    public void stopAbsActor(){
    	stopped = true;
    	synchronized(mailbox){
    		while(!mailbox.isEmpty()){
    			Message message = mailbox.poll();
        		receive((T) message);
    		}
    		mailbox.notify();
    	}
    }
}
