CREATE OR REPLACE FUNCTION upvoteanswer(_userid INT, _answerid INT)
RETURNS INT
LANGUAGE plpgsql
AS

$$
DECLARE
    v_upvote tblupvote%rowtype;
    upvote_count INT;

BEGIN

    SELECT *
    FROM tblupvote
    INTO v_upvote
    WHERE tblupvote.answerid = _answerid AND tblupvote.userid = _userid;

    IF not found THEN
        INSERT INTO tblupvote(answerid, userid, upvote_downvote) VALUES(_answerid, _userid, TRUE);
        UPDATE tblanswer SET upvotes = upvotes + 1 WHERE answerid = _answerid;
    ELSE
        IF v_upvote.upvote_downvote = TRUE THEN
            DELETE FROM tblupvote WHERE answerid = _answerid AND userid = _userid;
            UPDATE tblanswer SET upvotes = upvotes - 1 WHERE answerid = _answerid;
            ELSIF v_upvote.upvote_downvote = FALSE THEN
                UPDATE tblupvote
                SET upvote_downvote = true
                WHERE tblupvote.answerid = _answerid AND tblupvote.userid = _userid; 
                UPDATE tblanswer SET upvotes = upvotes + 2 WHERE answerid = _answerid;

        END IF;
    END IF;

    SELECT upvotes
    INTO upvote_count
    FROM tblanswer
    WHERE answerid = _answerid;

    return upvote_count;
END;
$$;