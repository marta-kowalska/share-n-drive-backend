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
        Predicate predicateBrand = null;
        Predicate predicatePrice = null;
        Predicate predicateSeat = null;
        Predicate predicateFuelType = null;
        Predicate predicateColor = null;
        Predicate predicateBodyType = null;
        Predicate predicateCarType = null;
        Predicate predicateDoors = null;
        Predicate predicateTransmission = null;

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
                        predicateBrand = criteriaBuilder.or(predicatesBrand.toArray(new Predicate[predicatesBrand.size()]));
                    }
                    break;
                case "color":
                    List<Predicate> predicatesColor = new ArrayList<>();
                    Path<String> colorPath = car.get("color");
                    for (String color : params.get(param)) {
                        predicatesColor.add(criteriaBuilder.like(colorPath, color));
                        predicateColor = criteriaBuilder.or(predicatesColor.toArray(new Predicate[predicatesColor.size()]));
                    }
                    break;
                case "price":
                    Path<Integer> intPath = car.get("price");
                    int maxPrice = Integer.parseInt((params.get("price")).get(0));
                    predicatePrice = criteriaBuilder.lessThanOrEqualTo(intPath, maxPrice);
                    break;
                case "doors":
                    List<Predicate> predicatesDoors = new ArrayList<>();
                    Path<Integer> doorsPath = car.get("doors");
                    for (String doorNumber : params.get(param)) {
                        predicatesDoors.add(criteriaBuilder.equal(doorsPath, Integer.parseInt(doorNumber)));
                    }
                    predicateDoors = criteriaBuilder.or(predicatesDoors.toArray(new Predicate[predicatesDoors.size()]));
                    break;
                case "seatNumber": // TODO change or remove
                    Path<Integer> seatPath = car.get("seatNumber");
                    int minSeatNumber = Integer.parseInt((params.get("seat_number")).get(0));
                    predicateSeat = criteriaBuilder.greaterThanOrEqualTo(seatPath, minSeatNumber);
                    break;

                case "fuelType":
                    List<Predicate> predicatesFuelType = new ArrayList<>();
                    Path<FuelType> fuelTypePath = car.get("fuelType");
                    for (String fuelType : params.get(param)) {
                        predicatesFuelType.add(criteriaBuilder.equal(fuelTypePath, FuelType.getTypeByName(fuelType)));
                        predicateFuelType = criteriaBuilder.or(predicatesFuelType.toArray(new Predicate[predicatesFuelType.size()]));
                    }
                    break;
                case "bodyType":
                    List<Predicate> predicatesBodyType = new ArrayList<>();
                    Path<FuelType> bodyTypePath = car.get("bodyType");
                    for (String bodyType : params.get(param)) {
                        predicatesBodyType.add(criteriaBuilder.equal(bodyTypePath, BodyType.getTypeByName(bodyType)));
                        predicateBodyType = criteriaBuilder.or(predicatesBodyType.toArray(new Predicate[predicatesBodyType.size()]));
                    }
                    break;
                case "carType":
                    List<Predicate> predicatesCarType = new ArrayList<>();
                    Path<FuelType> carTypePath = car.get("carType");
                    for (String carType : params.get(param)) {
                        predicatesCarType.add(criteriaBuilder.equal(carTypePath, CarType.getTypeByName(carType)));
                        predicateCarType = criteriaBuilder.or(predicatesCarType.toArray(new Predicate[predicatesCarType.size()]));
                    }
                    break;
                case "transmission":
                    Path<Transmission> transmissionPath = car.get("transmission");
                    String transmissionType = params.get("transmission").get(0);
                    predicateTransmission = criteriaBuilder.equal(transmissionPath, Transmission.getTypeByName(transmissionType));
                    break;
            }

        getNonNullPredicates(allPredicates, predicateBrand);
        getNonNullPredicates(allPredicates, predicatePrice);
        getNonNullPredicates(allPredicates, predicateSeat);
        getNonNullPredicates(allPredicates, predicateFuelType);
        getNonNullPredicates(allPredicates, predicateColor);
        getNonNullPredicates(allPredicates, predicateBodyType);
        getNonNullPredicates(allPredicates, predicateCarType);
        getNonNullPredicates(allPredicates, predicateDoors);
        getNonNullPredicates(allPredicates, predicateTransmission);

        Predicate finalPredicate
            = criteriaBuilder.and(allPredicates.toArray(new Predicate[allPredicates.size()]));
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private void getNonNullPredicates(List<Predicate> allPredicates, Predicate predicate) {
        if (predicate != null) {
            allPredicates.add(predicate);
        }
    }

}
