package ru.multicarta.shopping.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @PositiveOrZero
    private Integer count;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    @Column(name = "PURCHASE_DATE")
    @DateTimeFormat
    private LocalDate purchaseDate;

    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private Item item;

    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private Customer customer;

    @PrePersist
    private void prePersist() {
        purchaseDate = LocalDate.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PURCHASE { ")
                .append("ID=").append(id)
                .append(", COUNT=").append(count)
                .append(", AMOUNT=").append(amount)
                .append(", PURCHASE_DATE=").append(purchaseDate)
                .append(", ITEM=").append(item == null ? null : item.getName())
                .append(", CUSTOMER=").append(customer == null ? null : customer.getName() + " " + customer.getLastName())
                .append(" }");
        return sb.toString();
    }
}
