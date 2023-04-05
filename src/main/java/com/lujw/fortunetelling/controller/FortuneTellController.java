package com.lujw.fortunetelling.controller;

import com.lujw.fortunetelling.entity.Response;
import com.lujw.fortunetelling.entity.User;
import com.lujw.fortunetelling.service.FortuneTellService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api")
public class FortuneTellController {

    @Resource
    private FortuneTellService fortuneTellService;

    @PostMapping(value = {"/query"})
    public @ResponseBody Response queryFortuneTell(@RequestBody User user) {
        return fortuneTellService.queryFortuneTell(user);
    }
}
