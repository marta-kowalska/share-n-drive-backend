package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CarRepositoryCustomImpl implements CarRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarsByCriteria(Map<String, List<String>> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();
        for (String param : params.keySet()) {
            Path<String> path = car.get(param);
            // TODO works only for strings, how to make it work for ints, date and enums?
            for(String value : params.get(param)){
                predicates.add(cb.like(path, value));
            }
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
            .getResultList();
    }

    @Override
    public List<Car> findCarsByBrand(List<String> brands) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> car = query.from(Car.class);

        Path<String> path = car.get("brand");

        List<Predicate> predicates = new ArrayList<>();
        for (String brand : brands) {
            predicates.add(cb.like(path, brand));
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
            .getResultList();
    }

}
