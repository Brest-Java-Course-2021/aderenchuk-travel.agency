package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.ClientService;
import com.aderenchuk.brest.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    @Autowired
    ClientService clientService;

    @Autowired
    TourService tourService;

    /**
     * Goto client page.
     *
     * @return view name
     */
    @GetMapping(value = "/clients")
    public final String clients(Model model) {
        LOGGER.debug("clients()");
        model.addAttribute("clients", clientService.findAll());
        return "clients";
    }

    /**
     * Goto edit client page.
     *
     * @return view name
     */
    @GetMapping(value = "/client/{id}")
    public final String gotoEditClientPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditClientPage({}, {})", id, model);
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("client", optionalClient.get());
            model.addAttribute("tours", tourService.findAll());
            return "client";
        } else  {
            return "redirect:clients";
        }
    }

    /**
     * Update client.
     *
     * @param client client with filled data.
     * @param result binding result
     * @return       view name
     */
    @PostMapping(value = "/tour/{id}")
    public String updateClient(Client client, BindingResult result) {
        LOGGER.debug("updateClient({}, {})", client, result);
        if (result.hasErrors()) {
            return "tour";
        } else  {
            this.clientService.update(client);
            return "redirect:/tours";
        }
    }

    /**
     * Goto add client page.
     *
     * @return view name
     */
    @GetMapping(value = "/client")
    public final String gotoAddClientPage(Model model) {
        LOGGER.debug("gotoAddClientPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("client", new Client(1));
        model.addAttribute("tours", tourService.findAll());
        return "client";
    }

    /**
     * Persist new client into persistence storage.
     *
     * @param client new client with filled data.
     * @param result binding result.
     * @return view name
     */
    @PostMapping(value = "/client")
    public String addClient(Client client, BindingResult result) {
        LOGGER.debug("addClient({}, {})", client, result);
        if(result.hasErrors()) {
            return "client";
        } else {
            this.clientService.create(client);
            return "redirect:/clients";
        }
    }

    /**
     * Delete client.
     *
     * @return view name
     */
    @GetMapping(value = "/client/{clientId}/delete")
    public final String deleteClientById(Integer clientId, Model model) {
        LOGGER.debug("delete({}, {})", clientId, model);
        clientService.delete(clientId);
        return "redirect:/tours";
    }
}
