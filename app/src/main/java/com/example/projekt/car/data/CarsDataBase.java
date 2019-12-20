package com.example.projekt.car.data;

import android.content.Context;
import android.util.Log;

import com.example.projekt.car.Exceptions.CarDoesNotExist;
import com.example.projekt.car.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CarsDataBase {
    private ArrayList<Car> carArrayList;

    void init(){
        carArrayList.add(new Car(51.8745,19.363,false,54.0,"Seat Toledo",false,false,R.drawable.seat_toledo));
        carArrayList.add(new Car(51.77,19.48,false,50.0,"Audi",false,false,R.drawable.auto2));
        carArrayList.add(new Car(51.78,19.44,false,69.0,"Jeep",false,false,R.drawable.jeap));
        carArrayList.add(new Car(51.73,19.47,false,48.0,"Mercedes",false,false,R.drawable.mercedes));
        carArrayList.add(new Car(1.0,62.0,false,55.0,"Fiat Tipo",false,false,R.drawable.fiat_tipo));
        carArrayList.add(new Car(1.0,52.0,false,70.0,"Toyota Auris",false,false,R.drawable.toyota_auris));
        carArrayList.add(new Car(1.0,62.0,false,20.5,"KIA Ceed GT",false,false,R.drawable.kia_ceed_gt));
        carArrayList.add(new Car(1.0,62.0,false,54.0,"Seat Toledo2",false,false,R.drawable.seat_toledo));
        carArrayList.add(new Car(1.0,42.0,false,50.0,"Audi2",false,false,R.drawable.auto2));
        carArrayList.add(new Car(1.0,45.0,false,69.0,"Jeep2",false,false,R.drawable.jeap));
        carArrayList.add(new Car(1.0,25.0,false,48.0,"Mercedes2",false,false,R.drawable.mercedes));
        carArrayList.add(new Car(1.0,27.0,false,55.0,"Fiat Tipo2",false,false,R.drawable.fiat_tipo));
        carArrayList.add(new Car(1.0,28.0,false,70.0,"Toyota Auris2",false,false,R.drawable.toyota_auris));
        carArrayList.add(new Car(1.0,29.0,false,20.5,"KIA Ceed GT2",false,false,R.drawable.kia_ceed_gt));
    }

    public CarsDataBase(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    public CarsDataBase() {
        carArrayList=new ArrayList<>();

        init();
    }
    public void addCar(Car car){
        carArrayList.add(car);
    }
    public Boolean deleteCar(String carsID){

        for(Car c: carArrayList){
            if(c.getCarsID().equals(carsID)){
                return carArrayList.remove(c);
            }

        }
        return false;
    }
    public Car getCar(String carsID) throws CarDoesNotExist{
      //  Car car=null;
        for(int i=0;i<carArrayList.size();i++){
            Log.i("getCat",carsID+"     " +carArrayList.get(i).getCarsID());
            if((    carArrayList.get(i).getCarsID()).equals(carsID)){
                return carArrayList.get(i);
            }

        }
        throw new CarDoesNotExist("Car with ID: "+carsID+" doesn't exist" );
    }
    public Boolean saveData(Context ctx){
        String filename = "CarData";
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(carArrayList);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void readData(Context ctx){
        String fileName="CarData";

        try {
            FileInputStream fileInputStream;
            fileInputStream=ctx.openFileInput(fileName);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            carArrayList= (ArrayList<Car>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }
}
