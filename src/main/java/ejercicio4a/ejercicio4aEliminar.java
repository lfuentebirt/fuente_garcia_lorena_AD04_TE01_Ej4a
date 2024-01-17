package ejercicio4a;

import entidades.Tuition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ejercicio4aEliminar {

	public static void main(String[] args) {
		// creamos el Entity Manager Factory y el entity manager
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("demodb");
		
		EntityManager manager = factoria.createEntityManager();
		
		// comenzamos la transaccion
		manager.getTransaction().begin();
		
		try {		
			int idMatricula = 5; // he insertado el registro n 5
			// obtenemos la matricula
			Tuition matricula = manager.find(Tuition.class, idMatricula);
			
			// eliminamos el dato de la matricula al estudiante
			manager.remove(matricula);
			
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

}
