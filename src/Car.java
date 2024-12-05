public class Car extends Thread {
    private final int carId;
    private final ParkingLot parkingLot;
    private final ParkingStatus parkingStatus;
    private final int parkingDuration;
    private final int gateId;
    private final int arrivalTime;

    public Car(int carId, ParkingLot parkingLot, int parkingDuration, ParkingStatus parkingStatus, int gateId, int arrivalTime) {
        this.carId = carId;
        this.parkingLot = parkingLot;
        this.parkingDuration = parkingDuration;
        this.parkingStatus = parkingStatus;
        this.gateId = gateId;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car " + carId + " from Gate " + gateId + " arrived at time " + arrivalTime);
            double startWaitTime = startWaitTime = System.currentTimeMillis();
            if (!parkingLot.acquireSpot()) {
                System.out.println("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
                parkingLot.waitForSpot();
            }
            
            double waitTime = (System.currentTimeMillis() - startWaitTime) / 1000;
            parkingStatus.carArrived(gateId);
            if(waitTime==0){
                System.out.println("Car " + carId + " from Gate " + gateId + " parked. (Parking Status: "
                        + (4 - parkingLot.availableSpots()) + " spots occupied)");
            }
            else{
                System.out.println("Car " + carId + " from Gate " + gateId + " parked after waiting for " +waitTime+ " units of time. (Parking Status: "
                        + (4 - parkingLot.availableSpots()) + " spots occupied)");
            }


            Thread.sleep(parkingDuration * 1000L);

            parkingLot.releaseSpot(carId);
            parkingStatus.carLeft();

            System.out.println("Car " + carId + " from Gate " + gateId + " left after " + parkingDuration + " units of time. (Parking Status: "
                    + (4 - parkingLot.availableSpots()) + " spots occupied)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
