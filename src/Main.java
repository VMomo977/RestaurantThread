
public class Main {

    public static void main(String[] args) {
	System.out.println("Hello");

	Restaurant r = new Restaurant();
	RestaurantComputer rc = new RestaurantComputer(r);

	for(int i = 0; i < 30; i++){
	    new Guest(i, rc, r).start();
    }
		new Chef( rc, r).start();

    }
}
