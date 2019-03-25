package com.restaurant.management.service.facade;

import com.restaurant.management.domain.DailyOrderList;
import com.restaurant.management.domain.dto.DailyOrderListDto;
import com.restaurant.management.mapper.DailyOrderListMapper;
import com.restaurant.management.service.DailyOrderListService;
import com.restaurant.management.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyOrderListFacade {

    private DailyOrderListService dailyOrderListService;
    private DailyOrderListMapper dailyOrderListMapper;

    @Autowired
    public DailyOrderListFacade(DailyOrderListService dailyOrderListService, DailyOrderListMapper dailyOrderListMapper) {
        this.dailyOrderListService = dailyOrderListService;
        this.dailyOrderListMapper = dailyOrderListMapper;
    }

    public DailyOrderListDto openOrderList() {
        DailyOrderList dailyOrderList = dailyOrderListService.openOrderList();

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderList);
    }

    public List<DailyOrderListDto> getAll() {
        List<DailyOrderList> dailyOrderLists = dailyOrderListService.getAll();

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderLists);
    }

    public DailyOrderListDto getOrderListByUniqueId(String uniqueId) {
        DailyOrderList dailyOrderList = dailyOrderListService.getOrderListByUniqueId(uniqueId);

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderList);
    }

    public ApiResponse deleteByUniqueId(String uniqueId) {
        return dailyOrderListService.deleteByUniqueId(uniqueId);
    }

    public DailyOrderListDto addOrderToList(String orderNumber) {
        DailyOrderList dailyOrderList = dailyOrderListService.addOrderToList(orderNumber);

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderList);
    }

    public DailyOrderListDto removeOrderFromList(String orderNumber) {
        DailyOrderList dailyOrderList = dailyOrderListService.removeOrderFromList(orderNumber);

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderList);
    }

    public DailyOrderListDto closeDailyList() {
        DailyOrderList dailyOrderList = dailyOrderListService.closeDailyList();

        return dailyOrderListMapper.mapToDailyOrderListDto(dailyOrderList);
    }
}
