package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileAccessManager {

    private static final String filePath = "src/com/company/products.csv";

    public List<Product> read() {
        Path path = Paths.get(filePath);

        try {
            var lines = Files.readAllLines(path);

            return getProducts(lines);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private List<Product> getProducts(List<String> lines) {
        List<Product> products = new ArrayList<>();

        for (var line :
                lines) {
            var columns = line.split(",");

            var name = columns[0];
            var price = Double.parseDouble(columns[1]);
            var amount = Integer.parseInt(columns[2]);

            var p = new Product(name, price, amount);

            products.add(p);
        }

        return products;
    }

    public void write(List<Product> products) {

        try {
            var bw = new BufferedWriter(new FileWriter(filePath, false));

            for (var item :
                    products) {

                bw.write(item.getProductLine());
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
