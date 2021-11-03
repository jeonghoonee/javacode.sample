package javacode.sample.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


public class TestLamdaSample01 {

    public static void main(String[] args) throws Exception {
    	
    	List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };
        
        System.out.println("** Show programmers names:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        
        System.out.println("\n\n** Increase salary by 5% to programmers:");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
         
        
        System.out.println("before:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s %s; ", p.getFirstName(), p.getLastName(), p.getSalary()));
        
        javaProgrammers.forEach(giveRaise);
        System.out.println("\nafter:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s %s; ", p.getFirstName(), p.getLastName(), p.getSalary()));
        
        
        // Define some filters
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
        
        System.out.println("\n\n** Show female Java programmers older than 24 years:");
        javaProgrammers.stream()
                  .filter(ageFilter)
                  .filter(genderFilter)
                  .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        
        System.out.println("\n\n** Show first 3 Java programmers:");
        javaProgrammers.stream()
                  .limit(3)
                  .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        
        System.out.println("Sort and show the first 5 Java programmers by name:");
        //ascending
        List<Person> sortedJavaProgrammers1 = javaProgrammers
                  .stream()
                  .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                  .limit(5)
                  .collect(toList());
        
        System.out.println("\nascending sorted :");
        sortedJavaProgrammers1.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        
        //descending
        List<Person> sortedJavaProgrammers2 = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p2.getFirstName().compareTo(p.getFirstName())))
                .limit(5)
                .collect(toList());
        
        System.out.println("\ndescending sorted :");
        sortedJavaProgrammers2.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        
        System.out.println("\n\n** Get the lowest Java programmer salary:");
        Person pers = javaProgrammers
                  .stream()
                  .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                  .get();
         
        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());
         
        System.out.println("\n** Get the highest Java programmer salary:");
        Person person = javaProgrammers
                  .stream()
                  .max((p, p2) -> (p.getSalary() - p2.getSalary()))
                  .get();
         
        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());
        
        System.out.println("\n\n** Calculate total money spent for paying Java programmers by parallel :");
        int totalSalary = javaProgrammers
		                  .parallelStream()
		                  .mapToInt(p -> p.getSalary())
		                  .sum();
        
        System.out.println("totalSalary : " + totalSalary);
        
        int maxSalary = javaProgrammers
		                .parallelStream()
		                .max(Comparator.comparing(Person::getSalary)).get().getSalary();
        System.out.println("maxSalary : " + maxSalary);
        
        System.out.println("\n** Get count, min, max, sum, and average for numbers:");
        IntSummaryStatistics stats = javaProgrammers
                .stream()
                .map(p -> p.getSalary())
                .collect(toList()).stream().mapToInt(x -> x).summaryStatistics();

		System.out.println("Highest salary in List : " + stats.getMax());
		System.out.println("Lowest salary in List : " + stats.getMin());
		System.out.println("Sum of all salary : " + stats.getSum());
		System.out.println("Average salary : " + stats.getAverage()); 
        
        
        System.out.println("\n** Get java programmers first name to String:");
        String javaDevelopers = javaProgrammers
				                  .stream()
				                  .map(Person::getFirstName)
				                  .collect(joining(" ; "));    // this can be used as a token in further operations
        System.out.println(javaDevelopers);
         
        System.out.println("\n** Get Java programmers first name to Set:");
        Set<String> javaDevFirstName = javaProgrammers
					                  .stream()
					                  .map((Person p) -> p.getFirstName())
					                  .collect(toSet());
        System.out.println(javaDevFirstName);
         
        System.out.println("\n** Get Java programmers last name to TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
						                  .stream()
						                  .map(p -> p.getLastName())
						                  .collect(toCollection(TreeSet::new));
        System.out.println("ascending TreeSet : "+javaDevLastName);
        System.out.println("descending TreeSet : "+javaDevLastName.descendingSet());
    }
}

class Person {
	 
	private String firstName, lastName, job, gender;
	private int salary, age;
 
	public Person(String firstName, String lastName, String job, String gender, int age, int salary)       {
	          this.firstName = firstName;
	          this.lastName = lastName;
	          this.gender = gender;
	          this.age = age;
	          this.job = job;
	          this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}


