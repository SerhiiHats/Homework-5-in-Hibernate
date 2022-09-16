import color.Color;
import models.Author;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AuthorRepository;


import java.util.List;

/**
 * Домашнее задание 5.
 * Задание 2.
 * Настроить логирование для проекта с дополнительным заданием.
 * <p>
 * Доп.задание
 * Из пакета ex_002_insert_and_update создайте в цикле 200 объектов author и сохраните в БД.
 * Значения полей могут быть любыми.
 * Используйте метод flush для каждых 10 объектов.
 * Метод сommit выполняйте один раз в конце.
 **/

public class Main {
    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        logger.info(Color.RED + "Обращение к методу: authorRepository.add200EntityRnd() " + Color.DEFAULT);
        authorRepository.add200EntityRnd();

        logger.info(Color.RED + "Обращение к методу: authorRepository.getAll() " + Color.DEFAULT);
        List<Author> authorList = authorRepository.getAll();

        logger.info(Color.RED + "Начало цикла форейч: for (Author author : authorList)" + Color.DEFAULT);
        for (Author author : authorList) {
            logger.info(Color.GREEN + "получен объект " + author + Color.DEFAULT);
        }
        logger.info(Color.BIRUZOVII + "Конец цикла форейч: for (Author author : authorList)" + Color.DEFAULT);


    }


}
