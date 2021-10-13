package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Car> findCarsByCriteria(Map<String, List<String>> params) {
        List<Predicate> allPredicates = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = criteriaQuery.from(Car.class);

        for(String category : params.keySet()){
                List<Predicate> tempPredicates = new ArrayList<>();
                for(String value : params.get(category)){
                    tempPredicates.add(criteriaBuilder.equal(car.get(category), value));
                }
                allPredicates.add(criteriaBuilder.or(tempPredicates.toArray(new Predicate[0])));
            }
        Predicate finalPredicate
            = criteriaBuilder.and(allPredicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
