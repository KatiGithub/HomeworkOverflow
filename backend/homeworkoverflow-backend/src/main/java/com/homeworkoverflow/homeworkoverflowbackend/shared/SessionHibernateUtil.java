package com.homeworkoverflow.homeworkoverflowbackend.shared;

import org.hibernate.Session;

public class SessionHibernateUtil {
    private Session session;

    public SessionHibernateUtil() {
        session = SessionFactoryHibernateUtil.getSessionFactory().openSession();
    }

    public Session getSession() {
        return session;
    }
}
