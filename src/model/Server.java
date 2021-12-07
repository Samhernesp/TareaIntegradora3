package model; 
public class Server {

	private double cache;
	private int numProcessor;
	private String processorBrand;
	private int ram;
	private int numDisc;
	private double discCapacity;


	public Server(double cache, int numProcessor, String processorBrand, int ram, int numDisc, double discCapacity) {

		this.cache=cache;
		this.numProcessor=numProcessor;
		this.processorBrand=processorBrand;
		this.ram=ram;
		this.numDisc=numDisc;
		this.discCapacity=discCapacity;

	}

	public double getCache() {
		return cache;
	}

	public void setCache(double cache) {
		this.cache = cache;
	}

	public int getNumProcessor() {
		return numProcessor;
	}

	public void setNumProcessor(int numProcessor) {
		this.numProcessor = numProcessor;
	}

	public String getProcessorBrand() {
		return processorBrand;
	}

	public void setProcessorBrand(String processorBrand) {
		this.processorBrand = processorBrand;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getNumDisc() {
		return numDisc;
	}


	public void setNumDisc(int numDisc) {
		this.numDisc = numDisc;
	}

	public double getDiscCapacity() {
		return discCapacity;
	}

	public void setDiscCapacity(double discCapacity) {
		this.discCapacity = discCapacity;
	}

	public String toString() {

		return "Cache: "+ cache + "\n"+
		"Cache: "+ numProcessor + "\n"+
		"Cache: "+ processorBrand + "\n"+
		"Cache: "+ ram + "\n"+
		"Cache: "+ numDisc + "\n"+
		"Cache: "+ discCapacity + "\n";

	}

}