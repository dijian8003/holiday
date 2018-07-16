package com.asc.holiday.repository;

import com.asc.holiday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * User:dijian
 * Date:2018/6/27
 */
public interface HolidayRepository extends JpaRepository<Holiday, String>,JpaSpecificationExecutor<Holiday> {

}
