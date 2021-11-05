package com.aderenchuk.brest.service.soap_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aderenchuk.brest.*;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class TourController {
    private static final String NAMESPACE_URI = "http://www.travel-agency.com/tour-ws";

    @Autowired
    private TourService tourService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTourByIdRequest")
    @ResponsePayload
    public GetTourByIdResponse getTour(@RequestPayload GetTourByIdRequest request) {
        GetTourByIdResponse response = new GetTourByIdResponse();
        TourInfo tourInfo = new TourInfo();
        BeanUtils.copyProperties(tourService.findById(request.getTourId()), tourInfo);
        response.setTourInfo(tourInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllToursRequest")
    @ResponsePayload
    public GetAllToursResponse getAllTours() {
        GetAllToursResponse response = new GetAllToursResponse();
        List<TourInfo> tourInfoList = new ArrayList<>();
        List<Tour> tourList = tourService.findAll();
        for (int i = 0; i < tourList.size(); i++) {
            TourInfo ob = new TourInfo();
            BeanUtils.copyProperties(tourList.get(i), ob);
            tourInfoList.add(ob);
        }
        response.getTourInfo().addAll(tourInfoList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTourRequest")
    @ResponsePayload
    public AddTourResponse addTour(@RequestPayload AddTourRequest request) {
        AddTourResponse response = new AddTourResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Tour tour = new Tour();
        Integer flag = tourService.create(tour);
        if (flag == 1) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        } else {
            TourInfo tourInfo = new TourInfo();
            BeanUtils.copyProperties(tour, tourInfo);
            response.setTourInfo(tourInfo);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTourRequest")
    @ResponsePayload
    public UpdateTourResponse updateTourResponse(@RequestPayload UpdateTourRequest request) {
        Tour tour = new Tour();
        BeanUtils.copyProperties(request.getTourInfo(), tour);
        tourService.update(tour);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateTourResponse response = new UpdateTourResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTourRequest")
    @ResponsePayload
    public DeleteTourResponse deleteTourResponse(@RequestPayload DeleteTourRequest request, Integer tourId) {
        Optional<Tour> tour = tourService.findById(request.getTourId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (tour == null) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            tourService.delete(tourId);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }
        DeleteTourResponse response = new DeleteTourResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
