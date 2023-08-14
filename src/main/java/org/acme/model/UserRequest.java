package org.acme.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.sql.Date;

/* Declaring UserRequest class.
 * The class is used in POST and PUT requests.
 * Firstname, lastname, email, birthday and password must be provided by user
 */
public class UserRequest {
    @NotBlank /* firstname must not be null and contain at least one non-space character
               * firstname must start with an uppercase letter and contain at least one lowercase letter
               * max length of 11 characters allowed
               */
    @Pattern(regexp = "^[A-Z][a-z]{1,10}$", message = "Firstname must only consist of letters")
    private String firstname;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]{1,10}$", message = "Lastname must only consist of letters")
    private String lastname;
    @Email                  //Email must be well-formed and unique
    @Column(unique = true)
    private String email;
    @Past                   //Birthday must be an instant date in the past
    private Date birthday;
    @NotBlank(message = "password may not be empty")
                            /* Password must contain at least one digit, one special character,
                             * a lower- and an uppercase letter and be 9 to 17 characters long */
    @Pattern(regexp = "^(?=.*\\d)(?=.*[@?!.,#$%_^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
            message = "A digit, a lower and an upper case letter and a special character must occur at least once")
    private String password;
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
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
