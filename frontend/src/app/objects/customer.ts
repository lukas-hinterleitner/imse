import {User} from "./user";

export class Customer extends User{
  public registrationDate: string = ""; // save date as string for simplicity since it is not used
  public phoneNumber: string = "";
}
