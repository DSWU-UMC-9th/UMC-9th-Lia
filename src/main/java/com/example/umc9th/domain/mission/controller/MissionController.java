package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionStatus;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private static final Long HARD_CODED_MEMBER_ID = 1L; // DB에 실제 있는 회원 id 로 맞추기

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 3번. 가게에 미션 추가하기 API
     * POST /api/stores/{storeId}/missions
     */
    @PostMapping("/stores/{storeId}/missions")
    @Operation(
            summary = "가게에 미션 추가",
            description = "storeId 에 해당하는 가게에 새로운 미션을 등록합니다."
    )
    public ApiResponse<Mission> createMission(@PathVariable Long storeId,
                                              @RequestBody Mission missionRequest) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Mission mission = Mission.builder()
                .store(store)
                .title(missionRequest.getTitle())
                .description(missionRequest.getDescription())
                .rewardPoint(missionRequest.getRewardPoint())
                .status(MissionStatus.OPEN) // 기본은 OPEN 상태
                .build();

        Mission saved = missionRepository.save(mission);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, saved);
    }

    @PostMapping("/missions/{missionId}/challenge")
    @Operation(
            summary = "미션 도전하기",
            description = "로그인이 없어 memberId=1을 하드코딩해서 선택한 미션에 도전 상태를 추가합니다."
    )
    public ApiResponse<MemberMission> challengeMission(@PathVariable Long missionId) {

        // 1. 미션 찾기
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        // 2. 하드코딩된 유저 찾기
        Member member = memberRepository.findById(HARD_CODED_MEMBER_ID)
                .orElseThrow(() -> new IllegalArgumentException("하드코딩한 회원이 DB에 없습니다."));

        // 3. MemberMission 생성 (도전 중 상태)
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();

        // 4. 저장
        MemberMission saved = memberMissionRepository.save(memberMission);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, saved);
    }
}
