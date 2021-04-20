package models;

public class MyFile_Model {

    private String name;
    private int age;
    private String[] hobbies;

    public void MyFile_model(String name, int age, String[] hobbies)
    {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
}