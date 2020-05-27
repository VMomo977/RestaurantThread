import java.util.Random;

public class Guest extends Thread{
    private int guest_id;
    private RestaurantComputer restComp;
    private Restaurant restaurant;

    public Guest(int id, RestaurantComputer rc, Restaurant r){
        guest_id = id;
        restComp = rc;
        restaurant = r;
    }

    @Override
    public void run(){
        System.out.println("Run the guest " + guest_id);
        try {
            int asztal_idx;

            asztal_idx = restComp.asztalFoglal(this);
            System.out.println("Guest " + getGuest_id() + " a(z) " + asztal_idx + " . asztalt lefoglalta");

            for(int i = 0; i <2 ; i++) {

                restaurant.setRendeles(this);
                System.out.println("Guest " + getGuest_id() + " az " + i + ". rendeleset feladta");


                restaurant.getKeszKaja(this);
                System.out.println("Guest " + getGuest_id() + " az " + i + ". rendelest megkapta es eszi");

                Random r = new Random();
                int eszik = r.nextInt(2500 - 1500) + 1500;
                Thread.sleep(eszik);
            }

            restComp.asztalElhagyas(asztal_idx);
            System.out.println("Guest " + getGuest_id() + " elhagyta az " + asztal_idx +" asztalt");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getGuest_id() {
        return guest_id;
    }
}
