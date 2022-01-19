package com.abhishek.DemoHQL;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration confg = new Configuration().configure().addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(confg.getProperties()).buildServiceRegistry();
        SessionFactory sf = confg.buildSessionFactory(reg);
        Session session = sf.openSession();
        
        session.beginTransaction();
        //Random r = new Random();
        
//        for(int i = 1 ; i <= 50; i++) {
//        	Student s = new Student();
//        	s.setRollno(i);
//        	s.setName("Name" + i);
//        	s.setMarks(r.nextInt(100));
//        	session.save(s);
//        }
        
        //Query q = session.createQuery("from Student where rollno=7");
        //List<Student> students = q.list();
        
//        for(Student s: students) {
//        	System.out.println(s);
//        }
        
        //for projection
//        Query q = session.createQuery("select rollno,name,marks from Student s where s.marks > 50");
//        List<Object[]> students = (List<Object[]>) q.list();
//        
//        for(Object[] s: students) {
//        	System.out.println(s[0] + " " + s[1] + " " + s[2]);
//        }
        
        int b = 60;
        Query q = session.createQuery("select sum(marks) from Student s where s.marks > :b");
        q.setParameter("b", b);	
        //Object tMarks = (Object) q.uniqueResult();
        Long tMarks = (Long) q.uniqueResult();
        System.out.println(tMarks);
        
        session.getTransaction().commit();
    }
}
