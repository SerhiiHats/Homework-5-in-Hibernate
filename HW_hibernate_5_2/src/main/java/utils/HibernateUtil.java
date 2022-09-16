package utils;

import color.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            logger.info(Color.RED + "обращение к классу HibernateUtil static блок начало: buildSessionFactory()" + Color.DEFAULT);
            sessionFactory = new Configuration().configure().buildSessionFactory();
            logger.info(Color.GREEN + " получен и инициализирован sessionFactory" + Color.DEFAULT);

        } catch (HibernateException e) {
            logger.info(e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
        logger.info(Color.GREEN + "обращение к методу getSessionFactory()" + Color.DEFAULT);
        return sessionFactory;
    }
}
