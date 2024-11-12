package com.apisample.address.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apisample.address.DTO.ZipCodeDto;

@Service
public class ZipCodeService {
    @Autowired
    RestTemplate restTemplate;

    private static final String URL="http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

    public ZipCodeDto service(String zipcode) {
        return restTemplate.getForObject(URL, ZipCodeDto.class, zipcode);
    }
}
