export class User {
  constructor(
    public userMail: string,
    public password: string) {}

    public toJSON(): string {
      return JSON.stringify({
        userMail: this.userMail,
        password: this.password
      });
    }
}
