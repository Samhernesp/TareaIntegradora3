package model; 
public class Company {

	protected int nit;
	protected String name;
	private int [] id= new int [400];


	public Company(int nit, String name, int id) {
		this.nit=nit;
		this.name=name;
		addId(id);
	}

	public void addId(int id2) {

		boolean flag=false;

		for (int i = 0; i < id.length && !flag; i++) {
			if (id[i]==0) {
				id[i]=id2;
				flag=true;
			}
		}
	}

	public int getNit() {
		return nit;
	}

	
	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int returnPositionId(int j){

		return id[j];

	}

	public void deleteId(int j){

		id[j]=0;

	}

	public String toString() {

		return "Nit: "+nit+"\n"+
		"Name: "+ name +"\n";

	}

    public void deleteAllId() {

		for (int i = 0; i < id.length; i++) {
			id[i]=0;
		}

    }

}