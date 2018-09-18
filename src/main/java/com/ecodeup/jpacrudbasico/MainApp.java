package com.ecodeup.jpacrudbasico;

import java.util.Scanner;

import javax.persistence.EntityManager;

import com.ecodeup.jpacrudbasico.model.Producto;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Producto producto;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Crear Producto");
			System.out.println("2. Buscar Producto");
			System.out.println("3. Actualizar Producto");
			System.out.println("4. Elimnar Producto");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Digite el nombre del producto:");
				producto = new Producto();
				producto.setId(null);
				scanner.nextLine();
				producto.setNombre(scanner.nextLine());

				System.out.println("Digite el precio del producto:");
				producto.setPrecio(scanner.nextDouble());
				System.out.println(producto);
				entity.getTransaction().begin();
				entity.persist(producto);
				entity.getTransaction().commit();
				System.out.println("Producto registrado..");
				System.out.println();
				break;

			case 2:
				System.out.println("Digite el id del producto a buscar:");
				producto = new Producto();
				producto = entity.find(Producto.class, scanner.nextLong());
				if (producto != null) {
					System.out.println(producto);
					System.out.println();
				} else {
					System.out.println("Producto no encontrado...");
					System.out.println();
				}

				break;
			case 3:
				System.out.println("Digite el id del producto a actualizar:\n");
				producto = new Producto();

				producto = entity.find(Producto.class, scanner.nextLong());
				if (producto != null) {
					System.out.println(producto);
					System.out.println("Digite el nombre del producto:\n");
					scanner.nextLine();
					producto.setNombre(scanner.nextLine());
					System.out.println("Digite el precio del producto:\n");
					producto.setPrecio(scanner.nextDouble());
					entity.getTransaction().begin();
					entity.merge(producto);
					entity.getTransaction().commit();
					System.out.println("Producto actualizado..");
					System.out.println();
				} else {
					System.out.println("Producto no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Digite el id del producto a eliminar:\n");
				producto = new Producto();

				producto = entity.find(Producto.class, scanner.nextLong());
				if (producto != null) {
					System.out.println(producto);
					entity.getTransaction().begin();
					entity.remove(producto);
					entity.getTransaction().commit();
					System.out.println("Producto eliminado\n");
				} else {
					System.out.println("Producto no encontrado\n");
				}
				break;
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}

}
