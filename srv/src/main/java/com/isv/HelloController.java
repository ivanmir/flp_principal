package com.isv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "text/plain")
  @ResponseBody
  String home() {
    StringBuilder builder = new StringBuilder();
    builder.append("Hello World !!");

    return builder.toString();
  }

}
