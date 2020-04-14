package com.cg.fm.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fm.config.SwaggerConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import lombok.extern.slf4j.Slf4j;

@Api(value = "User Management",tags = { SwaggerConfig.TAG_1 })
@SwaggerDefinition()
@RestController
@RequestMapping("/users")
@Validated
@Slf4j
public class UserController {

}
