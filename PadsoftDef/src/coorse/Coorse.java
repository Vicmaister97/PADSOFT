package coorse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import courseElements.Course;
import users.Professor;
import users.Student;
import users.User;
public class Coorse implements java.io.Serializable {
	public static final Coorse coorse = new Coorse();
	private static final long serialVersionUID = 1L;
	private List<User> users;
	private List<Course> courses;
	
	public Coorse(){
		this.users = new ArrayList<User>();
		Professor p = new Professor("admin", "12345");
		this.users.add(p);
		this.courses = new ArrayList<Course>();
	}
	
	public User login (String uname, String pass){
		for (int i=0; i<this.getUsers().size(); i++){
			if(this.getUsers().get(i).getUsername().equals(uname)){
				if (this.getUsers().get(i).getPassword().equals(pass)){
					return this.getUsers().get(i);
				}
				else{
					return null;
				}
			}
		}
		return null;
	}
	
	public void addUser(User u){
		this.getUsers().add(u);
	}
	
	public void addCourse(Course c){
		this.getCourses().add(c);
	}
	
	public void save(String fname){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fname));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			}
			return;
		}
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void load(String fname) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));
		Object obj = ois.readObject();
		Coorse c = (Coorse) obj;
		this.setCourses(c.getCoursesL());
		this.setUsers(c.getUsersL());
		ois.close();
	}
	
	
	public void readFromFile(String fname){ 
		try {
			String test;
			String firstname, lastname, email, id, pass;
			BufferedReader br = new BufferedReader(new FileReader(fname));
			test=br.readLine();
			while((test = br.readLine()) != null){
				StringTokenizer Tok = new StringTokenizer(test, ";");
				firstname=Tok.nextToken();
				lastname=Tok.nextToken();
				email=Tok.nextToken();
				id=Tok.nextToken();
				pass=Tok.nextToken();
				this.addUser(new Student(email, pass, firstname, lastname, id, email));
			}
			if (br != null)
				br.close();
		}catch (IOException e) {

			e.printStackTrace();

		}
	}
	private List<User> getUsersL(){
		return users;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}
	/**
	 * @param users the users to set
	 */
	private void setUsers(List<User> users) {
		this.users = users;
	}
	private List<Course> getCoursesL(){
		return courses;
	}
	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}
	/**
	 * @param courses the courses to set
	 */
	private void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
