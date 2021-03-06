package fun.goodgoodstudy.bestpractice.urbanboot.urbanmanagement.controller;

import fun.goodgoodstudy.bestpractice.urbanboot.urbanmanagement.contract.pojo.TrafficLightColor;
import fun.goodgoodstudy.bestpractice.urbanboot.urbanmanagement.service.TrafficLightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class TrafficLightController {
    @Autowired
    private TrafficLightService trafficLightService;

    @GetMapping(path = "/urbanboot/cities/{cityId}/traffic-lights-count")
    public Integer getCityTrafficLightCount(
            @PathVariable("cityId") UUID cityId,
            @RequestParam(value = "color", required = false) String color) {
        if (cityId == null) {
            throw new IllegalArgumentException("城市ID不能为空");
        }

        TrafficLightColor colorType = TrafficLightColor.parse(color);
        if (colorType != null) {
            return trafficLightService.getCityTrafficLightCountByColor(cityId, colorType);
        } else {
            return trafficLightService.getCityTrafficLightCount(cityId);
        }
    }
}
