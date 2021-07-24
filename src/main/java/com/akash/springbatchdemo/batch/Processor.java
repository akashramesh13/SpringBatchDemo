package com.akash.springbatchdemo.batch;

import com.akash.springbatchdemo.models.User;
import lombok.extern.java.Log;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Log
public class Processor implements ItemProcessor<User, User> {
    private final Map<String, String> DEPT_NAMES = new HashMap<>();

    public Processor() {
        DEPT_NAMES.put("001", "TECHNOLOGY");
        DEPT_NAMES.put("002", "OPERATIONS");
        DEPT_NAMES.put("003", "ACCOUNTS");
    }

    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDept(dept);
        log.info(String.format("Changed from [%s] to [%s]", deptCode, dept));
        return user;
    }
}

