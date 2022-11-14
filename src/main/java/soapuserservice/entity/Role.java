package soapuserservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Role {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

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
