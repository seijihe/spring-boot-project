package com.seijihe.rest_with_spring_boot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.service.MathService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController 
@RequestMapping("/math")
public class MathController {

    // When we don't have the @Autowired
    private MathService service = new MathService();
    
    //http://localhost:8080/math/sum/3/5
    @RequestMapping(
        value = "/sum/{numberOne}/{numberTwo}",
        method = RequestMethod.GET
    )
    public Double sum(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo){
        
        return service.sum(numberOne, numberTwo);

        // if (true) throw new Exception(); // Mock assemble to mount the method body
        //return 1D;
    }

    @RequestMapping(
        value = "/subtraction/{numberOne}/{numberTwo}",
        method = RequestMethod.GET
    )
    public Double subtraction(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.subtraction(numberOne, numberTwo);
    }

    @RequestMapping(
        value = "/multiplication/{numberOne}/{numberTwo}",
        method = RequestMethod.GET
    )
    public Double multiplication(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.multiplication(numberOne, numberTwo);

    }

    @RequestMapping(
        value = "/division/{numberOne}/{numberTwo}",
        method = RequestMethod.GET
    )
    public Double divide(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.division(numberOne, numberTwo);

    }

    @RequestMapping(
        value = "/mean/{numberOne}/{numberTwo}",
        method = RequestMethod.GET
    )
    public Double mean(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.mean(numberOne, numberTwo);

    }

    @RequestMapping(
        value = "/square-root/{number}",
        method = RequestMethod.GET
    )
    public Double squareRoot(
        @PathVariable String number) {
        
        return service.squareRoot(number);

    }
    
}
