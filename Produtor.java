
package me_so;


public class Produtor implements Runnable {
    private int numero = 0;
    private Buffer buffer;
    private volatile boolean stopRequested;
    private Thread proxThread;    
    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }
    //método para executar o andamento das threads
    public void run() {
        proxThread = Thread.currentThread();
        stopRequested = false;        
        while(!stopRequested){
            buffer.coloca(numero++);
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
             
                Thread.currentThread().interrupt();
            }
        }
    }
    \\método para parar
    public void para() {
        stopRequested = true;
        if (proxThread != null) {
          proxThread.interrupt();
        }
    }    
}
