package com.aderenchuk.brest.service.rest_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String VERSION = "1.0";

    @GetMapping("")
    public String getVersion() {
        return "Version: " + VERSION;
    }
}
