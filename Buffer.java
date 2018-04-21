
package me_so;

import java.util.concurrent.ArrayBlockingQueue;


public class Buffer {
    private ArrayBlockingQueue<Integer> buffer = 
            new ArrayBlockingQueue<Integer>(30);
    
    //método para levar e verificar se é zero
    public int leva(){
        int numero = 0;
        try {
            numero = buffer.take();
        } catch (InterruptedException ex) {
            ;
        }
        return numero;
    }
    //método para colocar na fila e verificar
    public void coloca(int numero){
        try {
            buffer.put(numero);
        } catch (InterruptedException ex) {
            ;
        }
    }
}
