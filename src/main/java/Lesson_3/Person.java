package Lesson_3;

public class Person {
    //2. Написать простой класс PhoneBook(внутри использовать HashMap):
    //   В качестве ключа использовать фамилию
    //   В каждой записи всего два поля: phone, e-mail
    //3. Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов)
    //4. Отдельный метод для поиска e-mail по фамилии.
    private String name;
    private String surname;
    private String patronymicName;
    private String phoneNumber;
    private String email;

    public Person(String fullName, String phoneNumber, String email) {
        String[] fullNameSplit = fullName.split(" ");
        if (fullNameSplit.length > 0) this.surname = fullNameSplit[0].trim();
        if (fullNameSplit.length > 1) this.name = fullNameSplit[1].trim();
        if (fullNameSplit.length > 2) this.patronymicName = fullNameSplit[2].trim();
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Person(String name, String surname, String patronymicName, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymicName = patronymicName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", surname, name, patronymicName);
    }
}
