package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("""
        SELECT DISTINCT m
        FROM Member m
        LEFT JOIN FETCH m.reviews
        LEFT JOIN FETCH m.memberMissions
        WHERE m.id = :memberId
    """)
    Optional<Member> findMemberDetail(@Param("memberId") Long memberId);
}

