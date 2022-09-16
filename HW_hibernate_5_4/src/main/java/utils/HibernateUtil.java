package utils;

import color.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
//            logger.info(Color.RED + "инициализирована sessionFactory" + Color.DEFAULT);
        } catch (HibernateException e) {
            logger.info(e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
//        logger.info(Color.GREEN + "запущен метод getSessionFactory()" + Color.DEFAULT);
        return sessionFactory;
    }
}

