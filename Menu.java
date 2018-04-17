
package me_so;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import me_so.Buffer;
import me_so.Consumidor;
import me_so.Produtor;


//author:igor torga /marcel/vinicius

public class Menu extends javax.swing.JDialog {
    private boolean existe = false;
    private Produtor produtor;
    private Consumidor consumidor;
    private Buffer buffer;
    private ExecutorService exec = Executors.newCachedThreadPool();
  
    //₢riar forma principal do modelo
    public Menu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        componentes();
    }
   //Componentes do modelo usando javax.swing
    private void componentes() {
        jbtnIniciar = new javax.swing.JButton();
        jbtnParar = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jbtnIniciar.setText("Iniciar / Reiniciar");
        jbtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarActionPerformed(evt);
            }
        });
        jbtnParar.setText("Parar");
        jbtnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPararActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnParar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jbtnIniciar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnIniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnParar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
   
private void jbtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    if(!existe){
        buffer = new Buffer();
        consumidor = new Consumidor(buffer);        
        produtor = new Produtor(buffer);
        exec.execute(produtor);
        exec.execute(consumidor);  
        existe = true;                
    }
}
private void jbtnPararActionPerformed(java.awt.event.ActionEvent evt) {                                         
    if(this.existe){
        produtor.para();
        consumidor.para();
        buffer = null;
        produtor = null;
        consumidor = null;
        this.existe = false;
    }
}
   
//Visibilidade do modelo
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu dialog = new Menu(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private javax.swing.JButton jbtnIniciar;
    private javax.swing.JButton jbtnParar;
   
}
