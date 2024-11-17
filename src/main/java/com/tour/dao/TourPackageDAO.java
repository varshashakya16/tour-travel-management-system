package com.tour.dao;

import com.tour.entity.TourPackage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.tour.utils.HibernateUtil;

import java.util.List;

public class TourPackageDAO {

    // Save Tour Package
    public void saveTourPackage(TourPackage tourPackage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tourPackage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get Tour Package by ID
    public TourPackage getTourPackageById(int packageId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TourPackage.class, packageId);
        }
    }

    // Get all Tour Packages
    public List<TourPackage> getAllTourPackages() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TourPackage", TourPackage.class).list();
        }
    }

    // Update Tour Package
    public void updateTourPackage(TourPackage tourPackage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tourPackage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete Tour Package
    public void deleteTourPackage(int packageId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TourPackage tourPackage = session.get(TourPackage.class, packageId);
            if (tourPackage != null) {
                session.delete(tourPackage);
                System.out.println("Tour Package deleted successfully!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
