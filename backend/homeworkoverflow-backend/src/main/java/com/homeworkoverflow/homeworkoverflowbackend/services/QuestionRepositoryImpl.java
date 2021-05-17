package com.homeworkoverflow.homeworkoverflowbackend.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.Question.QuestionRepositoryCustom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.NoArgsConstructor;

@Repository
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    EntityManager entityManager;

    final Logger logger = LoggerFactory.getLogger(QuestionRepositoryImpl.class);

    public List<Question> getHomePage() {
        List lsQuestion = this.entityManager.createNativeQuery("SELECT * FROM tblquestion ORDER BY RANDOM();", Question.class).setMaxResults(20).getResultList();

        List<Question> listOfQuestion = new ArrayList<Question>();

        for(Iterator<Question> iterator = lsQuestion.iterator(); iterator.hasNext();) {
            listOfQuestion.add((Question) iterator.next());
        }

        logger.debug(listOfQuestion.toString());

        return listOfQuestion;
    }
    
}
