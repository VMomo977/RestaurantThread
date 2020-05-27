

public class RestaurantComputer extends Thread{
    private Restaurant restaurant;

    public  RestaurantComputer(Restaurant r){
        restaurant = r;
    }

    @Override
    public void run(){

        System.out.println("RestaurantComputer szal elindult es vege is");

    }

    public synchronized int asztalFoglal(Guest guest) throws InterruptedException {

        int asztal_idx ;
        System.out.println("Guest " + guest.getGuest_id() + " asztalt probal foglalni");
        while((asztal_idx = restaurant.getSzabadAsztal()) == -1){
            System.out.println("Jelenleg minden asztal foglalt, addig varakozik a vendeg");
            wait();
            asztal_idx = restaurant.getSzabadAsztal();
        }
        asztal_idx = restaurant.getSzabadAsztal();
        restaurant.setGuestToAsztal(asztal_idx, guest);
        notifyAll();

        return asztal_idx;
    }



    public  synchronized void asztalElhagyas(int idx){
        restaurant.setAsztalUresse(idx);
        notifyAll();
    }
}
