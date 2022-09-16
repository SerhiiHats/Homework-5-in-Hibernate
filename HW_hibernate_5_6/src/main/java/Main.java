import models.Author;
import repo.AuthorRepository;


import java.util.List;

/**Домашнее задание 5.
 * Задание 6.
 * Из пакета ex_002_insert_and_update создайте в цикле 200 объектов author и сохраните в БД.
 * Значения полей могут быть любыми.
 * Используйте метод flush для каждых 10 объектов.
 * Метод сommit выполняйте один раз в конце.
 **/
public class Main {
    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();

        authorRepository.add200EntityRnd();

        List<Author> authorList = authorRepository.getAll();
        for (Author author : authorList) {
            System.out.println(author);
        }
    }
}
