package Lesson_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PhoneBookDemo {
    private static final int MIN_VICTIMS_AMOUNT = 100;
    private static PhoneBook victimsList;

    public static void main(String[] args) {
        initVictimsList();
        Scanner cin = new Scanner(System.in);
        System.out.println("Введите фамилию для поиска телефонов:");
        if (cin.hasNext()) {
            String surname = cin.next();
            ArrayList<String> searchResults = victimsList.getPhonesBySurname(surname);
            if (searchResults.size() > 0) System.out.printf("Результаты поиска: %n%s%n", String.join(", ", searchResults));
            else System.out.printf("Ничего не найдено%n");
        }
        System.out.println("Введите фамилию для поиска e-mail'ов:");
        if (cin.hasNext()) {
            String surname = cin.next();
            ArrayList<String> searchResults = victimsList.getEMailsBySurname(surname);
            if (searchResults.size() > 0) System.out.printf("Результаты поиска: %n%s%n", String.join(", ", searchResults));
            else System.out.printf("Ничего не найдено%n");
        }
    }

    private static void initVictimsList() {
        victimsList = new PhoneBook(MIN_VICTIMS_AMOUNT);
        victimsList.addPerson(new Person("Абдулов Юрий Владимирович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Абдулов Петр Геннадьевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Абдулов Макар Захарович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Абнагимова Ирина Витальевна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Абнагимова Елена Даниловна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Абнагимова Мария Васильевна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Аввакумов Вадим Иванович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Аввакумов Григорий Александрович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Авдеев Александр Алексеевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Авдеев Петр Максимович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Александров Дмитрий Олегович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Александров Олег Анатольевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Александров Артем Владимирович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Ахметов Игорь Аркадьевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Ахметов Константин Алоизович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Бакинская Валентина Станиславовна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Блохин Леонид Якубович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Богданов Артур Исмаилович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Борисова Мария Андреевна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Борисова Антонина Алексеевна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Борисова Алевтина Никоноровна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Бочаров Сергей Михайлович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Бочаров Александр Михайлович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Гусаров Вячеслав Витальевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Гусаров Владимир Владимирович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Демидов Денис Леонтьевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Демидов Николай Владимирович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Демидов Марат Сергеевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Зеленова Любовь Григорьевна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Ильин Ильяс Витальевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Илюхин Федор Алексеевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Калинин Федор Якубович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Калуков Василий Павлович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Кожевников Вячеслав Дмитриевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Козлова Галина Михайловна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Петрова Марианна Александровна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Романов Олег Григорьевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Романов Даниил Александрович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Романов Михаил Дмитриевич", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Самойлова Глеб Рудольфович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Самойлова Вадим Рудольфович", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Самойлова Марина Степановна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Самойлова Ангелина Вячеславовна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Тимофеева Ирина Владимировна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
        victimsList.addPerson(new Person("Тимофеева Алина Марковна", RandValuesGenerator.getPhoneNumber(), RandValuesGenerator.getEMail()));
    }
}
