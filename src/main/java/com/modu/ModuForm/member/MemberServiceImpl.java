package com.modu.ModuForm.member;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;
    //Ctrl + Shift + Enter 자동 완성

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
