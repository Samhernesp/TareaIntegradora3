package ui;

import java.util.Scanner;
import model.Floor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataCenter {

    private Scanner sc;
    private Floor myFloor= new Floor();

    public DataCenter(){
        sc=new Scanner(System.in);
		myFloor.createMiniRoom();
    }

    public static void main(String[] args){

        DataCenter dt= new DataCenter();
		
		System.out.println("Iniciando la aplicación");

		int option=0;
		
		do{
			
			option= dt.showMenu();
			dt.executeOperation(option);
			
		}while (option!=0);

    }

    public int showMenu() {
		int option=0;

		System.out.println(
				"Menú principal, seleccione una opción\n" +
				"(1) Listado de minicuartos disponibles \n" +
				"(2) Alquilar un minicuarto \n" +
				"(3) Cancelar alquiler de una empresa \n"+
				"(4) Mostrar mapa del DataCenter\n"+
				"(5) Simular encendido de minicuartos\n"+
				"(6) Simular apagado de los minicuartos\n"+
				"(0) Para salir"
				);
		option= sc.nextInt();
		sc.nextLine();
		return option;
	}

	/**
	 * Descripcion: El método executeOperation evalua la opcion que el usuario ingreso para enviarlo al método correspondiente</br>
	 * @param <operatiom> <int>,es el valor de la opcion que el usuario ingreso en el método anterior.
	 * */

	public void executeOperation(int operation) {
		
		switch(operation) {
		case 0:
			System.out.println("Cerrando aplicación");
			break;
		case 1:
			System.out.println(myFloor.showMiniRoomList());
			break;
		case 2:
			rentMiniRoom() ;
			break;
		case 3:
			cancelCompanyRent();
			break;
		case 4:
			showDataCenterMap();
			break;
		case 5:
			simulateMiniRoomTurnOn();
			break;
		case 6:
            simulateMiniRoomTurnOff();
			break;
		default:
			System.out.println("Error, opción no válida");
		
		}
	}


    private void rentMiniRoom() {

		System.out.println("Digite un número de acuerdo a las siguientes opciones:\n"+
							"1) Alquilar para una empresa\n"+
							"2) Alquilar para un proyecto de investigación\n"
		);
		int option=sc.nextInt();
		sc.nextLine();

		String name="";

		int nit=0;

		if (option==1) {

			System.out.println("Ingrese el nombre de la empresa");
			name=sc.nextLine();

			System.out.println("Ingrese el nit de la empresa");
			nit=sc.nextInt();
			sc.nextLine();
			
		}else if (option==2) {

			name="Universidad Icesi";
			
			System.out.println("Ingrese el número del proyecto");
			nit=sc.nextInt();
			sc.nextLine();

		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");

		System.out.println("Digite el Id del minicuarto que desea alquilar");
		int id=sc.nextInt();
		sc.nextLine();

		if (myFloor.verifyId(id)) {

			myFloor.rentMiniRoom(id);

			myFloor.addCompany(name,nit,id);

			System.out.println("Digite la cantidad de servidores que desea usar.");
			int server=sc.nextInt();
			sc.nextLine();
	
			for (int i = 0; i < server; i++) {
	
				System.out.println("Servidor "+(i+1)+":");
	
				System.out.println("Digite el cache del servidor.");
				double cache=sc.nextInt();
				sc.nextLine();
				System.out.println("Digite la cantidad de procesadores.");
				int numProcessor=sc.nextInt();
				sc.nextLine();
				System.out.println("Digite la marca del procesador.");
				String processorBrand=sc.nextLine();
				System.out.println("Digite la capacidad de la ram.");
				int ram=sc.nextInt();
				sc.nextLine();
				System.out.println("Digite el número de discos.");
				int numDisc=sc.nextInt();
				sc.nextLine();
				System.out.println("Digite la capacidad de cada disco.");
				double discCapacity=sc.nextInt();
				sc.nextLine();
	
				myFloor.newServer(id,cache,numProcessor,processorBrand,ram,numDisc,discCapacity);
	
			}
	
			System.out.println(myFloor.calculateTotal(server,id));
			
		} else{
			System.out.println("El minicuarto ingresado no está disponible. Por favor verifique la lista de minicuartos antes de alquilar.");
		}

		

    }

    private void cancelCompanyRent() {

		int option=0;

		while (option<1  || option>2) {

			System.out.println(
				"Seleccione una opcion de cancelación\n" +
				"(1) Delete one miniroom \n" +
				"(2) Delete every miniroom of a company \n"
				);
			option= sc.nextInt();	
			sc.nextLine();
		}
			int id;

			if (option==1) {

				System.out.println("Digite el id del minicuarto que desea cancelar");
				id=sc.nextInt();
				sc.nextLine();

				if (myFloor.verifyId(id)) {
					System.out.println("No se puede cancelar un minicuarto que no ha sido alquilado");
				} else{
					myFloor.cancelMiniRoom(id);
					myFloor.deleteCompanyId(id);
					System.out.println("Minicuarto cancelado con exito");
				}
				
				
			}else if(option==2){

				System.out.println("Digite el nit de la compañia a la cual desea cancelar los minicuartos.");
				int nit=sc.nextInt();
				sc.nextLine();

				if (myFloor.verifyNit(nit)>=0) {

					myFloor.deleteEveryId(nit);
					System.out.println("Los minicuartos de la empresa se cancelaron con exito");

				}else{
					System.out.println("No se ha creado una empresa con ese Nit, intente de nuevo.");
				}

			}else{
				System.out.println("La opcion ingresada es invalida");
			}	

		
    }

    private void showDataCenterMap() {

		System.out.println(myFloor.showMap());

    }

    private void simulateMiniRoomTurnOn() {

		myFloor.simulateTurnOn();

    }

    private void simulateMiniRoomTurnOff() {

		System.out.println(
				"Ingrese alguna de las siguientes letras para la simulacion\n" +
				"(L) Apaga los primeros minicuartos de todos los corredores, junto con los minicuartos del primer corredor.\n" +
				"(Z) apaga los minicuartos del primer y último corredor, junto con los minicuartos de la diagonal inversa.\n"+
				"(H) apaga los minicuartos ubicados en los corredores impares \n"+
				"(O) apaga los minicuartos ubicados en las ventanas \n"+
				"(M) Apaga los minicuartos de una columna \n"+
				"(P) apaga los minicuartos de un corredor \n"
				);
		String option=sc.nextLine();
		if (option.equalsIgnoreCase("L")) {

			myFloor.simulateTurnOff(option);
			
		}else if (option.equalsIgnoreCase("Z")) {

			myFloor.simulateTurnOff(option);
			
		}else if (option.equalsIgnoreCase("H")) {

			myFloor.simulateTurnOff(option);
			
		}else if (option.equalsIgnoreCase("O")) {

			myFloor.simulateTurnOff(option);
			
		}else if (option.equalsIgnoreCase("M")) {
			int column=0;

			while (column<1 || column>50) {
				System.out.println("Ingrese el número de la columna");
				column=sc.nextInt();
				sc.nextLine();
				if (column<1 || column>50) {
					System.out.println("El número de la columna no existe");
				}
			}

			myFloor.simulateTurnOff2(option,column);
			
		}else if (option.equalsIgnoreCase("P")) {

			int corridor=0;

			while (corridor<1 || corridor>8) {
				System.out.println("Ingrese el número del corredor");
				corridor=sc.nextInt();
				sc.nextLine();
				if (corridor<1 || corridor>50) {
					System.out.println("El número del corredor ingresado no existe");
				}
			}

			myFloor.simulateTurnOff2(option,corridor);
			
		}else{
			System.out.println("La opcion ingresada no es valida.");
		}
		

    }


}