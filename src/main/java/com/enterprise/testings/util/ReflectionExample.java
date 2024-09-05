package com.enterprise.testings.util;

import java.util.Arrays;

/**
 * @author max.gun
 * @since 05/09/2024
 */
public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException {
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
        System.out.println("Students interfaces: ");
        Arrays.stream(interfaces)
                .map(Class::getName)
                .forEach(System.out::println);

        System.out.println("Bazinga!");
    }
}

interface Caucasian {

}

interface Geek {

}

class Person {
    int age;

    public Person() {
        this.age = 20;
    }
}

class Student extends Person implements Caucasian, Geek{
    int height;

    public Student() {
        this.height = 182;
    }
}

class Sophomore extends Student {

}
