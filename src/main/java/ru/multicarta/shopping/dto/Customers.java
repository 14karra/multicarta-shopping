package ru.multicarta.shopping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import ru.multicarta.shopping.entity.Customer;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public class Customers implements Serializable {

    private static final long serialVersionUID = 3639898896768313168L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Customer")
    private List<Customer> customers;

}
