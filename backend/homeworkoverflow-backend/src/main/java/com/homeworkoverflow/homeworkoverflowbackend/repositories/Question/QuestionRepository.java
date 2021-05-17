package com.homeworkoverflow.homeworkoverflowbackend.repositories.Question;
import java.util.List;
import java.util.Optional;


import com.homeworkoverflow.homeworkoverflowbackend.models.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface QuestionRepository extends JpaRepository<Question, Integer>, QuestionRepositoryCustom {

}