package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.User;
@ApplicationScoped /* Only one instance of the UserRepository class is created during lifetime of the application.
                    * Whenever it is required the same instance is injected and reused.
                    * The UserRepository class implements a PanacheRepository interface
                    * which provides basic database operations.
                    */
public class UserRepository implements PanacheRepository<User> {
                   /* Custom query for searching user by email.
                    * The email is passed as a parameter to Panache's find method
                    * First entry is extracted out of a PanacheQuery
                    */
    public User findByEmail(String email){
        return find("email",email).firstResult();
    }
}
