package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("VAZ", 2101);
        Car car2 = new Car("VAZ", 2102);
        Car car3 = new Car("VAZ", 2103);
        Car car4 = new Car("VAZ", 2104);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"), car1);
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"), car2);
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"), car3);
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"), car4);

        List<User> usersList = userService.listUsers();
        userService.showListOfUsers(usersList);

        CarService carService = context.getBean(CarService.class);
        List<Car> carsList = carService.listCars();
        carService.showListOfCars(carsList);

        User carOwnerUser = userService.findCarOwner(car3);
        System.out.println(carOwnerUser);

        context.close();
    }
}
