/**
 * Note: This docstring will go into the doc generated by Javadoc
 * 
 * A Person is the base class of any person type.
 * A Person has a name and an age.
 * A Person can say hello using the string returned by the hello() method. 
 */
public class Person {
	
	private String name;
	private int age;
	
	/** 
	 * Construct a Person object with the given name and age.
	 * 
	 * Remember:
	 * The constructor has the same name as the class
	 * no return type; not even void
	 * 
	 * @param name		The name of the Person
	 * @param age		The age of the Person
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	/** 
	 * Returns the name of this Person
	 * @return this person's name
	 */
	public String getName() {
		return this.name;
	}

	/** 
	 * Returns the name of this Person
	 * @return this person's age
	 */
	public int getAge() {
		return this.age;
	}

	/** 
	 * Returns a greeting and introduction of this person's name
	 * @return the string 'Hello, I am ' followed by this person's name
	 */	
	public String hello() {
		return "Hello, I am " + this.getName();
	}
}
