public class Student{
    private String name;
    private int matricNumber;
    private int age;
    private String sex;
    private String address;
    private double balance;
    
    public Student() {
        this.name = "no name";
        this.matricNumber = 0;
        this.age = 0;
        this.sex = "no sex";
        this.address = "no address";
        this.balance = 0;
    }

    public Student(String name, int matricNumber, int age, String sex, String address, double balance) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatricNumber() {
        return this.matricNumber;
    }

    public void setMatricNumber(int matricNumber) {
        this.matricNumber = matricNumber;
    }
    
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Name: " + this.name + "\nMatric Number: " 
                + this.matricNumber + "\nAge: " + this.age 
                + "\nSex: " + this.sex + "\nAddress: " 
                + this.address + "\nBalance: RM" + this.balance;
    }
}

// Name: Hamza 
// Matric Number: 2517277