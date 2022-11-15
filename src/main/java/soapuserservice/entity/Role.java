package soapuserservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    public Integer getId() {
        return id;
    }


//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private List<User> users;

//    @Transient
//    private boolean isNew;
//
//    @Override
//    public String getId() {
//        return name;
//    }
//
//    @Override
//    public boolean isNew() {
//        return isNew;
//    }
}
