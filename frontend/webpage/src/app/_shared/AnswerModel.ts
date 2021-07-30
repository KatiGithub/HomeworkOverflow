export class Answer {
    upvote: Boolean;
    downvote: Boolean;
    answer_id: number;
    answer_username: String;
    answer: String;
    date_posted: Date;
    upvotes: number;
    comments: number;

    constructor(
                answer_id: number,
                upvote: Boolean,
                downvote: Boolean,
                answer_username: String,
                answer: String,
                date_posted: Date,
                upvotes: number,
                comments: number) {
                    this.answer_id = answer_id;
                    this.upvote = upvote;
                    this.downvote = downvote;
                    this.answer_username = answer_username;
                    this.answer = answer;
                    this.date_posted = date_posted;
                    this.upvotes = upvotes;
                    this.comments = comments;
    }
}