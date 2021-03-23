package com.javarush.tasks.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        int id=0;
        if (args.length==0) {
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        List<Product> products = new ArrayList<Product>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            while (fileReader.ready()) {
                Product product = getProduct(fileReader.readLine());
                products.add(product);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Product product : products) {
            if (product.id>id) {
                id=product.id;
            }
        }
        if (args[0].equals("-c")) {
            id += 1;
            Product newProduct=readProduct(args, id);
            products.add(newProduct);
        } else if (args[0].equals("-u")||args[0].equals("-d")) {
            String sId=args[1];
            if (sId.length()>8) {
                sId=sId.substring(0,8);
            }
            id=Integer.parseInt(sId);
            if (args[0].equals("-u")) {
                Product updateProduct = readProduct(args, id);
                updateList(products, updateProduct);
            } else {
                deleteList(products, id);
            }
        }

        try (FileWriter fw = new FileWriter(fileName)) {
            for (Product product : products) {
                fw.write(product.toString());
                fw.write("\n");
            }
        }
    }
    //deleting product with ID id from the list products
    public static void deleteList(List<Product> products, int id) {
        int index=-1;
        for (Product curItem : products) {
            if (curItem.id==id) {
                index=products.indexOf(curItem);
            }
        }
        products.remove(index);
    }
    //updating product forUpdate in products
    public static void updateList(List<Product> products, Product forUpdate) {
        int index=-1;
        for (Product curItem : products) {
            if (curItem.id==forUpdate.id) {
                index=products.indexOf(curItem);
            }
        }
        products.remove(index);
        products.add(index, forUpdate);
    }
    //reading product from cl args
    public static Product readProduct(String[] line, int newId) {
        String name;
        String price;
        String qty;
        int modifier;
        if (line.length==4) {
            modifier=0;
        } else {
            modifier=1;
        }
        name=line[1+modifier]+" ";
        if (name.length()>30) {
            name=name.substring(0,30);
        }
        price = line[2+modifier];
        if (price.length()>8) {
            price=price.substring(0, 8);
        }
        qty = line[3+modifier];
        if (qty.length()>4) {
            qty=qty.substring(0,4);
        }
        return new Product(newId, name, price, qty);
    }
    //creating new Product from cl
    public static Product getProduct(String product) {
        String id=product.substring(0,8).trim();
        String name=product.substring(8, 38).trim();
        String price=product.substring(38, 46).trim();
        String qty=product.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price, qty);
    }

    public static class Product {
        int id;
        String name, price, qty;
        public Product(int id, String name, String price, String qty) {
            this.id=id;
            this.name=name;
            this.price=price;
            this.qty=qty;
        }

        @Override
        public String toString(){
            return String.format("%-8d%-30s%-8s%-4s", id, name, price, qty);
        }
    }
}
