package nl.hva.jeecourse.repository;

import nl.hva.jeecourse.model.Customer;
import nl.hva.jeecourse.model.Order;
import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.model.User;
import nl.hva.jeecourse.repository.impl.RepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestRepository {

    private Repository repo = new RepositoryImpl();

    @Test
    public void testAddingState() {

        State state = new State();
        state.setAbbreviation("FR");
        state.setName("Friesland");
        state  = repo.addState(state);

        State recovered = repo.getStateByAbbreviation(state.getAbbreviation());
        assertTrue(recovered != null);
        assertEquals(recovered.getAbbreviation(),state.getAbbreviation());
        assertTrue(state.getId() > 0);
    }

    @Test
    public void testListingStates() {

        State state = new State();
        state.setAbbreviation("ZH");
        state.setName("Zoud-Holland");
        state  = repo.addState(state);

        List<State> states = repo.getAllStates();
        assertTrue(states != null && states.size() >= 1);
    }

    @Test
    public void testAddingCustomer() {

        State state = new State();
        state.setAbbreviation("AZ");
        state.setName("Arizona");
        state  = repo.addState(state);

        State recovered = repo.getStateByAbbreviation("AZ");

        Customer c = new Customer();
        c.setFirstName("John");
        c.setLastName("Martin");
        c.setAddress("Lindenlaan, 34");
        c.setCity("Zaandijk");
        c.setGender("male");
        c.setLatitude(33299);
        c.setLongitude(-111963);
        c.setState(recovered);

        c = repo.addCustomer(c);

        Customer custRec = repo.getCustomerById(c.getId());

        assertEquals(custRec,c);
        assertEquals(custRec.getState(),c.getState());

    }

    @Test
    public void testListingCustomers() {
        testAddingCustomer();

        List<Customer> custs = repo.getAllCustomers();

        assertTrue(custs.size()>0);
    }

    @Test
    public void testRemoveCustomer() {

        State state = new State();
        state.setAbbreviation("CA");
        state.setName("California");
        state  = repo.addState(state);

        State recovered = repo.getStateByAbbreviation("AZ");

        Customer c = new Customer();
        c.setFirstName("Maria");
        c.setLastName("Marian");
        c.setAddress("Alf St, 34");
        c.setCity("San Francisco");
        c.setGender("female");
        c.setLatitude(33299);
        c.setLongitude(-111963);
        c.setState(recovered);

        c = repo.addCustomer(c);

        repo.removeCustomer(c.getId());

        Customer after = repo.getCustomerById(c.getId());

        assertNull(after);


    }

    @Test
    public void testRemoveInexistentCustomer() {
        Customer c = repo.removeCustomer(-1);

        assertNull(c);

    }

    @Test
    public void testModifyCustomer() {

        State state = new State();
        state.setAbbreviation("TX");
        state.setName("Texas");
        state  = repo.addState(state);

        State recovered = repo.getStateByAbbreviation("AZ");

        Customer c = new Customer();
        c.setFirstName("Alton");
        c.setLastName("Salton");
        c.setAddress("Alf St, 34");
        c.setCity("Houston");
        c.setGender("male");
        c.setLatitude(33299);
        c.setLongitude(-111963);
        c.setState(recovered);

        c = repo.addCustomer(c);

        c.setAddress("Elm Street");
        c.setGender("female");

        state = new State();
        state.setAbbreviation("WA");
        state.setName("Washington");
        state  = repo.addState(state);

        State stateRec = repo.getStateByAbbreviation("WA");

        c.setState(stateRec);

        repo.modifyCustomer(c);

        Customer after = repo.getCustomerById(c.getId());

        assertEquals(after.getAddress(),"Elm Street");
        assertEquals(after.getGender(),"female");
        assertEquals(after.getState(),stateRec);

    }


    @Test
    public void testPaged() {

        repo.removeAllCustomers();

        for(int i=0;i<13;i++) {
            testAddingCustomer();
        }

        List<Customer> customers = repo.getPagedCustomers(1,5);

        assertEquals(customers.size(),5);

        customers = repo.getPagedCustomers(2,5);

        assertEquals(customers.size(),5);

        customers = repo.getPagedCustomers(3,5);

        assertEquals(customers.size(),3);

        customers = repo.getPagedCustomers(10,5);

        assertEquals(customers.size(),0);
    }

    @Test
    public void testAddingUser() {

        User u = new User();

        u.setLogin("mfk");
        u.setName("Marcio Fuckner");
        u.setPassword("123");

        repo.addUser(u);

        User recovered = repo.getUser(u.getLogin());

        assertEquals(recovered.getName(),u.getName());
        assertEquals(recovered.getPassword(),u.getPassword());

        repo.removeUser("mfk");


    }

    @Test
    public void testRemovingUser() {

        User u = new User();

        u.setLogin("jack");
        u.setName("Jack Koko");
        u.setPassword("123");

        repo.addUser(u);

        User reco = repo.removeUser("jack");

        assertEquals(reco.getLogin(),u.getLogin());

        reco = repo.removeUser("jack");

        assertNull(reco);

        reco = repo.getUser("jack");

        assertNull(reco);


    }

    @Test
    public void testGettingUsers() {
        User u = new User();

        u.setLogin("jack");
        u.setName("Jack Koko");
        u.setPassword("123");

        repo.addUser(u);

        List<User> users = repo.getAllUsers();

        assertTrue(users.size() >= 1);

        repo.removeUser(u.getLogin());

    }

    @Test
    public void testOrder() {

        State state = new State();
        state.setAbbreviation("IL");
        state.setName("Illinois");
        state  = repo.addState(state);

        State recovered = repo.getStateByAbbreviation("IL");

        Customer c = new Customer();
        c.setFirstName("Mark");
        c.setLastName("Walton");
        c.setAddress("Bix St, 34");
        c.setCity("Springfield");
        c.setGender("male");
        c.setLatitude(33299);
        c.setLongitude(-111963);
        c.setState(recovered);

        c = repo.addCustomer(c);

        Order o1 = new Order();
        o1.setCustomer(c);
        o1.setProductName("iPhone");
        o1.setItemCost(100);

        repo.addOrder(o1);

        Order oRec = repo.getOrderById(o1.getId());

        assertEquals(oRec.getId(),o1.getId());
        assertEquals(oRec.getCustomer(),o1.getCustomer());
        assertEquals(oRec.getProductName(),o1.getProductName());
        assertEquals(oRec.getItemCost(),o1.getItemCost());

        List<Order> orders = repo.getAllOrders(c.getId());

        assertNotNull(orders);
        assertTrue(orders.size() > 0);

        Order o = repo.removeOrder(oRec.getId());

        assertEquals(o,oRec);

        Order s = repo.getOrderById(o.getId());

        assertNull(s);

    }




}
