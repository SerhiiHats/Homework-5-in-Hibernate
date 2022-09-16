package repo;

import color.Color;
import models.Author;
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

public class AuthorRepository {
    private static SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger();

    public AuthorRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
//        logger.info(Color.RED + "отработал конструктор public AuthorRepository()" + Color.DEFAULT);
    }


    public Author add(Author author) {
        logger.info(Color.RED + "начал работу метод: add(Author author), Author = " + author + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "выход из метода: add(Author author): статус транзакции: " + session.getTransaction().getStatus().name() + " - добавлен новый объект: " + author + Color.DEFAULT);
        session.close();
        return author;
    }

    public void add200EntityRnd() {
        logger.info(Color.RED + "начал работу метод: add200EntityRnd()" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        String[] newName = {"Анна", "София", "Виктория", "Дарья", "Анастасия", "Артем", "Александр",
                "Максим", "Дмитрий", "Матвей"};
        String[] newLastName = {"Мельник", "Шевченко", "Коваленко", "Бондаренко", "Бойко", "Ткаченко", "Кравченко",
                "Ковальчук", "Коваль", "Олийник"};
        Random rnd = new Random();
        int countEntity = 0;
        session.beginTransaction();
        do {
            countEntity++;
            Author author = new Author();
            author.setName(newName[rnd.nextInt(10)]);
            author.setLastName(newLastName[rnd.nextInt(10)]);
            int salary = rnd.nextInt(15000);
            author.setSalary(Math.max(salary, 6500));
            session.save(author);
//            logger.info(Color.GREEN + "добавлен новый объект: " + countEntity + " " + author + Color.DEFAULT);
            if (countEntity % 10 == 0) {
                session.flush();
//                logger.info(Color.BIRUZOVII + "в методе add200EntityRnd произведено выполнение метода flush() на количестве объектов = " + countEntity + Color.DEFAULT);
            }
        } while (countEntity != 200);

        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "выход из метода: add200EntityRnd: статус транзакции: " + session.getTransaction().getStatus().name() + " - записано объектов = " + countEntity + Color.DEFAULT);
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAll() {
        logger.info(Color.RED + "начал работу метод: getAll()" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        logger.info(Color.GREEN + "получена Session" + Color.DEFAULT);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        logger.info(Color.GREEN + "создана CriteriaBuilder" + Color.DEFAULT);
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        logger.info(Color.GREEN + "создана CriteriaQuery" + Color.DEFAULT);
        cq.from(Author.class);
        logger.info(Color.GREEN + "получена CriteriaQuery from" + Color.DEFAULT);
        Query query = session.createQuery(cq);
        logger.info(Color.GREEN + "выполнен запрос Query" + Color.DEFAULT);
        List<Author> authorList = query.getResultList();
        logger.info(Color.GREEN + "получен query.getResultList()" + Color.DEFAULT);
        session.close();
        logger.info(Color.GREEN + "Session ,была закрыта" + Color.DEFAULT);
        logger.info(Color.BIRUZOVII + "выход из метода: getAll()" + Color.DEFAULT);
        return authorList;
    }

    public Author getById(long id) {
        logger.info(Color.RED + "начал работу метод: getById (id= " + id + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        logger.info(Color.GREEN + "получен объект Author c id: " + id + " " + author + Color.DEFAULT);
        session.close();
        logger.info(Color.BIRUZOVII + "закончил работу метод: getById" + Color.DEFAULT);
        return author;
    }

    public void updateNameById(long id, String newName) {
        logger.info(Color.RED + "начал работу метод: updateNameById(id= " + id + ", newName= " + newName + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        logger.info(Color.GREEN + "получен объект Author c (id= " + id + ") " + author + Color.DEFAULT);
        String oldName = author.getName();
        author.setName(newName);
        logger.info(Color.GREEN + "объекту Author c (id= " + id + "), было заменено имя= " + oldName + ", на новое имя= " + newName + Color.DEFAULT);
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        logger.info(Color.BIRUZOVII + "выход из метода: updateNameById (id= " + id + ", newName= " + newName + "), статус транзакции: " + session.getTransaction().getStatus().name() + " - конечный объект = " + author + Color.DEFAULT);
        session.close();
    }

    public Author delete(Author author) {
        logger.info(Color.RED + "начал работу метод: delete(Author= " + author + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
        session.close();
        logger.info(Color.BIRUZOVII + "выход из метода: delete(Author= " + author + "), статус транзакции: " + session.getTransaction().getStatus().name() + " был удален объект Author= " + author + Color.DEFAULT);
        session.close();
        return author;
    }

    public Author deleteById(long id) {
        logger.info(Color.RED + "начал работу метод: deleteById (id= " + id + ")" + Color.DEFAULT);
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        logger.info(Color.GREEN + "получен объект Author(id= " + id + ") :" + author + Color.DEFAULT);
        session.beginTransaction();
        session.delete(author);
        logger.info(Color.GREEN + "удален объект Author(id= " + id + ") :" + Color.DEFAULT);
        session.getTransaction().commit();
        session.close();
        logger.info(Color.BIRUZOVII + "выход из метода: deleteById(long= " + id + "), статус транзакции: " + session.getTransaction().getStatus().name() + " был удален объект Author= " + author + Color.DEFAULT);
        return author;
    }
}
