package model; 

import java.util.ArrayList;
import java.util.Iterator;

public class Floor {

    private int numCorridors=8;
    private int numMiniRoom=50;
    private double baseValue=500000;

    private MiniRoom [] [] miniRoom=new MiniRoom [numCorridors][numMiniRoom];
    private ArrayList <Company> list;

    public Floor(){
        list=new ArrayList<Company>();
    }
    
    public void createMiniRoom() {

        int id=1;

        for (int i = 0; i < miniRoom.length; i++) {

            for (int j = 0; j < miniRoom[0].length; j++) {

                int corridor=(i+1);

                int column=(j+1);

                Ubication ubication;

                State state=State.FREE;
                On state1=On.OFF;

                if (i==0 || i == (numCorridors-1)) {

                    ubication= Ubication.HASMIRROR;
            
                } else if (j==0 || j==(numMiniRoom-1)) {

                    ubication= Ubication.HASMIRROR;
            
                } else{
                    ubication= Ubication.HASNOTMIRROR;
                }

                double rentalValue=0;

                if (ubication==Ubication.HASMIRROR && i==6) {

                    rentalValue=baseValue+(baseValue*(-(0.1+0.15)));
            
                } else if (ubication==Ubication.HASMIRROR && i>=1 && i<=5) {

                    rentalValue=baseValue+(baseValue*(0.25-0.1));
            
                } else if (ubication==Ubication.HASMIRROR) {

                    rentalValue=baseValue+(baseValue*(-0.1));
            
                }else if (i==6) {

                    rentalValue=baseValue+(baseValue*(-0.15));
            
                }else if (i>=1 && i<=5) {

                    rentalValue=baseValue+(baseValue*(0.25));
            
                }
            
                miniRoom[i][j]= new MiniRoom(corridor, ubication, column, rentalValue, id, state, state1);
                
                id++;
            }
            
        }
    }

    public String showMiniRoomList() {

        String result="Free MiniRooms: \n";

        for (int i = 0; i < miniRoom.length; i++) {
            
            for (int j = 0; j < miniRoom[0].length; j++) {

                if (miniRoom[i][j].getState()==State.FREE) {
                    result+=miniRoom[i][j].toString()+"-----------------------------"+"\n";
                }
                
            }

        }

        return result;

    }

    public boolean verifyId(int id) {

        boolean flag=false;

        for (int i = 0; i < miniRoom.length && !flag; i++) {
            
            for (int j = 0; j < miniRoom[0].length && !flag; j++) {

                if (miniRoom[i][j].getId()==id) {

                    if (miniRoom[i][j].getState()==State.FREE) {

                        flag=true;
                        
                    }
                   
                }
                
            }

        }

        return flag;

    }

    public void rentMiniRoom(int id) {

        boolean flag=false;

        for (int i = 0; i < miniRoom.length && !flag; i++) {
            
            for (int j = 0; j < miniRoom[0].length && !flag; j++) {

                if (miniRoom[i][j].getId()==id) {

                    if (miniRoom[i][j].getState()==State.FREE) {

                        miniRoom[i][j].setState(State.NOTFREE);
                        miniRoom[i][j].setState1(On.ON);

                        flag=true;
                        
                    }
                   
                }
                
            }

        }

    }

    public void addCompany(String name, int nit, int id) {

        if (list.isEmpty()) {

            Company object=new Company(nit, name, id);

            list.add(object);
            
        }else if (verifyNit(nit)>=0) {
            int position=verifyNit(nit);
            list.get(position).addId(id);
        }else{

            Company object=new Company(nit, name, id);
            list.add(object);
            
        }
       

    }

    public int verifyNit(int nit) {

        boolean out=false;
        int position=-1;

        for (int i = 0; i < list.size() && !out; i++) {
                if (list.get(i).getNit()==nit) {
                    position=i;
                    out=true;
                }
        }

        return position;
    }

    public void newServer(int id, double cache, int numProcessor, String processorBrand, int ram, int numDisc, double discCapacity) {

        boolean flag=false;

        for (int i = 0; i < miniRoom.length && !flag; i++) {
            for (int j = 0; j < miniRoom[0].length && !flag; j++) {
                if (miniRoom[i][j].getId()==id) {
                    miniRoom[i][j].addServer(cache, numProcessor, processorBrand, ram, numDisc, discCapacity);
                    flag=true;
                }
            }
        }

    }

