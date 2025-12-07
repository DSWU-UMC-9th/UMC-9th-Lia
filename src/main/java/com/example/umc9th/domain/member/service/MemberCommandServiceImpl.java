package com.example.umc9th.domain.member.service;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.Role;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        String encodedPw = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPw, Role.ROLE_USER);

        memberRepository.save(member);

        return MemberConverter.toJoinDTO(member);
    }
}
