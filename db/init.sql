CREATE TABLE IF NOT EXISTS tblUser(
    userId SERIAL PRIMARY KEY,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    firebaseUID TEXT NOT NULL,
    githubHandle TEXT,
    twitterHandle TEXT,
    instagramHandle TEXT,
    facebookHandle TEXT,
    userLocation TEXT
);

CREATE TABLE IF NOT EXISTS tblQuestion (
    questionId SERIAL PRIMARY KEY,
    questionTitle VARCHAR(50) UNIQUE NOT NULL,
    questionContent TEXT NOT NULL,
    askerUserId INT,
    tags TEXT[],
    dateAsked TIMESTAMPTZ,
    numberOfComments INT,
    numberOFUpvotes INT,
    CONSTRAINT fk_userid
        FOREIGN KEY(askerUserId)
            REFERENCES tblUser(userId)
);

CREATE TABLE IF NOT EXISTS tblAnswer(
    answerId SERIAL PRIMARY KEY,
    questionId INT,
    answerUserId INT,
    answerContent TEXT NOT NULL,
    datePosted TIMESTAMPTZ,
    numberOfComments INT,
    numberOfUpvotes INT,
    CONSTRAINT fk_userid
        FOREIGN KEY(answerUserId)
            REFERENCES tblUser(userId),
    CONSTRAINT fk_questionid
        FOREIGN KEY(questionId)
            REFERENCES tblQuestion(questionID)
);

CREATE TABLE IF NOT EXISTS tblComment(
    commentId SERIAL PRIMARY KEY,
    answerId INT,
    userId INT,
    commentContent TEXT NOT NULL,
    CONSTRAINT fk_answerid
        FOREIGN KEY(answerId)
            REFERENCES tblAnswer(answerId),
    CONSTRAINT fk_userid
        FOREIGN KEY(userId)
            REFERENCES tblUser(userId)
);

CREATE TABLE IF NOT EXISTS tblUpvoteDownvote(
    upvoteDownvoteId SERIAL PRIMARY KEY,
    upvotedownvote BOOLEAN NOT NULL,
    answerId INT,
    userId INT,
    CONSTRAINT fk_answerid
        FOREIGN KEY(answerId)
            REFERENCES tblAnswer(answerId),
    CONSTRAINT fk_userid
        FOREIGN KEY(userId)
            REFERENCES tblUser(userId)
);