package com.company;

public class Main {

    public static void main(String[] args) {

        var fa = new FileAccessCommonsCsv();

        var result = fa.read();

        for (var product :
                result) {
            System.out.println(product);
        }

        result.add(new Product("Chicken", 3, 2));

        fa.write(result);
    }
}
