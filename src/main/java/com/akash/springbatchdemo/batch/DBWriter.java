package com.akash.springbatchdemo.batch;

import com.akash.springbatchdemo.models.User;
import com.akash.springbatchdemo.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Log
public class DBWriter implements ItemWriter<User> {
    @Autowired
    private UserRepository repo;

    @Override
    public void write(List<? extends User> users) throws Exception {
        try {
            for (User user : users) {
                log.info("saving user: " + user);
                repo.save(user);
            }
        } catch (Exception e) {
            log.warning("Exception.. " + Arrays.toString(e.getStackTrace()));
        }
        log.info("Data saved for Users: " + users);
    }
}
