export class Answer {
    answer_username: String;
    answer: String;
    date_posted: Date;
    upvotes: Number;
    comments: Number;

    constructor(answer_username: String,
                answer: String,
                date_posted: Date,
                upvotes: Number,
                comments: Number) {
                    this.answer_username = answer_username;
                    this.answer = answer;
                    this.date_posted = date_posted;
                    this.upvotes = upvotes;
                    this.comments = comments;
    }
}