package object;

public class User {
	int id;
	String email;
	String fname;
	String sname;
	String created;

	public User(int userid, String useremail, String userfname, String usersname, String usercreated) {
		id = userid;
		email = useremail;
		fname = userfname;
		sname = usersname;
		created = usercreated;
	}

	public void getAll() {
		System.out.println(id);
		System.out.println(email);
		System.out.println(fname);
		System.out.println(sname);
		System.out.println(created);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	/*
	 * public int getCreated() { return created; }
	 * 
	 * public void setCreated(int created) { this.created = created; }
	 * 
	 */
}
