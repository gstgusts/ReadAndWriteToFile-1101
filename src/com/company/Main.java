package com.company;

public class Main {

    public static void main(String[] args) {
	  var fa = new FileAccessManager();

	  var result = fa.read();

//        for (var line :
//                result) {
//            System.out.println(line);
//        }

        result.add(new Product("Banana",1.2, 56));

        fa.write(result);
    }
}
