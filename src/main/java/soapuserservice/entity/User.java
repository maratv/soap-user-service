package soapuserservice.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_login", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + "*****" + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<Role>();
        }
        return this.roles;
    }
}
