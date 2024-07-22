
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListWorker {
    
    Random random = new Random();
    ArrayList<Integer> liste1 = new ArrayList<Integer>();
    ArrayList<Integer> liste2 = new ArrayList<Integer>();   
    //anahtar oluşturuyoruz
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    
    public  void liste1Ekle(){
        synchronized(lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
                liste1.add(random.nextInt(100));
            }
        }
        
        
    
    
    public  void liste2Ekle(){
    
            synchronized(lock2){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
                    liste2.add(random.nextInt(100));
                }
            
            }
        
    
    
    public void degerAta(){
        for(int i =0; i<1000; i++){
            liste1Ekle();
            liste2Ekle();
        }
    }
    
    public void calistir(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
               degerAta();
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
               degerAta();
            }
        });
        long baslangic = System.currentTimeMillis();
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ListWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("List 1 size :"+liste1.size()+"Liste 2 size :"+liste2.size());
        
        long bitis = System.currentTimeMillis();
        
        System.out.println("Gecen süre :"+(bitis-baslangic));
    }
    
}
