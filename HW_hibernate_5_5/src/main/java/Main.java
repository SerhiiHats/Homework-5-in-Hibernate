import color.Color;
import models.BookAuthor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AuthorRepository;
import repo.BookRepository;

import java.util.List;

/**
 * Домашнее задание 5
 * Задание 5*
 * К дополнительному заданию
 * В классе BookHelper создать метод, который получает название книг и имя автора.
 **/

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("начал работу метод Main");
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();

        authorRepository.add200EntityRnd();

        bookRepository.add20BookEntityRnd();

        List<BookAuthor> bookAuthorList = bookRepository.getAllBookAuthor();
        logger.info(Color.RED + "начало цикла по выводу названия книг и автора" + Color.DEFAULT);
        for (BookAuthor bookAuthor : bookAuthorList) {
            logger.info(bookAuthor);
        }
        logger.info(Color.BIRUZOVII + "выход из цикла по выводу названия книг и автора" + Color.DEFAULT);

        logger.info("метод Main  завершил работу");
    }
}
