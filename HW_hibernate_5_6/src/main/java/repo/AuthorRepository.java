package repo;

import models.Author;
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

    public AuthorRepository() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Author add(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public void add200EntityRnd() {
        Session session = sessionFactory.openSession();
        String[] newName = {"Анна", "София", "Виктория", "Дарья", "Анастасия", "Артем", "Александр",
                "Максим", "Дмитрий", "Матвей"};
        String[] newLastName = {"Мельник", "Шевченко", "Коваленко", "Бондаренко", "Бойко", "Ткаченко", "Кравченко",
                "Ковальчук", "Коваль", "Олийник"};
        Random rnd = new Random();
        session.beginTransaction();
        for (int i = 0; i < 200; i++) {
            Author author = new Author();
            author.setName(newName[rnd.nextInt(10)]);
            author.setLastName(newLastName[rnd.nextInt(10)]);
            int salary = rnd.nextInt(15000);
            author.setSalary(Math.max(salary, 6500));
            session.save(author);
            if (i % 10 == 0) {
                session.flush();
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        cq.from(Author.class);
        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
    }

    public Author getById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public void updateByIdName(long id, String newName) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        author.setName(newName);
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
    }

    public Author delete(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public Author deleteById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }
}
