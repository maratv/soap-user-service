package soapuserservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
// @Table(name = "users")
@Table(name = "users")
public class User { //} implements Persistable<String> {

    //   @Id
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
