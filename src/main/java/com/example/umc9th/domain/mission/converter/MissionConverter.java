package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MissionConverter {

    // 특정 가게의 미션 목록
    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> result) {
        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toStoreMissionDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())         // 필드명 맞게 수정
                .content(mission.getDescription())
                .point(mission.getRewardPoint())
                .createdAt(LocalDate.from(mission.getCreatedAt()))
                .build();
    }

    // 내가 진행중인 미션 목록
    public static MissionResDTO.MyProgressMissionListDTO toMyProgressMissionListDTO(Page<MemberMission> result) {
        return MissionResDTO.MyProgressMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMyProgressMissionDTO)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MyProgressMissionDTO toMyProgressMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MyProgressMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .title(memberMission.getMission().getTitle())
                .status(memberMission.getStatus())
                .startedAt(LocalDate.from(memberMission.getCreatedAt()))
                .build();
    }

    // 미션 완료 결과
    public static MissionResDTO.MissionCompleteDTO toMissionCompleteDTO(MemberMission memberMission) {
        return MissionResDTO.MissionCompleteDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .build();
    }
}

