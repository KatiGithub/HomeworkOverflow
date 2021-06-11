export class Question {
    date_asked: Date;
    question_id: Number;
    question: String;
    answer: String;
    asker_username: String;
    upvotes: Number;
    comments: Number;
    tags: Array<String>;

    constructor(question_id: Number, question: String, asker_username: String, upvotes: Number, comments: Number, tags: Array<String>, date_asked: Date) { 
        this.question_id = question_id;
        this.question = question;
        this.upvotes = upvotes;
        this.asker_username = asker_username;
        this.comments = comments;
        this.tags = tags;
        this.date_asked = date_asked;
    }

    public QuestionToObject(): Object {
        return {
            question: this.question,
            answer: this.answer,
            asker_username: this.asker_username,
            upvotes: this.upvotes,
            comments: this.comments
        };
    }
}