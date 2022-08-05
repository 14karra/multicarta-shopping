package ru.multicarta.shopping.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @NotNull
    @Column(name = "ID")
    private UUID id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "BIRTHDAY")
    @DateTimeFormat
    private Date birthday;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CUSTOMER { ")
                .append("ID=").append(id)
                .append(", NAME=").append(name)
                .append(", LAST_NAME=").append(lastName)
                .append(", BIRTHDAY=").append(birthday)
                .append(", USERNAME=").append(username)
                .append(" }");
        return sb.toString();
    }
}
