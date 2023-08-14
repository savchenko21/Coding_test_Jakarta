package org.acme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.sql.Date;

/* Declaring an entity class User.
 * The class gets mapped by Hibernate to a database table named 'users'.
 * Aditionally to properties of the UserRequest class, the User class has an id and a salt
 * which are generated programmatically and are not set by client
 */
@Entity
@Table(name = "users")
public class User {
    @Id                                                 //specfying id as a primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO)     /*hibernate selects a generation strategy
                                                         *based on database specific dialect (h2)*/
    private Long id;
    @NotBlank                      //firstname must be not null and contain at least one non-space character
    private String firstname;
    @NotBlank                      //lastname must be not null and contain at least one non-space character
    private String lastname;
    @Email @Column(unique = true)  //Email must be well-formed and unique
    private String email;
    @Past                          //Birthday must be an instant date in the past
    private Date birthday;
    @NotBlank
    private String salt;
    @NotBlank(message = "password may not be empty")
    private String password;

    public User(){
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    public String getSalt(){
        return salt;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
