export class User {
    userid: Number;
    firstname: String;
    lastname: String;
    email: String;
    address: String;
    title: String;

    github_username: String;
    twitter_handle: String;
    instagram_username: String;
    facebook_username: String;

    constructor(
        firstname: String,
        lastname: String,
        userid?: Number,
        email?: String,
        address?: String,
        title?: String,
        github_username?: String,
        twitter_handle?: String,
        instagram_username?: String,
        facebook_username?: String) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.userid = userid;
            this.email = email;
            this.address = address;
            this.title = title;
            this.github_username = github_username;
            this.twitter_handle = twitter_handle;
            this.instagram_username = instagram_username;
            this.facebook_username = facebook_username;
    }

}