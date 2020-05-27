

public class Chef extends Thread {

    private RestaurantComputer restComputer;
    private Restaurant restaurant;

    public Chef(RestaurantComputer restComp, Restaurant r){

        restComputer = restComp;
        restaurant = r;
    }

    @Override
    public void run(){
        while (true) {
            System.out.println("Chef var, hogy etel megrendeles legyen es tudjon fozni");

            Guest guest = null;

            guest = restaurant.getRendeles();

            System.out.println("Chef a guest " + guest.getGuest_id() + " rendelesest csinalja");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            restaurant.setElkeszultKaja(guest);
        }
    }
}
