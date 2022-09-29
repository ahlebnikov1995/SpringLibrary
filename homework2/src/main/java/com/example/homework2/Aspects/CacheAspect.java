package com.example.homework2.Aspects;

import com.example.homework2.models.Book;
import com.example.homework2.service.ServiceBookI;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@Aspect
public class CacheAspect {


    @Pointcut("execution(public * com.example.homework2.service.ServiceBookBookC.findAllBook(..))")
    public void callAtMyServicePublic() {
    }


    @AfterReturning(pointcut = "callAtMyServicePublic()", returning = "books")
    public void cacheAfter(List<Book> books) {

        try (FileWriter writer = new FileWriter("C:\\Users\\AHlebnik\\Desktop\\spring\\honeWorks\\homework2\\src\\main\\resources\\cache", false)) {
            for (int i = 0; i < books.size(); i++) {
                writer.write(books.get(i).toString());
                writer.append('\n');
            }

        } catch (IOException ex) {
            System.out.println("ВСЁ НАКРЫЛОСЬ" + ex.getMessage());
        }
    }

    @After("execution(public void com.example.homework2.service.ServiceBookBookC.*(..))")
    public void cacheCleaner(JoinPoint joinPoint) {

        try (FileWriter writer = new FileWriter("C:\\Users\\AHlebnik\\Desktop\\spring\\honeWorks\\homework2\\src\\main\\resources\\cache", false)) {
            writer.close();
        } catch (IOException ex) {
            System.out.println("ВСЁ НАКРЫЛОСЬ" + ex.getMessage());
        }


    }
}

