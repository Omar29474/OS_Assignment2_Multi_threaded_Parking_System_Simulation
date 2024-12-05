public class ParkingStatus {
    private int carsServed = 0;
    private int currentCarsInParking = 0;
    private final int[] gateCarCount = new int[3];

    public synchronized void carArrived(int gateId) {
        currentCarsInParking++;
        carsServed++;
        gateCarCount[gateId - 1]++;
    }

    public synchronized void carLeft() {
        currentCarsInParking--;
    }

    public synchronized void reportStatus() {
        System.out.println("\n--- Parking Lot Report ---");
        System.out.println("Total Cars Served: " + carsServed);
        System.out.println("Current Cars in Parking: " + currentCarsInParking);
        System.out.println("Gate 1 served " + gateCarCount[0] + " cars.");
        System.out.println("Gate 2 served " + gateCarCount[1] + " cars.");
        System.out.println("Gate 3 served " + gateCarCount[2] + " cars.");
    }
}
