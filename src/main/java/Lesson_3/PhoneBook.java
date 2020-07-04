package Lesson_3;

import java.util.*;

//2. Написать простой класс PhoneBook(внутри использовать HashMap):
//   В качестве ключа использовать фамилию
//   В каждой записи всего два поля: phone, e-mail

public class PhoneBook {
    private HashSet<Person> persons;
    private HashMap<String, HashSet<Person>> personsBySurnames;
    private static final int LOAD_CAPACITY = 2;

    public PhoneBook(int initialCapacity) {
        persons = new HashSet<>(initialCapacity, LOAD_CAPACITY);
        personsBySurnames = new HashMap<>(initialCapacity, LOAD_CAPACITY);
    }

    public void addPerson(Person person) {
        if (persons.contains(person)) return;
        persons.add(person);

        String personKey = getPersonHashKey(person);
        HashSet<Person> personsByKey = personsBySurnames.get(personKey);
        if (!(personsByKey instanceof HashSet)) personsByKey = new HashSet<>();
        personsByKey.add(person);
        personsBySurnames.put(personKey, personsByKey);
    }

    public void removePerson(Person person) {
        if (!(persons.contains(person))) return;
        persons.remove(person);

        String personKey = getPersonHashKey(person);
        HashSet<Person> personsByKey = personsBySurnames.get(personKey);
        if (personsByKey instanceof HashSet) personsByKey.remove(person);
    }

    //3. Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов)
    public ArrayList<String> getPhonesBySurname(String surname) {
        ArrayList<String> funcResult = new ArrayList<>();
        HashSet<Person> personsByKey = getPersonsBySurname(surname);
        if (personsByKey instanceof HashSet)
            personsByKey.forEach((k) -> {
                funcResult.add(k.getPhoneNumber());
            });
        return funcResult;
    }

    //4. Отдельный метод для поиска e-mail по фамилии.
    public ArrayList<String> getEMailsBySurname(String surname) {
        ArrayList<String> funcResult = new ArrayList<>();
        HashSet<Person> personsByKey = getPersonsBySurname(surname);
        if (personsByKey instanceof HashSet)
            personsByKey.forEach((k) -> {
                funcResult.add(k.getEmail());
            });
        return funcResult;
    }

    private HashSet<Person> getPersonsBySurname(String surname) {
        String personKey = surname.trim().toUpperCase();
        return personsBySurnames.get(personKey);
    }

    private static String getPersonHashKey(Person person) {
        return person.getSurname().trim().toUpperCase();
    }
}