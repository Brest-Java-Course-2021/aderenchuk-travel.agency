//package com.aderenchuk.brest.service.soap_app;
//
//import com.aderenchuk.brest.model.Tour;
//import com.aderenchuk.brest.service.TourService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//@Endpoint
//public class TourController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);
//
//    private TourService tourService;
//
//    @Autowired
//    public TourController(TourService tourService) {
//        this.tourService = tourService;
//    }
//
//    /**
//     * Go to tours list page
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/tours")
//    public final Collection<Tour> tours() {
//        LOGGER.debug("tours()");
//        return tourService.findAll();
//    }
////    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStateRequest")
////    @ResponsePayload
////    public GetStateResponse getState(@RequestPayload GetStateRequest request) {
////        GetStateResponse response = new GetStateResponse();
////        response.setState(stateRepository.findState(request.getId()));
////
////        return response;
////    }
//
////    /**
////     * find tour by id
////     * @param id
////     * @return tour
////     */
////    @GetMapping(value = "tours/{id}")
////    public ResponseEntity<Tour> tourById(@PathVariable Integer id) {
////        LOGGER.debug("find tour by id({})", id);
////
////        Optional<Tour> optionalTour = tourService.findById(id);
////        return optionalTour.isPresent()
////                ? new ResponseEntity<>(optionalTour.get(), HttpStatus.OK)
////                : new ResponseEntity(
////                        new ErrorResponse(List.of("Can't find Tour with such id")),
////                HttpStatus.NOT_FOUND);
////    }
//
//    /**
//     * create new tour
//     * @param tour
//     * @return id tour
//     */
//    @PostMapping(path = "/tours", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Integer> createTour(@RequestBody Tour tour) {
//        LOGGER.debug("createTour({})", tour);
//        Integer id = tourService.create(tour);
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }
//
//    /**
//     * Update tour
//     * @param tour
//     * @return id tour
//     */
//    @PutMapping(value = "/tours", consumes = {"application/json"}, produces = {"application/json"})
//    public ResponseEntity<Integer> updateTour(@RequestBody Tour tour) {
//        LOGGER.debug("updateTour({})", tour);
//        int result = tourService.update(tour);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    /**
//     * delete tour
//     * @param id
//     * @return result of delete
//     */
//    @DeleteMapping(value = "/tours/{id}", produces = {"application/json"})
//    public ResponseEntity<Integer> deleteTour (@PathVariable Integer id) {
//        int result = tourService.delete(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//}