    public String calculateTotal(int server, int id) {

        boolean flag=false;
        int position1=0;
        int position2=0; 
        double value;
        double totalValue;

        for (int i = 0; i < miniRoom.length && !flag; i++) {
            for (int j = 0; j < miniRoom[0].length && !flag; j++) {
                if (miniRoom[i][j].getId()==id) {
                    position1=i;
                    position2=j;
                    flag=true;
                }
            }
        }
       
        if (server<4) {

            value=miniRoom[position1][position2].getRentalValue();
            totalValue=value+(value*0.15);
            
        }else{
            totalValue=miniRoom[position1][position2].getRentalValue();
        }

        return "El valor total del alquiler es: " + totalValue;

    }

    public void cancelMiniRoom(int id) {

        for (int i = 0; i < miniRoom.length; i++) {

            for (int j = 0; j < miniRoom[0].length; j++) {

                if (miniRoom[i][j].getId()==id) {
                    miniRoom[i][j].setState(State.FREE);
                    miniRoom[i][j].deleteServers();

                }

            }
            
        }

    }

    public void deleteCompanyId(int id){

        boolean flag=false;

        for (int i = 0; i < list.size() && !flag; i++) {
            for (int j = 0; j < 400 && !flag; j++) {
                if (list.get(i).returnPositionId(j)==id) {
                    list.get(i).deleteId(j);
                    flag=true;
                }
            }
        }

    }

    public void deleteEveryId(int nit) {

        int id;

        for ( int i = 0; i < list.size(); i++) {

            if (list.get(i).getNit()==nit) {

                for (int j = 0; j < 400; j++) {

                    id=list.get(i).returnPositionId(j);
                    cancelMiniRoom(id);
                
                }
                list.get(i).deleteAllId();
            }
            
           
        }

    }

    public String showMap() {

        String result="Mapa DataCenter:\n";

        for (int i = 0; i < miniRoom.length; i++) {
            for (int j = 0; j < miniRoom[0].length; j++) {
                result+="ID: "+miniRoom[i][j].getId()+"\n"+"On State: " +miniRoom[i][j].getState1()+"\n"+"-------------------------"+"\n";
            }
        }

        return result;

    }

    public void simulateTurnOn() {

        for (int i = 0; i < miniRoom.length; i++) {
            for (int j = 0; j < miniRoom[0].length; j++) {
                miniRoom[i][j].setState1(On.ON);
            }
        }

    }

    public void simulateTurnOff(String option) {

        if (option.equalsIgnoreCase("L")) {

            for (int i = 0; i < miniRoom.length; i++) {
                for (int j = 0; j < miniRoom[0].length; j++) {
                    if (j==0) {
                        miniRoom[i][j].setState1(On.OFF);
                    }
                    if (i==0) {
                        miniRoom[i][j].setState1(On.OFF);
                    }
                }
            }
            
        }else if (option.equalsIgnoreCase("Z")) {

            for (int i = 0; i < miniRoom.length; i++) {
                for (int j = 0; j < miniRoom[0].length; j++) {
                    if (i==0 || i==7) {
                        miniRoom[i][j].setState1(On.OFF);
                    }
                }
            }
            
        }else if (option.equalsIgnoreCase("H")) {

            for (int i = 0; i < miniRoom.length; i++) {
                for (int j = 0; j < miniRoom[0].length; j++) {
                    if (i%2!=0) {
                        miniRoom[i][j].setState1(On.OFF);
                    }
                }
            }
            
        }else if (option.equalsIgnoreCase("O")) {

            for (int i = 0; i < miniRoom.length; i++) {
                for (int j = 0; j < miniRoom[0].length; j++) {
                    if (miniRoom[i][j].getUbication()==Ubication.HASMIRROR) {
                        miniRoom[i][j].setState1(On.OFF);
                    }
                }
            }
            
        }

    }

    public void simulateTurnOff2(String option, int corridor) {

        if (option.equalsIgnoreCase("M")) {

            for (int i = 0; i < miniRoom.length; i++) {
                miniRoom[i][(corridor-1)].setState1(On.OFF);
            }
            
        }else if (option.equalsIgnoreCase("P")) {

            for (int j = 0; j < miniRoom[0].length; j++) {
                miniRoom[(corridor-1)][j].setState1(On.OFF);
            }
            
        }

    }

    



}