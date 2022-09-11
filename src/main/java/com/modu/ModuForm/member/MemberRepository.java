package com.modu.ModuForm.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
