export class Question {
    question: String;
    answer: String;
    asker_username: String;
    upvotes: Number;
    comments: Number;

    constructor(question: String, asker_username: String, upvotes: Number, comments: Number) { 
        this.question = question,
        this.upvotes = upvotes,
        this.asker_username = asker_username;
        this.comments = comments;
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