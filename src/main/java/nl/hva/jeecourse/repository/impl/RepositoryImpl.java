package nl.hva.jeecourse.repository.impl;

import nl.hva.jeecourse.model.Customer;
import nl.hva.jeecourse.model.Order;
import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.model.User;
import nl.hva.jeecourse.repository.Repository;

import javax.inject.Singleton;
import javax.persistence.*;
import java.util.List;

@Singleton
public class RepositoryImpl implements Repository {

    private EntityManagerFactory entityManagerFactory;

    public RepositoryImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("labpu");
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public State addState(State state) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(state);
        em.getTransaction().commit();

        em.close();

        return state;
    }

    @Override
    public List<State> getAllStates() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<State> states =
                em.createQuery("SELECT s FROM State s").getResultList();

        em.close();

        return states;
    }

    @Override
    public State getStateByAbbreviation(String abbrev) {
        EntityManager em = getEntityManager();

        Query query = em.createQuery(
                "SELECT s FROM State s WHERE s.abbreviation = :abbrev");

        query.setParameter("abbrev",abbrev);

        State state = (State) query.getResultList().get(0);

        em.close();

        return state;
    }

    @Override
    public Customer addCustomer(Customer cust) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();

        em.close();

        return cust;
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Customer> customers =
                em.createQuery("SELECT c FROM Customer c").getResultList();

        em.close();

        return customers;

    }

    @Override
    public Customer getCustomerById(int id) {

        EntityManager em = getEntityManager();

        Customer c = em.find(Customer.class,id);

        em.close();

        return c;
    }

    @Override
    public Customer removeCustomer(int id) {
        EntityManager em = getEntityManager();

        Customer c = em.find(Customer.class,id);

        if(c == null) {
            return null;
        }

        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();

        em.close();

        return c;
    }

    @Override
    public void modifyCustomer(Customer cust) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.merge(cust);
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public List<Customer> getPagedCustomers(int pageNumber, int pageSize) {

        EntityManager em = getEntityManager();

        Query query = em.createQuery("SELECT c from Customer c");
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        List <Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public void removeAllCustomers() {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Customer").executeUpdate();
        em.getTransaction().commit();

        em.close();

    }

    @Override
    public User addUser(User user) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();

        return user;

    }

    @Override
    public User removeUser(String login) {
        EntityManager em = getEntityManager();

        User u = em.find(User.class,login);

        if(u == null) {
            return null;
        }

        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();

        em.close();

        return u;

    }

    @Override
    public User getUser(String login) {
        EntityManager em = getEntityManager();

        User u = em.find(User.class,login);

        return u;

    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<User> users =
                em.createQuery("SELECT u FROM User u").getResultList();

        em.close();

        return users;
    }

    @Override
    public Order getOrderById(int id) {
        EntityManager em = getEntityManager();

        Order o = em.find(Order.class,id);

        return o;
    }

    @Override
    public Order addOrder(Order order) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        em.close();

        return order;
    }

    @Override
    public Order removeOrder(int id) {
        EntityManager em = getEntityManager();

        Order o = em.find(Order.class,id);

        if(o == null) {
            return null;
        }

        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();

        em.close();

        return o;

    }

    @Override
    public List<Order> getAllOrders(int customerId) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Query query = getEntityManager().createQuery
                ("SELECT o FROM Order o WHERE o.customer.id = :customerId");
        query.setParameter("customerId",customerId);

        List<Order> orders = query.getResultList();

        em.close();

        return orders;

    }


}
