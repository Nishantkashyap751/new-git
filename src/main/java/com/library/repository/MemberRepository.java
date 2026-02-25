package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
