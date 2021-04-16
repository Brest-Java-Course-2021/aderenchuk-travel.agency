package com.aderenchuk.brest.web_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourDtoService;
import com.aderenchuk.brest.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    /**
     * Goto edit tour page.
     *
     * @return view name
     */
    @GetMapping(value = "/tour/{id}")
    public final String gotoEditTourPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditTourPage({}, {})", id, model);
        Optional<Tour> optionalTour = tourService.findById(id);
        if (optionalTour.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("tour", optionalTour.get());
            return "tour";
        } else {
            return "redirect:tours";
        }
    }


    /**
     * Goto new tour page.
     *
     * @return view name
     */
    @GetMapping(value = "/tour")
    public final String gotoEditAddTourPage(Model model) {
        LOGGER.debug("gotoAddTourPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("tour", new Tour());
        return "tour";
    }

    /**
     * Persist new tour into persistence storage.
     *
     * @param tour new tour with filled data.
     * @return view name
     */
    @PostMapping(value = "/tour")
    public String addTour(Tour tour) {
        LOGGER.debug("addTour({}, {})", tour);
        this.tourService.create(tour);
        return "redirect:/tours";
    }

    /**
     * Update tour.
     *
     * @param tour tour with filled data.
     * @return view name
     */
    @PostMapping(value = "/tour/{id}")
    public String updateTour(Tour tour) {
        LOGGER.debug("updateTour({}, {})", tour);
        this.tourService.update(tour);
        return "redirect:/tours";
    }

    /**
     * Delete tour.
     *
     * @return view name
     */
    @PostMapping(value = "/tour/{id}/delete")
    public String deleteTour(@PathVariable Integer id, Model model){
        LOGGER.debug("deleteTour({}, {})");
        tourService.delete(id);
        return "redirect:/tours";
    }
}
