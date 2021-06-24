export class User {
  private userid: Number;
  private firstname: String;
  private lastname: String;
  private username: String;
  private email: String;
  private address: String;
  private title: String;

  private github_username: String;
  private twitter_handle: String;
  private instagram_username: String;
  private facebook_username: String;

  constructor(
    firstname: String,
    lastname: String,
    username: String,
    userid?: Number,
    email?: String,
    address?: String,
    title?: String,
    github_username?: String,
    twitter_handle?: String,
    instagram_username?: String,
    facebook_username?: String
  ) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.userid = userid;
    this.email = email;
    this.address = address;
    this.title = title;
    this.github_username = github_username;
    this.twitter_handle = twitter_handle;
    this.instagram_username = instagram_username;
    this.facebook_username = facebook_username;
  }

  public static mapJsonToObject(obj: Object) {
    return new User(
      obj['firstname'],
      obj['lastname'],
      obj['username'],
      obj['userid'],
      obj['email'],
      obj['userlocation'],
      obj['title'],
      obj['githubhandle'],
      obj['twitterhandle'],
      obj['instagramhandle'],
      obj['facebookhandle']
    );
  }

  public getUserid(): Number {
    return this.userid;
  }

  public setUserId(userid: Number): void {
    this.userid = userid;
  }

  public getFirstname(): String {
    return this.firstname;
  }

  public getLastname(): String {
      return this.lastname;
  }

  public setLastname(lastname: String): void {
    this.lastname = lastname;
  }

  public getUsername(): String {
    return this.username;
  }

  public setUsername(username: String): void {
    this.username = username;
  }

  public getEmail(): String {
    return this.email;
  }

  public setEmail(email: String): void {
    this.email = email;
  }

  public getAddress(): String {
    return this.address;
  }

  public setAddress(address: String): void {
    this.address = address;
  }

  public getTitle(): String {
    return this.title;
  }

  public setTitle(title: String): void {
    this.title = title;
  }

  public getGithub_username(): String {
    return this.github_username;
  }
  
  public setGithub_username(github_username: String): void {
      this.github_username = github_username;
  }

  public getTwitter_handle(): String {
      return this.twitter_handle;
  }

  public setTwitter_handle(twitter_handle: String) {
      this.twitter_handle = twitter_handle;
  }

  public getInstagram_username(): String {
      return this.instagram_username;
  }

  public setInstagram_username(instagram_username: String): void {
      this.instagram_username = instagram_username;
  }

  public getFacebook_username(): String {
      return this.facebook_username;
  }

  public setFacebook_username(facebook_username: String): void {
      this.facebook_username = facebook_username;
  }
}
