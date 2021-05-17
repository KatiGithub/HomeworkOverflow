package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.shared.SessionHibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageQuestions {
    private Session session;
    private Transaction tx;

    public ManageQuestions() {
        this.session = new SessionHibernateUtil().getSession();
        this.tx = session.beginTransaction();
    }

    public Integer addQuestion(Question question) {
        Integer questionId = null;

        try {
            questionId = (Integer) this.session.save(question);
            tx.commit();
        } catch (HibernateException ex) {   
            if(this.tx != null) {
                this.tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            session.close();
        }

        return questionId;
    } 

    public List<Question> retrieveSomeQuestions() {
        List<Question> retrieveQuestions = new ArrayList<Question>();

        try {
           List questions = this.session.createQuery("SELECT Q FROM Question Q", Question.class).setMaxResults(20).list();

           for(Iterator iterator = questions.iterator(); iterator.hasNext();) {
                Question question = (Question) iterator.next();

                retrieveQuestions.add(question);
           }
        } catch(HibernateException ex) {
            if(this.tx != null) {
                this.tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            this.session.close();
        }

        return retrieveQuestions;
    }

    public Question retrieveSpecificQuestion(Integer questionid) {
        Question question = null;
        try {
            question = (Question) this.session.get(Question.class, questionid);
        } catch (Exception ex) {
            if(this.tx != null) {
                this.tx.rollback();
            }

            ex.printStackTrace();
        } finally {
            this.session.close();
        }

        return question;
    }
}
