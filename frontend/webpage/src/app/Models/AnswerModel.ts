import { User } from "./UserModel";

export class Answer {
    upvote: Boolean = null;
    answer_id: Number;
    answeruser: User
    answer: String;
    date_posted: Date;
    upvotes: Number;
    comments: Number;

    constructor(
                answer_id: number,
                upvote: Boolean,
                answeruser: User,
                answer: String,
                date_posted: Date,
                upvotes: Number,
                comments: Number) {
                    this.answer_id = answer_id;
                    this.upvote = upvote;
                    this.answeruser = answeruser;
                    this.answer = answer;
                    this.date_posted = date_posted;
                    this.upvotes = upvotes;
                    this.comments = comments;
    }

    public static mapJsonToAnswer(obj: Object): Answer {
        return new Answer(
            obj["answer_id"],
            obj["userUpvoteStatus"],
            User.mapJsonToObject(obj["answerUser"]),
            obj["answerContent"],
            obj["date_posted"],
            obj["upvotes"],
            obj["comments"]
        );
    }
}