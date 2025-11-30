package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {

    // 2) 특정 가게의 미션 목록
    MissionResDTO.StoreMissionListDTO getStoreMissions(Long storeId, Integer page);

    // 3) 내가 진행중인 미션 목록
    MissionResDTO.MyProgressMissionListDTO getMyProgressMissions(Long memberId, Integer page);

    // 4) 진행중인 미션 완료 처리
    MissionResDTO.MissionCompleteDTO completeMission(Long memberId, Long memberMissionId);
}
