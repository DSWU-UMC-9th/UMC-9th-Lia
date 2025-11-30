package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.entity.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    // 2) 특정 가게의 미션 목록
    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record StoreMissionDTO(
            Long missionId,
            String title,
            String content,
            Integer point,
            LocalDate createdAt
    ) {}

    // 3) 내가 진행중인 미션 목록
    @Builder
    public record MyProgressMissionListDTO(
            List<MyProgressMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MyProgressMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String title,
            MissionStatus status,
            LocalDate startedAt
    ) {}

    // 4) 미션 완료 응답
    @Builder
    public record MissionCompleteDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            MissionStatus status
    ) {}
}

