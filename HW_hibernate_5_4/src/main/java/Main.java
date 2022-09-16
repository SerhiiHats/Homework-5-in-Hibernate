import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AuthorRepository;
import repo.BookRepository;

/**
 * Домашнее задание 5
 * Задание 4
 * К дополнительному заданию добавить метод обновления имени автора по id.
 * (То, что было на уроке, только реализовать это правильно).
 * Аналогично сделать и в классе BookHelper с предыдущего ДЗ.
 **/

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("начало работы main");
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();

        authorRepository.add200EntityRnd();

        bookRepository.add20BookEntityRnd();

        authorRepository.updateNameById(117, "Bасилий");

        bookRepository.updateNameById(17, "Бегущий за ветром");
        logger.info("конец работы main");
    }
}
