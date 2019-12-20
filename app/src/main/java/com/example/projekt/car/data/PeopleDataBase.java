package com.example.projekt.car.data;

import android.content.Context;

import com.example.projekt.car.Exceptions.PersonDoesNotExist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PeopleDataBase {
    private ArrayList<Person> personArrayList;

    private void  init(){
        personArrayList.add(new Person("Adam","Kowalski","adamKowalski@gmail.com","admin"));
        personArrayList.add(new Person("Michał","Kowalski","michalKowalski@gmail.com","admin"));
        personArrayList.add(new Person("Mateusz","Kowalski","mateuszKowalski@gmail.com","admin"));
        personArrayList.add(new Person("Szymon","Kowalski","szymonKowalski@gmail.com","admin"));
        personArrayList.add(new Person("Artur","Kowalski","arturKowalski@gmail.com","admin"));
        personArrayList.add(new Person("admin","admin","admin","admin"));

    }
    public PeopleDataBase(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    public PeopleDataBase() {
        personArrayList=new ArrayList<>();
        init();
    }
    public void addPerson(Person person){
        personArrayList.add(person);
    }
    public Boolean isExist(String email){
        for(Person p: personArrayList){
            if(p.getEmail().equals(email)){
                return true;
            }

        }
        return false;
    }
    public Boolean deletePerson(String email){


        for(Person p: personArrayList){
            if(p.getEmail().equals(email)){
               return personArrayList.remove(p);
            }

        }

        return false;
    }
    public Person getPerson(String email) throws PersonDoesNotExist{

        for(int i=0;i<personArrayList.size();i++){
            if(personArrayList.get(i).getEmail().equals(email))
                return personArrayList.get(i);


        }


        throw new PersonDoesNotExist("Person with email: "+email+" doesn't exist" );
    }
    public int size(){
        return personArrayList.size();
    }
    public Boolean saveData(Context ctx){
        String filename = "PersonData";
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(personArrayList);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void readData(Context ctx){
        String fileName="PersonData";

        try {
            FileInputStream fileInputStream;
            fileInputStream=ctx.openFileInput(fileName);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            personArrayList= (ArrayList<Person>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
