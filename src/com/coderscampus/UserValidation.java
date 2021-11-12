/*
 * package com.coderscampus;
 * 
 * import java.util.Scanner;
 * 
 * public class UserValidation extends UserApp implements UserDataInput {
 * 
 * 
 * 
 * switch(option) case 1: static int promptOptions(User currentUser) {
 * System.out.println("----------");
 * System.out.println("Please choose from the following options:"); if
 * (currentUser instanceof SuperUser) {
 * System.out.println("(0) Log in as another user ");
 * 
 * System.out.println("(1) Update username");
 * System.out.println("(2) Update password");
 * System.out.println("(3) Update name"); System.out.println("(4) Exit"); String
 * option = scanner.nextLine();
 * 
 * return Integer.parseInt(option); break;
 * 
 * 
 * case 2: System.out.println("(1) Update username"); output = scan.nextLine();
 * System.out.println(); static String promptUpdateUsername() { System.out.
 * println("Which user would you like to login as? (Type in a valid username)");
 * String usernameToUpdate = scanner.nextLine(); return usernameToUpdate; static
 * void promptUpdateUsername(User currentUser) {
 * System.out.println("Please type in your new username: "); String username =
 * scanner.nextLine(); currentUser.setUsername(username); break; case 3:
 * System.out.println("Enter(2) Update password"); input = scan.nextLine();
 * System.out.println(); static void promptUpdatePassword(User currentUser) {
 * System.out.println("Please type in your new password: "); String password =
 * scanner.nextLine(); currentUser.setPassword(password); break; case 4:
 * System.out.println("(3) Update name"); comment = scan.nextLine();
 * System.out.println(); static void promptUpdateName(User currentUser) {
 * System.out.println("Please type in your new name: "); String name =
 * scanner.nextLine(); currentUser.setName(name); break; case 5:
 * System.out.println("(4) Exit "); System.out.println(); break; default:
 * System.out.println("Input invalid"); System.out.println(); }
 * 
 * @Override public String promptUsernameToUpdate() { // TODO Auto-generated
 * method stub return null; }
 * 
 * @Override public void promptUpdateName(User currentUser) { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * @Override public void promptUpdatePassword(User currentUser) { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * @Override public void promptUpdateUsername(User currentUser) { // TODO
 * Auto-generated method stub
 * 
 * }
 * 
 * @Override public int promptOptions(User currentUser) { // TODO Auto-generated
 * method stub return 0; }
 * 
 */