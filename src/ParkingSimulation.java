import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParkingSimulation {
    private final ParkingStatus parkingStatus = new ParkingStatus();
    private final ArrayList<Gate> gates = new ArrayList<>();
    private final ParkingLot parkingLot = new ParkingLot();

    public ParkingSimulation() {
        for (int i = 1; i <= 3; i++) {
            gates.add(new Gate(i, parkingLot, parkingStatus));
        }
    }

    public void loadCarsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);
                Gate gate = gates.get(gateId - 1);
                new Thread(() -> gate.processCar(carId, arrivalTime, parkingDuration)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingSimulation main = new ParkingSimulation();
        main.loadCarsFromFile("src/car_schedule");
        Thread.sleep(20000);
        main.parkingStatus.reportStatus();
    }
}