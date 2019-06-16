package application.MODEL;

public class Auther {
    private int id;
    private String name;
    private String lastName;
    private int year;

    public Auther(int id, String name, String lastName, int year) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
