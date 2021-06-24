import { User } from "./UserModel";

export class Answer {
    private upvoteUserStatus: Boolean = null;
    private answerid: Number;
    private answeruser: User
    private questionid: Number;
    private answerContent: String;
    private dateAnswered: Date;
    private upvotes: Number;
    private comments: Number;

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

    public setAnswerid(answerid: Number): void {
        this.answerid = answerid;
    }

    public getAnswerid(): Number {
        return this.answerid;
    }

    public setUpvoteUserStatus(upvoteUserStatus: Boolean): void {
        this.upvoteUserStatus = upvoteUserStatus
    }

    public getUpvoteUserStatus(): Boolean {
        return this.upvoteUserStatus;
    }

    public setAnswerUser(answeruser: User): void {
        this.answeruser = answeruser;
    }

    public getAnswerUser(): User {
        return this.answeruser;
    }

    public setQuestionid(questionId: Number): void {
        this.questionid = questionId;
    }

    public getQuestionid(): Number {
        return this.questionid;
    }

    public setAnswerContent(answerContent: String): void {
        this.answerContent = answerContent;
    }

    public getAnswerContent(): String {
        return this.answerContent;
    }

    public setDateAnswered(dateAnswered: Date): void {
        this.dateAnswered = dateAnswered;
    }

    public getDateAnswered(): Date {
        return this.dateAnswered;
    }

    public setUpvotes(upvotes: Number): void {
        this.upvotes = upvotes;
    }

    public getUpvotes(): Number {
        return this.upvotes;
    }

    public setComments(comments: Number) {
        this.comments = comments;
    }

    public getComments(): Number {
        return this.comments;
    }

}