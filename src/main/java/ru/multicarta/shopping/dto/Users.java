package ru.multicarta.shopping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import ru.multicarta.shopping.entity.User;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 3639898896768313169L;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("User")
    private List<User> users;

}
