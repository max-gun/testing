package com.enterprise.testings.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * @author max.gun
 * @since 12/09/2024
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("full_name")
    public String name;

    @JsonProperty("years")
    public int age;

    @JsonIgnore
    public String password;

    @JsonProperty("birthday-date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date birthDay;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String password, Date birthDay) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Person person = new Person("Denis", 27, "myPassword", new Date());

        // Serialization (Person -> JSON)
        System.out.println("Serialized:");
        String json = om.writeValueAsString(person);
        System.out.println(person);
        System.out.println(json);
        System.out.println("==========");

        // Deserialization (JSON -> Person)
        System.out.println("Deserialized:");
        Person mappedPerson = om.readValue(json, Person.class);
        System.out.println(mappedPerson);

        String manualJson = "{\"full_name\":\"Alexey\",\"years\":35,\"birthday-date\":\"2023-11-10\"}";
        Person manualPerson = om.readValue(manualJson, Person.class);
        System.out.println(manualPerson);
    }
}
