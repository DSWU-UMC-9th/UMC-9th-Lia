package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regions")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreController {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @PostMapping("/{regionId}/stores")
    @Operation(
            summary = "특정 지역에 가게 추가",
            description = "regionId 로 지역을 찾고, 해당 지역에 새로운 가게를 등록합니다."
    )
    public ApiResponse<Store> createStore(@PathVariable Long regionId,
                                          @RequestBody Store storeRequest) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

        Store store = Store.builder()
                .name(storeRequest.getName())
                .address(storeRequest.getAddress())
                .region(region)
                .build();

        Store saved = storeRepository.save(store);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, saved);
    }


}
