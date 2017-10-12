package com.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.service.UserService;

/**
 * 定时器
 * 
 * @date 2017-10-10
 * @author qchen
 */
@Component
@PropertySource("classpath:config.properties")
@Transactional
public class TimeTask {
    @Autowired
    private UserService userService;
    @Value("#{config['logOverDays']}")
    private int logOverDays;

    /**
     * 将log表中超过指定天数的记录迁移到log_his表中定时器
     */
    @Scheduled(cron = "${tranferLogToHistory.cron}")
    private void tranferLogToHistory() {
        // 将log表中超过指定天数的日志转移到日志记录历史表log_his表中
        int flag = this.userService.tranferLogToHistory(logOverDays);
        if (flag > 0) {
            // 清除log表中超过指定天数的日志
            this.userService.delLogOverDays(logOverDays);
        }
    }
}
