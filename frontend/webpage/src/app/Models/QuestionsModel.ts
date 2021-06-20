import { User } from "./UserModel";

export class Question {
    date_asked: Date;
    question_id: Number;
    question: String;
    questionContent: String;
    answer: String;
    asker: User;
    upvotes: Number;
    comments: Number;
    tags: Array<String>;

    constructor(question_id: Number, question: String, questionContent: String, asker: User, upvotes: Number, comments: Number, tags: Array<String>, date_asked: Date) { 
        this.question_id = question_id;
        this.question = question;
        this.questionContent = questionContent;
        this.upvotes = upvotes;
        this.asker = asker;
        this.comments = comments;
        this.tags = tags;
        this.date_asked = date_asked;
    }

    public QuestionToObject(): Object {
        return {
            question: this.question,
            answer: this.answer,
            asker: this.asker,
            upvotes: this.upvotes,
            comments: this.comments
        };
    }

    public static mapObjectToQuestion(obj: Object): Question {
        console.log(obj);

        return new Question(
            obj['questionId'],
            obj['questionTitle'],
            obj["questionContent"],
            User.mapJsonToObject(obj["asker"]),
            obj["upvotes"],
            obj["numberOfComments"],
            obj["tags"],
            new Date(obj["dateAsked"])
          );
    }
}