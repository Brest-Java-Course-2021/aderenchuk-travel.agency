package com.aderenchuk.brest.web_app;

import com.aderenchuk.brest.service.TourDtoService;
import com.aderenchuk.brest.service.TourService;
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

    private final TourService tourService;

    @Autowired
    public TourController(TourDtoService tourDtoService, TourService tourService) {
        this.tourDtoService = tourDtoService;
        this.tourService = tourService;
    }

    /**
     * Goto tours list page.
     *
     * @return view name
     */
    @GetMapping(value = "/tours")
    public final String tours(Model model) {
        LOGGER.debug("tours()");
        model.addAttribute("tours", tourDtoService.findAllQuantityClients());
        return "tours";
    }

//    /**
//     * Goto edit tour page.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/tour/{id}")
//    public final String gotoEditTourPage(@PathVariable Integer id, Model model) {
//        return "tour";
//    }
//
//
//    /**
//     * Goto new tour page.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/tour/{id}")
//    public final String gotoEditAddTourPage(Model model) {
//        return "tour";
//    }
}
