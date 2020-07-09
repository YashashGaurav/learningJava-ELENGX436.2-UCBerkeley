package ModuleOneToSix;

import java.util.*;

public class ParkingGarage {

	private final int WEIGHT_LIMIT = 25000;
	private final int VEHICLE_COUNT_LIMIT = 20;

	private final List<Vehicle> VehicleSlots = new ArrayList<Vehicle>(VEHICLE_COUNT_LIMIT);

	public boolean AddVehicle(Vehicle vehicle) {
		if (SlotsLeft() < 1) {
			System.out.println("No more parking slots left");
			return false;
		} else if (WeightCapacityLeft() < vehicle.getWeightInPounds()) {
			System.out.println("Garage weight capacity exceeded.");
			return false;
		} else {
			VehicleSlots.add(vehicle);
			System.out.println("Adding a " + vehicle.getClass().getSimpleName() + " to Garage.");
			return true;
		}
	}

	public boolean RemoveVehicle(Vehicle vehicle) {
		if (VehicleSlots.isEmpty()) {
			System.out.println("Garage Empty!");
			return false;

		} else if (!VehicleSlots.contains(vehicle)) {
			System.out.println("Vehicle not in Garage.");
			return false;
		} else {
			System.out.println("Removing a " + vehicle.getClass().getName());
			VehicleSlots.remove(vehicle);
			System.out.println("Vehicle " + vehicle.getClass().getSimpleName() + " left.");
			return true;
		}
	}

	public Vehicle RemoveVehicle() {
		if (VehicleSlots.isEmpty()) {
			System.out.println("Garage Empty!");
			return null;
		} else {
			var objectToBeRemoved = VehicleSlots.get(VehicleSlots.size() - 1);
			VehicleSlots.remove(objectToBeRemoved);
			System.out.println("Vehicle " + objectToBeRemoved.getClass().getSimpleName() + " left.");
			return objectToBeRemoved;
		}
	}

	public int SlotsLeft() {
		return VEHICLE_COUNT_LIMIT - VehicleSlots.size();
	}

	private int WeightCapacityLeft() {
		int currentWeight = 0;
		for (Vehicle vehicle : VehicleSlots) {
			currentWeight += vehicle.getWeightInPounds();
		}

		return WEIGHT_LIMIT - currentWeight;
	}

	public static void main(String[] args) {
		ParkingGarage garage1 = new ParkingGarage();
		var randomGenerator = new Random();

		// Add vehicles till Garage is full
		System.out.println("---- Filling Garage -----");
		for (boolean doLoop = true; doLoop == true;) {
			int randomNumber = randomGenerator.nextInt(5);

			switch (randomNumber) {
				case 0:
					doLoop = garage1.AddVehicle(new SaturnSL1());
					break;
				case 1:
					doLoop = garage1.AddVehicle(new HondaCivic());
					break;
				case 2:
					doLoop = garage1.AddVehicle(new MercedesC230());
					break;
				case 3:
					doLoop = garage1.AddVehicle(new ChevyS10());
					break;
				case 4:
					doLoop = garage1.AddVehicle(new SubaruOutback());
					break;
			}
		}
		// Remove 5 Vehicles
		System.out.println("---- Removing 5 vehicles -----");
		for (int count = 0; count < 5; count++) {
			garage1.RemoveVehicle();
		}
		// and add till full again
		System.out.println("---- Filling Garage -----");
		for (boolean doLoop = true; doLoop == true;) {
			int randomNumber = randomGenerator.nextInt(5);

			switch (randomNumber) {
				case 0:
					doLoop = garage1.AddVehicle(new SaturnSL1());
					break;
				case 1:
					doLoop = garage1.AddVehicle(new HondaCivic());
					break;
				case 2:
					doLoop = garage1.AddVehicle(new MercedesC230());
					break;
				case 3:
					doLoop = garage1.AddVehicle(new ChevyS10());
					break;
				case 4:
					doLoop = garage1.AddVehicle(new SubaruOutback());
					break;
			}
		}

	}

}

interface Domestic {
}

interface Import {
}

interface Japanese extends Import {
}

interface German extends Import {
}

interface Detroit extends Domestic {
}

interface SpringHill extends Domestic {
}

interface Vehicle {
	int getWeightInPounds();
}

interface Automobile extends Vehicle {
}

interface LargeAutomobile extends Vehicle {
}

interface Sedan extends Automobile {
}

interface Van extends LargeAutomobile {
}

interface Truck extends LargeAutomobile {
}

interface Compact extends Automobile {
}

interface SportsUtilityVehicle extends Truck, Van {
}

class SaturnSL1 implements SpringHill, Sedan {

	@Override
	public int getWeightInPounds() {
		return 0;
	}

}

class HondaCivic implements Japanese, Compact {

	@Override
	public int getWeightInPounds() {
		return 1000;
	}
}

class MercedesC230 implements German, Sedan {

	@Override
	public int getWeightInPounds() {
		return 1500;
	}
}

class ChevyS10 implements Detroit, Truck {

	@Override
	public int getWeightInPounds() {
		return 2500;
	}
}

class SubaruOutback implements Japanese, SportsUtilityVehicle {

	@Override
	public int getWeightInPounds() {
		return 1750;
	}
}
