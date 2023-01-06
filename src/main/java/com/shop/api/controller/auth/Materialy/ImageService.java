package com.shop.api.controller.auth.Materialy;

public class ImageService {
    public void save(Image image) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(image);
        session.getTransaction().commit();
        session.close();
    }
}
