//  Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
//  Создать множество ноутбуков.
//  Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
//  отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому
//  критерию:
//  1 - ОЗУ
//  2 - Объем ЖД
//  3 - Операционная система
//  4 - Цвет
//  Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
//  Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Set<NoteBook> filterNoteBook(Set<NoteBook> noteBooks, Map<String, String> criteria) {
        Set<NoteBook> filteredNoteBooks = new HashSet<>();
        for (NoteBook noteBook : noteBooks) {
            boolean criteriaMatch = true;
            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (noteBook.getRam() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "hdd":
                        if (noteBook.getHdd() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "os":
                        if (!noteBook.getOs().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "color":
                        if (!noteBook.getColor().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                }
                if (!criteriaMatch) {
                    break;
                }
            }
            if (criteriaMatch) {
                filteredNoteBooks.add(noteBook);
            }
        }
        return filteredNoteBooks;
    }

    public static Map<String, String> queryCriteria() {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, интересующего параметра:");
        System.out.println("1 - ОЗУ (4-16)");
        System.out.println("2 - Объем ЖД (128-1024)");
        System.out.println("3 - Операционная система (Linux, MacOS, Windows)");
        System.out.println("4 - Цвет (black, white, grey)");

        int criteria = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите минимальные значения для указанного параметра, или цвет, или название ОС:");
        String minValue = scanner.nextLine();
        scanner.close();

        switch (criteria) {
            case 1:
                filterCriteria.put("ram", minValue);
                break;
            case 2:
                filterCriteria.put("hdd", minValue);
                break;
            case 3:
                filterCriteria.put("os", minValue);
                break;
            case 4:
                filterCriteria.put("color", minValue);
                break;
        }

        return filterCriteria;
    }

    public static void main(String[] args) {
        Set<NoteBook> noteBooks = new HashSet<>();

        noteBooks.add(new NoteBook(4, 128, "Linux", "black"));
        noteBooks.add(new NoteBook(8, 256, "Linux", "white"));
        noteBooks.add(new NoteBook(16, 512, "Linux", "grey"));
        noteBooks.add(new NoteBook(4, 128, "Windows", "white"));
        noteBooks.add(new NoteBook(8, 256, "Windows", "grey"));
        noteBooks.add(new NoteBook(16, 512, "Windows", "black"));
        noteBooks.add(new NoteBook(4, 128, "MacOS", "grey"));
        noteBooks.add(new NoteBook(8, 256, "MacOS", "black"));
        noteBooks.add(new NoteBook(16, 512, "MacOS", "white"));

        System.out.println("Количество ноутбуков: " + noteBooks.size());

        var filterCriteria = queryCriteria();
        var filteredNoteBook = filterNoteBook(noteBooks, filterCriteria);
        if (filteredNoteBook.size() == 0)
        {
            System.out.println("Нет ноутбуков, соответствующих искомому параметру");
        }
        else for (NoteBook notebook : filteredNoteBook) {
            System.out.println(notebook);
        }
    }
}