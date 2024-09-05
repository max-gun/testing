package com.enterprise.testings.util;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author max.gun
 * @since 05/09/2024
 */
public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // one way to call a class blueprint - directly from class
        Class<?> clazz1 = Student.class;

        // second way via instance
        Student moshe = new Student();
        Class<?> clazz2 = moshe.getClass();

        // third way directly via name path
        Class<?> clazz3 = Class.forName("com.enterprise.testings.util.Student");

        System.out.println("Class Name: " + clazz1.getName());
        System.out.println("Class Name: " + clazz3.getName());

        Class<?> parent = clazz1.getSuperclass().getSuperclass();
        System.out.println("Students parents: " + parent.getName());

        Class<?>[] interfaces = clazz1.getInterfaces();
        System.out.println("Student interfaces: ");
        Arrays.stream(interfaces)
                .map(Class::getName)
                .forEach(System.out::println);

        Field[] fields = clazz2.getDeclaredFields();
        System.out.println("Students Fields: ");
        Arrays.stream(fields)
                .map(Field::getName)
                .forEach(System.out::println);

        Field heightField = clazz2.getDeclaredField("height");
        System.out.println("======");
        heightField.setAccessible(true);
        Object mosheHeight = heightField.get(moshe);

        System.out.println("mosheHeight: " + mosheHeight);



        System.out.println("Bazinga!");
    }
}

interface Caucasian {

}

interface Geek {

}

class Person {
    public int age;
    public String name;

    public Person() {
        this.name = "Bruria";
        this.age = 20;
    }
}

class Student extends Person implements Caucasian, Geek{
    private int height;
    public String type;

    public Student() {
        this.height = 182;
        this.type = "Type C";
    }

    public void doSomething() {
        System.out.println("I'm doing something");
    }

    public void doThis(String something) {
        System.out.println("I'm doing " + something);
    }
}

class Sophomore extends Student {

}
