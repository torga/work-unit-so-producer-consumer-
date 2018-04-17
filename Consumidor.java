
package me_so;

public class Consumidor implements Runnable {
    private Buffer buffer;
    private volatile boolean paraThread;
    private Thread proxThread;      
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }
   
    public void run() {
        proxThread = Thread.currentThread();
        paraThread = false;        
        while(!paraThread){
            int numero = buffer.leva();
            System.out.println(numero);
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                
                Thread.currentThread().interrupt();
            }
        }
    }
    public void para() {
        paraThread = true;
        if (proxThread != null) {
          proxThread.interrupt();
        }
    }     
}