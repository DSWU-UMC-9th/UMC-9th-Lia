package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionStatus;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private static final int PAGE_SIZE = 10;

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public MissionResDTO.StoreMissionListDTO getStoreMissions(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);

        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toStoreMissionListDTO(result);
    }

    @Override
    public MissionResDTO.MyProgressMissionListDTO getMyProgressMissions(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);

        Page<MemberMission> result =
                memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.IN_PROGRESS, pageRequest);

        return MissionConverter.toMyProgressMissionListDTO(result);
    }

    @Override
    @Transactional
    public MissionResDTO.MissionCompleteDTO completeMission(Long memberId, Long memberMissionId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findByIdAndMember(memberMissionId, member)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.IN_PROGRESS) {
            throw new MissionException(MissionErrorCode.ALREADY_COMPLETED);
        }

        // 진행중 → 완료 처리 (엔티티 비즈니스 로직 사용)
        memberMission.complete();

        return MissionConverter.toMissionCompleteDTO(memberMission);
    }

}
