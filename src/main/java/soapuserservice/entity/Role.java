package soapuserservice.entity;


import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Setter
@NoArgsConstructor
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

}
