
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private List<Guest> asztalok = new ArrayList<>();
    private LinkedList<Guest> rendeles = new LinkedList<>();
    private LinkedList<Guest> keszkajak = new LinkedList<>();
    {

        for (int i = 0; i < 4; ++i) {
            asztalok.add(null);
        }


    }
    public Restaurant(){

    }

    public int getSzabadAsztal(){

        for(int i = 0; i< 4; i++){
           if( asztalok.get(i) == null ){

               return i;
           }
        }

        return -1;
    }


    public void setGuestToAsztal(int asztal_idx, Guest guest){
        asztalok.set(asztal_idx, guest);
    }

    public void setRendeles(Guest guest){
        synchronized (rendeles) {
            rendeles.add(guest);
            rendeles.notify();
        }

    }

    public Guest getRendeles(){
        synchronized (rendeles) {
            while (rendeles.size() == 0) {
                try {
                    System.out.println("Meg nincs rendeles");
                    rendeles.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return rendeles.removeFirst();
        }
    }

    public void setElkeszultKaja(Guest guest){
        synchronized (keszkajak){
            keszkajak.add(guest);
            keszkajak.notify();
        }
    }

    public void getKeszKaja(Guest guest) throws InterruptedException {
        synchronized (keszkajak){
            while ( (keszkajak.size() == 0) || (!keszkajak.contains(guest) )){
                System.out.println("Meg nincs kesz kaja");
                keszkajak.wait();
            }
            keszkajak.remove(keszkajak.indexOf(guest));

        }
    }

    public void setAsztalUresse(int asztal_idx){
        asztalok.set(asztal_idx, null);
        System.out.println("Szabad a(z) " + asztal_idx + ". asztal");
        //System.out.println(asztalok.get(asztal_idx));
    }



}
