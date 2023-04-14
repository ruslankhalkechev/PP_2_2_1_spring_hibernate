package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user, Car car) {
        user.setCar(car);
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public User findCarOwner(Car car) {
        String hql = "FROM User user INNER JOIN FETCH user.car WHERE user.car.model = :modelOfCar " +
                "AND user.car.series = :seriesOfCar";
        try (Session session = userDao.getSessionFactory().openSession()) {
            TypedQuery<User> query = session.createQuery(hql);
            query.setParameter("modelOfCar", car.getModel());
            query.setParameter("seriesOfCar", car.getSeries());
            return query.getSingleResult();
        }
    }

    @Override
    public void showListOfUsers(List<User> usersList) {
        for (User user : usersList) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getSeries());
            System.out.println();
        }
    }
}
