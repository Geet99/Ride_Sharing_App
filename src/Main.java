import model.City;
import model.Gender;
import model.Vehicle;
import model.VehicleType;
import output.ConsolePrint;
import output.Print;
import repository.UserRepository;
import repository.VehicleRepository;
import service.RideService;
import service.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        UserRepository userRepository = new UserRepository();
        VehicleRepository vehicleRepository = new VehicleRepository(userRepository);
        UserService userService = new UserService(userRepository, vehicleRepository);
        RideService rideService = new RideService(userRepository, vehicleRepository);
        Print printer = new ConsolePrint();

        String response;

        response = userService.addUser("Rohan", Gender.valueOf("M"), 36);
        printer.print(response);
        response = userService.addVehicle("Rohan", VehicleType.valueOf("Swift"), "KA-01-12345");
        printer.print(response);
        response = userService.addUser("Shashank", Gender.valueOf("M"), 29);
        printer.print(response);
        response = userService.addVehicle("Shashank", VehicleType.valueOf("Baleno"), "TS-05-62395");
        printer.print(response);
        response = userService.addUser("Nandini", Gender.valueOf("F"), 29);
        printer.print(response);
        response = userService.addUser("Shipra", Gender.valueOf("F"), 27);
        printer.print(response);
        response = userService.addVehicle("Shipra", VehicleType.valueOf("Polo"), "KA-05-41491");
        printer.print(response);
        response = userService.addVehicle("Shipra", VehicleType.valueOf("Activa"), "KA-12-12332");
        printer.print(response);
        response = userService.addUser("Gaurav", Gender.valueOf("M"), 29);
        printer.print(response);
        response = userService.addUser("Rahul", Gender.valueOf("M"), 35);
        printer.print(response);
        response = userService.addVehicle("Rahul", VehicleType.valueOf("XUV"), "KA-05-1234");
        printer.print(response);

        response = rideService.offerRide("Rohan", City.valueOf("Hyderabad"), 1, VehicleType.valueOf("Swift"), "KA-01-12345", City.valueOf("Bangalore"));
        printer.print(response);
        response = rideService.offerRide("Shipra", City.valueOf("Bangalore"), 1, VehicleType.valueOf("Activa"), "KA-12-12332", City.valueOf("Mysore"));
        printer.print(response);
        response = rideService.offerRide("Shipra", City.valueOf("Bangalore"), 2, VehicleType.valueOf("Polo"), "KA-05-41491", City.valueOf("Mysore"));
        printer.print(response);
        response = rideService.offerRide("Shashank", City.valueOf("Hyderabad"), 2, VehicleType.valueOf("Baleno"), "TS-05-62395", City.valueOf("Bangalore"));
        printer.print(response);
        response = rideService.offerRide("Rahul", City.valueOf("Hyderabad"), 5, VehicleType.valueOf("XUV"), "KA-05-1234", City.valueOf("Bangalore"));
        printer.print(response);
        response = rideService.offerRide("Rohan", City.valueOf("Bangalore"), 1, VehicleType.valueOf("Swift"), "KA-01-12345", City.valueOf("Pune"));
        printer.print(response);

        response = rideService.selectRide("Nandini", City.valueOf("Bangalore"), City.valueOf("Mysore"), 1, "Most Vacant");
        printer.print(response);
        response = rideService.selectRide("Gaurav", City.valueOf("Bangalore"), City.valueOf("Mysore"), 1, VehicleType.valueOf("Activa"));
        printer.print(response);
        response = rideService.selectRide("Shashank", City.valueOf("Mumbai"), City.valueOf("Bangalore"), 1, "Most Vacant");
        printer.print(response);
        response = rideService.selectRide("Rohan", City.valueOf("Hyderabad"), City.valueOf("Bangalore"), 1, VehicleType.valueOf("Baleno"));
        printer.print(response);
        response = rideService.selectRide("Shashank", City.valueOf("Hyderabad"), City.valueOf("Bangalore"), 1, VehicleType.valueOf("Polo"));
        printer.print(response);

        response = rideService.endRide(1);
        printer.print(response);
        response = rideService.endRide(2);
        printer.print(response);
        response = rideService.endRide(3);
        printer.print(response);
        response = rideService.endRide(4);
        printer.print(response);

        rideService.printRideStats();
    }
}