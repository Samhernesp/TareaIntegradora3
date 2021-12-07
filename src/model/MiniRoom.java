package model; 

import java.util.ArrayList;
import java.util.Iterator;

public class MiniRoom {

	private int corridor;
	private Ubication ubication;
	private int column;
	private double rentalValue;
	private double baseRentalValue=500000;
	private int id;
	private State state;
	private On state1;
	private ArrayList <Server> servers;

	public MiniRoom(int corridor, Ubication ubication, int column, double rentalValue, int id, State state, On state1) {

		this.corridor=corridor;
		this.ubication=ubication;
		this.column=column;
		this.rentalValue=rentalValue;
		this.id=id;
		this.state=state;
		this.state1=state1;
		servers=new ArrayList<Server>();


	}

	public int getCorridor() {
		return corridor;
	}

	
	public void setCorridor(int corridor) {
		this.corridor = corridor;
	}

	public Ubication getUbication() {
		return ubication;
	}


	public void setUbication(Ubication ubication) {
		this.ubication = ubication;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public double getRentalValue() {
		return rentalValue;
	}

	public void setRentalValue(double rentalValue) {
		this.rentalValue = rentalValue;
	}

	public double getBaseRentalValue() {
		return baseRentalValue;
	}
	
	public void setBaseRentalValue(double baseRentalValue) {
		this.baseRentalValue = baseRentalValue;
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public On getState1() {
		return state1;
	}

	public void setState1(On state1) {
		this.state1 = state1;
	}

	public void addServer(double cache, int numProcessor, String processorBrand, int ram, int numDisc, double discCapacity){

		Server server=new Server(cache, numProcessor, processorBrand, ram, numDisc, discCapacity);

		servers.add(server);

	}


	public String toString() {

		return "Corridor: " + corridor+"\n"+
		"Ubication: " + ubication+"\n"+
		"Column: " + column+"\n"+
		"Value of rental: " + rentalValue+"\n"+
		"Base Value: " + baseRentalValue+"\n"+
		"State: " + state+"\n"+
		"Identification: " + id+"\n";

	}

	public void deleteServers() {

		for (int i = 0; i < servers.size(); i++) {
			servers.remove(i);
		}

	}

}