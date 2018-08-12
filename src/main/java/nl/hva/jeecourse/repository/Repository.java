package nl.hva.jeecourse.repository;

import nl.hva.jeecourse.model.Customer;
import nl.hva.jeecourse.model.Order;
import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.model.User;
import nl.hva.jeecourse.repository.exception.InconsistentCustomerException;

import java.util.List;

public interface Repository {

    /**
     * Add a state
     * @param state
     * @return the newly created state
     */
    State addState(State state);

    /**
     * Get the whole list of states
     * @return
     */
    List<State> getAllStates();

    /**
     * Get state by abbrev
     * @param abbrev
     * @return the state object or null if not found
     */
    State getStateByAbbreviation(String abbrev);

    /**
     * Get an array of bytes representing the flag of the state (image/png)
     * @param abbrev
     * @return always an array of bytes. returns a blank img if not found
     */
    byte[] getStateFlag(String abbrev);

    /**
     * Get an array of bytes representing the map of the state (image/png)
     * @param abbrev
     * @return always an array of bytes. returns a blank img if not found
     */
    byte[] getStateMap(String abbrev);

    /**
     * Remove all states of the db
     */
    void removeAllStates();

    /**
     * Add a customer
     * @param cust
     * @return the newly created customer
     * @throws InconsistentCustomerException if inconsistent data
     */
    Customer addCustomer(Customer cust) throws InconsistentCustomerException;

    /**
     * Get a list of customers
     * @return
     */
    List<Customer> getAllCustomers();

    /**
     * Get a specific customer
     * @param id
     * @return the customer or null if not found
     */
    Customer getCustomerById(int id);

    /**
     * remove the customer
     * @param id
     * @return the removed customer or null of not found
     */
    Customer removeCustomer(int id);

    /**
     * Change the customer
     * @param cust
     */

    void modifyCustomer(Customer cust);

    /**
     * Get a paged list of customers
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Customer> getPagedCustomers(int pageNumber, int pageSize);

    /**
     * remove all customers
     */
    void removeAllCustomers();

    /**
     * get the quantity of customers
     * @return
     */
    long getCustomerCount();

    /**
     * add a user into the db
     * @param user
     * @return the newly created user
     */
    User addUser(User user);

    /**
     * remove the specified user
     * @param login
     * @return the removed user or null if not found
     */
    User removeUser(String login);

    /**
     * get the user by login
     * @param login
     * @return the corresponding user or null if not found
     */
    User getUser(String login);

    /**
     * get a list of users
     * @return
     */
    List<User> getAllUsers();

    /**
     * returns if the login/password combination is valid
     * @param login
     * @param pwd
     * @return
     */
    boolean isValidUser(String login, String pwd);

    Order getOrderById(int id);
    Order addOrder(Order order);
    Order removeOrder(int id);
    List<Order> getAllOrders(int customerId);
    void removeAllOrders();


}
