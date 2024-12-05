import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore parkingSpots = new Semaphore(4, true);

    public boolean acquireSpot() {
        return parkingSpots.tryAcquire();
    }
    public void waitForSpot() throws InterruptedException {
        while (parkingSpots.availablePermits() == 0) {
            Thread.sleep(100);
        }
        parkingSpots.acquire();
    }

    public void releaseSpot(int carId) {
        parkingSpots.release();

    }

    public int availableSpots() {
        return parkingSpots.availablePermits();
    }
}
