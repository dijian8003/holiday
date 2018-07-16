package com.asc.holiday;

import com.asc.holiday.entity.Holiday;
import com.asc.holiday.service.HolidayTask;
import com.asc.holiday.utils.HolidayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
public class HolidayApplication implements CommandLineRunner {


    @Autowired
    private HolidayTask holidayTask;

    public static void main(String[] args) {
		SpringApplication.run(HolidayApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        if (args != null) {
            if (args.length == 2) {
                holidayTask.holiday(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            } else {
                holidayTask.holiday(Integer.parseInt(args[0]));
            }
        } else {
            holidayTask.holiday();
        }
    }
}
