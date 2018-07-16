package com.asc.holiday.service;

import com.asc.holiday.entity.Holiday;
import com.asc.holiday.repository.HolidayRepository;
import com.asc.holiday.utils.DateUtils;
import com.asc.holiday.utils.HolidayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User:dijian
 * Date:2018/6/28
 */
@Component
public class HolidayTask {
    public static final Logger LOGGER = LoggerFactory.getLogger(HolidayTask.class);

    @Qualifier("holidayRepository")
    @Autowired
    private HolidayRepository holidayRepository;

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IEmailServer emailService;

    public void holiday() {
        try {
            LOGGER.info("开始执行holiday");
            // 查最大日期
            String sql = "select max(c_date) cDate from cj_holiday";
            String maxDateStr = jdbcTemplate.queryForObject(sql, String.class);

            if (StringUtils.isNotBlank(maxDateStr)) {
                Calendar curDate = Calendar.getInstance();
                // 本月最后一天
                curDate.set(Calendar.DATE, curDate.getActualMaximum(Calendar.DATE));
                Date maxDate = DateUtils.stringToDate(maxDateStr, "yyyy-MM-dd");
                Date lastDate = DateUtils.stringToDate(DateUtils.dateToString(curDate.getTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
                LOGGER.info("数据库最大日期:{},本月最后一天:{}", maxDate, curDate.getTime());
                if (maxDate.compareTo(lastDate) < 0) {
                    // 数据库最大日期小于本月最后一天
                    // 抓取当月节假日
                    int year = curDate.get(Calendar.YEAR);
                    int month = curDate.get(Calendar.MONTH) + 1;
                    holiday(year, month);

                    // 抓取下月节假日
                    month += 1;
                    holiday(year, month);
                }  if (maxDate.compareTo(lastDate) > 0) {
                    // 数据库最大日期大于本月最后一天

                } else {
                    int year = curDate.get(Calendar.YEAR);
                    int month = curDate.get(Calendar.MONTH) + 1;

                    // 抓取下月节假日
                    month += 1;
                    holiday(year, month);
                }
            } else {
                // 抓取当月节假日
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                holiday(year, month);

                // 抓取下月节假日
                month += 1;
                holiday(year, month);

            }
            LOGGER.info("执行holiday结束");
        } catch (Exception e) {
            e.printStackTrace();
            emailService.sendSimpleMail("节假日", "获取节假日失败" + e.getMessage());
        }
    }

    public void holiday(int year, int month) {
        LOGGER.info("删除{}年{}月节假日", year, month);
        String delSql = "delete from cj_holiday where to_char(c_date,'yyyy-mm') = ?";
        jdbcTemplate.update(delSql, new Object[]{year + "-" + StringUtils.leftPad(String.valueOf(month), 2, "0")});
        LOGGER.info(delSql.replaceAll("\\?", "{}"), new Object[]{year + "-" + StringUtils.leftPad(String.valueOf(month), 2, "0")});

        LOGGER.info("获取{}年{}月节假日开始", year, month);
        List<Holiday> holidayList = HolidayUtil.getHolidayList(year, month);
        LOGGER.info("获取{}年{}月节假日结束", year, month);
        LOGGER.info("{}年{}月节假日：{}", year, month, holidayList);
        holidayRepository.save(holidayList);
        emailService.sendSimpleMail("节假日", "抓取" + year + "年" + month + "月节假日成功");
    }


    public void holiday(int year) {
        LOGGER.info("删除{}年节假日", year);
        String delSql = "delete from cj_holiday where to_char(c_date,'yyyy') = ?";
        jdbcTemplate.update(delSql, new Object[]{year});
        LOGGER.info(delSql.replaceAll("\\?", "{}"), new Object[]{year});

        LOGGER.info("获取{}年节假日开始", year);
        for (int i = 1; i <= 12; i++) {
            List<Holiday> holidayList = HolidayUtil.getHolidayList(year, i);
            holidayRepository.save(holidayList);
        }
        LOGGER.info("获取{}年节假日结束", year);

        emailService.sendSimpleMail("节假日", "抓取" + year + "年节假日成功");

    }
}
