package nl.hva.jeecourse.listener;

import nl.hva.jeecourse.model.State;
import nl.hva.jeecourse.model.User;
import nl.hva.jeecourse.repository.Repository;
import nl.hva.jeecourse.repository.impl.RepositoryImpl;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    private final static User[] USERS =
            {new User("admin@acme.com","administrator","admin123"),
             new User("root@acme.com","Root user","root123"),
             new User("devops@acme.com","devops","devops123")};

    private final static State[] STATES =
            {       new State("DR","Drenthe"),
                    new State("FL","Flevoland"),
                    new State("FR","Friesland"),
                    new State("GE","Gelderland"),
                    new State("GR","Groningen"),
                    new State("LI","Limburg"),
                    new State("NB","North Brabant"),
                    new State("NH","North Holland"),
                    new State("OV","Overijssel"),
                    new State("UT","Utrecht"),
                    new State("ZE","Zeeland"),
                    new State("ZH","South Holland")};

    @Inject
    private Repository repo;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        addDefaultUsers();
        addDefaultStates();

    }

    private void addDefaultUsers() {

        for(User user : USERS) {
            if(repo.getUser(user.getLogin()) == null) {
                repo.addUser(user);
            }
        }
    }

    private void addDefaultStates() {
        for(State state : STATES) {
            if(repo.getStateByAbbreviation(state.getAbbreviation()) == null) {
                repo.addState(state);
            }
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
