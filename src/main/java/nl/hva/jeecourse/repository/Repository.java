package nl.hva.jeecourse.repository;

import nl.hva.jeecourse.model.Customer;
import nl.hva.jeecourse.model.Order;
import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.model.User;

import java.util.List;

public interface Repository {

    State addState(State state);
    List<State> getAllStates();
    State getStateByAbbreviation(String abbrev);
    byte[] getStateFlag(String abbrev);
    byte[] getStateMap(String abbrev);
    void removeAllStates();

    Customer addCustomer(Customer cust);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    Customer removeCustomer(int id);
    void modifyCustomer(Customer cust);
    List<Customer> getPagedCustomers(int pageNumber, int pageSize);
    void removeAllCustomers();
    long getCustomerCount();

    User addUser(User user);
    User removeUser(String login);
    User getUser(String login);
    List<User> getAllUsers();
    boolean isValidUser(String login, String pwd);

    Order getOrderById(int id);
    Order addOrder(Order order);
    Order removeOrder(int id);
    List<Order> getAllOrders(int customerId);
    void removeAllOrders();


}
