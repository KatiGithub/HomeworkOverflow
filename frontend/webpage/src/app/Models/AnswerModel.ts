import { User } from "./UserModel";

export class Answer {
    upvoteUserStatus: Boolean = null;
    answerid: Number;
    answeruser: User
    questionid: Number;
    answerContent: String;
    dateAnswered: Date;
    upvotes: Number;
    comments: Number;

    constructor(
                answerid: number,
                upvoteUserStatus: Boolean,
                answeruser: User,
                questionid: Number,
                answerContent: String,
                dateAnswered: Date,
                upvotes: Number,
                comments: Number) {
                    this.answerid = answerid;
                    this.upvoteUserStatus = upvoteUserStatus;
                    this.answeruser = answeruser;
                    this.questionid = questionid;
                    this.answerContent = answerContent;
                    this.dateAnswered = dateAnswered;
                    this.upvotes = upvotes;
                    this.comments = comments;
    }

    public static mapJsonToAnswer(obj: Object): Answer {
        return new Answer(
            obj["answer_id"],
            obj["userUpvoteStatus"],
            User.mapJsonToObject(obj["answerUser"]),
            obj["questionId"],
            obj["answerContent"],
            obj["dateAnswered"],
            obj["upvotes"],
            obj["comments"]
        );
    }
}