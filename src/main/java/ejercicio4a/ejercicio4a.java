package ejercicio4a;

import java.time.LocalDate;

import entidades.Address;
import entidades.Student;
import entidades.Tuition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ejercicio4a {

	public static void main(String[] args) {
		/*4a. Crea un nuevo objeto Student y otro objeto Tuition (matrícula)
		 *  y guárdalo en la BD. Realiza las anotaciones necesarias en la clase entidad Student y en la clase Tuition. La asociación entre ambas entidades será @OneToOne unidireccional.
		 *  En este caso, optaremos por una asociación unidireccional para simplificar el modelo y porque no necesitaremos
		 *  navegar la relación en ambos sentidos. 
		 * 
		 */
		// creamos el Entity Manager Factory y el entity manager
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("demodb");
		
		EntityManager manager = factoria.createEntityManager();
		
		// comenzamos la transaccion
		manager.getTransaction().begin();
		try {		
			// creamos un objeto de la clase Student
			Student estudiante = crearEstudiante();
			
			// creamos la matricula, que contiene al estudiante, como se indica en el modelo
			Tuition matricula = crearMatricula();
			
			// añadimos el dato de la matricula al estudiante
			matricula.setStudent(estudiante);
	
			// guardamos el estudiante
			manager.persist(matricula);
			
			manager.getTransaction().commit();
			
		}catch(Exception e) {
			// si hay excepcion realizamos un rollback
			System.out.println("Rollback");
			manager.getTransaction().rollback();
			
			e.printStackTrace();
		}finally {
			manager.close();
			factoria.close();
		}
			
	}

	private static Tuition crearMatricula() {
		Tuition matri = new Tuition();
		
		matri.setFee(1000.5);
		
		return  matri;
	}

	private static Student crearEstudiante() {
		Student estu = new Student();
		
		estu.setFirstName("Lorena");
		estu.setLastName("Fuente");
		estu.setEmail("lfuente@birt.eus");
		estu.getPhones().add("666666666");
		estu.setBirthdate(LocalDate.parse("1990-01-01"));
		
		Address direccion = new Address();
		direccion.setAddressLine1("C/Alava");
		direccion.setAddressLine2("1, 4A");
		direccion.setCity("Vitoria");
		direccion.setZipCode("1000");
		
		estu.setAddress(direccion);
		
		return estu;
	}

}
