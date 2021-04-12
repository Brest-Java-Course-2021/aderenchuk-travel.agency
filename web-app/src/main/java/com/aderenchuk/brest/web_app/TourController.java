package com.aderenchuk.brest.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TourController {

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
