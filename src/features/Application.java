package features;

import java.util.Date;

import others.School;
import users.Teacher;

public class Application {
	private String appId;
	private Date dateApplied;
	private Teacher teacher;
	private School school;
	
	public Application() {
		appId = generateAppId();
		dateApplied = new Date();
	}
	
	public Application(Teacher teacher, School school){
		appId = generateAppId();
		this.teacher = teacher;
		this.school = school;
		dateApplied = new Date();	
	}

	public String getAppId() {
		return appId;
	}
	
	public Date getDateApplied() {
		return dateApplied;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public School getSchool() {
		return school;
	}
	
	public void assign(Teacher teacher) {
		this.teacher = teacher;
	}

	public void assign(School school) {
		this.school = school;
	}

	public String generateAppId() {
		int num = (int) (Math.random() * 10000 + 1);
		return "AP" + String.valueOf(num);
	}
	
}
