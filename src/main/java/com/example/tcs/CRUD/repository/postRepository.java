package com.example.tcs.CRUD.repository;

import com.example.tcs.CRUD.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Posts,Long> {
}
