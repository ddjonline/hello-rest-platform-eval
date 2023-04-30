package com.ddjonline.hello.springboot.hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello (" + counter.incrementAndGet() + ")";
    }

    @GetMapping("/naptime")
    @ResponseBody
    public String naptime() {
        return "your slice of pi is " + pi_digits(20000);
    }

    private static String pi_digits(int digits) {
        int scale = 10000;
        int array_init = 2000;
        StringBuffer pi = new StringBuffer();
        int[] arr = new int[digits + 1];
        int carry = 0;

        for (int i = 0; i <= digits; ++i)
            arr[i] = array_init;

        for (int i = digits; i > 0; i -= 14) {
            int sum = 0;
            for (int j = i; j > 0; --j) {
                sum = sum * j + scale * arr[j];
                arr[j] = sum % (j * 2 - 1);
                sum /= j * 2 - 1;
            }

            pi.append(String.format("%04d", carry + sum / scale));
            carry = sum % scale;
        }
        return pi.toString();
    }
}