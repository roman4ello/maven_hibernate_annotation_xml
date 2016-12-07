package com.journaldev.hibernate.main;

import com.journaldev.hibernate.model.Employee1;
import com.journaldev.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class HibernateAnnotationMain {

    private static final String names[] = {
            "Emily", "Michael", "Emma", "Joshua", "Madison",
            "Matthew", "Olivia", "Ethan", "Hannah", "Andrew",
            "Abigail", "Daniel", "Isabella", "William", "Ashley",
            "Joseph", "Samantha", "Christopher", "Elizabeth", "Anthony",
            "Alexis", "Ryan", "Sarah", "Nicholas", "Grace",
            "David", "Alyssa", "Alexander", "Sophia", "Tyler",
            "Lauren", "James", "Brianna", "John", "Kayla",
            "Dylan", "Natalie", "Nathan", "Anna", "Jonathan",
            "Jessica", "Brandon", "Taylor", "Samuel", "Chloe",
            "Christian", "Hailey", "Benjamin"
    };
    private static final String roles[] = {
            "QAEngineer", "CEO", "DEV", "Tester", "Manager"};


    public void HQLDwery1() {
        Employee1 emp = new Employee1();
        emp.setName("David");
        emp.setRole("Developer");
        emp.setInsertTime(new Date());

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID=" + emp.getId());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }

    public void HQLQwerySelectCortage(Integer id) {

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query query = session.createQuery("from Employee1 where id = " + id);
        Employee1 result = (Employee1) query.uniqueResult();


        session.getTransaction().commit();

        System.out.println("result.getId()   =" + result.getId());
        System.out.println("result.getName() = " + result.getName());
        System.out.println("result.getRole()  = " + result.getRole());
        System.out.println("result.getClass()  =" + result.getClass());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }

    //all employee
    public void HQLQwerySelectAllCortages() {

        String myHQL = "from Employee1";

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Employee1.class);
        cr.add(Restrictions.between("insertTime", new Date(1 * 29981100000L), new Date(20 * 29981100000L)));
//        cr.add(Restrictions.between("insertTime", new Date(1 * 29981100000L), new Date(20 * 29981100000L)));
//       cr.add(Restrictions.eq("name", "Chloe"));
//        List results = cr.list();
        ArrayList<Employee1> employee1s = new ArrayList<Employee1>(cr.list());
        for (Employee1 employee1 : employee1s) {
            System.out.println(employee1);
        }

//        Query query = session.createQuery(myHQL);
//        ArrayList<Employee1> result_list = new ArrayList<Employee1>(query.list());
//
//        if (result_list.size() == 0) {
//            System.out.println("Sorry, but no cartage in this DataBase");
//        } else {
//            for (Employee1 employee1 : result_list) {
//                System.out.println(employee1);
//            }
//        }

        session.getTransaction().commit();
        sessionFactory.close();
    }

    //employee by name
    public void HQLQwerySelectCortageByName(String nameOFemployee) {
        String myHQL = "FROM Employee1 WHERE name = '" + nameOFemployee + "'";

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery(myHQL);
        ArrayList<Employee1> result_list = new ArrayList<Employee1>(query.list());

        if (result_list.size() == 0) {
            System.out.println("Sorry, but no cartage with name = " + nameOFemployee + " in this DataBase");
        } else {
            for (Employee1 employee1 : result_list) {
                System.out.println(employee1);
            }
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }

    //employee by date
    public void HQLQwerySelectCortageByDate(String insertTime) {
        String myHQL = "FROM Employee1 WHERE insertTime = '" + insertTime + "'";

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery(myHQL);
        ArrayList<Employee1> result_list = new ArrayList<Employee1>(query.list());

        if (result_list.size() == 0) {
            System.out.println("Sorry, but no cartage with insertTime = " + insertTime + " in this DataBase");
        } else {
            for (Employee1 employee1 : result_list) {
                System.out.println(employee1);
            }
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }

    //employee added after date
    public void HQLQwerySelectCortagesAfterDate(String insertTime) {
        String myHQL = "FROM Employee1 WHERE insertTime >= '" + insertTime + "'";

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery(myHQL);
        ArrayList<Employee1> result_list = new ArrayList<Employee1>(query.list());

        if (result_list.size() == 0) {
            System.out.println("Sorry, but no cartage with insertTime = " + insertTime + " in this DataBase");
        } else {
            for (Employee1 employee1 : result_list) {
                System.out.println(employee1);
            }
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }

    //employee added between dates
    public void HQLQwerySelectCortagesBetweenDates(String insertTimeFrom, String insertTimeTo, boolean inclusiveFlag) {

        String inclusiveHQL = "FROM Employee1 WHERE insertTime >= '" + insertTimeFrom + "' AND insertTime <= '" + insertTimeTo + "'";
        String unInclusiveHQL = "FROM Employee1 WHERE insertTime > '" + insertTimeFrom + "' AND insertTime < '" + insertTimeTo + "'";
        String myHQL = inclusiveFlag == true ? inclusiveHQL : unInclusiveHQL;

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery(myHQL);
        ArrayList<Employee1> result_list = new ArrayList<Employee1>(query.list());

        if (result_list.size() == 0) {
            if (inclusiveFlag) {
                System.out.println("Sorry, but no cartage during:  " + insertTimeFrom + " and " + insertTimeTo + " inclusing in this DataBase");
            } else {

                System.out.println("Sorry, but no cartage during:  " + insertTimeFrom + " and " + insertTimeTo + " unIncluding in this DataBase");
            }

        } else {
            for (Employee1 employee1 : result_list) {
                System.out.println(employee1);
            }
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }


    //insert employees
    public void HQLQweryInsertCortages() {

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Employee1 employee1;

        for (int i = 0; i < names.length - 1; i++) {

            employee1 = new Employee1();
            employee1.setName(names[new Random().nextInt(names.length)]);
            employee1.setRole(roles[new Random().nextInt(roles.length)]);
            employee1.setInsertTime(new Date(i * 29981100000L));
            session.save(employee1);
        }

        session.getTransaction().commit();
        sessionFactory.close();
    }


    public static void main(String[] args) {

        HibernateAnnotationMain hibernateAnnotationMain = new HibernateAnnotationMain();
        hibernateAnnotationMain.HQLQwerySelectAllCortages();

//        hibernateAnnotationMain.HQLQwerySelectCortageByName("David");
//        hibernateAnnotationMain.HQLQwerySelectCortageByDate("2016-11-27");
//        hibernateAnnotationMain.HQLQwerySelectCortagesAfterDate("2016-11-24");
//        hibernateAnnotationMain.HQLQwerySelectCortagesBetweenDates("2016-11-01", "2016-11-27", false);
//        hibernateAnnotationMain.HQLQweryInsertCortages();

    }

}
