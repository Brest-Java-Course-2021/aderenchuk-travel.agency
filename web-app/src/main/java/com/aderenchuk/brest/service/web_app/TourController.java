package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import com.aderenchuk.brest.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    @Autowired
    private final TourDtoService tourDtoService;

    @Autowired
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
    @GetMapping
    public final String tours(@RequestParam(value = "dateFrom", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                              @RequestParam(value = "dateTo", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                              Model model) {

        if (dateFrom != null && dateTo != null) {
            LOGGER.debug("Find tours between dates. dateFrom = {}, dateTo = {}", dateFrom, dateTo);
            List<TourDto> tourDtoList = tourDtoService.findAllQuantityClientsAndDateFilter(dateFrom, dateTo);
            model.addAttribute("tours", tourDtoList);
        } else  {
            LOGGER.debug("Find all with quantity clients");
            List<TourDto> tourDtoList = tourDtoService.findAllQuantityClients();
            model.addAttribute("tours", tourDtoList);
        }
        return "tours";
    }

    /**
     * Goto edit tour page.
     *
     * @return view name
     */
    @GetMapping(value = "/{tourId}")
    public final String gotoEditTourPage(@PathVariable Integer tourId, Model model) {
        LOGGER.debug("gotoEditTourPage({},{})", tourId, model);
        Optional<Tour> optionalTour = tourService.findById(tourId);
        if (optionalTour.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("tour", optionalTour.get());
            return "tour";
        } else {
            return "redirect:tours";
        }
    }

    /**
     * Update tour.
     *
     * @param tour tour with filled data.
     * @return view name
     */
    @PostMapping(value = "/{id}")
    public String updateTour(@ModelAttribute("tour") Tour tour, BindingResult result, Model model) {
        LOGGER.debug("updateTour({}, {})", tour, result);
        model.addAttribute("tourEntity", tour);
        if(result.hasErrors()) {
            return "tour";
        } else {
            this.tourService.update(tour);
            return "redirect:/tours";
        }
    }

    /**
     * Goto new tour page.
     *
     * @return view name
     */
    @GetMapping(value = "/add")
    public final String gotoAddTourPage(Model model) {
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
    @PostMapping(value = "/add")
    public String addTour(Tour tour, BindingResult result) {
        LOGGER.debug("addTour({}, {})", tour, result);
        if (result.hasErrors()) {
            return "tour";
        } else {
            this.tourService.create(tour);
            return "redirect:/tours";
        }

    }

    /**
     * Delete tour.
     *
     * @return view name
     */
    @PostMapping(value = "/{tourId}/delete")
    public String deleteTour(@PathVariable Integer tourId, Model model){
        LOGGER.debug("deleteTour({}, {})", tourId, model);
        tourService.delete(tourId);
        return "redirect:/tours";
    }
}
