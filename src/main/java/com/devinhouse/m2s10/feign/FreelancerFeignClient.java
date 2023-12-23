package com.devinhouse.m2s10.feign;

import com.devinhouse.m2s10.model.Freelancer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "freelancerApi", url = "URL_DA_API_EXTERNA")
public interface FreelancerFeignClient {

    @GetMapping("/jornalistas")
    List<Freelancer> getFreelancerJornalistas();
}
