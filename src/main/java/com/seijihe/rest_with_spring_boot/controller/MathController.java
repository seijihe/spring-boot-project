package com.seijihe.rest_with_spring_boot.controller;

import org.springframework.web.bind.annotation.RestController;
import com.seijihe.rest_with_spring_boot.service.MathService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController 
@RequestMapping("/math")
public class MathController {

    private MathService service = new MathService();
    
    //http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
        @PathVariable("numberOne") String numberOne,
        @PathVariable("numberTwo") String numberTwo){
        
        return service.sum(numberOne, numberTwo);

        // if (true) throw new Exception(); // Mock assemble to mount the method body
        //return 1D;
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.subtraction(numberOne, numberTwo);
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.multiplication(numberOne, numberTwo);

    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double divide(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.division(numberOne, numberTwo);

    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
        @PathVariable String numberOne,
        @PathVariable String numberTwo) {
        
        return service.mean(numberOne, numberTwo);

    }

    @RequestMapping("/square-root/{number}")
    public Double squareRoot(
        @PathVariable String number) {
        
        return service.squareRoot(number);

    }
    
}
