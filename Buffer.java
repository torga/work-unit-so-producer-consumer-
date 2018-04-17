
package me_so;

import java.util.concurrent.ArrayBlockingQueue;


public class Buffer {
    private ArrayBlockingQueue<Integer> buffer = 
            new ArrayBlockingQueue<Integer>(30);
    
    
    public int leva(){
        int numero = 0;
        try {
            numero = buffer.take();
        } catch (InterruptedException ex) {
            ;
        }
        return numero;
    }
    
    public void coloca(int numero){
        try {
            buffer.put(numero);
        } catch (InterruptedException ex) {
            ;
        }
    }
}