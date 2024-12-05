public class Gate implements Runnable {
    private final ParkingLot parkingLot;
    private final ParkingStatus parkingStatus;
    private final int gateId;

    public Gate(int gateId, ParkingLot parkingLot, ParkingStatus parkingStatus) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.parkingStatus = parkingStatus;
    }

    public void processCar(int carId, int arrivalTime, int parkingDuration) {
        try {
            Thread.sleep(arrivalTime * 1000L);
            Car car = new Car(carId, parkingLot, parkingDuration, parkingStatus, gateId, arrivalTime);
            car.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
