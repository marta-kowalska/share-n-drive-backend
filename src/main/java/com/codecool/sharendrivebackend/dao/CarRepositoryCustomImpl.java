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
            for(String value : params.get(param)){
                predicates.add(cb.like(path, value));
            }
        }
        query.select(car)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
            .getResultList();
    }

}
