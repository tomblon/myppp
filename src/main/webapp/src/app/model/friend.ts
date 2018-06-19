export class Friend {
  id: string = "";
  name: string = "";
  email: string = "";

  constructor() {}

  public toJSON(): string {
    return JSON.stringify({
      id: this.id,
      name: this.name,
      email: this.email
    });
  }
}
