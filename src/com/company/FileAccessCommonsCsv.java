package com.company;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileAccessCommonsCsv {

    private static final String filePath = "src/com/company/products2.csv";
    private static final String[] HEADERS = new String[]{"name", "price", "amount"};

    public void write(List<Product> products) {
        try {
            FileWriter writer = new FileWriter(filePath);

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader(HEADERS));

            for (var p :
                    products) {
                printer.printRecord(p.getName(), p.getPrice(), p.getAmount());
            }

            printer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> read() {

        try {
            Reader in = new FileReader(filePath);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(in);

            return StreamSupport.stream(records.spliterator(), false)
                    .map(r -> new Product(
                            r.get("name"),
                            Double.parseDouble(r.get("price")),
                            Integer.parseInt(r.get("amount"))))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
