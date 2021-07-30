export class User {
    username: String;
    email: String;
    phone: String;
    address: String;
    title: String;

    github_username: String;
    twitter_handle: String;
    instagram_username: String;
    facebook_username: String;

    constructor(
        username?: String,
        email?: String,
        phone?: String,
        address?: String,
        title?: String,
        github_username?: String,
        twitter_handle?: String,
        instagram_username?: String,
        facebook_username?: String) {
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.github_username = github_username;
            this.twitter_handle = twitter_handle;
            this.instagram_username = instagram_username;
            this.facebook_username = facebook_username;
    }
}