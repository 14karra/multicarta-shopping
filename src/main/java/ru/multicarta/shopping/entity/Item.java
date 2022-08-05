package ru.multicarta.shopping.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "ITEM_ID_SEQ", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_SEQ")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    @Column(name = "QUANTITY")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @Column(name = "INSERTION_DATE")
    @DateTimeFormat
    private OffsetDateTime insertionDate;

    @PrePersist
    private void prePersist() {
        insertionDate = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ITEM { ")
                .append("ID=").append(id)
                .append(", NAME=").append(name)
                .append(", DESCRIPTION=").append(description)
                .append(", AMOUNT=").append(amount)
                .append(", QUANTITY=").append(quantity)
                .append(", INSERTION_DATE=").append(insertionDate)
                .append(" }");
        return sb.toString();
    }
}
