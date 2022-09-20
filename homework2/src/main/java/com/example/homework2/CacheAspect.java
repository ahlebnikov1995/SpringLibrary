package com.example.homework2;

import com.example.homework2.models.Book;
import com.example.homework2.service.ServiceBookI;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

        }catch(IOException ex){
            System.out.println("ВСЁ НАКРЫЛОСЬ" + ex.getMessage());
        }
    }
}
