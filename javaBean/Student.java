package javaBean;

import java.io.Serializable;

public class Student implements Serializable{

	private String name;
     
     public String getName(){
          return name;
     }
     
     public void setName(String name){
        this.name = name;
     }
     
     public Student(String name){
        super();
        this.name = name;
     }
     
     public Student(){}
     
     public String toString(){
         return "Student[name="+name+"]";
     }
}
