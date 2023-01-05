import {User} from "./user";

export class Employee extends User{
  public hiringDate: string = ""; // save date as string for simplicity since it is not used
  public salary: number = -1;
}
