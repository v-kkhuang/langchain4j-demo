package org.example.LangChain.resetful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/langchain")
public class LangChainrestful {
    @RequestMapping(name = "/demo",method = RequestMethod.GET)
    public String demo(){
        return "demo";
    }
}
