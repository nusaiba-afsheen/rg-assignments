package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Q2. Lambda expressions");
//        ArrayList<Integer> numbers = new ArrayList<Integer>();
//        numbers.add(5);
//        numbers.add(9);
//        numbers.add(8);
//        numbers.add(1);
//        numbers.forEach( (n) -> { System.out.println(n); } );

//        System.out.println("Q3. Optional");
//        String[] words = new String[10];
//
//        Optional<String> checkNull = Optional.ofNullable(words[5]);
//
//        if (checkNull.isPresent()) {
//            String word = words[5].toLowerCase();
//            System.out.print(word);
//        }
//        else
//            System.out.println("word is null");

//        System.out.println("Q4. Date Time API");
//        LocalDate today = LocalDate.now();
//        System.out.println("Today: " + today);
//
//        LocalTime now = LocalTime.now();
//        System.out.println("Current time: " + now);
//
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        System.out.println("Current date and time: " + currentDateTime);


//        List< String > colors = Arrays.asList("RED", "grEEn", "white", "Orange", "pink");
//        System.out.println("List of strings: " + colors);
//        // Convert strings to uppercase using streams
//        List < String > uppercaseStrings = colors.stream()
//                .map(String::toUpperCase)
//                .collect(Collectors.toList());
//        System.out.println("\nUppercase Strings: " + uppercaseStrings);

        // Using a lambda expression
        Function<String, Integer> lambda = s -> Integer.parseInt(s);
        System.out.println("Lambda: " + lambda.apply("123"));

        // Using a method reference
        Function<String, Integer> methodRef = Integer::parseInt;
        System.out.println("Method Reference: " + methodRef.apply("123"));
    }
}