package com.tour.dao;

import com.tour.entity.Review;
import com.tour.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewDAO {

    // Save Review
    public void saveReview(Review review) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(review); // Hibernate automatically handles review_id
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get Review by ID
    public Review getReviewById(int reviewId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Review.class, reviewId);
        }
    }

    // Get All Reviews
    public List<Review> getAllReviews() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Review", Review.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if an exception occurs
        }
    }

    // Get all Reviews for a specific tour
    public List<Review> getReviewsByTourId(int tourId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Review WHERE tourPackage.packageId = :tourId", Review.class)
                          .setParameter("tourId", tourId)
                          .list();
        }
    }

    // Get all Reviews by a specific user
    public List<Review> getReviewsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Review WHERE user.userId = :userId", Review.class)
                          .setParameter("userId", userId)
                          .list();
        }
    }

    // Update Review
    public void updateReview(Review review) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete Review
    public void deleteReview(int reviewId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Review review = session.get(Review.class, reviewId);
            if (review != null) {
                session.delete(review);
                System.out.println("Review deleted successfully!");
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
