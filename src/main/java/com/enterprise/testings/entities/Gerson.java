package com.enterprise.testings.entities;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author max.gun
 * @since 12/09/2024
 */
@Entity
public class Gerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("full_name")
    @Expose
    public String name;

    @SerializedName("years")
    @Expose
    public int age;

    @Expose(serialize = false, deserialize = false)
    public String password;

    @JsonAdapter(AgeAdapter.class)
    @Expose
    public int birthDay;

    public Gerson() {
    }

    public Gerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Gerson(String name, int age, String password, int birthDay) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Gerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }

    public static void main(String[] args) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Gerson gerson = new Gerson("Viole", 19, "12345",22);

        // Serialization (Gerson -> JSON)
        System.out.println("Serialized:");
        System.out.println(gerson);
        String json = gson.toJson(gerson);
        System.out.println(json);

        System.out.println("==========");
        // Deserialization (JSON -> Gerson)
        System.out.println("Deserialized:");
        Gerson mappedGerson = gson.fromJson(json, Gerson.class);
        Person mappedPerson = gson.fromJson(json, Person.class);
        System.out.println(mappedGerson);
        System.out.println(mappedPerson);
    }
}

class AgeAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

    @Override
    public JsonElement serialize(Integer years, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(years + " Years");
    }

    @Override
    public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String ageString = jsonElement.getAsString();
        return Integer.parseInt(ageString.replace(" Years", ""));
    }
}
