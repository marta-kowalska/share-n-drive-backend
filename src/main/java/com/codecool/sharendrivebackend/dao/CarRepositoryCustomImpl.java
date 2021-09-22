package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.car.Car;
import com.codecool.sharendrivebackend.model.car.FuelType;
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
    public List<Car> findCarsByCriteria1(Map<String, List<String>> params) {
        Predicate predicateForBrand = null;
        Predicate predicateFoPrice = null;
        Predicate predicateFoSeat = null;
        Predicate predicateFuelType = null;
        List<Predicate> allPredicates = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> car = criteriaQuery.from(Car.class);
        for (String param : params.keySet())
            switch (param) {
                case "brand":
                    List<Predicate> predicatesBrand = new ArrayList<>();
                    Path<String> brandPath = car.get("brand");
                    for (String brand : params.get(param)) {
                        predicatesBrand.add(criteriaBuilder.like(brandPath, brand));
                        predicateForBrand = criteriaBuilder.or(predicatesBrand.toArray(new Predicate[predicatesBrand.size()]));
                    }
                    break;
                case "price":
                    Path<Integer> intPath = car.get("price");
                    int maxPrice = Integer.parseInt((params.get("price")).get(0));
                    predicateFoPrice = criteriaBuilder.lessThanOrEqualTo(intPath, maxPrice);
                    break;

                case "seat_number":
                    Path<Integer> seatPath = car.get("seatNumber");
                    int minSeatNumber = Integer.parseInt((params.get("seat_number")).get(0));
                    predicateFoSeat = criteriaBuilder.greaterThanOrEqualTo(seatPath, minSeatNumber);
                    break;

                case "fuel_type":
                    List<Predicate> predicatesFuelType = new ArrayList<>();
                    Path<FuelType> fuelTypePath = car.get("fuelType");
                    for (String fuelType : params.get(param)) {
                        predicatesFuelType.add(criteriaBuilder.equal(fuelTypePath, FuelType.getTypeByName(fuelType)));
                        predicateFuelType = criteriaBuilder.or(predicatesFuelType.toArray(new Predicate[predicatesFuelType.size()]));
                    }
                    break;
            }

        getNonNullPredicates(allPredicates, predicateForBrand);
        getNonNullPredicates(allPredicates, predicateFoPrice);
        getNonNullPredicates(allPredicates, predicateFoSeat);
        getNonNullPredicates(allPredicates, predicateFuelType);

        Predicate finalPredicate
            = criteriaBuilder.and(allPredicates.toArray(new Predicate[allPredicates.size()]));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    private void getNonNullPredicates(List<Predicate> allPredicates, Predicate predicate){
        if(predicate!=null){
            allPredicates.add(predicate);
        }
    }

}
