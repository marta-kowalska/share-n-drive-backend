package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarsByStringValue(List<String> values, String paramName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<String> path = car.get(paramName);

        List<Predicate> predicates = new ArrayList<>();
        for (String value : values) {
            predicates.add(cb.like(path, value));
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
            .getResultList();
    }

    @Override
    public List<Car> findCarsByFuelType(List<String> fuelTypes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<FuelType> path = car.get("fuelType");

        List<Predicate> predicates = new ArrayList<>();
        for (String type : fuelTypes) {
            predicates.add(cb.equal(path, FuelType.getTypeByName(type)));
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
            .getResultList();
    }

    @Override
    public List<Car> findCarsByBodyType(List<String> bodyTypes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<BodyType> path = car.get("bodyType");

        List<Predicate> predicates = new ArrayList<>();
        for (String type : bodyTypes) {
            predicates.add(cb.equal(path, BodyType.getTypeByName(type)));
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
            .getResultList();
    }

    @Override
    public List<Car> findCarsByCarType(List<String> carTypes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<CarType> path = car.get("carType");

        List<Predicate> predicates = new ArrayList<>();
        for (String type : carTypes) {
            predicates.add(cb.equal(path, CarType.getTypeByName(type)));
        }
        query.select(car)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public List<Car> findCarsByTransmissionType(List<String> transmission) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<CarType> path = car.get("transmission");

        List<Predicate> predicates = new ArrayList<>();
        for (String type : transmission) {
            predicates.add(cb.equal(path, Transmission.getTypeByName(type)));
        }
        query.select(car)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
                .getResultList();
    }

}
