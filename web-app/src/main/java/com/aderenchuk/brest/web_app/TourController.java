package com.aderenchuk.brest.web_app;

import com.aderenchuk.brest.service.TourDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final TourDtoService tourDtoService;

    @Autowired
    public TourController(TourDtoService tourDtoService) {
        this.tourDtoService = tourDtoService;
    }

    @GetMapping(value = "/tours")
    private final String tours(Model model) {
        return "tours";
    }

    @GetMapping(value = "/tour/{id}")
    private final String gotoEditTourPage(@PathVariable Integer id, Model model) {
        return "tour";
    }

    @GetMapping(value = "/tour/{id}")
    private final String gotoEditAddTourPage(Model model) {
        return "tour";
    }
}
