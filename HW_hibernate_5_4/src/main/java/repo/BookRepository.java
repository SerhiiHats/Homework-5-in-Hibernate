package repo;

import color.Color;
import models.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Random;

public class BookRepository {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger();

    public BookRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
//        logger.info(Color.RED + "отработал конструктор public BookRepository()" + Color.DEFAULT);
    }

    public Book add(Book book) {
        logger.info(Color.RED + "начал работу метод: add(Book book), Book = " + book + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "добавлен новый объект: " + book + Color.DEFAULT);
        session.close();
        return book;
    }

    public void add20BookEntityRnd() {
        logger.info(Color.RED + "начал работу метод: add20BookEntityRnd" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        String[] newName = {"Убить пересмешника", "Гордость и предубеждение", "Дневник Анны Франк",
                "1984", "Гарри Поттер и философский камень", "Властелин колец", "Великий Гэтсби",
                "Паутина Шарлотты", "Маленькие женщины", "Хоббит", "451° по Фаренгейту", "Джейн Эйр",
                "Унесенные ветром", "Скотный двор", "Над пропастью во ржи", "Приключения Гекльберри Финна",
                "Прислуга", "Гроздья гнева", "Лев, Колдунья и Платяной шкаф", "Голодные игры"};

        Random rnd = new Random();
        int countEntity = 0;
        session.beginTransaction();
        do {
            countEntity++;
            Book book = new Book();
            book.setName(newName[rnd.nextInt(20)]);
            book.setAuthorId(rnd.nextInt(200));
            session.save(book);
//            logger.info(Color.GREEN + "добавлен новый объект: " + countEntity + " " + book + Color.DEFAULT);
            if (countEntity % 10 == 0) {
                session.flush();
//                logger.info(Color.BIRUZOVII + "в методе add20BookEntityRnd произведено выполнение метода flush() на количестве объектов = " + countEntity + Color.DEFAULT);
            }
        } while (countEntity != 20);

        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "выход из метода: add20BookEntityRnd: статус транзакции: " + session.getTransaction().getStatus().name() + " - записано объектов = " + countEntity + Color.DEFAULT);
        session.close();
    }


    @SuppressWarnings("unchecked")
    public List<Book> getAll() {
        logger.info(Color.RED + "начал работу метод: getAll()" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        logger.info(Color.GREEN + "получена Session" + Color.DEFAULT);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        logger.info(Color.GREEN + "создана CriteriaBuilder" + Color.DEFAULT);
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        logger.info(Color.GREEN + "создана CriteriaQuery" + Color.DEFAULT);
        cq.from(Book.class);
        logger.info(Color.GREEN + "получена CriteriaQuery from" + Color.DEFAULT);
        Query query = session.createQuery(cq);
        logger.info(Color.GREEN + "выполнен запрос Query" + Color.DEFAULT);
        List<Book> authorList = query.getResultList();
        logger.info(Color.GREEN + "получен query.getResultList()" + Color.DEFAULT);
        session.close();
        logger.info(Color.GREEN + "Session ,была закрыта" + Color.DEFAULT);
        logger.info(Color.BIRUZOVII + "закончил работу метод: getAll()" + Color.DEFAULT);
        return authorList;
    }


    public Book getById(long id) {
        logger.info(Color.RED + "начал работу метод: getById (id= " + id + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        logger.info(Color.GREEN + "получен объект Book c id: " + id + " " + book + Color.DEFAULT);
        session.close();
        logger.info(Color.BIRUZOVII + "закончил работу метод: getById" + Color.DEFAULT);
        return book;
    }

    public void updateNameById(long id, String newName) {
        logger.info(Color.RED + "начал работу метод: updateNameById (id= " + id + ", newName= " + newName + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        logger.info(Color.GREEN + "получен объект Book c (id= " + id + ") " + book + Color.DEFAULT);
        String oldName = book.getName();
        book.setName(newName);
        logger.info(Color.GREEN + "объекту Book c (id= " + id + "), было заменено имя= " + oldName + ", на новое имя= " + newName + Color.DEFAULT);
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "выход из метода: updateNameById (id= " + id + ", newName= " + newName + "), статус транзакции: " + session.getTransaction().getStatus().name() + " - конечный объект = " + book + Color.DEFAULT);
        session.close();
    }

    public Book delete(Book book) {
        logger.info(Color.RED + "начал работу метод: delete(Book book) Book= " + book + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        session.close();
        logger.info(Color.BIRUZOVII + "выход из метода: delete(Book= " + book + "), статус транзакции: " + session.getTransaction().getStatus().name() + " был удален объект Book= " + book + Color.DEFAULT);
        session.close();
        return book;
    }

    public Book deleteById(long id) {
        logger.info(Color.RED + "начал работу метод: deleteById (id= " + id + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        logger.info(Color.GREEN + "получен объект Book c id: " + id + " " + book + Color.DEFAULT);
        session.beginTransaction();
        session.delete(book);
        logger.info(Color.GREEN + "удален объект Book(id= " + id + ") :" + Color.DEFAULT);
        session.getTransaction().commit();
        session.close();
        logger.info(Color.BIRUZOVII + "выход из метода: deleteById(long= " + id + "), статус транзакции: " + session.getTransaction().getStatus().name() + " был удален объект Book= " + book + Color.DEFAULT);
        return book;
    }
}
