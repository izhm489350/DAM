package com.mycompany.iofile;

import java.io.*;
import java.util.ArrayList;
public class FileHandler {
    private static final String data_folder = "data";
    private static final String users_file = data_folder + "/users_list.txt";
    private static final String users_folder = data_folder + "/users";
    private static final String items_file = data_folder + "/items.txt";
    private static final String carts_folder = data_folder + "/carts";
    
    
    
    public static void setupFiles() {
        try {
            new File(data_folder).mkdirs();
            new File(users_folder).mkdirs();
            new File(carts_folder).mkdirs();

            File usersFile = new File(users_file);
            if (!usersFile.exists()) {
                usersFile.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile));
                writer.write("matric,password,userType");
                writer.newLine();
                writer.close();
            }
            File itemsFile = new File(items_file);
            if (!itemsFile.exists()) {
                itemsFile.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(itemsFile));
                writer.write("itemId,sellerMatric,itemName,price,description,status");
                writer.newLine();
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Error setting up files: " + e.getMessage());
        }
    }
    public static boolean userExists(String matric) {
        setupFiles();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(users_file));
            String line;

            reader.readLine(); 

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 1 && data[0].equals(matric)) {
                    reader.close();
                    return true;
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }

        return false;
    }
    public static boolean saveUserCredentials(String matric, String password, String userType) {
        setupFiles();

        if (userExists(matric)) {
            return false;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(users_file, true));
            writer.write(matric + "," + password + "," + userType);
            writer.newLine();
            writer.close();
            return true;

        } catch (IOException e) {
            System.out.println("Error saving user credentials: " + e.getMessage());
            return false;
        }
    }
    public static boolean checkLogin(String matric, String password) {
        setupFiles();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(users_file));
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String savedMatric = data[0];
                    String savedPassword = data[1];
                    if (savedMatric.equals(matric) && savedPassword.equals(password)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error checking login: " + e.getMessage());
        }
        return false;
    }
    
    public static void createUserBiodata(String name, String matric, String password, String userType, String phone) {
        setupFiles();
        String filePath = users_folder + "/" + matric + "_biodata.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("name=" + name);
            writer.newLine();
            
            writer.write("matric=" + matric);
            writer.newLine();

            writer.write("password=" + password);
            writer.newLine();

            writer.write("userType=" + userType);
            writer.newLine();

            writer.write("phone=" + phone);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating biodata file: " + e.getMessage());
        }
    }
    public static boolean registerUser(String name, String matric, String password, String userType, String phone) {
    setupFiles();

    if (userExists(matric)) {
        return false;
    }

    boolean saved = saveUserCredentials(matric, password, userType);

    if (saved) {
        createUserBiodata(name, matric, password, userType, phone);
        return true;
    }

    return false;
}
    public static String getUserType(String matric) {
    setupFiles();

    try {
        BufferedReader reader = new BufferedReader(new FileReader(users_file));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length >= 3 && data[0].equals(matric)) {
                reader.close();
                return data[2];
            }
        }

        reader.close();

    } catch (IOException e) {
        System.out.println("Error getting user type: " + e.getMessage());
    }

    return "";
}
    public static void saveItem(String itemId, String sellerMatric, String itemName, double price, String description) {
        setupFiles();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(items_file, true));
            String status = "available";
            writer.write(itemId + "," + sellerMatric + "," + itemName + "," + price + "," + description + "," + status);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving item: " + e.getMessage());
        }
    }
    public static ArrayList<String> loadItems() {
        setupFiles();
        ArrayList<String> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(items_file));
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
        return items;
    }
    public static ArrayList<String> loadSellerItems(String sellerMatric) {
    setupFiles();

    ArrayList<String> sellerItems = new ArrayList<>();

    try {
        BufferedReader reader = new BufferedReader(new FileReader(items_file));
        String line;

        reader.readLine(); // skip header

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            if (data.length >= 2 && data[1].equals(sellerMatric)) {
                sellerItems.add(line);
            }
        }

        reader.close();

    } catch (IOException e) {
        System.out.println("Error loading seller items: " + e.getMessage());
    }

    return sellerItems;
}
    public static void saveCart(String buyerMatric, ArrayList<String> cartItems) {
        setupFiles();
        String cartFile = carts_folder + "/" + buyerMatric + "_cart.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(cartFile));
            writer.write("itemId,quantity");
            writer.newLine();
            for (String item : cartItems) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }
    public static ArrayList<String> loadCart(String buyerMatric) {
        setupFiles();
        ArrayList<String> cartItems = new ArrayList<>();
        String cartFile = carts_folder + "/" + buyerMatric + "_cart.txt";
        try {
            File file = new File(cartFile);
            if (!file.exists()) {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("itemId,quantity");
                writer.newLine();
                writer.close();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                cartItems.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading cart: " + e.getMessage());
        }
        return cartItems;
    }      
}
